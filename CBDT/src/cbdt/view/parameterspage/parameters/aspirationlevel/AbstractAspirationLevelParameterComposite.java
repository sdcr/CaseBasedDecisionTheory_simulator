package cbdt.view.parameterspage.parameters.aspirationlevel;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * The subclasses of this abstract class are used to display a widget for the
 * input of a parameter related to the aspiration level. It extends the
 * {@link SimpleParameterComposite}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractAspirationLevelParameterComposite extends
		SimpleParameterComposite implements Observer {

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public AbstractAspirationLevelParameterComposite(Composite parent) {
		super(parent);
		this.setHintLabel(new SimpleParameterHintLabelWrapper(this));
	}

	public void setParametersModel(Parameters parametersModel) {
		parametersModel.addObserver(this);
		this.update(parametersModel, null);
	}

}
