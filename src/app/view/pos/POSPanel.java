package app.view.pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import app.controller.TransactionController;
import app.dto.CartDTO;
import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.pos.datapanel.DataPanel;
import main.Main;
import util.FileHelper;
import util.MessageBox;
import util.Speaker;

public class POSPanel extends JPanel implements ActionListener, IPOSPanel
{
	private MyImageButton btnProcess, btnPending;
	private DataPanel dataPanel;

	public POSPanel()
	{
		POSPanel.this.setBackground(Color.WHITE);

		POSPanel.this.setLayout(new BorderLayout());

		JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		actionButtonPanel.setOpaque(false);
		actionButtonPanel.add(getPendingButton());
		actionButtonPanel.add(getProcessButton());
		POSPanel.this.add(actionButtonPanel, BorderLayout.NORTH);
		POSPanel.this.add(getDataPanel(), BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String message = "";
		
		if (e.getSource() == getProcessButton())
		{
			if (Main.subject.getData().getRowCount() < 1)
			{
				message = "You don\'t add any item yet.";
				Speaker.speak(message);
				MessageBox.error(message);
				return;
			}

			ArrayList<CartDTO> carts = new ArrayList<>();

			DefaultTableModel tableData = Main.subject.getData();
			
			for (int i = 0; i < tableData.getRowCount(); i++)
			{
				CartDTO dto = new CartDTO();
				dto.setId(Integer.valueOf(tableData.getValueAt(i, CartDTO.ID_INDEX).toString()));
				dto.setName(tableData.getValueAt(i, CartDTO.NAME_INDEX).toString());
				dto.setQuantity(Integer.valueOf(tableData.getValueAt(i, CartDTO.QUANTITY_INDEX).toString()));
				dto.setPrice(Double.valueOf(tableData.getValueAt(i, CartDTO.PRICE_INDEX).toString()));
				carts.add(dto);
			}

			TransactionController.add(carts);

			message = "Transaction completed.";
			Speaker.speak(message);
			MessageBox.success(message);

			Main.subject.setData(null);
		}
		else if (e.getSource() == getPendingButton())
		{
			if (Main.subject.getData().getRowCount() < 1)
			{
				message = "You don\'t add any item yet.";
				Speaker.speak(message);
				MessageBox.success(message);
				return;
			}
			
			message = "Are you sure want to pending this transaction ?"; 
			Speaker.speak(message);

			int confirmationResult = MessageBox.confirmation(message);

			if (confirmationResult == JOptionPane.YES_OPTION)
			{
				Main.subject.pendingTransaction(Main.subject.getData());
				Main.subject.setData(null);

				message = "Transaction postponed.";
				MessageBox.success(message);
			}
		}
	}

	@Override
	public MyImageButton getProcessButton()
	{
		if (btnProcess == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/done-icon.png"));
				btnProcess = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnProcess.setPreferredSize(new Dimension(50, 50));
				btnProcess.addActionListener(this);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		return btnProcess;
	}

	@Override
	public MyImageButton getPendingButton()
	{
		if (btnPending == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/pending-icon.png"));
				btnPending = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnPending.setPreferredSize(new Dimension(50, 50));
				btnPending.addActionListener(this);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}

		return btnPending;
	}

	@Override
	public DataPanel getDataPanel()
	{
		if (dataPanel == null)
		{
			dataPanel = new DataPanel(Main.subject);
		}

		return dataPanel;
	}
}
