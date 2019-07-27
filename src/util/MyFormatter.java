package util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyFormatter
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
	
	public static String formatToNumberWithSeparator(double number)
	{
		DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setGroupingSeparator('.');
		formatter.setDecimalFormatSymbols(symbols);
		return formatter.format(number);
	}
}
