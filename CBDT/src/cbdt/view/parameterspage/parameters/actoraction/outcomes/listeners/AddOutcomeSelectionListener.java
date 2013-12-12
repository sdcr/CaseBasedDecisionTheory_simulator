package cbdt.view.parameterspage.parameters.actoraction.outcomes.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

/**
 * This class implements the {@link SelectionListener} interface and is used to
 * add a default {@link ActorActionOutcome} to an {@link ActorAction}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AddOutcomeSelectionListener implements SelectionListener {

	private TableItem emptyTableItem;
	private ParametersController controller;
	private ActorAction actorAction;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 * @param actorAction
	 * @param emptyTableItem
	 */
	public AddOutcomeSelectionListener(ParametersController controller,
			ActorAction actorAction, TableItem emptyTableItem) {
		super();
		this.controller = controller;
		this.actorAction = actorAction;
		this.emptyTableItem = emptyTableItem;
	}

	public TableItem getEmptyTableItem() {
		return emptyTableItem;
	}

	public void setEmptyTableItem(TableItem emptyTableItem) {
		this.emptyTableItem = emptyTableItem;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		TableItem selectedItem = (TableItem) e.item;
		if (selectedItem.equals(emptyTableItem)) {
			controller.addDefaultActorActionOutcomeToModel(actorAction);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// nothing to do
	}

}
