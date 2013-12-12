package cbdt.view.parameterspage.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionComposite;
import cbdt.view.parameterspage.parameters.actoraction.outcomes.listeners.AddOutcomeSelectionListener;

/**
 * This class extends {@link TableViewer} and allows the displaying and
 * modification of {@link ActorActionOutcome}s.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActorActionOutcomesTableViewer extends TableViewer {

	private TableItem emptyTableItem;

	private AddOutcomeSelectionListener emptyTableItemSelectionChangedListener;

	private ActorActionComposite parent;

	private RemoveColumnLabelProvider removeColumnLabelProvider;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param style
	 */
	public ActorActionOutcomesTableViewer(ActorActionComposite parent, int style) {
		super(parent, style | SWT.FULL_SELECTION | SWT.NO_SCROLL);
		this.parent = parent;

		this.setContentProvider(new ArrayContentProvider());

		TableViewerColumnFactory columnFactory = new TableViewerColumnFactory();
		columnFactory.createColumns(this);
		removeColumnLabelProvider = columnFactory
				.getRemoveColumnLabelProvider();

		final Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		emptyTableItem = createEmptyTableItem(table);
		emptyTableItemSelectionChangedListener = new AddOutcomeSelectionListener(
				parent.getController(), parent.getActorAction(), emptyTableItem);
		this.getTable().addSelectionListener(
				emptyTableItemSelectionChangedListener);

		this.resizeTable();
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

	public void setActorActionOutcomesInput(Object input) {
		removeEmptyTableItem();
		removeColumnLabelProvider.removeAllLabels();
		setInput(input);
		reAddEmptyTableItem();
		this.resizeTable();
	}

	private void reAddEmptyTableItem() {
		emptyTableItem = createEmptyTableItem(this.getTable());
		emptyTableItemSelectionChangedListener
				.setEmptyTableItem(emptyTableItem);
	}

	private void removeEmptyTableItem() {
		Table table = this.getTable();
		int emptyTableIndex = table.indexOf(emptyTableItem);
		if (emptyTableIndex >= 0)
			table.remove(table.indexOf(emptyTableItem));
	}

	private void resizeTable() {
		Table table = this.getTable();
		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		GridData tableGridData = new GridData();
		tableGridData.heightHint = computedSize.y;
		table.setLayoutData(tableGridData);

		// cbdtFrameComposite repack
		parent.getParent().getParent().getParent().getParent().getParent()
				.getParent().pack();
	}

	public ActorActionComposite getParent() {
		return parent;
	}
}
