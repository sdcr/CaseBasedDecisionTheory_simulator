package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class ResizeListener implements Listener{

	private ScrolledComposite scrolledComposite;
	private Composite generalWrapper;

	public ResizeListener(ScrolledComposite scrolledComposite,
			Composite generalWrapper) {
				this.scrolledComposite = scrolledComposite;
				this.generalWrapper = generalWrapper;
	}

	@Override
	public void handleEvent(Event event) {
		scrolledComposite.setMinSize(generalWrapper.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

}
