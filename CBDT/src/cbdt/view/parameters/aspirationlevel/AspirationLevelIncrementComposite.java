package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameters.SimpleParameterGridDataFactory;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelIncrementModifyListener;
import cbdt.view.parameters.aspirationlevel.listeners.UseAspirationLevelIncrementSelectionListener;

public class AspirationLevelIncrementComposite extends
		Composite implements Observer{

	private Text text;
	private DoubleFormatChecker doubleFormatChecker;
	private Button useAspirationLevelIncrementButton;

	public AspirationLevelIncrementComposite(Composite parent,
			ParametersPageController controller) {
		super(parent, SWT.NONE);
		
		SimpleParameterGridDataFactory gridDataFactory = new SimpleParameterGridDataFactory();
		this.setLayoutData(gridDataFactory.getCompositesGridData());
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.horizontalSpacing = 0;
		this.setLayout(gridLayout);
		
		useAspirationLevelIncrementButton = new Button(this, SWT.CHECK);
		useAspirationLevelIncrementButton.setText("increment by");

		text = new Text(this, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(gridDataFactory.getTextGridData());
		
		SimpleParameterHintLabelWrapper hintLabelWrapper = new SimpleParameterHintLabelWrapper(this);
		
		doubleFormatChecker = new DoubleFormatChecker();
		text.addModifyListener(
				new AspirationLevelIncrementModifyListener(controller,
						hintLabelWrapper, doubleFormatChecker));
		
		useAspirationLevelIncrementButton
			.addSelectionListener(new UseAspirationLevelIncrementSelectionListener(
				controller, useAspirationLevelIncrementButton));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Parameters) {
			Parameters params = (Parameters) o;
			boolean usingAspirationLevelIncrement = params.isUsingAspirationLevelIncrement();
			useAspirationLevelIncrementButton.setSelection(usingAspirationLevelIncrement);
			text.setEnabled(usingAspirationLevelIncrement);
			text.setText(String.valueOf(params.getAspirationLevelIncrement()));
		}
	}
	
	public boolean hasValidValue() {
		return doubleFormatChecker.isValidValue(text.getText());
	}

	
	public void setParametersModel(Parameters parametersModel) {
		parametersModel.addObserver(this);
		this.update(parametersModel, null);
	}
}
