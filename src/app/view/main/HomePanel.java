package app.view.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import util.FilePathHelper;

public class HomePanel extends JPanel implements IHomePanel {
	private MyImageButton btnCastToCustomerViewScreen, btnRestorePendingTransaction;

	public HomePanel() {
		this.setBackground(Color.WHITE);
		
		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		
		
		
//		FlowLayout layout = new FlowLayout();
//		layout.setAlignment(FlowLayout.CENTER);
//		JPanel panel = new JPanel(layout);
		
		c.gridx = 0;
		c.insets = new Insets(0, 0, 0, 5);
		this.add(getCastToCustomerViewScreenButton(), c);
		
		c.gridx = 1;
		c.insets = new Insets(0, 5, 0, 0);
		this.add(getRestorePendingTransactionButton(), c);
//		
//		this.add(panel, c);
	}

	@Override
	public MyImageButton getCastToCustomerViewScreenButton() {
		if (btnCastToCustomerViewScreen == null)
		{
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/add-icon.png"));
				
				btnCastToCustomerViewScreen = ButtonFactory.getInstance().create("Cast To Customer View Screen", 
						MyImageButton.TOP, 
						image);
				
				btnCastToCustomerViewScreen.setPreferredSize(new Dimension(200, 150));
				
				btnCastToCustomerViewScreen.setText(
						setButtonStyle(btnCastToCustomerViewScreen.getText())
					);
				
			} catch (InvalidParameterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return btnCastToCustomerViewScreen;
	}

	@Override
	public MyImageButton getRestorePendingTransactionButton() {
		if (btnRestorePendingTransaction == null)
		{
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/restore-icon.png"));
				
				btnRestorePendingTransaction = ButtonFactory.getInstance().create("Restore Pending Transaction", 
						MyImageButton.TOP, 
						image);
				
				btnRestorePendingTransaction.setPreferredSize(new Dimension(200, 150));
				
				btnRestorePendingTransaction.setText(
						setButtonStyle(btnRestorePendingTransaction.getText())
					);
				
			} catch (InvalidParameterException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return btnRestorePendingTransaction;
	}

	private String setButtonStyle(String text)
	{
		return String.format("<html>"
				+ "<center style='width: 100px;'>"
				+ "%s"
				+ "</center>"
				+ "</html>", text);
	}
}
