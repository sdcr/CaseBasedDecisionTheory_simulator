package cbdt.view.parameterspage.parameters.actoraction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.parameters.AbstractParametersControllerAccessComposite;
import cbdt.view.parameterspage.parameters.actoraction.listeners.AddActorActionMouseListener;

/**
 * This composite contains all view elements that deal with the display of
 * {@link ActorAction}s. In particular, it allows the creation and removal of
 * {@link ActorAction}s.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActorActionWrapComposite extends
		AbstractParametersControllerAccessComposite implements Observer {

	private Composite actorActionsWrapper;
	private Map<ActorAction, ActorActionComposite> shownCompositesMap;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param style
	 * @param controller
	 */
	public ActorActionWrapComposite(Composite parent, int style,
			ParametersController controller) {
		super(parent, style, controller);
		shownCompositesMap = new HashMap<ActorAction, ActorActionComposite>();

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
	 * Set a {@link Button} that allows the adding of another
	 * {@link ActorAction}.
	 */
	private void createAddActorActionButton() {
		Button addActorActionItemButton = new Button(this, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton
				.addMouseListener(new AddActorActionMouseListener(this));
	}

	/**
	 * Initializes the actor action view elements with an existing list of
	 * {@link ActorAction} objects.
	 * 
	 * @param actorActions
	 *            The {@link ActorAction} parameters from which to initialize.
	 */
	public void setParametersModel(Parameters params) {
		params.addObserver(this);
		update(params, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Parameters) {
			Parameters params = (Parameters) arg0;
			List<ActorAction> newActionsList = params.getActorActions();
			Set<ActorAction> shownActions = shownCompositesMap.keySet();

			addNewActorActionComposites(newActionsList, shownActions);
			removeSuperfluosActorActionComposites(newActionsList, shownActions);
			this.getParent().getParent().getParent().getParent().pack();
		}
	}

	/**
	 * Removes the {@link ActorActionComposite}s of {@link ActorAction}s from
	 * the view, that are no longer part of the parameters model.
	 * 
	 * @param toBeDisplayedActionsList
	 *            a {@link List} of {@link ActorAction}s that are to be
	 *            displayed
	 * @param shownActions
	 *            a {@link Set} of {@link ActorAction}s that currently are
	 *            displayed
	 */
	private void removeSuperfluosActorActionComposites(
			List<ActorAction> toBeDisplayedActionsList, Set<ActorAction> shownActions) {
		Set<ActorAction> toRemoveFromMap = new HashSet<ActorAction>();
		for (ActorAction shownAction : shownActions) {
			if (!toBeDisplayedActionsList.contains(shownAction)) {
				shownCompositesMap.get(shownAction).dispose();
				toRemoveFromMap.add(shownAction);
			}
		}
		for (ActorAction actionToRemove : toRemoveFromMap)
			shownCompositesMap.remove(actionToRemove);
	}

	/**
	 * Adds {@link ActorActionComposite}s of {@link ActorAction}s to the view,
	 * that are now part of the parameters model.
	 * 
	 * @param toBeDisplayedActionsList
	 *            a {@link List} of {@link ActorAction}s that are to be
	 *            displayed
	 * @param shownActions
	 *            a {@link Set} of {@link ActorAction}s that currently are
	 *            displayed
	 */
	private void addNewActorActionComposites(List<ActorAction> toBeDisplayedActionsList,
			Set<ActorAction> shownActions) {
		for (ActorAction newAction : toBeDisplayedActionsList) {
			if (!shownActions.contains(newAction)) {
				ActorActionComposite newComposite = new ActorActionComposite(
						actorActionsWrapper, newAction, getController());
				shownCompositesMap.put(newAction, newComposite);
			}
		}
	}
}
