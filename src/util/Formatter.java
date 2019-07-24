package util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter
{
	private static NumberFormat numberFormatter = NumberFormat.getCurrencyInstance();
	
	public static String formatToSQLDate(Date date)
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormatter.format(date);
	}
	
	public static String formatToHumanReadableDate(Date date)
	{
		SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm");
		return dateFormatter.format(date);
	}
	
	public static String formatToCurrency(double price)
	{
		return numberFormatter.format(price);
	}
}
