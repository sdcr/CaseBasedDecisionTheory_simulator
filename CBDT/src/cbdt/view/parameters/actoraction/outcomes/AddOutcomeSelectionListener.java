package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

import cbdt.controller.Controller;
import cbdt.model.ActorAction;

public class AddOutcomeSelectionListener implements
		SelectionListener {

	private TableItem emptyTableItem;
	private Controller controller;
	private ActorAction actorAction;
	
	public AddOutcomeSelectionListener(Controller controller, ActorAction actorAction, TableItem emptyTableItem) {
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
		TableItem selectedItem = (TableItem)e.item;
		if(selectedItem.equals(emptyTableItem)){
			controller.addDefaultActorActionOutcomeToModel(actorAction);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		//nothing to do
	}

}