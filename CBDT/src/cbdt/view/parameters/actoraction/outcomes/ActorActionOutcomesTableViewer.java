package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.ActorActionComposite;

/**
 * The table viewer that allows the display and modification of ActorActionOutcomes.
 * @author S-lenovo
 */
public class ActorActionOutcomesTableViewer extends TableViewer {

	private String[] tableTitles = {"probability", "utility", ""};
	private int[] widths = {100, 100, 20};

	private TableItem emptyTableItem;

	private AddOutcomeSelectionListener emptyTableItemSelectionChangedListener;

	private ActorActionComposite parent;
	private RemoveColumnLabelProvider removeColumnLabelProvider;
		
	public ActorActionOutcomesTableViewer(ActorActionComposite parent, int style) {
		super(parent, style | SWT.FULL_SELECTION | SWT.NO_SCROLL);
		this.parent = parent;
		
		this.setContentProvider(new ArrayContentProvider());
		removeColumnLabelProvider = new RemoveColumnLabelProvider(this);
		createColumns();
		
		final Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		emptyTableItem = createEmptyTableItem(table);
		emptyTableItemSelectionChangedListener = new AddOutcomeSelectionListener(
				parent.getController(), parent.getRepresentedActorAction(), emptyTableItem);
		this.getTable().addSelectionListener(emptyTableItemSelectionChangedListener);
		
		this.resizeTable();
	}
	
	/**
	 * Creates the probability, utility and "remove" columns of this table viewer.
	 */
	private void createColumns() {
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
		
		TableViewerColumn removeIconColumn = createTableViewerColumn(this, tableTitles[2], widths[2], 2);
		removeIconColumn.setLabelProvider(removeColumnLabelProvider);
	}

	private TableItem createEmptyTableItem(final Table table) {
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0, "add outcome...");
		return tableItem;
	}

	public void add(Object element) {
		removeEmptyTableItem();
		super.add(element);
		reAddEmptyTableItem();
		this.resizeTable();
	}
	
	public void remove(Object element) {
		removeEmptyTableItem();
		super.remove(element);
		reAddEmptyTableItem();
		this.resizeTable();
	}
	
	public void setActorActionOutcomesInput(Object input){
		removeEmptyTableItem();
		removeColumnLabelProvider.removeAllLabels();
		setInput(input);
		reAddEmptyTableItem();
		this.resizeTable();
	}

	private void reAddEmptyTableItem() {
		emptyTableItem = createEmptyTableItem(this.getTable());
		emptyTableItemSelectionChangedListener.setEmptyTableItem(emptyTableItem);
	}

	private void removeEmptyTableItem() {
		Table table = this.getTable();
		int emptyTableIndex = table.indexOf(emptyTableItem);
		if(emptyTableIndex >= 0)
			table.remove(table.indexOf(emptyTableItem));
	}
	
	private void resizeTable() {
		Table table = this.getTable();
		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		GridData tableGridData = new GridData();
		tableGridData.heightHint = computedSize.y;
		table.setLayoutData(tableGridData);
		
		//cbdtFrameComposite repack
		parent.getParent().getParent().getParent().getParent().getParent().pack();
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		return viewerColumn;
	}
	
	public ActorActionComposite getParent() {
		return parent;
	}
}
