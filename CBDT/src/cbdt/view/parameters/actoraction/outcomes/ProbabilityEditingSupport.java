package cbdt.view.parameters.actoraction.outcomes;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

import cbdt.model.parameters.ActorActionOutcome;

public class ProbabilityEditingSupport extends EditingSupport {

	private TableViewer tableViewer;

	public ProbabilityEditingSupport(TableViewer viewer) {
		super(viewer);
		tableViewer = viewer;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return new TextCellEditor(tableViewer.getTable());
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		return String.valueOf(((ActorActionOutcome)element).getProbability());
	}

	@Override
	protected void setValue(Object element, Object value) {
		double parsedDouble;
		try {
			parsedDouble = Double.parseDouble((String)value);
			((ActorActionOutcome)element).setProbability(parsedDouble);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		tableViewer.update(element, null);
	}

}
