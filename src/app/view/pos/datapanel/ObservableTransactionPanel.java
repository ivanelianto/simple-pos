package app.view.pos.datapanel;

import javax.swing.JPanel;

import app.view.pos.Cart;
import main.Main;

public abstract class ObservableTransactionPanel extends JPanel
{
	public Cart subscribedSubject;
	
	public ObservableTransactionPanel(Cart subscribedSubject)
	{
		this.subscribedSubject = subscribedSubject;
		Main.subject.add(this);
	}

	public abstract void update();
}
