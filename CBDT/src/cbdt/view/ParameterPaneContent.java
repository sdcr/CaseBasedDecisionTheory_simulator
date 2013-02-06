package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class ParameterPaneContent implements ISimulationPluginPaneContent{

	private final int CBDT_LABEL_HEIGHT = 40;
	private final FontData CBDT_TITLE_FONT_DATA = new FontData("Arial", 14, SWT.NORMAL);
	private ISimulationPlugin plugin;
	
	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	public Composite getComposite(Composite parent) {
		Composite cbdtFrameComposite = new CBDTcomposite(parent, SWT.NONE);		
		Composite parameterComposite = new Composite(cbdtFrameComposite, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 20;
		parameterComposite.setLayout(rowLayout);
		
		Label parameterLabel = new Label(parameterComposite, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(parent.getDisplay(), labelFontData));
		
		return cbdtFrameComposite;
	}

	@Override
	public ISimulationPlugin getSimulationPlugin() {
		return plugin;
	}

	@Override
	public void setSimulationPlugin(ISimulationPlugin plugin) {
		this.plugin = plugin;
	}

}
