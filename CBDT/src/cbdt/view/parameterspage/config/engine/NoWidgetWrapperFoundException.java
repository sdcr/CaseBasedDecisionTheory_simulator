package cbdt.view.parameterspage.config.engine;

/**
 * This class is to indicate that no widget wrapper has been found.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NoWidgetWrapperFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoWidgetWrapperFoundException(String string) {
		super(string);
	}

}
