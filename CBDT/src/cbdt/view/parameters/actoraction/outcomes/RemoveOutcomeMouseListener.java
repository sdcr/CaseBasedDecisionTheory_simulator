package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.ActorActionOutcome;

public class RemoveOutcomeMouseListener implements MouseListener{

	private ActorActionOutcome toRemove;
	private ActorActionOutcomesTableViewer tableViewer;

	public RemoveOutcomeMouseListener(ActorActionOutcomesTableViewer viewer, ActorActionOutcome toRemove) {
		this.tableViewer = viewer;
		this.toRemove = toRemove;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
		
	}

	@Override
	public void mouseDown(MouseEvent e) {
		tableViewer.remove(toRemove);
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
