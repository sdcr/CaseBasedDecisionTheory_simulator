package cbdt.view.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

import cbdt.control.ParametersController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public class EngineConfigSelectionListener implements SelectionListener {

	ParametersController controller;

	List<AbstractEngineConfiguration> configsList;

	public EngineConfigSelectionListener(ParametersController controller) {
		this.controller = controller;
		configsList = new ArrayList<AbstractEngineConfiguration>();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Combo comboBox = (Combo) e.getSource();
		controller.setChoosenConfig(configsList.get(comboBox
				.getSelectionIndex()));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	public void addEngineConfig(AbstractEngineConfiguration config) {
		configsList.add(config);
	}

}
