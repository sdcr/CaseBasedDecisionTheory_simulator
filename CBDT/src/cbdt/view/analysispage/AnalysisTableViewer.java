package cbdt.view.analysispage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

//TODO: RED
/**
 * This class extends the {@link TableViewer} class and is used to display
 * simulation {@link Result}s in a table.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AnalysisTableViewer extends TableViewer {

	private List<TableViewerColumn> absoluteOccuranceColumns;
	private List<TableViewerColumn> relativeOccuranceColumns;
	private TableViewerColumn lowestAspirationLevelColumn;

	public AnalysisTableViewer(Composite parent) {
		super(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION | SWT.BORDER);
		this.setContentProvider(new ArrayContentProvider());

		Table table = this.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		RowData rowData = new RowData();
		rowData.height = 70;
		table.setLayoutData(rowData);

		absoluteOccuranceColumns = new ArrayList<TableViewerColumn>();
		relativeOccuranceColumns = new ArrayList<TableViewerColumn>();

		createStageAndEUColumns();
	}

	private void createStageAndEUColumns() {
		TableViewerColumn colStage = new TableViewerColumn(this, SWT.NONE);
		colStage.getColumn().setText("Stage");
		colStage.getColumn().setWidth(45);
		colStage.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				StageResult stageResult = (StageResult) element;
				return Integer.toString(stageResult.getStage());
			}
		});

		TableViewerColumn colEU = new TableViewerColumn(this, SWT.NONE);
		colEU.getColumn().setText("Expected utility");
		colEU.getColumn().setWidth(95);
		colEU.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof BigDecimalStageResult) {
					BigDecimalStageResult stageResult = (BigDecimalStageResult) element;
					return stageResult.getExpectedBigDecimalUtility()
							.toString();
				}
				StageResult stageResult = (StageResult) element;
				return Double.toString(stageResult.getExpectedUtility());
			}
		});
	}

	public void createOccuranceColumns(CommonConfig commonConfig,
			Result simulationResult) {
		disposeOccuranceColumns();
		if (commonConfig != null && simulationResult.getStageResults() != null
				&& !simulationResult.getStageResults().isEmpty()) {
			StageResult firstStageResult = simulationResult.getStageResults()
					.get(0);
			if (commonConfig.isCalculateAbsoluteActionOccurances()
					&& simulationResult.getStageResults() != null
					&& !simulationResult.getStageResults().isEmpty()) {
				for (final ActorAction action : firstStageResult
						.getAbsoluteActionOccurances().keySet()) {
					TableViewerColumn colAbsOccurances = new TableViewerColumn(
							this, SWT.NONE);
					colAbsOccurances.getColumn().setText(
							"Abs. occ.: " + action.getActionName());
					colAbsOccurances.getColumn().setWidth(100);
					colAbsOccurances.getColumn().setResizable(true);
					colAbsOccurances
							.setLabelProvider(new ColumnLabelProvider() {
								@Override
								public String getText(Object element) {
									StageResult stageResult = (StageResult) element;
									return stageResult
											.getAbsoluteActionOccurances()
											.get(action).toString();
								}
							});
					absoluteOccuranceColumns.add(colAbsOccurances);
				}
			}
			if (commonConfig.isCalculateRelativeActionOccurances()
					&& !simulationResult.getStageResults().isEmpty()) {
				Map<ActorAction, Double> relativeActionOccurances = firstStageResult
						.getRelativeActionOccurances();
				Set<ActorAction> actorActions = null;
				if (relativeActionOccurances != null)
					actorActions = relativeActionOccurances.keySet();
				else
					actorActions = ((BigDecimalStageResult) firstStageResult)
							.getRelativeBigDecimalActionOccurances().keySet();

				for (final ActorAction action : actorActions) {
					TableViewerColumn colRelOccurances = new TableViewerColumn(
							this, SWT.NONE);
					colRelOccurances.getColumn().setText(
							"Rel. occ.: " + action.getActionName());
					colRelOccurances.getColumn().setWidth(100);
					colRelOccurances.getColumn().setResizable(true);
					colRelOccurances
							.setLabelProvider(new ColumnLabelProvider() {
								@Override
								public String getText(Object element) {
									if (element instanceof BigDecimalStageResult) {
										BigDecimalStageResult stageResult = (BigDecimalStageResult) element;
										return stageResult
												.getRelativeBigDecimalActionOccurances()
												.get(action).toString();
									}
									StageResult stageResult = (StageResult) element;
									if (stageResult.getStage() != 0)
										return Double.toString(stageResult
												.getRelativeActionOccurances()
												.get(action));
									else
										return "-";
								}
							});
					relativeOccuranceColumns.add(colRelOccurances);
				}
			}
		}
	}

	public void createLowestAspirationLevelColumn(CommonConfig commonConfig,
			Result simulationResult) {
		if (lowestAspirationLevelColumn != null)
			lowestAspirationLevelColumn.getColumn().dispose();
		if (commonConfig.isCalculateLowestAspirationLevels()) {
			lowestAspirationLevelColumn = new TableViewerColumn(this, SWT.NONE);
			lowestAspirationLevelColumn.getColumn()
					.setText("Lowest asp. level");
			lowestAspirationLevelColumn.getColumn().setWidth(100);
			lowestAspirationLevelColumn.getColumn().setResizable(true);
			lowestAspirationLevelColumn
					.setLabelProvider(new ColumnLabelProvider() {
						@Override
						public String getText(Object element) {
							if (element instanceof BigDecimalStageResult) {
								BigDecimalStageResult stageResult = (BigDecimalStageResult) element;
								return stageResult
										.getLowestBigDecimalAspirationLevel()
										.toString();
							}
							StageResult stageResult = (StageResult) element;
							return Double.toString(stageResult
									.getLowestAspirationLevel());
						}
					});
		}
	}

	private void disposeOccuranceColumns() {
		for (TableViewerColumn column : absoluteOccuranceColumns) {
			column.getColumn().dispose();
		}
		for (TableViewerColumn column : relativeOccuranceColumns) {
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
