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
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class ResultsPersistenceManager implements IResultsPersistenceManager {

	@Override
	public void saveResultToFile(String filepath, Result result, AbstractEngineConfiguration config) throws IOException {
		BufferedWriter outWriter = null;
		outWriter = new BufferedWriter(new FileWriter(filepath));
		outWriter.write("Stage;Expected utility");

		Map<ActorAction, BigDecimal> firstAbsOccurnces = null;
		Map<ActorAction, Double> firstRelOccurnces = null;

		List<StageResult> stageResults = result.getStageResults();
		if (stageResults != null && !stageResults.isEmpty()) {
			firstAbsOccurnces = stageResults.get(0)
					.getAbsoluteActionOccurances();
			if (firstAbsOccurnces != null) {
				outWriter.write(";Absolute occurances");
			}
			firstRelOccurnces = stageResults.get(0)
					.getRelativeActionOccurances();
			if (firstRelOccurnces != null) {
				if(firstAbsOccurnces != null)
					for (int i = 0; i < firstAbsOccurnces.keySet().size(); i++)
						outWriter.write(";");
				else
					outWriter.write(";");
				outWriter.write("Relative occurances");
			}
			outWriter.newLine();
			outWriter.write(";");

			if (firstAbsOccurnces != null) {
				Set<ActorAction> actorActions = firstAbsOccurnces.keySet();
				for (ActorAction action : actorActions) {
					outWriter.write(";" + action.getActionName());
				}
			}
			if (firstRelOccurnces != null) {
				Set<ActorAction> actorActions = firstRelOccurnces.keySet();
				for (ActorAction action : actorActions) {
					outWriter.write(";" + action.getActionName());
				}
			}
		}

		outWriter.newLine();

		for (StageResult stageResult : stageResults) {
			outWriter.write(stageResult.getStage() + ";"
					+ stageResult.getExpectedUtility());
			if (firstAbsOccurnces != null) {
				Set<Entry<ActorAction, BigDecimal>> entries = stageResult
						.getAbsoluteActionOccurances().entrySet();
				for (Entry<ActorAction, BigDecimal> entry : entries) {
					outWriter.write(";" + entry.getValue());
				}
			}
			if (firstRelOccurnces != null) {
				Set<Entry<ActorAction, Double>> entries = stageResult
						.getRelativeActionOccurances().entrySet();
				for (Entry<ActorAction, Double> entry : entries) {
					outWriter.write(";" + entry.getValue());
				}
			}

			outWriter.newLine();
		}
		
		outWriter.newLine();
		writeConfigurationDetails(config, outWriter);
		outWriter.close();
	}

	private void writeConfigurationDetails(AbstractEngineConfiguration config,
			BufferedWriter outWriter) throws IOException {
		outWriter.write("Used algorithm: ;"+config.getName());
		if(config instanceof DFSkeepTreeEngineConfig){
			outWriter.write("Keeping tree in memory:;"+((DFSkeepTreeEngineConfig)config).isSaveTreeStructure());
			outWriter.write("Keeping action names:;"+((DFSkeepTreeEngineConfig)config).isSaveActionNames());
			outWriter.write("Keeping aspiration levels:;"+((DFSkeepTreeEngineConfig)config).isSaveAspirationLevels());
		}
		if(config instanceof DFSmatrixHighPrecEngineConfig){
			outWriter.write("Number of used required places:;"+((DFSmatrixHighPrecEngineConfig)config).getNumberOfDecimalPlaces());
		}
	}

}
