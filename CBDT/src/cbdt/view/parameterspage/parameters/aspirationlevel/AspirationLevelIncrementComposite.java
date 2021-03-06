package cbdt.view.parameterspage.parameters.aspirationlevel;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.SimpleParameterGridDataFactory;
import cbdt.view.parameterspage.parameters.aspirationlevel.listeners.AspirationLevelIncrementModifyListener;
import cbdt.view.parameterspage.parameters.aspirationlevel.listeners.IncrementAspirationLevelSparselySelectionListener;

/**
 * This class is used to display the widget to input the aspiration level
 * increment.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AspirationLevelIncrementComposite extends Composite implements
		Observer {

	public static final String TOOL_TIP_TEXT = "Applied every stage indexed by a power\nof "
			+ "2. Supersedes weighting between prev.\nasp. level and max. aver. utility.";

	private Text text;
	private DoubleFormatChecker doubleFormatChecker;
	private Button useAspirationLevelIncrementButton;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param controller
	 */
	public AspirationLevelIncrementComposite(Composite parent,
			ParametersController controller) {
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

		SimpleParameterHintLabelWrapper hintLabelWrapper = new SimpleParameterHintLabelWrapper(
				this);

		doubleFormatChecker = new DoubleFormatChecker();
		text.addModifyListener(new AspirationLevelIncrementModifyListener(
				controller, hintLabelWrapper, doubleFormatChecker));

		useAspirationLevelIncrementButton
				.addSelectionListener(new IncrementAspirationLevelSparselySelectionListener(
						controller, useAspirationLevelIncrementButton));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Parameters) {
			Parameters params = (Parameters) o;
			boolean usingAspirationLevelIncrement = params
					.isIncrementingAspirationLevelSparsely();
			useAspirationLevelIncrementButton
					.setSelection(usingAspirationLevelIncrement);
			text.setEnabled(usingAspirationLevelIncrement);
			text.setText(String.valueOf(params.getAspirationLevelIncrement()));
		}
	}

	/**
	 * Checks if the current value is valid.
	 * 
	 * @return
	 */
	public boolean hasValidValue() {
		return doubleFormatChecker.isValidValue(text.getText());
	}

	/**
	 * Set the {@link Parameters} model.
	 * 
	 * @param parametersModel
	 */
	public void setParametersModel(Parameters parametersModel) {
		parametersModel.addObserver(this);
		this.update(parametersModel, null);
	}
}
