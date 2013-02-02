package simulation.core.view;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import simulation.core.control.Controller;

public class PluginsBar extends ScrolledComposite{

	private Controller controller;

	public PluginsBar(Composite parent, int style, final Controller controller) {
		super(parent, style);
		this.controller = controller;
		
		//initialize
		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(), 0, 0, 255);
		setBackground(simulationPluginsBackgroundColor);
		setExpandVertical(true);
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		setLayoutData(pluginsBarGridData);
		
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

}
