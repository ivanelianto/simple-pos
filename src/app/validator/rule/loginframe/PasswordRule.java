package app.validator.rule.loginframe;

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
		if (hashedPassword.isEmpty())
			return "Password must be filled.";
		
		return "";
	}

}
