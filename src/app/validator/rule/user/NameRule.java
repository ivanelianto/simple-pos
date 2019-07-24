package app.validator.rule.user;

import app.validator.rule.IRule;

public class NameRule implements IRule
{
	private String name;

	public NameRule(String name)
	{
		this.name = name;
	}

	@Override
	public String validate()
	{
		return isEmpty();
	}

	private String isEmpty()
	{
		return name.isEmpty() ? "Name must be filled." : "";
	}
}
