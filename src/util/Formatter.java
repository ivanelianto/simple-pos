package util;

import java.text.NumberFormat;

public class Formatter
{
	private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	public static String formatToCurrency(double price)
	{
		return formatter.format(price);
	}
}
