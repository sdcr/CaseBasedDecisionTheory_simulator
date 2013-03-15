package cbdt.view.parameters.actoraction;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.ParametersController;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameters.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.listeners.RemoveActorActionMouseListener;
import cbdt.view.parameters.actoraction.outcomes.ActorActionOutcomesTableViewer;

/**
 * This composite contains all view elements that represent one ActorAction.
 * In specific, it contains a table with which the user can modify a set of ActorActionOutcomes.
 * @author S-lenovo
 */
public class ActorActionComposite extends AbstractControllerAccessComposite implements Observer{
	
	private static final int actionNameTextWidth = 190;
	private static final String CLOSE_ICON_LARGE_18_LOCATION = "/resources/close-icon-large-18.png";
	private static final String CLOSE_ICON_MEDIUM_18_LOCATION = "/resources/close-icon-medium-18.png";

	private Text actionNameText;
	private ActorActionOutcomesTableViewer actorActionOutcomesTableViewer;
	private ActorAction representedActorAction;
	private ProbabilitySumHintLabelWrapper probabilityHintLabel;

	public ActorActionComposite(final Composite parent, ActorAction representedActorAction, ParametersController controller) {
		super(parent, SWT.BORDER , controller);
		this.representedActorAction = representedActorAction;
		representedActorAction.addObserver(this);

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		createActorActionNameWidgets();
		createActorActionRemoveWidget();
		createActorActionOutcomesWidgets();

		probabilityHintLabel = new ProbabilitySumHintLabelWrapper(this);
	
		update(representedActorAction, null);
		this.getParent().getParent().getParent().pack();
	}

	/**
	 * Creates the widgets that allow the user to set the list of ActorActionOutcomes which 
	 * belong to the represented ActorAction.
	 */
	private void createActorActionOutcomesWidgets() {
		Label actionOutcomesLabel = new Label(this, SWT.NONE);
		actionOutcomesLabel.setText("Action outcomes:");
		GridData outcomesLabelGridData = new GridData();
		outcomesLabelGridData.verticalAlignment = SWT.BEGINNING;
		actionOutcomesLabel.setLayoutData(outcomesLabelGridData);
		
		actorActionOutcomesTableViewer = new ActorActionOutcomesTableViewer(this, SWT.BORDER);
	}

	/**
	 * Creates the widgets that allow the removal of the represented ActorAction.
	 */
	private void createActorActionRemoveWidget() {
		HoverLabelWrapper closeLabel = new HoverLabelWrapper(this, SWT.NONE,
				CLOSE_ICON_LARGE_18_LOCATION, CLOSE_ICON_MEDIUM_18_LOCATION);
		closeLabel.getLabel().addMouseListener(new RemoveActorActionMouseListener(this));
	}

	/**
	 * Creates the widgets that allow the setting of the name of the represented ActorAction.
	 */
	private void createActorActionNameWidgets() {
		Label actionNameLabel = new Label(this, SWT.NONE);
		actionNameLabel.setText("Action name:");
		actionNameText = new Text(this, SWT.SINGLE | SWT.BORDER);
		actionNameText.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				Text text =  (Text)e.widget;
				String newActorActionName = text.getText();
				getController().setActorActionName(representedActorAction, newActorActionName);
			}
		});
		
		GridData gridData = new GridData();
		gridData.widthHint = actionNameTextWidth;
		actionNameText.setLayoutData(gridData);
	}

	/**
	 * @return The ActorAction which is represented by this ActorActionComposite.
	 */
	public ActorAction getActorAction() {
		return representedActorAction;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof ActorAction && ((ActorAction)arg0).equals(representedActorAction)){
			actionNameText.setText(representedActorAction.getActionName());
			List<ActorActionOutcome> actionOutcomes = representedActorAction.getActionOutcomes();
			for(ActorActionOutcome outcome : actionOutcomes){
				outcome.addObserver(this);
			}
			actorActionOutcomesTableViewer.setActorActionOutcomesInput(actionOutcomes);
			updateProbabilityHintVisibility();
		}
		if(arg0 instanceof ActorActionOutcome)
			updateProbabilityHintVisibility();
	}

	private void updateProbabilityHintVisibility() {
		if(representedActorAction.hasValidProbabilityDistribution())
			probabilityHintLabel.setVisible(false);
		else
			probabilityHintLabel.setVisible(true);
	}
	
}
