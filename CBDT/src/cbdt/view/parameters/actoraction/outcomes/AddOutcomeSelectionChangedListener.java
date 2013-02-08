package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.ActorActionComposite;

public class AddOutcomeSelectionChangedListener implements
		ISelectionChangedListener {

	private TableItem emptyTableItem;
	private ActorActionOutcomesTableViewer tableViewer;


	
	public AddOutcomeSelectionChangedListener(TableItem emptyTableItem,
			ActorActionOutcomesTableViewer tableViewer) {
		super();
		this.emptyTableItem = emptyTableItem;
		this.tableViewer = tableViewer;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event.getSelection();
		if(selection.isEmpty()){
			ActorActionComposite actorActionComposite = tableViewer.getParent();
			ActorActionOutcome defaultOutcome = actorActionComposite
					.getController().addDefaultActorActionOutcomeToModel(
							actorActionComposite.getRepresentedActorAction());
			tableViewer.add(defaultOutcome);
		}

	}

	public TableItem getEmptyTableItem() {
		return emptyTableItem;
	}
	
	public void setEmptyTableItem(TableItem emptyTableItem) {
		this.emptyTableItem = emptyTableItem;
	}

}
