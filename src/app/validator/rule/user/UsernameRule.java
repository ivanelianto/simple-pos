package app.validator.rule.user;

import app.validator.rule.IRule;

public class UsernameRule implements IRule
{
	private String username;

	public UsernameRule(String username)
	{
		this.username = username;
	}

	@Override
	public String validate()
	{
		if (username.isEmpty())
			return "Username must be filled.";

		return "";
	}

}
