package cbdt.view.parameters.actoraction;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;

class RemoveActorActionMouseListener implements MouseListener {

		private ActorActionComposite toDispose;

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
			toDispose.getController().removeActorActionFromModel(toDispose.getRepresentedActorAction());
			Composite actorActionCompositesFrame = toDispose.getParent();
			toDispose.dispose();
			actorActionCompositesFrame.getParent().getParent().getParent().getParent().pack();
		}
	}