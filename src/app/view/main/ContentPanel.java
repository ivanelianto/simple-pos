package app.view.main;

import java.awt.CardLayout;

import javax.swing.JPanel;

import app.view.game.GamePanel;
import app.view.manage.product.ManageProductPanel;
import app.view.manage.user.ManageUserPanel;
import app.view.pos.POSPanel;

public class ContentPanel extends JPanel
{
	public final static String HOME_PANEL = "Home Panel";
	public final static String MANAGE_USER_PANEL = "Manage User Panel";
	public final static String MANAGE_PRODUCT_PANEL = "Manage Product Panel";
	public final static String POS_PANEL = "POS Panel";
	public final static String GAME_PANEL = "Game Panel";

	private CardLayout cardLayout;

	public ContentPanel()
	{
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(new HomePanel(), HOME_PANEL);
		this.add(new ManageUserPanel(), MANAGE_USER_PANEL);
		this.add(new ManageProductPanel(), MANAGE_PRODUCT_PANEL);
		this.add(new POSPanel(), POS_PANEL);
		this.add(new GamePanel(), GAME_PANEL);
	}

	public CardLayout getCardLayout()
	{
		return this.cardLayout;
	}

}
