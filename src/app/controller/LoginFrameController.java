package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import app.validator.Validator;
import app.validator.rule.loginframe.PasswordRule;
import app.validator.rule.loginframe.UsernameRule;
import app.view.ILoginFrame;

public class LoginFrameController
{
	private ILoginFrame loginFrameComponents;

	private static LoginFrameController instance;

	private LoginFrameController(ILoginFrame components)
	{
		this.loginFrameComponents = components;
	}

	public static LoginFrameController getInstance(ILoginFrame components)
	{
		if (instance == null)
			instance = new LoginFrameController(components);

		return instance;
	}

	public ActionListener onLoginButtonClick()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				boolean isValid = Validator.validate(new UsernameRule(loginFrameComponents.getUsernameField()),
						new PasswordRule(loginFrameComponents.getPasswordField()));
				
				if (!isValid)
				{
					JOptionPane.showMessageDialog(null, 
							Validator.getErrorMessages().get(0), 
							"Stop", 
							JOptionPane.ERROR_MESSAGE);	
				}
			}
		};
	}
}
