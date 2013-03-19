package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;

public class RelativeActionOccurancesSelectionListener implements
		SelectionListener {

	private AbstractEngineConfigController configController;
	private Button relActionOcurrancesButton;

	public RelativeActionOccurancesSelectionListener(
			AbstractEngineConfigController configController,
			Button relActionOcurrancesButton) {
				this.configController = configController;
				this.relActionOcurrancesButton = relActionOcurrancesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setCalcRelActionOccurances(relActionOcurrancesButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
