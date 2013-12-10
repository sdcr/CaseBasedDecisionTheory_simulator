package cbdt.view.parameterspage.config.widgetswrapper.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.model.parameters.ActorAction;

/**
 * This class is an implementation of the {@link SelectionListener} and is used
 * to set whether the relative occurrences of {@link ActorAction}s for every
 * tree depth are to be saved during the computation.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class RelativeActionOccurancesSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button relActionOcurrancesButton;

	/**
	 * The constructor.
	 * 
	 * @param commonConfigController
	 * @param relActionOcurrancesButton
	 */
	public RelativeActionOccurancesSelectionListener(
			CommonConfigController commonConfigController,
			Button relActionOcurrancesButton) {
		this.commonConfigController = commonConfigController;
		this.relActionOcurrancesButton = relActionOcurrancesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController
				.setCalcRelActionOccurances(relActionOcurrancesButton
						.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
