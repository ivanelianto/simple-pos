package app.factory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;

import app.view.MyColor;

public class ButtonFactory
{
	private final static int DEFAULT_WIDTH = 150;
	private final static int DEFAULT_HEIGHT = 25;

	private static ButtonFactory instance;

	private ButtonFactory()
	{
	}

	public static ButtonFactory getInstance()
	{
		if (instance == null)
			instance = new ButtonFactory();

		return instance;
	}

	private void setDefaultStyle(JButton button)
	{
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.decode(MyColor.MAIN_BACKGROUND));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	public JButton create(String text)
	{
		JButton btn = new JButton(text);
		setDefaultStyle(btn);
		return btn;
	}
}
