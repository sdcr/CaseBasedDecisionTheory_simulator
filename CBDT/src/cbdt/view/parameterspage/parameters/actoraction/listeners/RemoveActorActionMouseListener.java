package cbdt.view.parameterspage.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.parameters.ActorAction;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionComposite;

/**
 * This class implements the {@link MouseListener} and is used to remove a
 * {@link ActorAction} from the model.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class RemoveActorActionMouseListener implements MouseListener {

	private ActorActionComposite toDispose;

	/**
	 * The constructor.
	 * 
	 * @param toDispose
	 */
	public RemoveActorActionMouseListener(ActorActionComposite toDispose) {
		this.toDispose = toDispose;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
		toDispose.getController().removeActorActionFromModel(
				toDispose.getActorAction());
	}
}