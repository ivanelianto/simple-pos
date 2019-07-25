package app.view.pos.datapanel;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import app.controller.ProductController;
import app.dto.CartDTO;
import app.model.Product;
import util.Encryptor;
import util.Formatter;

public class CartTransferHandler extends TransferHandler
{
	private DataPanel dataPanel;
	
	public CartTransferHandler(DataPanel dataPanel)
	{
		this.dataPanel = dataPanel;
	}
	
	@Override
	public boolean canImport(TransferSupport support)
	{
		try
		{
			List<?> files = (List<?>) support.getTransferable()
					.getTransferData(DataFlavor.javaFileListFlavor);
			
			for (File file : (File[]) files.toArray())
				if (!file.getName().endsWith(".iv"))
					return false;
		}
		catch (Exception e) { }

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
				
				Product product = ProductController.getProductByID(dto.getId());
				
				if (!product.isAvailableStock(1))
				{
					JOptionPane.showMessageDialog(null, "Insufficient stock.", "Stop",
							JOptionPane.ERROR_MESSAGE);

					return false;
				}

				boolean isExist = false;

				DefaultTableModel data = this.dataPanel.subscribedSubject.getData();
				for (int i = 0; i < data.getRowCount(); i++)
				{
					int idInRow = Integer.parseInt(data.getValueAt(i, CartDTO.ID_INDEX).toString());
					int qtyInRow = Integer.parseInt(data.getValueAt(i, CartDTO.QUANTITY_INDEX).toString());

					if (dto.getId() == idInRow)
					{
						product = ProductController.getProductByID(idInRow);

						if (!product.isAvailableStock(qtyInRow + 1))
						{
							JOptionPane.showMessageDialog(null, "Insufficient stock.", "Stop",
									JOptionPane.ERROR_MESSAGE);

							return false;
						}

						data.setValueAt(++qtyInRow, i, CartDTO.QUANTITY_INDEX);

						String subtotalWithCurrencies = Formatter
								.formatToCurrency(getSubtotal(qtyInRow, dto.getPrice()));

						data.setValueAt(subtotalWithCurrencies, i, CartDTO.SUBTOTAL_INDEX);

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

				this.dataPanel.setTableView(data);
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

	private double getSubtotal(int quantity, double price)
	{
		return quantity * price;
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
		
		String productRawString = Encryptor.decodeBase64(lines.get(0));

		String[] encodedProductFields = productRawString.split("#");

		return new CartDTO(Integer.valueOf(encodedProductFields[CartDTO.ID_INDEX]),
				encodedProductFields[CartDTO.NAME_INDEX], Integer.valueOf(encodedProductFields[CartDTO.QUANTITY_INDEX]),
				Double.valueOf(encodedProductFields[CartDTO.PRICE_INDEX]));
	}


}
