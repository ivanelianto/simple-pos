package app.view.main;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.manage.user.ManageUserPanel;

public class ContentPanel extends JPanel {
	public final static String HOME_PANEL = "Home Panel";
	public final static String MANAGE_USER_PANEL = "Manage User Panel";
	public final static String MANAGE_PRODUCT_PANEL = "Manage Product Panel";
	public final static String POS_PANEL = "POS Panel";
	public final static String REPORT_PANEL = "Report Panel";
	
	private IMainFrame components;
	private CardLayout cardLayout;
	
	public ContentPanel(IMainFrame components)
	{
		this.components = components;
		
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(new HomePanel(), HOME_PANEL);
		this.add(new ManageUserPanel(), MANAGE_USER_PANEL);
	}
	
	public CardLayout getCardLayout()
	{
		return this.cardLayout;
	}

}
