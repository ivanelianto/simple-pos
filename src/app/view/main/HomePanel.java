package app.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import app.view.dialog.transaction.PendingTransactionDialog;
import app.view.pos.datapanel.TransactionUserScreenFrame;
import main.Main;
import util.FileHelper;

public class HomePanel extends JPanel implements ActionListener, IHomePanel
{
	private MyImageButton btnCastToCustomerViewScreen, btnRestorePendingTransaction;

	public HomePanel()
	{
		this.setBackground(Color.WHITE);

		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		c.gridx = 0;
		c.insets = new Insets(0, 0, 0, 5);
		this.add(getCastToCustomerViewScreenButton(), c);

		c.gridx = 1;
		c.insets = new Insets(0, 5, 0, 0);
		this.add(getRestorePendingTransactionButton(), c);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnCastToCustomerViewScreen)
		{
			TransactionUserScreenFrame frame = new TransactionUserScreenFrame(Main.subject);
			frame.setVisible(true);
		}
		else if (e.getSource() == btnRestorePendingTransaction)
		{
			try (PendingTransactionDialog dialog = new PendingTransactionDialog())
			{
				dialog.setVisible(true);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	@Override
	public MyImageButton getCastToCustomerViewScreenButton()
	{
		if (btnCastToCustomerViewScreen == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/add-icon-white.png"));

				btnCastToCustomerViewScreen = ButtonFactory.getInstance().create("Cast To Customer View Screen",
						MyImageButton.TOP, image, ButtonFactory.ACCENT_STYLE);
				btnCastToCustomerViewScreen.setPreferredSize(new Dimension(200, 150));
				btnCastToCustomerViewScreen.setText(setButtonStyle(btnCastToCustomerViewScreen.getText()));
				btnCastToCustomerViewScreen.addActionListener(this);
			}
			catch (InvalidParameterException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return btnCastToCustomerViewScreen;
	}

	@Override
	public MyImageButton getRestorePendingTransactionButton()
	{
		if (btnRestorePendingTransaction == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/restore-icon-white.png"));

				btnRestorePendingTransaction = ButtonFactory.getInstance().create("Restore Pending Transaction",
						MyImageButton.TOP, image, ButtonFactory.ACCENT_STYLE);
				btnRestorePendingTransaction.setPreferredSize(new Dimension(200, 150));
				btnRestorePendingTransaction.setText(setButtonStyle(btnRestorePendingTransaction.getText()));
				btnRestorePendingTransaction.addActionListener(this);

			}
			catch (InvalidParameterException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		return btnRestorePendingTransaction;
	}

	private String setButtonStyle(String text)
	{
		return String.format("<html>" + "<center style='width: 100px;'>" + "%s" + "</center>" + "</html>", text);
	}
}
