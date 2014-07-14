package cbdt.control.validators;

/**
 * A subclass of {@link NumberFormatChecker}, which checks whether a String
 * value can be parsed as a {@link Integer}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class IntegerFormatChecker implements NumberFormatChecker {

	@Override
	public boolean isValidValue(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
