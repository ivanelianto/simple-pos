package app.factory;

import java.awt.Color;

import javax.swing.JLabel;

import app.view.MyColor;

public class LabelFactory
{
	private static LabelFactory instance;

	private LabelFactory()
	{
	}

	public static LabelFactory getInstance()
	{
		if (instance == null)
			instance = new LabelFactory();

		return instance;
	}
	
	private void setDefaultStyle(JLabel label)
	{
		label.setBackground(Color.WHITE);
		label.setForeground(Color.decode(MyColor.ACCENT_BACKGROUND));
		label.setOpaque(true);
	}
	
	public JLabel create(String text)
	{
		JLabel lbl = new JLabel(text);
		setDefaultStyle(lbl);
		return lbl;
	}
}
