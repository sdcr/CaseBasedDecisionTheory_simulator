package cbdt.view.parameterspage.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.aspirationlevel.listeners.AspirationLevelDiscountModifyListener;

/**
 * This class extends the {@link AbstractAspirationLevelParameterComposite} and
 * is used to allow the input of the aspiration level discount factor.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AspirationLevelDiscountComposite extends
		AbstractAspirationLevelParameterComposite {

	public static final String TOOL_TIP_TEXT = "Determines weighting\nbetween prev. asp. level\nand max. aver. utility.";

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param controller
	 */
	public AspirationLevelDiscountComposite(Composite parent,
			ParametersController controller) {
		super(parent);
		DoubleFormatChecker doubleFormatChecker = new DoubleFormatChecker();
		this.setNumberFormatChecker(doubleFormatChecker);
		getText().addModifyListener(
				new AspirationLevelDiscountModifyListener(controller,
						getHintLabel(), doubleFormatChecker));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Parameters) {
			Parameters params = (Parameters) o;
			getText().setText(
					String.valueOf(params.getAspirationLevelDecrementFactor()));
		}
	}

}
