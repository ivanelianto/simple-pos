package app.view.pos.datapanel;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import app.view.pos.ObservableTransactionPanel;
import app.view.pos.Cart;
import util.Formatter;

public class DataPanel extends ObservableTransactionPanel implements IDataPanel
{
	private static final int ID_INDEX = 0;
	private static final int QUANTITY_INDEX = 2;
	private static final int PRICE_INDEX = 3;
	private static final int SUBTOTAL_INDEX = 4;
	private JTable table;
	
	public DataPanel(Cart transaction)
	{
		super(transaction);
		
		this.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(getMainTable());
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
						List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
						String[] encodedProductFields = lines.get(0).split("#");
						Vector<String> productFields = new Vector<String>();
						productFields.addAll(Arrays.asList(encodedProductFields));
						
						int quantity = Integer.valueOf(encodedProductFields[QUANTITY_INDEX]);
						double price = Double.valueOf(encodedProductFields[PRICE_INDEX]); 
						productFields.add(Formatter.formatToCurrency(getSubtotal(quantity, price)));

						boolean isExist = false;

						DefaultTableModel data = subscribedSubject.getData();
						
						for (int i = 0; i < data.getRowCount(); i++)
						{
							String id = String.valueOf(data.getValueAt(i, ID_INDEX));
							quantity = Integer.valueOf(data.getValueAt(i, QUANTITY_INDEX).toString());

							if (productFields.get(ID_INDEX).equals(id))
							{
								data.setValueAt(++quantity, i, QUANTITY_INDEX);

								String subtotalWithCurrencies = Formatter.formatToCurrency(getSubtotal(quantity, price));
								data.setValueAt(subtotalWithCurrencies, i, SUBTOTAL_INDEX);
								
								isExist = true;
								break;
							}
						}

						if (!isExist)
							data.addRow(productFields);
						
						subscribedSubject.setData(data);
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
		};

		this.setTransferHandler(handler);
	}


	@Override
	public void update()
	{
		DefaultTableModel data = subscribedSubject.getData();
		this.table.setModel(data);
	}
	

	@Override
	public JTable getMainTable()
	{
		if (table == null)
		{
			table = new JTable(subscribedSubject.getData());
			table.removeColumn(table.getColumnModel().getColumn(0));
		}

		return table;
	}

	private double getSubtotal(int quantity, double price)
	{
		return quantity * price;
	}
}
