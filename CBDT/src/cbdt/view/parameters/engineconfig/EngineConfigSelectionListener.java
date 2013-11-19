package cbdt.view.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

import cbdt.control.pages.ParametersPageController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;

public class EngineConfigSelectionListener implements SelectionListener {

	ParametersPageController controller;

	List<AbstractEngineConfig> configsList;

	public EngineConfigSelectionListener(ParametersPageController controller) {
		this.controller = controller;
		configsList = new ArrayList<AbstractEngineConfig>();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Combo comboBox = (Combo) e.getSource();
		controller.setChoosenEngineConfig(configsList.get(comboBox
				.getSelectionIndex()));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void addEngineConfig(AbstractEngineConfig config) {
		configsList.add(config);
	}

}
