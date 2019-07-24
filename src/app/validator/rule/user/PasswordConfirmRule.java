package app.validator.rule.user;

import app.model.User;
import app.repository.UserRepository;
import app.validator.rule.IRule;
import util.Encryptor;

public class PasswordConfirmRule implements IRule
{

	private int userId;
	private String oldPassword, newPassword;

	public PasswordConfirmRule(int userId, String oldPassword, String newPassword)
	{
		this.userId = userId;
		this.oldPassword = oldPassword;
	}

	@Override
	public String validate()
	{
		if (!isValidOldPassword())
			return "Invalid old password.";
		return "";
	}

	private boolean isValidOldPassword()
	{
		String hashedOldPassword = Encryptor.hashSHA256(oldPassword);

		User user = UserRepository.findUserById(userId);

		return user.getPassword().equals(hashedOldPassword);
	}

}
