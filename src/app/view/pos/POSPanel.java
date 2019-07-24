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

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.pos.datapanel.DataPanel;
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
			int confirmationResult = JOptionPane.showConfirmDialog(null,
					"Are you sure want to pending this transaction ?", "Confirmation", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);

			if (confirmationResult == JOptionPane.YES_OPTION)
			{
				ArrayList<DefaultTableModel> pendingTransactions = Main.subject.getPendingTransactions();
				pendingTransactions.add(Main.subject.getData());
				Main.subject.setData(null);
				
				JOptionPane.showMessageDialog(null, 
						"Transaction postponed.",
						"Success",
						JOptionPane.INFORMATION_MESSAGE);
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
				btnProcess = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image, ButtonFactory.INVERTED_PRIMARY_STYLE);
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
				btnPending = ButtonFactory.getInstance().create("", MyImageButton.LEFT, image, ButtonFactory.INVERTED_PRIMARY_STYLE);
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