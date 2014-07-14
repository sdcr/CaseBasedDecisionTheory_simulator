package cbdt.control.validators;

/**
 * A subclass of {@link NumberFormatChecker}, which checks whether a String
 * value can be parsed as a {@link Double}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DoubleFormatChecker implements NumberFormatChecker {

	@Override
	public boolean isValidValue(String text) {
		try {
			Double.parseDouble(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
