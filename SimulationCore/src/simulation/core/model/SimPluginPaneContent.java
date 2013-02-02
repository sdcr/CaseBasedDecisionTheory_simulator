package simulation.core.model;

import org.eclipse.swt.widgets.Widget;

public class SimPluginPaneContent {

	private Widget content;
	private String contentName;
	
	public SimPluginPaneContent(Widget content, String contentName) {
		this.content = content;
		this.contentName = contentName;
	}


	public Widget getContent() {
		return content;
	}


	public String getContentName() {
		return contentName;
	}
}
