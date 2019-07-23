package main;

import javax.swing.SwingUtilities;

import app.model.User;
import app.view.dialog.user.UserDialog;

public class Main
{
	public static User currentUser = null;
	
	public Main()
	{
//		LoginFrame loginFrame = new LoginFrame();
//		loginFrame.setVisible(true);
		
//		new MainFrame().setVisible(true);
		User user = new User();
		user.setId(2);
		user.setName("William");
		user.setUsername("AM18-2");
		user.setPassword("3fc9b689459d738f8c88a3a48aa9e33542016b7a4052e001aaa536fca74813cb");
		new UserDialog(user).setVisible(true);
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
