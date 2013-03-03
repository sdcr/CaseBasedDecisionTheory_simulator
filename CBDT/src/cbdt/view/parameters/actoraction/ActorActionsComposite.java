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
import cbdt.view.AbstractControllerAccessComposite;

/**
 * This composite contains all view elements that deal with the display of actor actions.
 * In particular, it allows the creation and removal of actor actions.
 * @author S-lenovo
 */
public class ActorActionsComposite extends AbstractControllerAccessComposite {

	private Composite actorActionsWrapper;

	public ActorActionsComposite(Composite parent, int style, Controller controller) {
		super(parent, style | SWT.BORDER, controller);

		this.setLayout(new GridLayout(2,false));
		createLabel();

		actorActionsWrapper = new Composite(this, SWT.NONE);		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		actorActionsWrapper.setLayout(rowLayout);

		createAddActorActionButton();
	}

	/**
	 * Set the label of this composite.
	 */
	private void createLabel() {
		Label actorActionsLabel = new Label(this, SWT.NONE);
		actorActionsLabel.setText("Actor actions:");	
		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);
	}
	
	/**
	 * Set a button that allows the adding of another actor action.
	 */
	private void createAddActorActionButton() {
		Button addActorActionItemButton = new Button(this, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton.addMouseListener(new AddActorActionMouseListener(this));
		
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.END;
		buttonGridData.horizontalSpan = 2;
		addActorActionItemButton.setLayoutData(buttonGridData);
	}

	/**
	 * Initializes the actor action view elements with an existing list of ActorAction objects.
	 * @param actorActions The ActorAction parameters from which to initialize.
	 */
	public void initialize(List<ActorAction> actorActions) {
		for(ActorAction representedActorAction : actorActions){
			addActorActionComposite(representedActorAction);
		}
	}

	/**
	 * Adds another actor action view element for an ActorAction model object.
	 * @param representedActorAction
	 */
	public void addActorActionComposite(ActorAction representedActorAction){
		new ActorActionComposite(actorActionsWrapper, SWT.NONE,
				representedActorAction, getController());
	}
}
