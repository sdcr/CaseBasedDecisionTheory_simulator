package cbdt.view.parameterspage.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.parameters.ActorAction;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionWrapComposite;

/**
 * This class implements the {@link MouseListener} and is used to cause the
 * addition of another {@link ActorAction} to the model.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AddActorActionMouseListener implements MouseListener {

	private ActorActionWrapComposite actorActionsComposite;

	/**
	 * The constructor.
	 * 
	 * @param actorActionCompositeParent
	 */
	public AddActorActionMouseListener(
			ActorActionWrapComposite actorActionCompositeParent) {
		this.actorActionsComposite = actorActionCompositeParent;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
		actorActionsComposite.getController().addDefaultActorActionToModel();
	}

}
