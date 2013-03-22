package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.control.simulation.algorithm.dfsmatrix.AbstractInitFactory;
import cbdt.control.simulation.algorithm.dfsmatrix.VirtualNodeContentVisitor;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;

public class BigDecimalVirtualNodeContentVisitor extends VirtualNodeContentVisitor{

	private BigDecimal[] expectedUtilities;
	private BigDecimalChildNodeContentGenerator childContentGenerator;
	private BigDecimalNodeContent[][] contentsMatrix;

	public BigDecimalVirtualNodeContentVisitor(Parameters parameters,
			CommonEngineConfiguration commonConfig, BigDecimalNodeContent[][] contentsMatrix,
			AbstractInitFactory factory, BigDecimal[] emptyExpectedUtilities,
			BigDecimal[][] absoluteActionOccurances, IProgressMonitor monitor) {
		super(parameters, commonConfig, null, factory, null,
				absoluteActionOccurances, monitor);
		this.contentsMatrix = contentsMatrix;
		this.expectedUtilities = emptyExpectedUtilities;
		childContentGenerator = new BigDecimalChildNodeContentGenerator(outcomeMatrix,
				new BigDecimal(parameters.getWeightingFactorAlpha()));
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
			BigDecimalNodeContent parentContent = contentsMatrix[stage][index];
			actionSelector.computeSelectedActions(selectedActionsIndices, parentContent);
			int numberOfSelectedActions = getNumberOfSelectedActions();
			
			BigDecimal multiActionProbability = big_one.divide(new BigDecimal(numberOfSelectedActions), mathContext);
			
			BigDecimal childrensExpectedUtilitySum = new BigDecimal(0);
			int childIndex = 0;
			int childrenStage = stage + 1;
			for (int i = 0; i < numberOfSelectedActions; i++) {
				int selectedActionIndex = selectedActionsIndices[i];

				if (leafStage==null && (commonConfig.isCalculateAbsoluteActionOccurances()
						|| commonConfig.isCalculateRelativeActionOccurances()))
					absoluteActionOccurances[stage][selectedActionIndex] = absoluteActionOccurances[stage][selectedActionIndex]
							.add(big_one, mathContext);
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					BigDecimalNodeContent childContent = contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex);

					if(leafStage==null){
						childrensExpectedUtilitySum = childrensExpectedUtilitySum.add(
								childContent.probabilityProduct.multiply(
								((BigDecimalActorActionOutcome)(outcomeMatrix[selectedActionIndex][outcomeIndex])).utility, 
								NodeVisitor.mathContext), NodeVisitor.mathContext);
					}
					childIndex++;
				}
			}
			if(leafStage==null)
				expectedUtilities[stage] = expectedUtilities[stage].add(childrensExpectedUtilitySum, NodeVisitor.mathContext);

			for (int i = 0; i < childIndex; i++) {
				visitRecursively(childrenStage, i);
			}
			if(progessStage!=null && progessStage==stage)
				monitor.worked(1);
		}

	}

	
}
