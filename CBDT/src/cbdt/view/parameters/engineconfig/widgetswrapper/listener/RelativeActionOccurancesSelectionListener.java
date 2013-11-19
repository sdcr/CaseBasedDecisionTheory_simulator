package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameters.config.common.CommonConfigController;

public class RelativeActionOccurancesSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button relActionOcurrancesButton;

	public RelativeActionOccurancesSelectionListener(
			CommonConfigController commonConfigController,
			Button relActionOcurrancesButton) {
		this.commonConfigController = commonConfigController;
		this.relActionOcurrancesButton = relActionOcurrancesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController.setCalcRelActionOccurances(relActionOcurrancesButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
