package app.view.custom_component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;

public class MyCircleButton extends JButton {
	private final static int DEFAULT_WIDTH = 150;
	private final static int DEFAULT_HEIGHT = 150;

	public MyCircleButton() {
		super();
		setOpaque(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);

//		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
	}

	private int getDiameter() {
		int diameter = Math.min(getWidth(), getHeight());
		return diameter;
	}

	@Override
	public Dimension getPreferredSize() {
		FontMetrics metrics = getGraphics().getFontMetrics(getFont());
		int minDiameter = 10 + Math.max(metrics.stringWidth(getText()), metrics.getHeight());
		return new Dimension(minDiameter, minDiameter);
	}

	@Override
	public boolean contains(int x, int y) {
		int radius = getDiameter() / 2;
		return Point2D.distance(x, y, getWidth() / 2, getHeight() / 2) < radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int diameter = getDiameter();
		int radius = diameter / 2;

		g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, diameter, diameter);

		g.setColor(MyColor.getPrimaryBackground());
		g.setFont(getFont());
		FontMetrics metrics = g.getFontMetrics(getFont());
		int stringWidth = metrics.stringWidth(getText());
		int stringHeight = metrics.getHeight();
		g.drawString(getText(), 
				getWidth() / 2 - stringWidth / 2, 
				getHeight() / 2 + stringHeight / 4);
	}
}
