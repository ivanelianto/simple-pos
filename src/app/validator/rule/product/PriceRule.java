package app.validator.rule.product;

import app.validator.rule.IRule;

public class PriceRule implements IRule
{

	private final static double MINIMUM_PRICE = 1000.0;

	private String price;

	public PriceRule(String price)
	{
		this.price = price;
	}

	@Override
	public String validate()
	{
		if (price.isEmpty())
			return "Price must be filled.";
		else if (!isNumeric(price))
			return "Price must be numeric.";
		else if (!isValidPrice())
			return "Price must be at least " + MINIMUM_PRICE;

		return "";
	}

	private boolean isValidPrice()
	{
		return Double.parseDouble(price) >= MINIMUM_PRICE;
	}

}
