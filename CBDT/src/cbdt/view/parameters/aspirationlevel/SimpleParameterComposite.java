package cbdt.view.parameters.aspirationlevel;

import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cbdt.model.parameters.Parameters;
import cbdt.view.HintLabelWrapper;

public abstract class SimpleParameterComposite extends Composite implements Observer {

	private Text text;
	private HintLabelWrapper  hintLabel;

	public SimpleParameterComposite(Composite parent) {
		super(parent, SWT.NONE);

		GridData gridData = new GridData();
		gridData.widthHint = 200;
		this.setLayoutData(gridData);
		
		this.setLayout(new RowLayout());

		text = new Text(this, SWT.SINGLE | SWT.BORDER);
		RowData textRowData = new RowData();
		textRowData.width = 150;
		text.setLayoutData(textRowData);
		
		hintLabel = new MalformedTextEntryHintLabelWrapper(this);
	}

	public Text getText(){
		return text;
	}
	
	public HintLabelWrapper  getHintLabel(){
		return hintLabel;
	}

	public boolean hasValidValue(){
		return NumberFormatChecker.hasValidDoubleFormat(text.getText());
	}
	
	public void setParametersModel(Parameters parametersModel){
		parametersModel.addObserver(this);
		this.update(parametersModel, null);
	}

}