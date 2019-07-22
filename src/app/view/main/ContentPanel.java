package app.view.main;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	private IMainFrame components;
	private CardLayout cardLayout;
	
	public ContentPanel(IMainFrame components)
	{
		this.components = components;
		
		cardLayout = new CardLayout();
		this.setLayout(cardLayout);
		this.add(new HomePanel());
	}
	
	public CardLayout getCardLayout()
	{
		return this.cardLayout;
	}

}
