package cbdt.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;

public class ActorActionOutcomesTableViewer extends TableViewer {

	private static final int TABLE_HEIGHT_HINT_REDUCTION = 14;
	
	private String[] tableTitles = {"probability", "utility"};
	private int[] widths = {100, 100};

	private TableItem emptyTableItem;

	private EmptyTableItemSelectionChangedListener emptyTableItemSelectionChangedListener;
		
		
	public ActorActionOutcomesTableViewer(Composite parent, int style) {
		super(parent, style | SWT.FULL_SELECTION);
		
		this.setContentProvider(new ArrayContentProvider());
		
		TableViewerColumn probabilityColumn = createTableViewerColumn(this, tableTitles[0], widths[0], 0);
		probabilityColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ActorActionOutcome outcome = (ActorActionOutcome) element;
				return Double.toString(outcome.getProbability());
			}
		});
		probabilityColumn.setEditingSupport(new ProbabilityEditingSupport(this));
		
		TableViewerColumn utilityColumn = createTableViewerColumn(this, tableTitles[1], widths[1], 1);
		utilityColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				ActorActionOutcome outcome = (ActorActionOutcome) element;
				return Double.toString(outcome.getUtility());
			}
		});
		utilityColumn.setEditingSupport(new UtilityEditingSupport(this));
		
		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		List<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		this.setInput(list);
		
		emptyTableItem = new TableItem(table, SWT.NONE);
		emptyTableItemSelectionChangedListener = new EmptyTableItemSelectionChangedListener(
						emptyTableItem, this);
		this.addSelectionChangedListener(emptyTableItemSelectionChangedListener);
		
		resizeActorActionOutcomesTable(table);
	}

	@Override
	public void add(Object element) {
		removeEmptyTableItem();
		super.add(element);
		reAddEmptyTableItem();
	}
	
	public void setActorActionOutcomesInput(Object input){
		removeEmptyTableItem();
		setInput(input);
		reAddEmptyTableItem();
	}

	private void reAddEmptyTableItem() {
		emptyTableItem = new TableItem(this.getTable(), SWT.NONE);
		emptyTableItemSelectionChangedListener.setEmptyTableItem(emptyTableItem);
	}

	private void removeEmptyTableItem() {
		Table table = this.getTable();
		table.remove(table.indexOf(emptyTableItem));
	}
	
	private void resizeActorActionOutcomesTable(Table table) {
		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		GridData tableGridData = new GridData();
		tableGridData.heightHint = computedSize.y - TABLE_HEIGHT_HINT_REDUCTION;
		table.setLayoutData(tableGridData);
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		return viewerColumn;
	}
}
