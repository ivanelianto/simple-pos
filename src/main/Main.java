package main;

import javax.swing.SwingUtilities;

import app.model.User;
import app.view.login.LoginFrame;
import app.view.pos.Cart;

public class Main
{
	public static Cart subject = new Cart();

	public static User currentUser = null;

	public Main()
	{
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
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
