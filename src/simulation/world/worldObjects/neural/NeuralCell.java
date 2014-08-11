package simulation.world.worldObjects.neural;

/**
 * A neural Cell has a memory and is linked to a bunch of other cells.
 * @author Jarato (Paul David Fährmann)
 * @version 1.0
 */

import simulation.Constants;
import simulation.world.worldObjects.DNA;
import util.Pair;

public class NeuralCell
{
	private Pair<NeuralCell, Double>[]	link	= null;
	private double						memory	= 0.0;

	/**
	 * Constructor, which connects this cell with every cell in the pConnectedCells - array.
	 * 
	 * @param pConnectedCells
	 *            contains every NeuralCell, which is connected with this
	 */
	public NeuralCell(NeuralCell... pConnectedCells)
	{
		if (pConnectedCells != null)
		{
			this.link = new Pair[pConnectedCells.length];
			for (int i = 0; i < pConnectedCells.length; i++)
			{
				link[i].setX(pConnectedCells[i]);
			}
		}
	}

	/**
	 * returns the stored value of the cell
	 * 
	 * @return the stored value of the cell
	 */
	public double getMemory()
	{
		return memory;
	}

	/**
	 * Overwrites the stored value of the cell with pMemory
	 * 
	 * @param pMemory
	 *            the new stored value
	 */
	public void setMemory(double pMemory)
	{
		this.memory = pMemory;
	}

	/**
	 * Adds a value (pMemory) to the stored value of the cell.
	 * <p>
	 * new stored value = old stored value + pMemory
	 * <p>
	 * 
	 * @param pMemory
	 *            the value, which is added to the stored value
	 */
	public void addMemory(double pMemory)
	{
		this.memory += pMemory;
	}

	/**
	 * Calculates the number of genes that are necessary for the construction of the cell (method: construct(DNA)).
	 * <p>
	 * The number of needed genes matches the number of linked cells, because every link has a value/weight.
	 * <p>
	 * 
	 * If link == null, the method returns 0
	 * 
	 * @return number of needed genes ( = number of linked cells)
	 */
	public int getNumberOfNeededGenes()
	{
		if (link != null)
		{
			return link.length;
		}
		return 0;
	}

	/**
	 * Pushes the stored value (= memory) to the connected cells.
	 * <p>
	 * Thereafter, the stored value is reset to 0.
	 */
	public void push()
	{
		if (this.memory != 0.0)
		{
			for (int i = 0; i < link.length; i++)
			{
				link[i].getX().addMemory(memory * link[i].getY());
			}
			memory = 0.0;
		}
	}

	/**
	 * Overwrites every link-value/weight with values between -1 and 1 depending on the given DNA (pDNA).
	 * <p>
	 * Therefore all values in the DNA will be normed to -1 and 1.
	 * <p>
	 * The minValue of the given DNA is calculated to be -1 and the maxValue to be 1.
	 * <p>
	 * The old values of the links will be lost.
	 * 
	 * @param pDNA
	 *            the given DNA
	 * @throws IllegalArgumentException
	 *             if the number of genes in pDNA don't match the number of needed genes of the cell
	 */
	public void construct(DNA pDNA) throws IllegalAccessException
	{
		if (pDNA.getNumberOfGenes() != getNumberOfNeededGenes())
		{
			throw new IllegalArgumentException("length mismatch: number of linkvalues (" + pDNA.getNumberOfGenes() + ") has to match number of links (" + getNumberOfNeededGenes() + ")!");
		}
		else
		{
			double[] normed = pDNA.normTo(Constants.NEURAL_CELL.getDoubleValue(0), Constants.NEURAL_CELL.getDoubleValue(1));
			for (int i = 0; i < link.length; i++)
			{
				link[i].setY(normed[i]);
			}
		}
	}
}