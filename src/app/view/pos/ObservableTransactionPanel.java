package app.view.pos;

import javax.swing.JPanel;

public abstract class ObservableTransactionPanel extends JPanel
{
	public Cart subscribedSubject;
	
	public ObservableTransactionPanel(Cart subscribedSubject)
	{
		this.subscribedSubject = subscribedSubject;
	}

	public abstract void update();
}
