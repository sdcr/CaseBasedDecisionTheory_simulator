package cbdt.view.parameters;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import cbdt.controller.Controller;
import cbdt.model.Parameters;
import cbdt.view.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.ActorActionsComposite;

/**
 * This composite contains all view elements that deal with the user's parameter input.
 * @author S-lenovo
 */
public class ParametersComposite extends AbstractControllerAccessComposite implements Observer{

	private ActorActionsComposite actorActionsComposite;

	public ParametersComposite(Composite parent, int style, Controller controller) {
		super(parent, style, controller);
		
		GridLayout gridLayout = new GridLayout(2, false);
		this.setLayout(gridLayout);
		
		createParametersTitleLabel();		
		
		createParameterLabel("Actor actions:");
		actorActionsComposite = new ActorActionsComposite(this, SWT.NONE, controller);
		
		createParameterLabel("Initial aspiration level:");
		createParameterText("100");
		createParameterLabel("Aspiration level increment:");
		createParameterText("10");
		createParameterLabel("Aspiration discount factor:");
		createParameterText("0,5");
	}

	private void createParameterText(String initialText){
		Text text = new Text(this, SWT.SINGLE | SWT.BORDER);
		text.setText(initialText);
		GridData gridData = new GridData();
		gridData.widthHint = 130;
		text.setLayoutData(gridData);
//		initAspirationLevelText.addModifyListener(new ModifyListener() {
//			
//			@Override
//			public void modifyText(ModifyEvent e) {
//				Text text =  (Text)e.widget;
//				getController().setInitialAspirationLevel(Double.parseDouble(text.getText()));
//			}
//		});
	}
	
	/**
	 * Set the label of this composite.
	 */
	private void createParameterLabel(String labelText) {
		Label actorActionsLabel = new Label(this, SWT.NONE);
		actorActionsLabel.setText(labelText);	
		
		GridData actorActionsLabelGridData = new GridData();
		actorActionsLabelGridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(actorActionsLabelGridData);
	}

	
	/**
	 * Creates the title label for this composite.
	 */
	private void createParametersTitleLabel() {
		Label parameterLabel = new Label(this, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(this.getDisplay(), labelFontData));
		
		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
		parameterLabel.setLayoutData(parameterLabelGridData);
	}

	/**
	 * Initializes the parameter view elements with an existing parameters model. 
	 * @param parameters The parameters model from which to initialize this view.
	 */
	public void setParametersModel(Parameters parameters) {
		parameters.addObserver(this);
		actorActionsComposite.setParametersModel(parameters);
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO implement
	}
	
}
