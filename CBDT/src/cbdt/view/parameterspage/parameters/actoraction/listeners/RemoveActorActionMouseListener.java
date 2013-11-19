package cbdt.view.parameterspage.parameters.actoraction.listeners;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.view.parameterspage.parameters.actoraction.ActorActionComposite;

public class RemoveActorActionMouseListener implements MouseListener {

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
			toDispose.getController().removeActorActionFromModel(toDispose.getActorAction());
		}
	}