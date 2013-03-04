package cbdt.view.parameters.actoraction;

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

import cbdt.controller.Controller;
import cbdt.model.ActorAction;
import cbdt.view.AbstractControllerAccessComposite;
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

	public ActorActionComposite(final Composite parent, int style, ActorAction representedActorAction, Controller controller) {
		super(parent, style | SWT.BORDER , controller);
		this.representedActorAction = representedActorAction;
		representedActorAction.addObserver(this);

		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.marginTop = 0;
		setLayout(gridLayout);

		createActorActionNameWidgets();
		createActorActionRemoveWidget();
		createActorActionOutcomesWidgets();
	
		update(representedActorAction, null);
		
		this.getParent().getParent().pack();
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
			actorActionOutcomesTableViewer.setActorActionOutcomesInput(representedActorAction.getActionOutcomes());
		}
	}
	
}
