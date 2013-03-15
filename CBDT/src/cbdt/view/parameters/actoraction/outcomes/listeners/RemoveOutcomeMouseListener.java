package cbdt.view.parameters.actoraction.outcomes.listeners;

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameters.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class RemoveOutcomeMouseListener implements MouseListener {

	private ActorActionOutcome toRemove;
	private ActorActionOutcomesTableViewer tableViewer;

	TableEditor tableEditor;

	public RemoveOutcomeMouseListener(ActorActionOutcomesTableViewer viewer,
			ActorActionOutcome toRemove, TableEditor tableEditor) {
		this.tableViewer = viewer;
		this.toRemove = toRemove;
		this.tableEditor = tableEditor;
	}

	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
		AbstractControllerAccessComposite actorActionComposite = tableViewer
				.getParent();
		actorActionComposite.getController().removeActorActionOutcomeFromModel(
				toRemove);
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}

}
