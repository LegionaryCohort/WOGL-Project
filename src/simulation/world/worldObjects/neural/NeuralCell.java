package simulation.world.worldObjects.neural;

/**
 * @author Jarato (Paul David Fährmann)
 */

import simulation.world.worldObjects.DNA;
import util.Pair;

public class NeuralCell
{
	private Pair<NeuralCell, Double>[]	link	= null;
	private double						memory	= 0.0;

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

	public double getMemory()
	{
		return memory;
	}

	public void setMemory(double pMemory)
	{
		this.memory = pMemory;
	}

	public void addMemory(double pMemory)
	{
		this.memory += pMemory;
	}

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
			memory = 0;
		}
	}

	public void construct(DNA pDNA)
	{
		if (pDNA.getNumberOfGenes() != getNumberOfNeededGenes())
		{
			throw new IllegalArgumentException("length mismatch: number of linkvalues (" + pDNA.getNumberOfGenes() + ") has to match number of links (" + getNumberOfNeededGenes() + ")!");
		}
		else
		{
			double[] normed = pDNA.normTo(-1, +1);
			for (int i = 0; i < link.length; i++)
			{
				link[i].setY(normed[i]);
			}
		}
	}
}