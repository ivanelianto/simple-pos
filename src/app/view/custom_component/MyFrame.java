package app.view.custom_component;

import javax.swing.JFrame;

public abstract class MyFrame extends JFrame
{

	public MyFrame()
	{
		setTitle("SIVle POS");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public abstract void initializeComponent();

}
