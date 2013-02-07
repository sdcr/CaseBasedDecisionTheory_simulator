package cbdt.view.parameters.actoraction;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.ActorAction;


public class AddActorActionMouseListener implements MouseListener {

	private ActorActionsComposite actorActionsParent;

	public AddActorActionMouseListener(ActorActionsComposite actorActionCompositeParent) {
		this.actorActionsParent = actorActionCompositeParent;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
		ActorAction newDefaultActorAction = actorActionsParent.getController().addDefaultActorActionToModel();
		actorActionsParent.addActorActionComposite(newDefaultActorAction);
		
		//TODO: rethink packing
		actorActionsParent.getParent().getParent().getParent().pack();
	}

}
