package cbdt.view.parameterspage.parameters.actoraction.outcomes.listeners;

import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameterspage.parameters.AbstractParametersControllerAccessComposite;
import cbdt.view.parameterspage.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

/**
 * This class implements the {@link MouseListener} interface and is used to
 * remove an {@link ActorActionOutcome} from an {@link ActorAction}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class RemoveOutcomeMouseListener implements MouseListener {

	private ActorActionOutcome toRemove;
	private ActorActionOutcomesTableViewer tableViewer;

	TableEditor tableEditor;

	/**
	 * The constructor.
	 * 
	 * @param viewer
	 * @param toRemove
	 * @param tableEditor
	 */
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
		AbstractParametersControllerAccessComposite actorActionComposite = tableViewer
				.getParent();
		actorActionComposite.getController().removeActorActionOutcomeFromModel(
				toRemove);
	}

	@Override
	public void mouseUp(MouseEvent e) {
	}

}
