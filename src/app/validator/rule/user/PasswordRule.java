package app.validator.rule.user;

import app.validator.rule.IRule;

public class PasswordRule implements IRule
{
	private String hashedPassword;

	public PasswordRule(String hashedPassword)
	{
		this.hashedPassword = hashedPassword;
	}

	@Override
	public String validate()
	{
		if (isEmpty())
			return "Password must be filled.";

		return "";
	}

	private boolean isEmpty()
	{
		return hashedPassword.isEmpty();
	}
}
