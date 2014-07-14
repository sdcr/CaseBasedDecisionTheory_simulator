package cbdt.view.parameterspage.config.common.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.model.parameters.ActorAction;

/**
 * This class is an implementation of the {@link SelectionListener} and is used
 * to set whether the number of absolute occurrences of {@link ActorAction}s is
 * to be saved during the computation.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AbsoluteActionOccurancesSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button absActionOcurrancesButton;

	/**
	 * The constructor.
	 * 
	 * @param commonConfigController
	 * @param absActionOcurrancesButton
	 */
	public AbsoluteActionOccurancesSelectionListener(
			CommonConfigController commonConfigController,
			Button absActionOcurrancesButton) {
		this.absActionOcurrancesButton = absActionOcurrancesButton;
		this.commonConfigController = commonConfigController;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController
				.setCalcAbsActionOccurances(absActionOcurrancesButton
						.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
