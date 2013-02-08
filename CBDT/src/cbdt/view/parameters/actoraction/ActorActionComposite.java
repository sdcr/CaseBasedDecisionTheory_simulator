package cbdt.view.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.controller.Controller;
import cbdt.model.ActorAction;
import cbdt.view.parameters.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

public class ActorActionComposite extends AbstractControllerAccessComposite {
	
	private static final String CLOSE_ICON_LARGE_18_LOCATION = "/resources/close-icon-large-18.png";
	private static final String CLOSE_ICON_MEDIUM_18_LOCATION = "/resources/close-icon-medium-18.png";
	private Text actionNameText;
	private ActorActionOutcomesTableViewer actorActionOutcomesTableViewer;
	private ActorAction representedActorAction;

	public ActorActionComposite(final Composite parent, int style, ActorAction representedActorAction, Controller controller) {
		super(parent, style | SWT.BORDER , controller);
		this.representedActorAction = representedActorAction;

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		createActorActionNameWidgets();
		createActorActionDisposeWidget();
		createActorActionOutcomesWidgets();
	
		actorActionOutcomesTableViewer
				.setActorActionOutcomesInput(representedActorAction
						.getActionOutcomes());
		
		this.getParent().getParent().pack();
	}

	private void createActorActionOutcomesWidgets() {
		Label actionOutcomesLabel = new Label(this, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		GridData outcomesLabelGridData = new GridData();
		outcomesLabelGridData.verticalAlignment = SWT.BEGINNING;
		actionOutcomesLabel.setLayoutData(outcomesLabelGridData);
		
		actorActionOutcomesTableViewer = new ActorActionOutcomesTableViewer(this, SWT.NONE);
	}

	private void createActorActionDisposeWidget() {
		HoverLabelWrapper closeLabel = new HoverLabelWrapper(this, SWT.NONE,
				CLOSE_ICON_LARGE_18_LOCATION, CLOSE_ICON_MEDIUM_18_LOCATION);
		closeLabel.getLabel().addMouseListener(new RemoveActorActionMouseListener(this));
	}

	private void createActorActionNameWidgets() {
		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		actionNameText = new Text(this, SWT.SINGLE);
		actionNameText.setText(representedActorAction.getActionName());
		actionNameText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text text =  (Text)e.widget;
				String newActorActionName = text.getText();
				ActorActionComposite actorActionComposite = (ActorActionComposite) text.getParent();
				actorActionComposite.getRepresentedActorAction().setActionName(
						newActorActionName);
			}
		});
	}

	public ActorAction getRepresentedActorAction() {
		return representedActorAction;
	}
	
}
