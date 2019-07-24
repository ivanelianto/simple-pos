package app.validator.rule.loginframe;

import javax.swing.JPasswordField;

import app.validator.rule.IRule;

public class PasswordRule implements IRule
{
	private JPasswordField passwordField;

	public PasswordRule(JPasswordField usernameField)
	{
		this.passwordField = usernameField;
	}

	@Override
	public String validate()
	{
		String password = new String(this.passwordField.getPassword());

		if (password.isEmpty())
			return "Password must be filled.";

		return "";
	}

}
