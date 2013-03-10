package cbdt.view.analysis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;

public class AnalysisPageFactory implements ISimulationPluginPageFactory{

	
	@Override
	public String getName() {
		return "Ergebnis-Analyse";
	}

	@Override
	public Composite getPageComposite(Composite parent) {
		Composite parameterComposite = new Composite(parent, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		parameterComposite.setLayout(rowLayout);
		Label l = new Label(parameterComposite, SWT.NONE);
		l.setText("Analyse");
		return parameterComposite;
	}

}
