package cbdt.view.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ProbabilityHintLabelFactory {
	
	public Label createHintLabel(Composite parent, int style){
		Label hintLabel = new Label(parent, style);
		
		hintLabel.setText("*");
		hintLabel.setToolTipText("The sum of the probabilities should to be one.");
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		hintLabel.setLayoutData(gridData);
		
		FontData hintFontData = new FontData("Arial", 13, SWT.BOLD);
		hintLabel.setFont(new Font(hintLabel.getDisplay(), hintFontData));
		
		Color fontColor = new Color(hintLabel.getDisplay(), 255, 150, 150);
		hintLabel.setForeground(fontColor);
		hintLabel.setVisible(false);
		return hintLabel;
	}
}
