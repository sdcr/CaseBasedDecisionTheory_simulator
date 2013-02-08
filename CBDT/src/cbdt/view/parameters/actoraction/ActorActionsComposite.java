package cbdt.view.parameters.actoraction;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.controller.Controller;
import cbdt.model.ActorAction;
import cbdt.view.parameters.AbstractControllerAccessComposite;

public class ActorActionsComposite extends AbstractControllerAccessComposite {

	private Composite actorActionsWrapper;

	public ActorActionsComposite(Composite parent, int style, Controller controller) {
		super(parent, style | SWT.BORDER, controller);

		this.setLayout(new GridLayout(2,false));
		createActorActionsLabel(this);

		actorActionsWrapper = new Composite(this, SWT.NONE);		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		actorActionsWrapper.setLayout(rowLayout);

		createAddActorActionButton();
	}

	private void createActorActionsLabel(Composite actorActionComposite) {
		Label actorActionsLabel = new Label(actorActionComposite, SWT.NONE);
		actorActionsLabel.setText("Actor actions:");	
		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);
	}
	
	private void createAddActorActionButton() {
		Button addActorActionItemButton = new Button(this, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton.addMouseListener(new AddActorActionMouseListener(this));
		
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.END;
		buttonGridData.horizontalSpan = 2;
		addActorActionItemButton.setLayoutData(buttonGridData);
	}

	public void initialize(List<ActorAction> actorActions) {
		for(ActorAction representedActorAction : actorActions){
			addActorActionComposite(representedActorAction);
		}
	}

	public void addActorActionComposite(ActorAction representedActorAction){
		ActorActionComposite actorActionComposite = new ActorActionComposite(
				actorActionsWrapper, SWT.NONE, representedActorAction,
				getController());
	}
}
