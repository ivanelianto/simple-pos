package app.validator.rule.product;

import app.validator.rule.IRule;

public class StockRule implements IRule
{

	private final static int MINIMUM_STOCK = 1;

	private String stock;

	public StockRule(String stock)
	{
		this.stock = stock;
	}

	@Override
	public String validate()
	{
		if (stock.isEmpty())
			return "Stock must be filled.";
		else if (!isNumeric(stock))
			return "Stock must be numeric";
		else if (!isValidStock())
			return "Stock must be at least 1.";

		return "";
	}

	private boolean isValidStock()
	{
		return Integer.parseInt(stock) >= MINIMUM_STOCK;
	}
}
