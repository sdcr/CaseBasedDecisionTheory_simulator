package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.ParametersPageController;

public class RelativeActionOccurancesSelectionListener implements
		SelectionListener {

	private Button relActionOcurrancesButton;
	private ParametersPageController pageController;

	public RelativeActionOccurancesSelectionListener(
			ParametersPageController pageController,
			Button relActionOcurrancesButton) {
		this.pageController = pageController;
		this.relActionOcurrancesButton = relActionOcurrancesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		pageController.setCalcRelActionOccurances(relActionOcurrancesButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
