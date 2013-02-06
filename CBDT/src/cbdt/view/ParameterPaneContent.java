package cbdt.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;
import cbdt.model.CBDTSimulationParameters;

public class ParameterPaneContent implements ISimulationPluginPaneContent{

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

		createTitleLabel(parameterComposite);		
		createActorActionUI(parameterComposite);
		
		return cbdtFrameComposite;
	}

	private void createTitleLabel(Composite parameterComposite) {
		Label parameterLabel = new Label(parameterComposite, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(parameterComposite.getDisplay(), labelFontData));
	}

	private void createActorActionUI(Composite parameterComposite) {
		Composite actorActionComposite = new Composite(parameterComposite, SWT.NONE);
		actorActionComposite.setLayout(new GridLayout(2,false));
		
		Label actorActionsLabel = new Label(actorActionComposite, SWT.NONE);
		actorActionsLabel.setText("Actor actions:");		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);

		Composite actorActionItemsComposite = new Composite(actorActionComposite, SWT.NONE);		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		actorActionItemsComposite.setLayout(rowLayout);

		createActorActionItem(actorActionItemsComposite);
		
		Button addActorActionItemButton = new Button(actorActionComposite, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton.addMouseListener(new AddActorActionMouseListener(this, actorActionItemsComposite));
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.END;
		buttonGridData.horizontalSpan = 2;
		addActorActionItemButton.setLayoutData(buttonGridData);
	}

	void createActorActionItem(Composite actorActionItemsComposite) {
		Label parameterLabel = new Label(actorActionItemsComposite, SWT.NONE);
		parameterLabel.setText("ActorActionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
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
