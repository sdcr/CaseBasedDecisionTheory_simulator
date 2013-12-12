package cbdt.view.parameterspage.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.aspirationlevel.listeners.InitialAspirationLevelModifyListener;

/**
 * This class is used to display the widgets necessary to allow the input of the
 * initial aspiration level. It extends
 * {@link AbstractAspirationLevelParameterComposite}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class InitialAspirationLevelComposite extends
		AbstractAspirationLevelParameterComposite {

	public static final String TOOL_TIP_TEXT = "Aspiration level before\nany action is taken.";

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param controller
	 */
	public InitialAspirationLevelComposite(Composite parent,
			ParametersController controller) {
		super(parent);
		DoubleFormatChecker doubleFormatChecker = new DoubleFormatChecker();
		this.setNumberFormatChecker(doubleFormatChecker);
		getText().addModifyListener(
				new InitialAspirationLevelModifyListener(controller,
						getHintLabel(), doubleFormatChecker));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Parameters) {
			Parameters params = (Parameters) o;
			getText().setText(
					String.valueOf(params.getInitialAspirationLevel()));
		}
	}

}
