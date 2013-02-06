package cbdt.view;


import java.util.ArrayList;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;
import cbdt.model.ActorActionOutcome;
import cbdt.model.CBDTSimulationParameters;

public class ParameterPaneContent implements ISimulationPluginPaneContent{

	private static final int TABLE_HEIGHT_HINT_REDUCTION = 14;
	private ISimulationPlugin plugin;
	
	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	public Composite getComposite(Composite parent) {
		Composite cbdtFrameComposite = new CBDTcomposite(parent, SWT.NONE);		
		Composite parameterComposite = new Composite(cbdtFrameComposite, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 20;
		parameterComposite.setLayout(rowLayout);
		
		CBDTSimulationParameters parameters = new CBDTSimulationParameters();

		createParameterTitleLabel(parameterComposite);		
		createActorActionUI(parameterComposite);
		
		return cbdtFrameComposite;
	}

	private void createParameterTitleLabel(Composite parameterComposite) {
		Label parameterLabel = new Label(parameterComposite, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(parameterComposite.getDisplay(), labelFontData));
	}

	private void createActorActionUI(Composite parameterComposite) {
		Composite actorActionComposite = new Composite(parameterComposite, SWT.NONE);
		actorActionComposite.setLayout(new GridLayout(2,false));
		
		createActorActionsLabel(actorActionComposite);

		Composite actorActionItemsComposite = new Composite(actorActionComposite, SWT.NONE);		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		actorActionItemsComposite.setLayout(rowLayout);

		createActorActionItem(actorActionItemsComposite);
		
		createAddActorActionButton(actorActionComposite, actorActionItemsComposite);
	}

	private void createActorActionsLabel(Composite actorActionComposite) {
		Label actorActionsLabel = new Label(actorActionComposite, SWT.NONE);
		actorActionsLabel.setText("Actor actions:");	
		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);
	}

	private void createAddActorActionButton(Composite actorActionComposite,
			Composite actorActionItemsComposite) {
		Button addActorActionItemButton = new Button(actorActionComposite, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton.addMouseListener(new AddActorActionMouseListener(this, actorActionItemsComposite));
		
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.END;
		buttonGridData.horizontalSpan = 2;
		addActorActionItemButton.setLayoutData(buttonGridData);
	}

	void createActorActionItem(Composite actorActionItemsComposite) {
		Composite actorActionItemComposite = new Composite(actorActionItemsComposite, SWT.BORDER);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginTop = 0;
		actorActionItemComposite.setLayout(gridLayout);

		Label actionNameLabel = new Label(actorActionItemComposite, SWT.NONE);
		actionNameLabel.setText("Action name:");
		Text actionNameText = new Text(actorActionItemComposite, SWT.SINGLE);
		
		Label actionOutcomesLabel = new Label(actorActionItemComposite, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		
		TableViewer tableViewer = createActionOutcomesTable(actorActionItemComposite);
		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		//just for fun now
		ArrayList<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		list.add(new ActorActionOutcome(0, 1));	

		tableViewer.setInput(list);
		
		TableItem emptyTableItem = new TableItem(table, SWT.NONE);

		tableViewer.addSelectionChangedListener(new EmptyTableItemSelectionChangedListener(
						emptyTableItem, tableViewer));

		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		GridData tableGridData = new GridData();
		tableGridData.heightHint = computedSize.y - TABLE_HEIGHT_HINT_REDUCTION;
		table.setLayoutData(tableGridData);
		
		actorActionItemsComposite.getParent().getParent().pack();
	}

	private TableViewer createActionOutcomesTable(Composite actorActionItemComposite) {
		TableViewer tableViewer = new TableViewer(actorActionItemComposite,SWT.FULL_SELECTION);
		tableViewer.setContentProvider(new ArrayContentProvider());
		String[] tableTitles = {"probability", "utility"};
		int[] widths = {100, 100};
		
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
		
		return tableViewer;
	}
	
	private TableViewerColumn createTableViewerColumn(TableViewer viewer,
			String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE, colNumber);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		return viewerColumn;
	}

	@Override
	public ISimulationPlugin getSimulationPlugin() {
		return plugin;
	}

	@Override
	public void setSimulationPlugin(ISimulationPlugin plugin) {
		this.plugin = plugin;
	}

}
