package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;

import cbdt.model.ActorActionOutcome;

public class TableViewerColumnFactory {

	private String[] tableTitles = {"probability", "utility", ""};
	
	private int[] widths = {100, 100, 20};
	
	RemoveColumnLabelProvider removeColumnLabelProvider;
	
	/**
	 * Creates the probability, utility and "remove" columns of this table viewer.
	 */
	void createColumns(ActorActionOutcomesTableViewer tableViewer) {
		TableViewerColumn probabilityColumn = createTableViewerColumn(tableViewer, tableTitles[0], widths[0], 0);
		probabilityColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ActorActionOutcome outcome = (ActorActionOutcome) element;
				return Double.toString(outcome.getProbability());
			}
		});
		probabilityColumn.setEditingSupport(new ProbabilityEditingSupport(tableViewer));
		
		TableViewerColumn utilityColumn = createTableViewerColumn(tableViewer, tableTitles[1], widths[1], 1);
		utilityColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ActorActionOutcome outcome = (ActorActionOutcome) element;
				return Double.toString(outcome.getUtility());
			}
		});
		utilityColumn.setEditingSupport(new UtilityEditingSupport(tableViewer));
		
		TableViewerColumn removeIconColumn = createTableViewerColumn(tableViewer, tableTitles[2], widths[2], 2);
		removeColumnLabelProvider = new RemoveColumnLabelProvider(tableViewer);
		removeIconColumn.setLabelProvider(removeColumnLabelProvider);
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		return viewerColumn;
	}
	
	public RemoveColumnLabelProvider getRemoveColumnLabelProvider(){
		return removeColumnLabelProvider;
	}
}
