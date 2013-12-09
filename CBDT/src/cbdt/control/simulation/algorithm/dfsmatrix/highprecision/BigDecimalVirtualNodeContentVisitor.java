package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.control.simulation.algorithm.dfsmatrix.VirtualNodeContentVisitor;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.Parameters;

public class BigDecimalVirtualNodeContentVisitor extends VirtualNodeContentVisitor{

	private BigDecimalChildNodeContentGenerator childContentGenerator;
	private BigDecimalSimulationState bigDecimalSimState;

	public BigDecimalVirtualNodeContentVisitor(Parameters parameters,
			CommonConfig commonConfig, IProgressMonitor monitor,
			BigDecimalSimulationState simState) {
		super(parameters,commonConfig,monitor,null);
		bigDecimalSimState = simState;
		
		BigDecimalInitFactory factory = new BigDecimalInitFactory(parameters, commonConfig);
		outcomeMatrix = factory.getOutcomeMatrix();
		childContentGenerator = new BigDecimalChildNodeContentGenerator(outcomeMatrix,
				new BigDecimal(parameters.getAspirationLevelDecrementFactor()), 
				parameters.isIncrementingAspirationLevelSparsely(), 
				new BigDecimal(parameters.getAspirationLevelIncrement()));
		int numberOfActions = parameters.getActorActions().size();
		actionSelector = new BigDecimalActionSelector(numberOfActions);
	}

	@Override
	protected void visitRecursively(int stage, int index) throws InterruptedException {
		if(monitor.isCanceled())
			throw new InterruptedException();
		if(leafStage!=null && leafStage==stage)
			numberOfLeafs++;
		else if (stage < commonConfig.getNumberOfRequestedExpectedUtilityValues()) {
			BigDecimalNodeContent parentContent = bigDecimalSimState.contentsMatrix[stage][index];
			actionSelector.computeSelectedActions(selectedActionsIndices, parentContent);
			int numberOfSelectedActions = getNumberOfSelectedActions();
			
			BigDecimal multiActionProbability = big_one.divide(new BigDecimal(numberOfSelectedActions), mathContext);
			
			BigDecimal childrensExpectedUtilitySum = new BigDecimal(0);
			int childIndex = 0;
			int childrenStage = stage + 1;
			for (int i = 0; i < numberOfSelectedActions; i++) {
				int selectedActionIndex = selectedActionsIndices[i];

				if (leafStage==null && commonConfig.isCalculateAbsoluteActionOccurances())
//						|| commonConfig.isCalculateRelativeActionOccurances()))
					bigDecimalSimState.absoluteActionOccurances[childrenStage][selectedActionIndex] = bigDecimalSimState.absoluteActionOccurances[childrenStage][selectedActionIndex]
							.add(big_one, mathContext);
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					BigDecimalNodeContent childContent = bigDecimalSimState.contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex, childrenStage);

					if(leafStage==null){
						childrensExpectedUtilitySum = childrensExpectedUtilitySum.add(
								childContent.probabilityProduct.multiply(
								((BigDecimalActorActionOutcome)(outcomeMatrix[selectedActionIndex][outcomeIndex])).utility, 
								NodeVisitor.mathContext), NodeVisitor.mathContext);
						if(commonConfig.isCalculateLowestAspirationLevels())
							updateLowestAspirationUtilities(childContent.aspirationLevel, childrenStage);
					}
					if (leafStage==null && commonConfig.isCalculateRelativeActionOccurances()){
						bigDecimalSimState.relativeActionOccurances[childrenStage][selectedActionIndex] = 
								bigDecimalSimState.relativeActionOccurances[childrenStage][selectedActionIndex].add(childContent.probabilityProduct, NodeVisitor.mathContext);
					}
					
					childIndex++;
				}
			}
			if(leafStage==null)
				bigDecimalSimState.expectedUtilities[childrenStage] = bigDecimalSimState.expectedUtilities[childrenStage].add(childrensExpectedUtilitySum, NodeVisitor.mathContext);

			for (int i = 0; i < childIndex; i++) {
				visitRecursively(childrenStage, i);
			}
			if(progessStage!=null && progessStage==stage)
				monitor.worked(1);
		}

	}

	private void updateLowestAspirationUtilities(BigDecimal aspirationLevel,
			int stage) {
		if(bigDecimalSimState.lowestAspirationLevels[stage].compareTo(aspirationLevel)==1){
			bigDecimalSimState.lowestAspirationLevels[stage] = aspirationLevel;
		}
	}

	
}
