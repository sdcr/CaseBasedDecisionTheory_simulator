package cbdt.view.parameterspage.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.aspirationlevel.listeners.InitialAspirationLevelModifyListener;

public class InitialAspirationLevelComposite extends
		AbstractAspirationLevelParameterComposite {

	public static final String TOOL_TIP_TEXT = "Aspiration level before\nany action is taken.";
	
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
