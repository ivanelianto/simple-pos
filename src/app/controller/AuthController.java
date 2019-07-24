package app.controller;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;
import app.validator.Validator;
import app.validator.rule.user.PasswordRule;
import app.validator.rule.user.UsernameRule;
import main.Main;
import util.Hasher;

public class AuthController
{
	public static String login(String username, String password)
	{
		String hashedPassword = Hasher.hash(password);

		boolean isValid = Validator.validate(new UsernameRule(username), new PasswordRule(hashedPassword));

		if (!isValid)
		{
			return Validator.getErrorMessages().get(0);
		}
		else
		{
			ArrayList<User> users = UserRepository.findUserByUsernameAndPassword(username, hashedPassword);

			if (users.size() < 1)
			{
				return "Invalid Username or Password.";
			}
			else
			{
				Main.currentUser = users.get(0);
			}
		}

		return "";
	}
}
