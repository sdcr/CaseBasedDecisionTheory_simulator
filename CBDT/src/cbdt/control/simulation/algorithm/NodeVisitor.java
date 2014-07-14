package cbdt.control.simulation.algorithm;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * An abstract class for the visitors of the different algorithms.
 * 
 * @author Stephan da Costa Ribeiro
 *
 */
public abstract class NodeVisitor {

	public static BigDecimal big_one = new BigDecimal(1);
	public static MathContext mathContext = new MathContext(30);

}
