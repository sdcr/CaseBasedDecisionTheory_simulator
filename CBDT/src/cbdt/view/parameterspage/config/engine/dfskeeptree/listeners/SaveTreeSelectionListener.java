package cbdt.view.parameterspage.config.engine.dfskeeptree.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.engine.DFSkeepTreeConfigController;

/**
 * The {@link SelectionListener} implementation to set the enginge config model
 * to save the tree.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SaveTreeSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveTreeButton;

	/**
	 * The constructor.
	 * 
	 * @param configController
	 * @param saveTreeButton
	 */
	public SaveTreeSelectionListener(
			DFSkeepTreeConfigController configController, Button saveTreeButton) {
		this.configController = configController;
		this.saveTreeButton = saveTreeButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveTree(saveTreeButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
