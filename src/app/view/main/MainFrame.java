package app.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.view.custom_component.MyFrame;
import app.view.custom_component.MyImageButton;
import main.Main;
import util.FilePathHelper;

public class MainFrame extends MyFrame implements ActionListener, IMainFrame
{
	private JLabel lblTitle;
	private MyImageButton btnHome;
	private MyImageButton btnManageUser;
	private MyImageButton btnManageProduct;
	private MyImageButton btnPOS;
	private MyImageButton btnReport;
	private MyImageButton btnLogout;
	private SidePanel sidePanel;
	private ContentPanel contentPanel;

	public MainFrame()
	{
		setResizable(false);
		setSize(825, 600);
		setLocationRelativeTo(null);
		initializeComponent();
	}

	@Override
	public void initializeComponent()
	{
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.WHITE);

		sidePanel = getSidePanel();
		sidePanel.setBackground(Color.WHITE);
		panel.add(sidePanel, BorderLayout.WEST);

		contentPanel = getContentPanel();
		contentPanel.setBackground(Color.WHITE);
		panel.add(contentPanel, BorderLayout.CENTER);

		this.add(panel, BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		CardLayout layout = this.getContentPanel().getCardLayout();
		
		if (e.getSource() == btnHome)
		{
			layout.show(getContentPanel(), ContentPanel.HOME_PANEL);
		}
		else if (e.getSource() == btnManageUser)
		{
			layout.show(getContentPanel(), ContentPanel.MANAGE_USER_PANEL);
		}
		else if (e.getSource() == btnManageProduct)
		{
			layout.show(getContentPanel(), ContentPanel.MANAGE_PRODUCT_PANEL);
		}
		else if (e.getSource() == btnPOS)
		{
			
		}
		else if (e.getSource() == btnReport)
		{
			
		}
		else if (e.getSource() == btnLogout)
		{
			Main.currentUser = null;
			// TODO: Close Frame and Trigger Reshow Login Frame
		}
	}

	@Override
	public JLabel getTitleLabel()
	{
		if (lblTitle == null)
		{
			lblTitle = LabelFactory.getInstance().create("");
			lblTitle.setIcon(new ImageIcon(FilePathHelper.getAssetsPath() + "/logo.png"));
			lblTitle.setHorizontalAlignment(JButton.CENTER);
		}

		return lblTitle;
	}

	@Override
	public MyImageButton getHomeButton()
	{
		if (btnHome == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/home-icon.png"));

				btnHome = (MyImageButton) ButtonFactory.getInstance().create("Home", MyImageButton.LEFT, image);

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
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/user-icon.png"));

				btnManageUser = (MyImageButton) ButtonFactory.getInstance().create("Manage User", MyImageButton.LEFT,
						image);

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
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/home-icon.png"));

				btnManageProduct = (MyImageButton) ButtonFactory.getInstance().create("Manage Product",
						MyImageButton.LEFT, image);

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
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/pos-icon.png"));

				btnPOS = (MyImageButton) ButtonFactory.getInstance().create("POS", MyImageButton.LEFT, image);

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
	public MyImageButton getReportButton()
	{
		if (btnReport == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/report-icon.png"));

				btnReport = (MyImageButton) ButtonFactory.getInstance().create("Transaction Report", MyImageButton.LEFT,
						image);

				btnReport.setText(setLeftButtonStyle(btnReport.getText()));
				
				btnReport.addActionListener(this);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return btnReport;
	}

	@Override
	public MyImageButton getLogoutButton()
	{
		if (btnLogout == null)
		{
			try
			{
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath() + "/logout-icon.png"));

				btnLogout = (MyImageButton) ButtonFactory.getInstance().create("Logout", MyImageButton.LEFT, image);

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

	@Override
	public SidePanel getSidePanel()
	{
		if (sidePanel == null)
			sidePanel = new SidePanel(this);

		return sidePanel;
	}

	@Override
	public ContentPanel getContentPanel()
	{
		if (contentPanel == null)
			contentPanel = new ContentPanel(this);

		return contentPanel;
	}

	private String setLeftButtonStyle(String text)
	{
		return String.format("<html>" + "<p style='width: 150px; padding-left: 10px'>" + "%s" + "</p>" + "</html>",
				text);
	}
}
