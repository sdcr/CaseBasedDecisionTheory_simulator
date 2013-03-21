package cbdt.control.validators;


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
