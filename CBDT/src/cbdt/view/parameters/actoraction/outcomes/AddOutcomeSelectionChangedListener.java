package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.ActorActionComposite;

public class AddOutcomeSelectionChangedListener implements
		SelectionListener {

	private TableItem emptyTableItem;
	private ActorActionOutcomesTableViewer tableViewer;
	
	
	public AddOutcomeSelectionChangedListener(TableItem emptyTableItem,
			ActorActionOutcomesTableViewer tableViewer) {
		super();
		this.emptyTableItem = emptyTableItem;
		this.tableViewer = tableViewer;
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
			ActorActionComposite actorActionComposite = tableViewer.getParent();
			ActorActionOutcome defaultOutcome = actorActionComposite
					.getController().addDefaultActorActionOutcomeToModel(
							actorActionComposite.getRepresentedActorAction());
			tableViewer.add(defaultOutcome);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		//nothing to do
	}

}
