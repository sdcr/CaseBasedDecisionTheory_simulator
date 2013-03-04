package cbdt.view.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.view.parameters.actoraction.ActorActionsComposite;


public class AddActorActionMouseListener implements MouseListener {

	private ActorActionsComposite actorActionsComposite;

	public AddActorActionMouseListener(ActorActionsComposite actorActionCompositeParent) {
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
