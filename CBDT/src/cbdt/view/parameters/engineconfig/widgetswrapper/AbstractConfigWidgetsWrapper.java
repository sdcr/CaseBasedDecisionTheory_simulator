package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree.RequestedExpectedUtilityValuesModifyListener;

public abstract class AbstractConfigWidgetsWrapper implements Observer {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;
	
	public AbstractConfigWidgetsWrapper(Composite parent) {
		reqValuesLabel = new Label(parent, SWT.NONE);
		reqValuesLabel.setText("Required expected utility values:");

		requiredExpectedUtilitiesComposite = new SimpleParameterComposite(
				parent);
		requiredExpectedUtilitiesComposite.setHintLabel(new HintLabelWrapper(
				requiredExpectedUtilitiesComposite));
		requiredExpectedUtilitiesComposite
				.setToolTipText("The value must be a natural number");

		integerFormatChecker = new IntegerFormatChecker();
		requiredExpectedUtilitiesComposite
				.setNumberFormatChecker(integerFormatChecker);
	}

	public void setEngineConfigModel(AbstractEngineConfiguration config) {
		config.addObserver(this);
		update(config, null);
	}
	
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof DFSkeepTreeEngineConfig) {
			DFSkeepTreeEngineConfig config = (DFSkeepTreeEngineConfig) arg0;
			requiredExpectedUtilitiesComposite.getText().setText(
					String.valueOf(config.getRequestedExpectedUtilityValues()));
		}
	}

	public void setParent(Composite parent) {
		reqValuesLabel.setParent(parent);
		requiredExpectedUtilitiesComposite.setParent(parent);
		parent.layout();
	}
	
	public void setVisible(boolean visibility){
		reqValuesLabel.setVisible(visibility);
		requiredExpectedUtilitiesComposite.setVisible(visibility);
	}

	public void setConfigController(
			AbstractEngineConfigController configController) {
		requiredExpectedUtilitiesComposite.getText().addModifyListener(
				new RequestedExpectedUtilityValuesModifyListener(
						configController,
						requiredExpectedUtilitiesComposite.getHintLabel(),
						integerFormatChecker));
	}

	public void setGridData(){
		reqValuesLabel.setLayoutData(new GridData());
		requiredExpectedUtilitiesComposite.setLayoutData(new GridData());
	}
}
