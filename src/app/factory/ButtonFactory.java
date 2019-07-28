package app.factory;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;

import app.view.custom_component.MyColor;
import app.view.custom_component.MyImageButton;

public class ButtonFactory
{
	private final static int DEFAULT_WIDTH = 150;
	private final static int DEFAULT_HEIGHT = 25;
	public final static int PRIMARY_STYLE = 12;
	public final static int INVERTED_PRIMARY_STYLE = 21;
	public final static int ACCENT_STYLE = 13;
	public final static int INVERTED_ACCENT_STYLE = 31;

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

	private void setPrimaryDefaultStyle(JButton button)
	{
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(MyColor.getPrimaryBackground());
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	private void setInvertedPrimaryStyle(JButton button)
	{
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.WHITE);
		button.setForeground(MyColor.getPrimaryBackground());
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	private void setAccentDefaultStyle(JButton button)
	{
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		button.setBackground(MyColor.getAccentBackground());
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.addFocusListener(new FocusListener()
		{
			@Override
			public void focusLost(FocusEvent arg0)
			{
				button.setBackground(MyColor.getAccentBackground());
			}
			
			@Override
			public void focusGained(FocusEvent arg0)
			{
				button.setBackground(MyColor.getAccentDarkerBackground());
			}
		});
		
//		button.addMouseListener(new MouseAdapter()
//		{
//			@Override
//			public void mousePressed(MouseEvent e)
//			{
//				button.setBackground(MyColor.getAccentDarkerBackground());
//			}
//
//			@Override
//			public void mouseReleased(MouseEvent e)
//			{
//				button.setBackground(MyColor.getAccentBackground());
//			}
//		});
		button.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	private void setInvertedAccentStyle(JButton button)
	{
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(MyColor.getAccentBackground());
		button.setBackground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	private void setStyle(JButton button, int style)
	{
		switch (style)
		{
			case PRIMARY_STYLE:
				setPrimaryDefaultStyle(button);
				break;
			case INVERTED_PRIMARY_STYLE:
				setInvertedPrimaryStyle(button);
				break;
			case ACCENT_STYLE:
				setAccentDefaultStyle(button);
				break;
			case INVERTED_ACCENT_STYLE:
				setInvertedAccentStyle(button);
				break;
		}
	}

	public JButton create(String text)
	{
		JButton btn = new JButton(text);
		setPrimaryDefaultStyle(btn);
		return btn;
	}

	public JButton create(String text, int style)
	{
		JButton btn = create(text);
		setStyle(btn, style);
		return btn;
	}

	public MyImageButton create(String text, int position, Image image)
	{
		MyImageButton btn = new MyImageButton(text, position, image);
		setPrimaryDefaultStyle(btn);
		return btn;
	}

	public MyImageButton create(String text, int position, Image image, int style)
	{
		MyImageButton btn = create(text, position, image);
		setStyle(btn, style);
		return btn;
	}
}
