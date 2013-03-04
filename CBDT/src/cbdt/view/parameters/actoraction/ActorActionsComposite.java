package cbdt.view.parameters.actoraction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.controller.Controller;
import cbdt.model.ActorAction;
import cbdt.model.Parameters;
import cbdt.view.AbstractControllerAccessComposite;

/**
 * This composite contains all view elements that deal with the display of actor actions.
 * In particular, it allows the creation and removal of actor actions.
 * @author S-lenovo
 */
public class ActorActionsComposite extends AbstractControllerAccessComposite implements Observer {

	private Composite actorActionsWrapper;
	private Map<ActorAction,ActorActionComposite> shownCompositesMap;

	public ActorActionsComposite(Composite parent, int style, Controller controller) {
		super(parent, style | SWT.BORDER, controller);
		shownCompositesMap = new HashMap<ActorAction, ActorActionComposite>();

//		this.setLayout(new GridLayout(2,false));
//		createLabel();

		RowLayout thisLayout = new RowLayout();
		thisLayout.type = SWT.VERTICAL;
		this.setLayout(thisLayout);
		
		actorActionsWrapper = new Composite(this, SWT.NONE);		
		RowLayout wrapperLayout = new RowLayout();
		wrapperLayout.type = SWT.VERTICAL;
		actorActionsWrapper.setLayout(wrapperLayout);

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
		
//		GridData buttonGridData = new GridData();
//		buttonGridData.horizontalAlignment = SWT.END;
//		buttonGridData.horizontalSpan = 2;
//		addActorActionItemButton.setLayoutData(buttonGridData);
	}

	/**
	 * Initializes the actor action view elements with an existing list of ActorAction objects.
	 * @param actorActions The ActorAction parameters from which to initialize.
	 */
	public void setParametersModel(Parameters params) {
		params.addObserver(this);
		update(params, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Parameters){
			Parameters params = (Parameters)arg0;
			List<ActorAction> newActionsList = params.getActorActions();
			Set<ActorAction> shownActions = shownCompositesMap.keySet();

			addNewActorActionComposites(newActionsList, shownActions);
			removeSuperfluosActorActionComposites(newActionsList, shownActions);
			this.getParent().getParent().getParent().pack();
		}
	}

	private void removeSuperfluosActorActionComposites(List<ActorAction> newActionsList,
			Set<ActorAction> shownActions) {
		Set<ActorAction> toRemoveFromMap = new HashSet<ActorAction>();
		for(ActorAction shownAction : shownActions){
			if(!newActionsList.contains(shownAction)){
				shownCompositesMap.get(shownAction).dispose();
				toRemoveFromMap.add(shownAction);
			}
		}
		for(ActorAction actionToRemove : toRemoveFromMap)
			shownCompositesMap.remove(actionToRemove);
	}

	private void addNewActorActionComposites(List<ActorAction> newActionsList,
			Set<ActorAction> shownActions) {
		for(ActorAction newAction : newActionsList){
			if(!shownActions.contains(newAction)){
				ActorActionComposite newComposite = new ActorActionComposite(actorActionsWrapper, SWT.NONE,
						newAction, getController());
				shownCompositesMap.put(newAction, newComposite);
			}
		}
	}
}
