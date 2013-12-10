package cbdt.view.parameterspage.parameters.actoraction.outcomes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.parameterspage.parameters.actoraction.HoverLabelWrapper;
import cbdt.view.parameterspage.parameters.actoraction.outcomes.listeners.RemoveOutcomeMouseListener;

/**
 * This class extends the {@link ColumnLabelProvider} and is used to display a
 * {@link HoverLabelWrapper} in the {@link ActorActionOutcomesTableViewer}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class RemoveColumnLabelProvider extends ColumnLabelProvider {

	private static final String CLOSE_ICON_MEDIUM_12_LOCATION = "/resources/close-icon-medium-12.png";
	private static final String CLOSE_ICON_SMALL_12_LOCATION = "/resources/close-icon-small-12.png";
	private ActorActionOutcomesTableViewer actorActionOutcomesTableViewer;

	private List<TableEditor> tableEditors;

	/**
	 * The constructor.
	 * 
	 * @param actorActionOutcomesTableViewer
	 */
	public RemoveColumnLabelProvider(
			ActorActionOutcomesTableViewer actorActionOutcomesTableViewer) {
		this.actorActionOutcomesTableViewer = actorActionOutcomesTableViewer;
		this.tableEditors = new ArrayList<TableEditor>();
	}

	@Override
	public String getText(Object element) {
		return "";
	}

	@Override
	public void update(ViewerCell cell) {
		TableItem item = (TableItem) cell.getItem();

		Composite labelsParent = (Composite) cell.getViewerRow().getControl();
		HoverLabelWrapper removeRowLabel = new HoverLabelWrapper(labelsParent,
				SWT.NONE, CLOSE_ICON_MEDIUM_12_LOCATION,
				CLOSE_ICON_SMALL_12_LOCATION);
		ActorActionOutcome toRemove = (ActorActionOutcome) cell.getElement();

		TableEditor editor = new TableEditor(item.getParent());
		editor.grabHorizontal = true;
		editor.grabVertical = true;
		removeRowLabel.getLabel().addMouseListener(
				new RemoveOutcomeMouseListener(actorActionOutcomesTableViewer,
						toRemove, editor));

		editor.setEditor(removeRowLabel.getLabel(), item, cell.getColumnIndex());

		tableEditors.add(editor);
	}

	/**
	 * Removes all labels in the table.
	 */
	public void removeAllLabels() {
		for (TableEditor tableEditor : tableEditors) {
			tableEditor.getEditor().dispose();
			tableEditor.dispose();
		}
		tableEditors.clear();
	}

}
