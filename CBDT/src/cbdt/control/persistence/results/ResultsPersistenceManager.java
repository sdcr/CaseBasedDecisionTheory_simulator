package cbdt.control.persistence.results;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cbdt.model.parameters.ActorAction;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class ResultsPersistenceManager implements IResultsPersistenceManager {

	@Override
	public void saveResultToFile(String filepath, Result result) throws IOException {
		BufferedWriter outWriter = null;
		outWriter = new BufferedWriter(new FileWriter(filepath));
		outWriter.write("Stage;Expected utility");

		Map<ActorAction, Integer> firstAbsOccurnces = null;
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
				Set<Entry<ActorAction, Integer>> entries = stageResult
						.getAbsoluteActionOccurances().entrySet();
				for (Entry<ActorAction, Integer> entry : entries) {
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
		outWriter.close();
	}

}
