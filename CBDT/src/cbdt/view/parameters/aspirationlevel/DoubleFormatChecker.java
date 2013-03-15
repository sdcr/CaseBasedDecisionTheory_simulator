package cbdt.view.parameters.aspirationlevel;

import cbdt.view.NumberFormatChecker;

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
