package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameters.config.common.CommonConfigController;

public class AbsoluteActionOccurancesSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button absActionOcurrancesButton;

	public AbsoluteActionOccurancesSelectionListener(
			CommonConfigController commonConfigController,
			Button absActionOcurrancesButton) {
		this.absActionOcurrancesButton = absActionOcurrancesButton;
		this.commonConfigController = commonConfigController;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController.setCalcAbsActionOccurances(absActionOcurrancesButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
