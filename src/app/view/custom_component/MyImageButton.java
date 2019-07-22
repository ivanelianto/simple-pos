package app.view.custom_component;

import java.awt.Image;
import java.security.InvalidParameterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyImageButton extends JButton
{
	public final static int TOP = 1;
	public final static int RIGHT = 2;
	public final static int BOTTOM = 3;
	public final static int LEFT = 4;
	public final static int DEFAULT_IMAGE_WIDTH = 50;
	public final static int DEFAULT_IMAGE_HEIGHT = 50;

	private int imagePosition = -1;

	private Image image = null;

	public MyImageButton(String text, int imagePosition, Image image) throws InvalidParameterException
	{
		if (!isValidPosition(imagePosition))
			throw new InvalidParameterException();

		this.setText(text);
		
		this.imagePosition = imagePosition;
		setImagePosition(imagePosition);
		
		this.image = image;
		setImage(image);
	}

	private boolean isValidPosition(int imagePosition)
	{
		return imagePosition == TOP 
				|| imagePosition == BOTTOM 
				|| imagePosition == RIGHT 
				|| imagePosition == LEFT;
	}

	public void setImagePosition(int position) throws InvalidParameterException
	{
		if (!isValidPosition(imagePosition))
			throw new InvalidParameterException();
		
		switch (position)
		{
			case TOP:
				setHorizontalTextPosition(JButton.CENTER);
				setVerticalTextPosition(JButton.BOTTOM);
				break;
			case BOTTOM:
				setHorizontalTextPosition(JButton.CENTER);
				setVerticalTextPosition(JButton.TOP);
				break;
			case RIGHT:
				setHorizontalTextPosition(JButton.LEFT);
				setVerticalTextPosition(JButton.CENTER);
				break;
			case LEFT:
				setHorizontalTextPosition(JButton.RIGHT);
				setVerticalTextPosition(JButton.CENTER);
				break;
		}
	}
	
	public void setImage(Image image)
	{
		this.setIcon(new ImageIcon(image));
	}

	public void setImageSize(int width, int height)
	{
		this.image = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		this.setImage(this.image);
	}

}
