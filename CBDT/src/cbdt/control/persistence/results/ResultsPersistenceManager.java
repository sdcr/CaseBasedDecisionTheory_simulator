package cbdt.control.persistence.results;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

/**
 * A manager which is able to store {@link Result}s and config objects to file
 * as CSV.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ResultsPersistenceManager implements IResultsPersistenceManager {

	private Map<ActorAction, BigDecimal> firstAbsOccurances;
	private Map<ActorAction, Double> firstRelDoubleOccurances;
	private Map<ActorAction, BigDecimal> firstRelBigDecimalOccurances;
	private Double firstDoubleLowestAspLevels;
	private BigDecimal firstBigDecimalLowestAspLevels;

	private List<ActorAction> absOccActorActionsList;
	private List<ActorAction> relOccActorActionsList;

	@Override
	public void saveResultToFile(String filepath, Result result,
			CommonConfig commonConfig, AbstractEngineConfig config)
			throws IOException {
		BufferedWriter outWriter = null;
		outWriter = new BufferedWriter(new FileWriter(filepath));
		absOccActorActionsList = new ArrayList<ActorAction>();
		relOccActorActionsList = new ArrayList<ActorAction>();

		List<StageResult> stageResults = result.getStageResults();
		writeTitleLines(outWriter, stageResults, commonConfig);
		outWriter.newLine();

		writeData(outWriter, stageResults, commonConfig);

		outWriter.newLine();
		writeConfigurationDetails(config, outWriter);
		outWriter.close();
	}

	/**
	 * Write results data as well as the common config to a
	 * {@link BufferedWriter}.
	 * 
	 * @param outWriter
	 * @param stageResults
	 * @param commonConfig
	 * @throws IOException
	 */
	private void writeData(BufferedWriter outWriter,
			List<StageResult> stageResults, CommonConfig commonConfig)
			throws IOException {
		for (StageResult stageResult : stageResults) {
			if (stageResult instanceof BigDecimalStageResult) {
				BigDecimalStageResult bigDecimalStageResult = (BigDecimalStageResult) stageResult;
				outWriter.write(bigDecimalStageResult.getStage() + ";"
						+ bigDecimalStageResult.getExpectedBigDecimalUtility()
						+ ";");
			} else {
				outWriter.write(stageResult.getStage() + ";"
						+ stageResult.getExpectedUtility() + ";");
			}
			if (commonConfig.isCalculateAbsoluteActionOccurances()) {
				for (ActorAction action : absOccActorActionsList) {
					outWriter.write(stageResult.getAbsoluteActionOccurances()
							.get(action) + ";");
				}
			}
			if (commonConfig.isCalculateRelativeActionOccurances()) {
				if (firstRelDoubleOccurances != null) {
					for (ActorAction action : relOccActorActionsList) {
						outWriter.write(stageResult
								.getRelativeActionOccurances().get(action)
								+ ";");
					}
				} else if (firstRelBigDecimalOccurances != null) {
					for (ActorAction action : relOccActorActionsList) {
						outWriter.write(((BigDecimalStageResult) stageResult)
								.getRelativeBigDecimalActionOccurances().get(
										action)
								+ ";");
					}
				}
			}

			if (commonConfig.isCalculateLowestAspirationLevels()) {
				if (firstDoubleLowestAspLevels != null) {
					outWriter.write(stageResult.getLowestAspirationLevel()
							+ ";");
				} else {
					if (firstBigDecimalLowestAspLevels != null) {
						outWriter.write(((BigDecimalStageResult) stageResult)
								.getLowestBigDecimalAspirationLevel() + ";");
					}
				}
			}

			outWriter.newLine();
		}
	}

	private void writeTitleLines(BufferedWriter outWriter,
			List<StageResult> stageResults, CommonConfig commonConfig)
			throws IOException {
		if (stageResults != null && !stageResults.isEmpty()) {
			outWriter.write("Stage;Expected utility;");
			if (commonConfig.isCalculateAbsoluteActionOccurances()) {
				outWriter.write("Absolute occurrences");
				firstAbsOccurances = stageResults.get(0)
						.getAbsoluteActionOccurances();
				writeDelimiters(firstAbsOccurances.keySet().size(), outWriter);
			}
			if (commonConfig.isCalculateRelativeActionOccurances()) {
				outWriter.write("Relative occurrences");
				if (stageResults.get(0) instanceof BigDecimalStageResult) {
					firstRelBigDecimalOccurances = ((BigDecimalStageResult) stageResults
							.get(0)).getRelativeBigDecimalActionOccurances();
					writeDelimiters(firstRelBigDecimalOccurances.keySet()
							.size(), outWriter);
				} else {
					firstRelDoubleOccurances = stageResults.get(0)
							.getRelativeActionOccurances();
					writeDelimiters(firstRelDoubleOccurances.keySet().size(),
							outWriter);
				}
			}
			if (commonConfig.isCalculateLowestAspirationLevels()) {
				outWriter.write("Lowest aspiration levels");
				if (stageResults.get(0) instanceof BigDecimalStageResult) {
					firstBigDecimalLowestAspLevels = ((BigDecimalStageResult) stageResults
							.get(0)).getLowestBigDecimalAspirationLevel();
				} else {
					firstDoubleLowestAspLevels = stageResults.get(0)
							.getLowestAspirationLevel();
				}
			}

			outWriter.newLine();
			outWriter.write(";;");

			if (commonConfig.isCalculateAbsoluteActionOccurances()) {
				Set<ActorAction> actorActions = firstAbsOccurances.keySet();
				for (ActorAction action : actorActions) {
					outWriter.write(action.getActionName() + ";");
					absOccActorActionsList.add(action);
				}
			}
			if (commonConfig.isCalculateRelativeActionOccurances()) {
				Set<ActorAction> actorActions = null;
				if (firstRelDoubleOccurances != null)
					actorActions = firstRelDoubleOccurances.keySet();
				else if (firstRelBigDecimalOccurances != null)
					actorActions = firstRelBigDecimalOccurances.keySet();
				if (actorActions != null) {
					for (ActorAction action : actorActions) {
						outWriter.write(action.getActionName() + ";");
						relOccActorActionsList.add(action);
					}
				}
			}
			if (commonConfig.isCalculateLowestAspirationLevels()) {
				outWriter.write(";");
			}
		}
	}

	private void writeDelimiters(int number, BufferedWriter outWriter)
			throws IOException {
		for (int i = 0; i < number; i++) {
			outWriter.write(";");
		}
	}

	private void writeConfigurationDetails(AbstractEngineConfig config,
			BufferedWriter outWriter) throws IOException {
		outWriter.write("Used algorithm: ;" + config.getName());
		outWriter.newLine();
		if (config instanceof DFSkeepTreeEngineConfig) {
			outWriter.write("Keeping tree in memory:;"
					+ ((DFSkeepTreeEngineConfig) config).isSaveTreeStructure());
			outWriter.newLine();
			outWriter.write("Keeping action names:;"
					+ ((DFSkeepTreeEngineConfig) config).isSaveActionNames());
			outWriter.newLine();
			outWriter.write("Keeping aspiration levels:;"
					+ ((DFSkeepTreeEngineConfig) config)
							.isSaveAspirationLevels());
		}
		if (config instanceof DFSmatrixHighPrecEngineConfig) {
			outWriter.write("Precision decimal places:;"
					+ ((DFSmatrixHighPrecEngineConfig) config)
							.getNumberOfDecimalPlaces());
		}
	}

}
