package cbdt.view;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
		
		ActorActionOutcomesTableViewer tableViewer = new ActorActionOutcomesTableViewer(actorActionItemComposite, SWT.NONE);

		// just for fun
		List<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		list.add(new ActorActionOutcome(0, 1));		
		tableViewer.setActorActionOutcomesInput(list);
		
		actorActionItemsComposite.getParent().getParent().pack();
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
