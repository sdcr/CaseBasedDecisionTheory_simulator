package cbdt.view.parameterspage.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionComposite;
import cbdt.view.parameterspage.parameters.actoraction.outcomes.listeners.AddOutcomeSelectionListener;

/**
 * This class extends {@link TableViewer} and allows the display and
 * modification of {@link ActorActionOutcome}s.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActorActionOutcomesTableViewer extends TableViewer {

	private TableItem addOutcomeTableItem;

	private AddOutcomeSelectionListener addOutcomeSelectionListener;

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

		addOutcomeTableItem = createAddOutcomeTableItem(table);
		addOutcomeSelectionListener = new AddOutcomeSelectionListener(
				parent.getController(), parent.getActorAction(),
				addOutcomeTableItem);
		this.getTable().addSelectionListener(addOutcomeSelectionListener);

		this.resizeTable();
	}

	/**
	 * Creates a {@link TableItem} that is to function as a button, which allows
	 * to add a blank {@link ActorActionOutcome} to the {@link ActorAction}
	 * represented by this object.
	 * 
	 * @param table
	 * @return
	 */
	private TableItem createAddOutcomeTableItem(final Table table) {
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(0, "add outcome...");
		return tableItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.AbstractTableViewer#add(java.lang.Object)
	 */
	public void add(Object element) {
		removeAddOutcomeTableItem();
		super.add(element);
		reAddAddOutcomeTableItem();
		this.resizeTable();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.AbstractTableViewer#remove(java.lang.Object)
	 */
	public void remove(Object element) {
		removeAddOutcomeTableItem();
		super.remove(element);
		reAddAddOutcomeTableItem();
		this.resizeTable();
	}

	/**
	 * Completely resets the content of the table.
	 * 
	 * @param input
	 */
	public void setActorActionOutcomesInput(Object input) {
		removeAddOutcomeTableItem();
		removeColumnLabelProvider.removeAllLabels();
		setInput(input);
		reAddAddOutcomeTableItem();
		this.resizeTable();
	}

	/**
	 * Readds the addOutcomeTableItem.
	 */
	private void reAddAddOutcomeTableItem() {
		addOutcomeTableItem = createAddOutcomeTableItem(this.getTable());
		addOutcomeSelectionListener.setAddOutcomeTableItem(addOutcomeTableItem);
	}

	/**
	 * Removes the addOutcomeTableItem.
	 */
	private void removeAddOutcomeTableItem() {
		Table table = this.getTable();
		int tableItemIndex = table.indexOf(addOutcomeTableItem);
		if (tableItemIndex >= 0)
			table.remove(tableItemIndex);
	}

	/**
	 * Resizes the table.
	 */
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

	/**
	 * @return The {@link ActorActionComposite} which contains this object.
	 */
	public ActorActionComposite getParent() {
		return parent;
	}
}
