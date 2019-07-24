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
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.pos.datapanel.DataPanel;
import app.view.pos.datapanel.TransactionUserScreenFrame;
import main.Main;
import util.FileHelper;

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
		if (e.getSource() == getProcessButton())
		{
			// TODO: Insert Into DB
		}
		else if (e.getSource() == getPendingButton())
		{
			ArrayList<DefaultTableModel> pendingTransactions = Main.subject.getPendingTransactions();
			pendingTransactions.add(Main.subject.getData());
			Main.subject.setData(null);
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
				btnProcess = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image);
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
				btnPending = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image);
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

			TransactionUserScreenFrame frame = new TransactionUserScreenFrame(Main.subject);
			frame.setVisible(true);
		}

		return dataPanel;
	}

}
