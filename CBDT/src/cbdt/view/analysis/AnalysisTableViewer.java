package cbdt.view.analysis;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class AnalysisTableViewer extends TableViewer{

	private List<TableViewerColumn> absoluteOccuranceColumns;
	private List<TableViewerColumn> relativeOccuranceColumns;
	
	public AnalysisTableViewer(Composite parent) {
		super(parent, SWT.MULTI | SWT.H_SCROLL
			      | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		this.setContentProvider(new ArrayContentProvider());
		
		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true); 
		
		RowData rowData = new RowData();
		rowData.height = 70;
		table.setLayoutData(rowData);
		
		absoluteOccuranceColumns = new ArrayList<TableViewerColumn>();
		relativeOccuranceColumns = new ArrayList<TableViewerColumn>();
		
		createEUColumns();
	}
	
	private void createEUColumns() {
		TableViewerColumn colStage = new TableViewerColumn(this, SWT.NONE);
		colStage.getColumn().setText("Stage");
		colStage.getColumn().setWidth(45);
		colStage.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				StageResult stageResult = (StageResult) element;
				return Integer.toString(stageResult.getStage());
			}
		});

		TableViewerColumn colEU = new TableViewerColumn(this, SWT.NONE);
		colEU.getColumn().setText("Expected utility");
		colEU.getColumn().setWidth(95);
		colEU.setLabelProvider(new ColumnLabelProvider(){
			@Override
			public String getText(Object element) {
				StageResult stageResult = (StageResult) element;
				return Double.toString(stageResult.getExpectedUtility());
			}
		});
	}

	public void createOccuranceColumns(AbstractEngineConfiguration config, Result simulationResult) {
		disposeOccuranceColumns();
		if(config != null){
			if (config.isCalculateAbsoluteActionOccurances() && simulationResult.getStageResults()!=null && !simulationResult.getStageResults().isEmpty()) {
				for(final ActorAction action : simulationResult.getStageResults().get(0).getAbsoluteActionOccurances().keySet()){
					TableViewerColumn colAbsOccurances = new TableViewerColumn(this, SWT.NONE);
					colAbsOccurances.getColumn().setText("Abs. occ.: "+action.getActionName());
					colAbsOccurances.getColumn().setWidth(100);
					colAbsOccurances.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public String getText(Object element) {
							StageResult stageResult = (StageResult) element;
							return Integer.toString(stageResult.getAbsoluteActionOccurances().get(action));
						}
					});
					absoluteOccuranceColumns.add(colAbsOccurances);
				}
			}
			if (config.isCalculateRelativeActionOccurances() && !simulationResult.getStageResults().isEmpty()) {
				for(final ActorAction action : simulationResult.getStageResults().get(0).getRelativeActionOccurances().keySet()){
					TableViewerColumn colRelOccurances = new TableViewerColumn(this, SWT.NONE);
					colRelOccurances.getColumn().setText("Rel. occ.: "+action.getActionName());
					colRelOccurances.getColumn().setWidth(100);
					colRelOccurances.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public String getText(Object element) {
							StageResult stageResult = (StageResult) element;
							return Double.toString(stageResult.getRelativeActionOccurances().get(action));
						}
					});
					relativeOccuranceColumns.add(colRelOccurances);
				}
			}
		}
	}

	private void disposeOccuranceColumns() {
		for(TableViewerColumn column : absoluteOccuranceColumns){
			column.getColumn().dispose();
		}
		for(TableViewerColumn column : relativeOccuranceColumns){
			column.getColumn().dispose();
		}
	}

	public void resizeTable() {
		Table table = this.getTable();
		Point computedSize = table.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		RowData tableGridData = new RowData();
		tableGridData.height = computedSize.y;
		table.setLayoutData(tableGridData);
	}
}