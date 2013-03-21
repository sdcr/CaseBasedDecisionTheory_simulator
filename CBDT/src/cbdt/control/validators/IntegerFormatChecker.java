package cbdt.control.validators;



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
