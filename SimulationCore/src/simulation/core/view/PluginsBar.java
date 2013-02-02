package simulation.core.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class PluginsBar extends Composite {

	private Controller controller;
	private PluginPane pluginPane;
	private Composite scrollContent;
	private ScrolledComposite scrolledComposite;
	private TreeViewer viewer;

	public PluginsBar(Composite parent, int style, final Controller controller) {
		super(parent, style);
		System.out.println("create the pluginsbar");
		this.controller = controller;

		// init coloring
		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(),
				150, 150, 255);
		setBackground(simulationPluginsBackgroundColor);

		// initialize this objects behaviour within the parent
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBarGridData.widthHint = 200;
		setLayoutData(pluginsBarGridData);

		// // init layout of objects in this object
		this.setLayout(new FillLayout());
		// scrolledComposite = new ScrolledComposite(this, SWT.V_SCROLL);
		// scrollContent = new Composite(scrolledComposite, SWT.NONE);
		//
		// //layout and coloring of scrollContent
		// RowLayout rowLayout = new RowLayout();
		// rowLayout.wrap = false;
		// rowLayout.type = SWT.VERTICAL;
		// scrollContent.setLayout(rowLayout);
		// scrollContent.setBackground(simulationPluginsBackgroundColor);
		//
		// //init of scrolledComposite
		// scrolledComposite.setExpandVertical(true);
		// scrolledComposite.setExpandHorizontal(true);
		// scrolledComposite.setContent(scrollContent);

		viewer = new TreeViewer(this, SWT.MULTI);
		viewer.setContentProvider(new PluginsBarTreeContentProvider());
		viewer.setLabelProvider(new PluginsBarItemLabelProvider());

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event
							.getSelection();
					if (selection.getFirstElement() instanceof ISimulationPluginPaneContent) {
						ISimulationPluginPaneContent newForegroundPaneContent = ((ISimulationPluginPaneContent) selection
								.getFirstElement());
						pluginPane.setToForeGround(newForegroundPaneContent);
					}
				}
			}
		});

		insertExampleContent();

		// for (int i = 0; i < 50; i++) {
		// Label l = new Label(scrollContent, SWT.NONE);
		// l.setText("label");
		// l.setBackground(new Color(parent.getDisplay(),
		// 100, 0, 255));
		// scrolledComposite.setMinSize(scrollContent.computeSize(scrolledComposite.getSize().x,
		// SWT.DEFAULT));
		// }

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
			public Composite getComposite(Composite parent) {
				Composite c = new Composite(parent, SWT.NONE);
				c.setBackground(new Color(parent.getDisplay(),255,0,0));
				c.setLayout(new RowLayout());
				Label l = new Label(c, SWT.NONE);
				l.setText("The label");
				return c;
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
				return retVal;
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
		System.out.println("update pluginsbar");

		viewer.setInput(plugins);

		// for (ISimulationPlugin plugin : plugins) {
		// System.out.println("add plugin to bar");
		// PluginsBarItem barItem = new PluginsBarItem(plugin, scrollContent,
		// SWT.PUSH);
		// scrolledComposite.setMinSize(scrollContent.computeSize(scrolledComposite.getSize().x,
		// SWT.DEFAULT));
		// }
	}

}
