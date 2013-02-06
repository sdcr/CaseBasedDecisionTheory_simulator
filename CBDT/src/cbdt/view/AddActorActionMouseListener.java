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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown(MouseEvent e) {
		parameterPaneContent.createActorActionItem(actorActionItemsComposite);
		actorActionItemsComposite.getParent().getParent().pack();
	}

	@Override
	public void mouseUp(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
