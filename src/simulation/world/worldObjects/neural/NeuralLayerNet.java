package simulation.world.worldObjects.neural;

import simulation.world.worldObjects.DNA;

/**
 * It's a simulation of a neural network with different layers.
 * 
 * @author Jarato (Paul David Fährmann)
 * @version 1.0
 */

public abstract class NeuralLayerNet
{
	private NeuralCell[][]	layer	= null;

	/**
	 * Creates every layer and every cell and connects every cell in a layer with every cell in the next layer.
	 * 
	 * @param pLayerCells
	 *            number of cells in each layer
	 */
	public NeuralLayerNet(int... pLayerCells)
	{
		layer = new NeuralCell[pLayerCells.length][];
		int i = layer.length - 1;
		layer[i] = new NeuralCell[pLayerCells[i]];
		createAndConnectCells(layer[i], null);
		for (i -= 1; i >= 0; i--)
		{
			layer[i] = new NeuralCell[pLayerCells[i]];
			createAndConnectCells(layer[i], layer[i + 1]);
		}
	}

	/**
	 * Returns the input-layer of the net.
	 * <p>
	 * This is the layer with index: 0
	 * 
	 * @return input-layer
	 */
	protected NeuralCell[] getInputLayer()
	{
		return layer[0];
	}

	/**
	 * Returns the memory-values of output-layer of the net. <br>
	 * This is the layer with index: layer.length - 1
	 * 
	 * @return memory-values of the output-layer
	 */
	protected double[] getOutput()
	{
		double[] result = new double[layer[layer.length - 1].length];
		for (int i = 0; i < result.length; i++)
		{
			result[i] = layer[layer.length - 1][i].getMemory();
		}
		return result;
	}

	/**
	 * Calculates the number of genes that are necessary for the construction of the net (method: construct(DNA)).
	 * <p>
	 * The number of needed genes matches the number of linked cells of added up from every cell in the net.
	 * <p>
	 * The last layer is ignored because no cell in the last layer is connected to any cell.
	 * 
	 * @return number of needed genes
	 */
	public int getNumberOfNeededGenes()
	{
		int sum = 0;
		for (int i = 0; i < layer.length - 1; i++)
		{
			for (int j = 0; j < layer[i].length; j++)
			{
				sum += layer[i][j].getNumberOfNeededGenes();
			}
		}
		return sum;
	}

	/**
	 * Overwrites every link-value/weight in every cell of every layer with values between -1 and 1 depending on the given DNA (pDNA).
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
	 *             if the number of genes in pDNA don't match the number of needed genes of the net
	 */
	public void construct(DNA pDNA) throws IllegalAccessException
	{
		if (pDNA.getNumberOfGenes() != this.getNumberOfNeededGenes())
		{
			throw new IllegalArgumentException("length mismatch: number of genes (" + pDNA.getNumberOfGenes() + ") must match number of needed genes (" + this.getNumberOfNeededGenes() + ")");
		}
		else
		{
			int pos = 0;
			for (int i = 0; i < layer.length - 1; i++)
			{
				for (int j = 0; j < layer[i].length; j++)
				{
					layer[i][j].construct(pDNA.getSequence(pos, layer[i + 1].length));
					pos += layer[i + 1].length;
				}
			}
		}
	}

	/**
	 * Creates every NeuralCell in pCurrentLayer and connects them with every Cell in pConnectedLayer.
	 * 
	 * @param pCurrentLayer
	 *            the created cells.
	 * @param pConnectedLayer
	 *            the connected cells.
	 */
	private void createAndConnectCells(NeuralCell[] pCurrentLayer, NeuralCell[] pConnectedLayer)
	{
		for (int i = 0; i < pCurrentLayer.length; i++)
		{
			pCurrentLayer[i] = new NeuralCell(pConnectedLayer);
		}
	}

	/**
	 * Calculates every input through the interlayer to the output-layer.
	 */
	public void calculateNet()
	{
		for (int k = 0; k < getOutputLayer().length; k++)
		{
			getOutputLayer()[k].setMemory(0.0);
		}
		for (int i = 0; i < layer.length - 1; i++)
		{
			for (int j = 0; j < layer[i].length; j++)
			{
				layer[i][j].push();
			}
		}
	}

	public abstract double[] interpretOutput();

	public abstract void setInput(double[] pInput);
}