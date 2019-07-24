package app.validator.rule.loginframe;

import javax.swing.JTextField;

import app.validator.rule.IRule;

public class UsernameRule implements IRule
{
	private JTextField usernameField;

	public UsernameRule(JTextField usernameField)
	{
		this.usernameField = usernameField;
	}

	@Override
	public String validate()
	{
		String username = this.usernameField.getText();
		
		if (username.isEmpty())
			return "Username must be filled.";
		
		return "";
	}

}
