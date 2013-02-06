package cbdt.view;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;

public class EmptyTableItemSelectionChangedListener implements
		ISelectionChangedListener {

	private TableItem emptyTableItem;
	private TableViewer tableViewer;
	
	public EmptyTableItemSelectionChangedListener(TableItem emptyTableItem,
			TableViewer tableViewer) {
		super();
		this.emptyTableItem = emptyTableItem;
		this.tableViewer = tableViewer;
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		StructuredSelection selection = (StructuredSelection) event.getSelection();
		if(selection.isEmpty()){
			System.out.println("selection of the empty");
			Table table = tableViewer.getTable();
			table.remove(table.indexOf(emptyTableItem));
			tableViewer.add(new ActorActionOutcome(0, 0));
			emptyTableItem = new TableItem(table, SWT.NONE);		
		}

	}

}
