package app.view.custom_component;

import java.awt.Color;

public class MyColor
{
	public final static String PRIMARY_BACKGROUND = "#FF5A5F";
	public final static String PRIMARY_DARKER_BACKGROUND = "#E65055";
	public final static String PRIMARY_TEXT = "";
	public final static String ACCENT_BACKGROUND = "#006C70";
	public final static String ACCENT_DARKER_BACKGROUND = "#005457";
	public final static String ACCENT_TEXT = "";
	public final static String DARK_BLUE_GRAY_BACKGROUND = "#37474F";

	private static Color getColor(String code)
	{
		return Color.decode(code);
	}

	public static Color getPrimaryBackground()
	{
		return getColor(PRIMARY_BACKGROUND);
	}

	public static Color getPrimaryDarkerBackground()
	{
		return getColor(PRIMARY_DARKER_BACKGROUND);
	}

	public static Color getAccentBackground()
	{
		return getColor(ACCENT_BACKGROUND);
	}

	public static Color getAccentDarkerBackground()
	{
		return getColor(ACCENT_DARKER_BACKGROUND);
	}

	public static Color getDarkBlueGrayBackground()
	{
		return getColor(DARK_BLUE_GRAY_BACKGROUND);
	}
}
