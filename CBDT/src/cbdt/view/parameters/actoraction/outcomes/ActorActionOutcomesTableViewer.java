package cbdt.view.parameters.actoraction.outcomes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.HoverLabelWrapper;

public class ActorActionOutcomesTableViewer extends TableViewer {

	private String[] tableTitles = {"probability", "utility", ""};
	private int[] widths = {100, 100, 20};

	private TableItem emptyTableItem;

	private EmptyTableItemSelectionChangedListener emptyTableItemSelectionChangedListener;

	private Composite parent;
		
	private static final String CLOSE_ICON_MEDIUM_12_LOCATION = "/resources/close-icon-medium-12.png";
	private static final String CLOSE_ICON_SMALL_12_LOCATION = "/resources/close-icon-small-12.png";
		
	public ActorActionOutcomesTableViewer(Composite parent, int style) {
		super(parent, style | SWT.FULL_SELECTION | SWT.NO_SCROLL);
		this.parent = parent;
		
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
		TableViewerColumn removeIconColumn = createTableViewerColumn(this, tableTitles[2], widths[2], 2);

		removeIconColumn.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				return "";
			}
			
			@Override
			public void update(ViewerCell cell) {
				TableItem item = (TableItem) cell.getItem();
				
				HoverLabelWrapper removeRowLabel = new HoverLabelWrapper(
						(Composite) cell.getViewerRow().getControl(), SWT.NONE,
						CLOSE_ICON_MEDIUM_12_LOCATION,
						CLOSE_ICON_SMALL_12_LOCATION);
				
				TableEditor editor = new TableEditor(item.getParent());
                editor.grabHorizontal  = true;
                editor.grabVertical = true;
                editor.setEditor(removeRowLabel.getHoverLabel() , item, cell.getColumnIndex());
                editor.layout();
			}
		});
		
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
		
		this.resizeTable();
	}

	@Override
	public void add(Object element) {
		removeEmptyTableItem();
		super.add(element);
		reAddEmptyTableItem();
		this.resizeTable();
	}
	
	public void setActorActionOutcomesInput(Object input){
		removeEmptyTableItem();
		setInput(input);
		reAddEmptyTableItem();
		this.resizeTable();
	}

	private void reAddEmptyTableItem() {
		emptyTableItem = new TableItem(this.getTable(), SWT.NONE);
		emptyTableItemSelectionChangedListener.setEmptyTableItem(emptyTableItem);
	}

	private void removeEmptyTableItem() {
		Table table = this.getTable();
		table.remove(table.indexOf(emptyTableItem));
	}
	
	private void resizeTable() {
		Table table = this.getTable();
		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		GridData tableGridData = new GridData();
		tableGridData.heightHint = computedSize.y;
		table.setLayoutData(tableGridData);
		
		//cbdtFrameComposite repack
		parent.getParent().getParent().getParent().pack();
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
