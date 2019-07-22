package app.factory;

import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import app.view.custom_component.MyColor;

public class TextFieldFactory
{
	private final static int DEFAULT_WIDTH = 150;
	private final static int DEFAULT_HEIGHT = 25;
	
	private static TextFieldFactory instance;

	private TextFieldFactory()
	{
	}

	public static TextFieldFactory getInstance()
	{
		if (instance == null)
			instance = new TextFieldFactory();

		return instance;
	}

	private void setDefaultStyle(JTextField textField)
	{
		textField.setBorder(new MatteBorder(0, 0, 2, 0, MyColor.getAccentBackground()));
		textField.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	public JTextField create()
	{
		JTextField textField = new JTextField();
		setDefaultStyle(textField);
		return textField;
	}

	public JTextField create(boolean isPasswordField)
	{
		JTextField textField = null;

		if (isPasswordField)
			textField = new JPasswordField();
		else
			textField = new JTextField();

		setDefaultStyle(textField);
		return textField;
	}

	public JTextField create(int width, int height)
	{
		JTextField textField = create();
		setDefaultStyle(textField);
		textField.setPreferredSize(new Dimension(width, height));
		return textField;
	}

	public JTextField create(boolean isPasswordField, int width, int height)
	{
		JTextField textField = null;

		if (isPasswordField)
			textField = new JPasswordField();
		else
			textField = new JTextField();

		setDefaultStyle(textField);
		textField.setPreferredSize(new Dimension(width, height));
		return textField;
	}
}
