package cbdt.control.persistence.results;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class ResultsPersistenceManager implements IResultsPersistenceManager {

	private Map<ActorAction, BigDecimal> firstAbsOccurances;
	private Map<ActorAction, Double> firstRelDoubleOccurances;
	private Map<ActorAction, BigDecimal> firstRelBigDecimalOccurances;

	@Override
	public void saveResultToFile(String filepath, Result result, AbstractEngineConfiguration config) throws IOException {
		BufferedWriter outWriter = null;
		outWriter = new BufferedWriter(new FileWriter(filepath));
		outWriter.write("Stage;Expected utility");

		firstAbsOccurances = null;
		firstRelDoubleOccurances = null;
		firstRelBigDecimalOccurances = null;

		List<StageResult> stageResults = result.getStageResults();
		writeTitleLines(outWriter, stageResults);

		outWriter.newLine();

		writeData(outWriter, stageResults);
		
		outWriter.newLine();
		writeConfigurationDetails(config, outWriter);
		outWriter.close();
	}

	private void writeData(BufferedWriter outWriter,
			List<StageResult> stageResults) throws IOException {
		for (StageResult stageResult : stageResults) {
			if(stageResult instanceof BigDecimalStageResult){
				BigDecimalStageResult bigDecimalStageResult = (BigDecimalStageResult)stageResult;
				outWriter.write(bigDecimalStageResult.getStage() + ";"
						+ bigDecimalStageResult.getExpectedBigDecimalUtility());
			}else{
				outWriter.write(stageResult.getStage() + ";"
						+ stageResult.getExpectedUtility());
			}
			if (firstAbsOccurances != null) {
				Set<Entry<ActorAction, BigDecimal>> entries = stageResult
						.getAbsoluteActionOccurances().entrySet();
				for (Entry<ActorAction, BigDecimal> entry : entries) {
					outWriter.write(";" + entry.getValue());
				}
			}
			if (firstRelDoubleOccurances != null) {
				Set<Entry<ActorAction, Double>> entries = stageResult
						.getRelativeActionOccurances().entrySet();
				for (Entry<ActorAction, Double> entry : entries) {
					outWriter.write(";" + entry.getValue());
				}
			}else
				if(firstRelBigDecimalOccurances != null){
					Set<Entry<ActorAction, BigDecimal>> entries = ((BigDecimalStageResult)stageResult)
							.getRelativeBigDecimalActionOccurances().entrySet();
					for (Entry<ActorAction, BigDecimal> entry : entries) {
						outWriter.write(";" + entry.getValue());
					}
				}
			

			outWriter.newLine();
		}
	}

	private void writeTitleLines(BufferedWriter outWriter,
			List<StageResult> stageResults) throws IOException {
		if (stageResults != null && !stageResults.isEmpty()) {
			firstAbsOccurances = stageResults.get(0).getAbsoluteActionOccurances();
			if (firstAbsOccurances != null) {
				outWriter.write(";Absolute occurances");
			}
			firstRelDoubleOccurances = stageResults.get(0).getRelativeActionOccurances();
			if (firstRelDoubleOccurances != null) {
				writeAbsOccurancesDelimiters(outWriter);
			}else{
				if(stageResults.get(0) instanceof BigDecimalStageResult)
					firstRelBigDecimalOccurances = ((BigDecimalStageResult)stageResults.get(0)).getRelativeBigDecimalActionOccurances();
				writeAbsOccurancesDelimiters(outWriter);
			}
			outWriter.newLine();
			outWriter.write(";");

			if (firstAbsOccurances != null) {
				Set<ActorAction> actorActions = firstAbsOccurances.keySet();
				for (ActorAction action : actorActions) {
					outWriter.write(";" + action.getActionName());
				}
			}
			Set<ActorAction> actorActions = null;
			if (firstRelDoubleOccurances != null)
				actorActions = firstRelDoubleOccurances.keySet();
			else if (firstRelBigDecimalOccurances != null) 
				actorActions = firstRelBigDecimalOccurances.keySet();
			if(actorActions != null){
				for (ActorAction action : actorActions) {
					outWriter.write(";" + action.getActionName());
				}
			}
		}
	}

	private void writeAbsOccurancesDelimiters(BufferedWriter outWriter) throws IOException {
		if(firstAbsOccurances != null)
			for (int i = 0; i < firstAbsOccurances.keySet().size(); i++)
				outWriter.write(";");
		else
			outWriter.write(";");
		outWriter.write("Relative occurances");
	}

	private void writeConfigurationDetails(AbstractEngineConfiguration config,
			BufferedWriter outWriter) throws IOException {
		outWriter.write("Used algorithm: ;"+config.getName());
		outWriter.newLine();
		if(config instanceof DFSkeepTreeEngineConfig){
			outWriter.write("Keeping tree in memory:;"+((DFSkeepTreeEngineConfig)config).isSaveTreeStructure());
			outWriter.newLine();
			outWriter.write("Keeping action names:;"+((DFSkeepTreeEngineConfig)config).isSaveActionNames());
			outWriter.newLine();
			outWriter.write("Keeping aspiration levels:;"+((DFSkeepTreeEngineConfig)config).isSaveAspirationLevels());
		}
		if(config instanceof DFSmatrixHighPrecEngineConfig){
			outWriter.write("Precision decimal places:;"+((DFSmatrixHighPrecEngineConfig)config).getNumberOfDecimalPlaces());
		}
	}

}
