package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.InitialAspirationLevelModifyListener;

public class InitialAspirationLevelComposite extends
		AbstractAspirationLevelParameterComposite {

	public static final String TOOL_TIP_TEXT = "Aspiration level before\nany action is taken.";
	
	public InitialAspirationLevelComposite(Composite parent,
			ParametersPageController controller) {
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
