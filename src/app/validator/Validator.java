package app.validator;

import java.util.ArrayList;

import app.validator.rule.IRule;

public class Validator
{
	private static ArrayList<String> errorMessages = new ArrayList<>();

	private Validator()
	{
	}

	public static ArrayList<String> getErrorMessages()
	{
		return errorMessages;
	}

	public static boolean validate(IRule... rules)
	{
		boolean isValid = true;

		for (IRule rule : rules)
		{
			String errorMessage = rule.validate();

			if (!errorMessage.isEmpty())
			{
				errorMessages.add(errorMessage);
				isValid = false;
			}
		}

		return isValid;
	}
}
