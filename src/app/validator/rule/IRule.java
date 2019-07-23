package app.validator.rule;

public interface IRule
{
	String validate();
	
	default boolean isNumeric(String text) {
		try {
			Double.parseDouble(text);
		}
		catch (Exception ex) {
			return false;
		}
		
		return true;
	}
}
