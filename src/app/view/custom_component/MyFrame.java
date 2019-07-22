package app.view.custom_component;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class MyFrame extends JFrame
{

	public MyFrame()
	{
		setTitle("SIVle POS");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}

	public abstract void initializeComponent();

}
