package cbdt.control.validators;

/**
 * The subclasses of this abstract class check whether a {@link String} has a
 * certain required format property.
 * 
 * @author Stephan da Costa Ribeiro
 */
public interface NumberFormatChecker {

	public abstract boolean isValidValue(String text);
}
