package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * A subclass of {@link Exception} indicating that no controller has been found
 * for a {@link AbstractEngineConfig} object.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NoEngineConfigControllerException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoEngineConfigControllerException(String string) {
		super(string);
	}

}
