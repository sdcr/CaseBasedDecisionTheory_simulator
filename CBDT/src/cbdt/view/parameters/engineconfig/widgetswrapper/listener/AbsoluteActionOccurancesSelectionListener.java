package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.ParametersPageController;

public class AbsoluteActionOccurancesSelectionListener implements
		SelectionListener {

	private ParametersPageController pageController;
	private Button absActionOcurrancesButton;

	public AbsoluteActionOccurancesSelectionListener(
			ParametersPageController pageController,
			Button absActionOcurrancesButton) {
		this.absActionOcurrancesButton = absActionOcurrancesButton;
		this.pageController = pageController;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		pageController.setCalcAbsActionOccurances(absActionOcurrancesButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
