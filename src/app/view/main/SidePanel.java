package app.view.main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.view.custom_component.MyImageButton;
import main.Main;
import util.FileHelper;

public class SidePanel extends JPanel implements ActionListener, ISidePanel
{
	private MyImageButton btnHome;
	private MyImageButton btnManageUser;
	private MyImageButton btnManageProduct;
	private MyImageButton btnPOS;
	private MyImageButton btnGame;
	private MyImageButton btnLogout;
	
	private IMainFrame components;

	public SidePanel(IMainFrame components)
	{
		this.components = components;
		
		this.setBackground(Color.WHITE);

		GridBagLayout gbLayout = new GridBagLayout();
		this.setLayout(gbLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.BOTH;
		this.setPreferredSize(new Dimension(300, this.getHeight()));

		c.weighty = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(components.getTitleLabel(), c);

		c.weighty = 1;
		c.gridy = 1;
		this.add(getHomeButton(), c);

		c.gridy = 2;
		this.add(getManageUserButton(), c);

		c.gridy = 3;
		this.add(getManageProductButton(), c);

		c.gridy = 4;
		this.add(getPOSButton(), c);

		c.gridy = 5;
		this.add(getGameButton(), c);
		
		c.gridy = 6;
		this.add(getLogoutButton(), c);

		c.gridy = 7;
		c.weighty = 3;
		JPanel blankPanel = new JPanel();
		blankPanel.setOpaque(false);
		this.add(blankPanel, c);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		CardLayout layout = components.getContentPanel().getCardLayout();

		if (e.getSource() == btnHome)
		{
			layout.show(components.getContentPanel(), ContentPanel.HOME_PANEL);
		}
		else if (e.getSource() == btnManageUser)
		{
			layout.show(components.getContentPanel(), ContentPanel.MANAGE_USER_PANEL);
		}
		else if (e.getSource() == btnManageProduct)
		{
			layout.show(components.getContentPanel(), ContentPanel.MANAGE_PRODUCT_PANEL);
		}
		else if (e.getSource() == btnPOS)
		{
			layout.show(components.getContentPanel(), ContentPanel.POS_PANEL);
		}
		else if (e.getSource() == btnGame)
		{
			layout.show(components.getContentPanel(), ContentPanel.GAME_PANEL);
		}
		else if (e.getSource() == btnLogout)
		{
			Main.currentUser = null;
			components.close();
		}
	}

	@Override
	public MyImageButton getHomeButton()
	{
		if (btnHome == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/home-icon.png"));

				btnHome = (MyImageButton) ButtonFactory.getInstance().create("Home", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnHome.setText(setLeftButtonStyle(btnHome.getText()));
				btnHome.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnHome;
	}

	@Override
	public MyImageButton getManageUserButton()
	{
		if (btnManageUser == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/user-icon.png"));

				btnManageUser = (MyImageButton) ButtonFactory.getInstance().create("Manage User", MyImageButton.LEFT,
						image, ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnManageUser.setText(setLeftButtonStyle(btnManageUser.getText()));
				btnManageUser.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnManageUser;
	}

	@Override
	public MyImageButton getManageProductButton()
	{
		if (btnManageProduct == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/home-icon.png"));

				btnManageProduct = (MyImageButton) ButtonFactory.getInstance().create("Manage Product",
						MyImageButton.LEFT, image, ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnManageProduct.setText(setLeftButtonStyle(btnManageProduct.getText()));
				btnManageProduct.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnManageProduct;
	}

	@Override
	public MyImageButton getPOSButton()
	{
		if (btnPOS == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/pos-icon.png"));

				btnPOS = (MyImageButton) ButtonFactory.getInstance().create("POS", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnPOS.setText(setLeftButtonStyle(btnPOS.getText()));
				btnPOS.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnPOS;
	}
	
	@Override
	public MyImageButton getGameButton()
	{
		if (btnGame == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/game-icon.png"));

				btnGame = (MyImageButton) ButtonFactory.getInstance().create("I\'m Boring", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnGame.setImageSize(32, 32);
				btnGame.setText(setLeftButtonStyle(btnGame.getText()));
				btnGame.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return btnGame;
	}

	@Override
	public MyImageButton getLogoutButton()
	{
		if (btnLogout == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FileHelper.getAssetsPath() + "/logout-icon.png"));

				btnLogout = (MyImageButton) ButtonFactory.getInstance().create("Logout", MyImageButton.LEFT, image,
						ButtonFactory.INVERTED_PRIMARY_STYLE);
				btnLogout.setText(setLeftButtonStyle(btnLogout.getText()));
				btnLogout.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnLogout;
	}

	private String setLeftButtonStyle(String text)
	{
		return String.format("<html>" + "<p style='width: 150px; padding-left: 10px'>" + "%s" + "</p>" + "</html>",
				text);
	}
}
