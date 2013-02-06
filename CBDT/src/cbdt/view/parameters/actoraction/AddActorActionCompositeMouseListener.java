package cbdt.view.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;


public class AddActorActionCompositeMouseListener implements MouseListener {

	private Composite actorActionCompositeParent;

	public AddActorActionCompositeMouseListener(Composite actorActionCompositeParent) {
		this.actorActionCompositeParent = actorActionCompositeParent;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
		new ActorActionComposite(actorActionCompositeParent, SWT.NONE);
		actorActionCompositeParent.getParent().getParent().pack();
	}

}
