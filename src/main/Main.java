package main;

import javax.swing.SwingUtilities;

import app.model.User;
import app.view.dialog.user.UserDialog;
import app.view.main.MainFrame;

public class Main
{
	public static User currentUser = null;
	
	public Main()
	{
//		LoginFrame loginFrame = new LoginFrame();
//		loginFrame.setVisible(true);
		
		new MainFrame().setVisible(true);
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
