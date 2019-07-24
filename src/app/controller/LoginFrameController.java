package app.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.model.User;
import app.repository.UserRepository;
import app.validator.Validator;
import app.validator.rule.loginframe.PasswordRule;
import app.validator.rule.loginframe.UsernameRule;
import app.view.ILoginFrame;
import main.Main;
import util.Hasher;

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
				boolean isValid = Validator.validate(
						new UsernameRule(loginFrameComponents.getUsernameField()),
						new PasswordRule(loginFrameComponents.getPasswordField()));
				
				if (!isValid)
				{
					JOptionPane.showMessageDialog(null, 
							Validator.getErrorMessages().get(0), 
							"Stop", 
							JOptionPane.ERROR_MESSAGE);	
				}
				else
				{
					String username = loginFrameComponents.getUsernameField().getText();
					String hashedPassword = Hasher.hash(new String(loginFrameComponents.getPasswordField().getPassword()));
					ArrayList<User> users = UserRepository.findUserByUsernameAndPassword(username, hashedPassword);

					if (users.size() < 1)
					{
						JOptionPane.showMessageDialog(null, 
								"Invalid Username or Password.", 
								"Stop", 
								JOptionPane.ERROR_MESSAGE);	
					}
					else
					{
						Main.currentUser = users.get(0);
						
						// Open Main Window
					}
				}
			}
		};
	}
}
