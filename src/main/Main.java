package main;

import javax.swing.SwingUtilities;

import app.model.User;
import app.view.main.MainDialog;
import app.view.pos.Cart;

public class Main
{
	public static Cart subject = new Cart();
	
	public static User currentUser = null;
	
	public Main()
	{
//		LoginFrame loginFrame = new LoginFrame();
//		loginFrame.setVisible(true);
		
		// TODO: Pas Add Product, Create File Dengan Format {}#{}#{}#{}
		// TODO: Save Ke Folder "products"
		// TODO: Restore Pending Transaction
		// TODO: Search Transaction
		// TODO: Research Tentang Chart
		
		new MainDialog().setVisible(true);
	}

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Main();
			}
		});
	}
}
