package cbdt.view.parameterspage.config;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * This class extends the {@link SelectionListener} and is used to take action
 * when the user selects to use a different {@link AbstractEngineConfig}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class EngineConfigSelectionListener implements SelectionListener {

	ParametersConfigPageController controller;

	List<AbstractEngineConfig> configsList;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 */
	public EngineConfigSelectionListener(
			ParametersConfigPageController controller) {
		this.controller = controller;
		configsList = new ArrayList<AbstractEngineConfig>();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		Combo comboBox = (Combo) e.getSource();
		controller.setCurrentlyChosenEngineConfig(configsList.get(comboBox
				.getSelectionIndex()));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/**
	 * Add a {@link AbstractEngineConfig} to the list from which the user can
	 * choose.
	 * 
	 * @param config
	 */
	public void addEngineConfig(AbstractEngineConfig config) {
		configsList.add(config);
	}

}
