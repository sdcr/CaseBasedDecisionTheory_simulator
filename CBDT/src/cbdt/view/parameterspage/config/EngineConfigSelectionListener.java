package cbdt.view.parameterspage.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.model.config.AbstractEngineConfig;

public class EngineConfigSelectionListener implements SelectionListener {

	ParametersConfigPageController controller;

	List<AbstractEngineConfig> configsList;

	public EngineConfigSelectionListener(ParametersConfigPageController controller) {
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
