package app.view.pos.datapanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import app.controller.ProductController;
import app.dto.CartDTO;
import app.model.Product;
import app.view.pos.Cart;
import app.view.pos.IPOSPanel;
import util.Formatter;

public class DataPanel extends ObservableTransactionPanel implements IDataPanel
{
	private static final int ID_INDEX = 0;
	private static final int NAME_INDEX = 1;
	private static final int QUANTITY_INDEX = 2;
	private static final int PRICE_INDEX = 3;
	private static final int SUBTOTAL_INDEX = 4;
	private JTable table;
	private IPOSPanel POSPanel;

	public DataPanel(Cart transaction, IPOSPanel panel)
	{
		super(transaction);
		
		this.POSPanel = panel;

		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(getMainTable());
		
		scrollPane.getViewport().setBackground(Color.WHITE);
		this.add(scrollPane, BorderLayout.CENTER);

		initializeComponent();
	}

	private void initializeComponent()
	{
		TransferHandler handler = new TransferHandler()
		{
			@Override
			public boolean canImport(TransferSupport support)
			{
				try
				{
					List<File> files = (List<File>) support.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : files)
					{
						if (!file.getName().endsWith(".iv"))
							return false;
					}
				}
				catch (Exception e)
				{
				}

				return true;
			}

			@Override
			public boolean importData(JComponent comp, Transferable t)
			{
				try
				{
					List<File> files = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

					for (File file : files)
					{
						CartDTO dto = parseToCartDTO(file);

						boolean isExist = false;

						DefaultTableModel data = subscribedSubject.getData();
						for (int i = 0; i < data.getRowCount(); i++)
						{
							int idInRow = Integer.parseInt(data.getValueAt(i, ID_INDEX).toString());
							int qtyInRow = Integer.parseInt(data.getValueAt(i, QUANTITY_INDEX).toString());

							if (dto.getId() == idInRow)
							{
								Product product = ProductController.getProductByID(idInRow);

								if (!isValidQuantity(qtyInRow, product))
								{
									JOptionPane.showMessageDialog(null, "Insufficient stock.", "Stop",
											JOptionPane.ERROR_MESSAGE);

									return false;
								}

								data.setValueAt(++qtyInRow, i, QUANTITY_INDEX);

								String subtotalWithCurrencies = Formatter
										.formatToCurrency(getSubtotal(qtyInRow, dto.getPrice()));
								
								data.setValueAt(subtotalWithCurrencies, i, SUBTOTAL_INDEX);

								isExist = true;
								break;
							}
						}

						if (!isExist)
						{
							Vector<String> fields = new Vector<>();
							fields.add(dto.getId() + "");
							fields.add(dto.getName());
							fields.add(dto.getQuantity() + "");
							fields.add(dto.getPrice() + "");
							
							double subtotal = dto.getQuantity() * dto.getPrice();
							fields.add(Formatter.formatToCurrency(subtotal));
							
							data.addRow(fields);
						}
						
						setTableView(data);
					}
				}
				catch (UnsupportedFlavorException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}

				return true;
			}

			private boolean isValidQuantity(int quantity, Product product)
			{
				return (quantity + 1) <= product.getStock();
			}
		};

		this.setTransferHandler(handler);
	}

	@Override
	public void update()
	{
		DefaultTableModel data = subscribedSubject.getData();
		setTableView(data);
	}

	@Override
	public JTable getMainTable()
	{
		if (table == null)
		{
			table = new JTable();
			setTableView(subscribedSubject.getData());
		}

		return table;
	}

	private double getSubtotal(int quantity, double price)
	{
		return quantity * price;
	}

	private void setTableView(DefaultTableModel data)
	{
		this.table.setModel(data);

		if (data.getRowCount() == 0)
			table.removeColumn(table.getColumnModel().getColumn(0));
	}

	private CartDTO parseToCartDTO(File file)
	{
		List<String> lines;

		try
		{
			lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}

		String[] encodedProductFields = lines.get(0).split("#");
		return new CartDTO(Integer.valueOf(encodedProductFields[ID_INDEX]), encodedProductFields[NAME_INDEX],
				Integer.valueOf(encodedProductFields[QUANTITY_INDEX]),
				Double.valueOf(encodedProductFields[PRICE_INDEX]));
	}
}
