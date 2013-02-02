package simulation.core.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Widget;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class PluginsBar extends Composite {

	private Controller controller;
	private PluginPane pluginPane;

	public PluginsBar(Composite parent, int style, final Controller controller) {
		super(parent, style);
		System.out.println("create the pluginsbar");
		this.controller = controller;

		// init coloring
		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(),
				0, 0, 255);
		// setBackground(simulationPluginsBackgroundColor);
		// setSize(300, 100);

		// initialize this objects behaviour within the parent
		// setExpandVertical(true);
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBarGridData.widthHint = 200;
		setLayoutData(pluginsBarGridData);

		// init layout of objects in this object
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = false;
		rowLayout.type = SWT.VERTICAL;
		this.setLayout(rowLayout);

		for (int i = 0; i < 50; i++) {
			Label l = new Label(this, SWT.PUSH);
			l.setText("label");
			l.setBackground(simulationPluginsBackgroundColor);
		}
		// this.setContent(l);

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDown(MouseEvent e) {
				controller.doSth();
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void setPluginPane(PluginPane pluginPane) {
		this.pluginPane = pluginPane;
	}

	public void insertExampleContent() {

		final ISimulationPluginPaneContent exampleContent = new ISimulationPluginPaneContent() {

			private ISimulationPlugin plugin;

			@Override
			public Widget getWidget(Composite parent) {
				Button b = new Button(parent, SWT.PUSH);
				return null;
			}

			@Override
			public String getName() {
				return "example pane";
			}

			@Override
			public ISimulationPlugin getSimulationPlugin() {
				return null;
			}

			@Override
			public void setSimulationPlugin(ISimulationPlugin plugin) {
				this.plugin = plugin;
			}
		};

		ISimulationPlugin plugin = new ISimulationPlugin() {

			@Override
			public List<ISimulationPluginPaneContent> getPaneContents() {
				List<ISimulationPluginPaneContent> retVal = new ArrayList<ISimulationPluginPaneContent>();
				retVal.add(exampleContent);
				return null;
			}

			@Override
			public String getName() {
				return "example";
			}
		};

		exampleContent.setSimulationPlugin(plugin);
		List<ISimulationPlugin> list = new ArrayList<ISimulationPlugin>();
		list.add(plugin);
		update(list);
	}

	public void update(List<ISimulationPlugin> plugins) {
		// TODO Auto-generated method stub
		System.out.println("update pluginsbar");
		for (ISimulationPlugin plugin : plugins) {
			PluginsBarItem barItem = new PluginsBarItem(plugin, this, SWT.PUSH);
		}
	}

}
