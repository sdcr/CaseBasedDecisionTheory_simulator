package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;

public class AbsoluteActionOccurancesSelectionListener implements
		SelectionListener {

	private AbstractEngineConfigController configController;
	private Button absActionOcurrancesButton;

	public AbsoluteActionOccurancesSelectionListener(
			AbstractEngineConfigController configController, Button absActionOcurrancesButton) {
				this.configController = configController;
				this.absActionOcurrancesButton = absActionOcurrancesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setCalcAbsActionOccurances(absActionOcurrancesButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
