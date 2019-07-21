package main;

import app.model.User;
import app.view.LoginFrame;

public class Main
{
	public static User currentUser = null;
	
	public Main()
	{
		LoginFrame loginFrame = new LoginFrame();
		loginFrame.setVisible(true);
	}

	public static void main(String[] args)
	{
		new Main();
	}
}
