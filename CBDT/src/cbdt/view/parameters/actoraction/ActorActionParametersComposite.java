package cbdt.view.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ActorActionParametersComposite extends Composite {

	public ActorActionParametersComposite(Composite parent, int style) {
		super(parent, style);

		this.setLayout(new GridLayout(2,false));
		
		createActorActionsLabel(this);

		Composite actorActionCompositeParent = new Composite(this, SWT.NONE);		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		actorActionCompositeParent.setLayout(rowLayout);

		ActorActionComposite actorActionComposite = new ActorActionComposite(actorActionCompositeParent, SWT.NONE);
		createAddActorActionCompositeButton(this, actorActionCompositeParent);
	}

	private void createActorActionsLabel(Composite actorActionComposite) {
		Label actorActionsLabel = new Label(actorActionComposite, SWT.NONE);
		actorActionsLabel.setText("Actor actions:");	
		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);
	}
	
	private void createAddActorActionCompositeButton(Composite actorActionParametersComposite,
			Composite actorActionCompositeParent) {
		Button addActorActionItemButton = new Button(actorActionParametersComposite, SWT.NONE);
		addActorActionItemButton.setText("Add additional actor action");
		addActorActionItemButton.addMouseListener(new AddActorActionCompositeMouseListener(actorActionCompositeParent));
		
		GridData buttonGridData = new GridData();
		buttonGridData.horizontalAlignment = SWT.END;
		buttonGridData.horizontalSpan = 2;
		addActorActionItemButton.setLayoutData(buttonGridData);
	}

}
