package app.validator.rule.user;

import java.util.ArrayList;

import app.model.User;
import app.repository.UserRepository;
import app.validator.rule.IRule;

public class UniqueUsernameRule implements IRule
{
	private String username;

	public UniqueUsernameRule(String username)
	{
		this.username = username;
	}

	@Override
	public String validate()
	{
		return isUsernameExist();
	}

	public String isUsernameExist()
	{
		ArrayList<User> users = UserRepository.findUserByUsername(username);

		if (users.size() > 0)
			return "User with that username exist.";

		return "";
	}

}
