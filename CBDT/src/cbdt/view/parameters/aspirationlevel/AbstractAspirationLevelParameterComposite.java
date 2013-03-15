package cbdt.view.parameters.aspirationlevel;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.Parameters;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.SimpleParameterComposite;

public abstract class AbstractAspirationLevelParameterComposite extends
		SimpleParameterComposite implements Observer {

	public AbstractAspirationLevelParameterComposite(Composite parent) {
		super(parent);
		HintLabelWrapper hintLabelWrapper = new HintLabelWrapper(this);
		hintLabelWrapper.setToolTipText("The value must be a decimal.");
		this.setHintLabel(hintLabelWrapper);
	}

	public void setParametersModel(Parameters parametersModel) {
		parametersModel.addObserver(this);
		this.update(parametersModel, null);
	}

}