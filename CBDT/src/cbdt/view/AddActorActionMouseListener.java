package cbdt.view;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;

public class AddActorActionMouseListener implements MouseListener {

	private Composite actorActionItemsComposite;
	private ParameterPaneContent parameterPaneContent;

	public AddActorActionMouseListener(ParameterPaneContent parameterPaneContent, Composite actorActionItemsComposite) {
		this.parameterPaneContent = parameterPaneContent;
		this.actorActionItemsComposite = actorActionItemsComposite;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e) {
	}

	@Override
	public void mouseDown(MouseEvent e) {
	}

	@Override
	public void mouseUp(MouseEvent e) {
		parameterPaneContent.createActorActionItem(actorActionItemsComposite);
		actorActionItemsComposite.getParent().getParent().pack();
	}

}
