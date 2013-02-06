package cbdt.view.parameters.actoraction;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class ActorActionComposite extends Composite {

	public ActorActionComposite(Composite parent, int style) {
		super(parent, style | SWT.BORDER);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		Text actionNameText = new Text(this, SWT.SINGLE);
		
		Label actionOutcomesLabel = new Label(this, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		
		ActorActionOutcomesTableViewer tableViewer = new ActorActionOutcomesTableViewer(this, SWT.NONE);

		// just for fun
		List<ActorActionOutcome> list = new ArrayList<ActorActionOutcome>();
		list.add(new ActorActionOutcome(0, 1));
		list.add(new ActorActionOutcome(0, 1));		
		tableViewer.setActorActionOutcomesInput(list);
		
		this.getParent().getParent().pack();
	}

}
