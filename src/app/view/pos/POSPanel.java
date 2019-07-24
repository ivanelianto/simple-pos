package app.view.pos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.pos.datapanel.DataPanel;
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

		}
		else if (e.getSource() == getPendingButton())
		{

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
				btnProcess.addActionListener(this);
				btnProcess.setPreferredSize(new Dimension(50, 50));
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
				btnPending.addActionListener(this);
				btnPending.setPreferredSize(new Dimension(50, 50));
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
			Cart subject = new Cart();

			JFrame frame = new JFrame();
			frame.setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
			frame.setSize(500, 500);
			frame.setAlwaysOnTop(true);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.add(new DataPanel(subject), BorderLayout.CENTER);
			frame.setVisible(true);

			dataPanel = new DataPanel(subject);
		}

		return dataPanel;
	}

}
