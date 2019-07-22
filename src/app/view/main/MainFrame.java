package app.view.main;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import app.controller.MainFrameController;
import app.factory.ButtonFactory;
import app.factory.LabelFactory;
import app.view.custom_component.MyColor;
import app.view.custom_component.MyFrame;
import app.view.custom_component.MyImageButton;
import util.FilePathHelper;

public class MainFrame extends MyFrame implements IMainFrame, IMainFramePanel
{
	private JLabel lblTitle;
	private MyImageButton btnHome;
	private MyImageButton btnManageUser;
	private MyImageButton btnManageProduct;
	private MyImageButton btnPOS;
	private MyImageButton btnReport;
	private MyImageButton btnLogout;
	private MyImageButton btnCastToCustomerViewScreen;
	private MyImageButton btnRestorePendingTransaction;
	private JPanel sidePanel;
	private JPanel contentPanel;

	public MainFrame()
	{
		setSize(800, 600);
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/home-icon.png"));
				
				btnHome = (MyImageButton) ButtonFactory.getInstance().create("Home",
						MyImageButton.LEFT,
						image);
				
				btnHome.setText(setLeftButtonStyle(btnHome.getText()));
				
				btnHome.addActionListener(MainFrameController.getInstance(this).onHomeButtonClick());
				
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/user-icon.png"));
				
				btnManageUser = (MyImageButton) ButtonFactory.getInstance().create("Manage User",
						MyImageButton.LEFT,
						image);

				btnManageUser.setText(setLeftButtonStyle(btnManageUser.getText()));
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/home-icon.png"));
				
				btnManageProduct = (MyImageButton) ButtonFactory.getInstance().create("Manage Product",
						MyImageButton.LEFT,
						image);

				btnManageProduct.setText(setLeftButtonStyle(btnManageProduct.getText()));
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/pos-icon.png"));
				
				btnPOS = (MyImageButton) ButtonFactory.getInstance().create("POS",
						MyImageButton.LEFT,
						image);

				btnPOS.setText(setLeftButtonStyle(btnPOS.getText()));
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/report-icon.png"));
				
				btnReport = (MyImageButton) ButtonFactory.getInstance().create("Transaction Report",
						MyImageButton.LEFT,
						image);

				btnReport.setText(setLeftButtonStyle(btnReport.getText()));
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/logout-icon.png"));
				
				btnLogout = (MyImageButton) ButtonFactory.getInstance().create("Logout",
						MyImageButton.LEFT,
						image);

				btnLogout.setText(setLeftButtonStyle(btnLogout.getText()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return btnLogout;
	}

	@Override
	public MyImageButton getCastToCustomerViewScreenButton()
	{
		if (btnCastToCustomerViewScreen == null)
		{
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/add-icon.png"));
				btnCastToCustomerViewScreen = (MyImageButton) ButtonFactory.getInstance().create("Cast To Customer View Screen",
						MyImageButton.TOP,
						image);
			} catch (Exception e) {
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
			try {
				Image image = ImageIO.read(new File(FilePathHelper.getAssetsPath()  + "/restore-icon.png"));
				btnRestorePendingTransaction = (MyImageButton) ButtonFactory.getInstance().create("Home",
						MyImageButton.TOP,
						image);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return btnRestorePendingTransaction;
	}

	@Override
	public JPanel getSidePanel() {
		if (sidePanel == null)
			sidePanel = new SidePanel(this);
		
		return sidePanel;
	}

	@Override
	public JPanel getContentPanel() {
		if (contentPanel == null)
			contentPanel = new ContentPanel(this);
		
		return contentPanel;
	}

	private String setLeftButtonStyle(String text)
	{
		return String.format("<html>"
				+ "<p style='width: 150px; padding-left: 10px'>"
				+ "%s"
				+ "</p>"
				+ "</html>", text);
	}
}
