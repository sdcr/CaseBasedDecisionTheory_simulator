package cbdt.view.parameters.aspirationlevel;

public class NumberFormatChecker {

	public static boolean hasValidDoubleFormat(String s){
		try {
			Double.parseDouble(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
