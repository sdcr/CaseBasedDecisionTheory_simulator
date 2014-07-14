package cbdt.model.config.engine;

/**
 * This class models the engine configuration for the DFSmatrix algorithm. There
 * are currently no settings which can be set for an execution with this
 * algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixEngineConfig extends AbstractEngineConfig {

	@Override
	public String getName() {
		return "DFS with matrix structure";
	}

}
