package cbdt.model;

public interface ICBDTSimulationEngine {
	
	/**
	 * @param parameters
	 * @return
	 */
	public CBDTSimulationResult computeSimulation(CBDTSimulationParameters parameters);

}
