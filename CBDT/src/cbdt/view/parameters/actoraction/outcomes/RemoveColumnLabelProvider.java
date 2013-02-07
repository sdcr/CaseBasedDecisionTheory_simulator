package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.ActorActionOutcome;
import cbdt.view.parameters.actoraction.HoverLabelWrapper;

public class RemoveColumnLabelProvider extends ColumnLabelProvider {

	private static final String CLOSE_ICON_MEDIUM_12_LOCATION = "/resources/close-icon-medium-12.png";
	private static final String CLOSE_ICON_SMALL_12_LOCATION = "/resources/close-icon-small-12.png";
	private ActorActionOutcomesTableViewer actorActionOutcomesTableViewer;

	
	public RemoveColumnLabelProvider(
			ActorActionOutcomesTableViewer actorActionOutcomesTableViewer) {
				this.actorActionOutcomesTableViewer = actorActionOutcomesTableViewer;
		
	}

	@Override
	public String getText(Object element) {
		return "";
	}
	
	@Override
	public void update(ViewerCell cell) {
		TableItem item = (TableItem) cell.getItem();
		
		HoverLabelWrapper removeRowLabel = new HoverLabelWrapper(
				(Composite) cell.getViewerRow().getControl(), SWT.NONE,
				CLOSE_ICON_MEDIUM_12_LOCATION,
				CLOSE_ICON_SMALL_12_LOCATION);
		ActorActionOutcome toRemove = (ActorActionOutcome) cell.getElement();
		removeRowLabel.getLabel().addMouseListener(
				new RemoveOutcomeMouseListener(actorActionOutcomesTableViewer, toRemove));
		
		TableEditor editor = new TableEditor(item.getParent());
        editor.grabHorizontal  = true;
        editor.grabVertical = true;
        editor.setEditor(removeRowLabel.getLabel() , item, cell.getColumnIndex());
        editor.layout();
	}

}
