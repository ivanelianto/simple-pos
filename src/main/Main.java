package main;

import app.view.LoginFrame;

public class Main
{
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
