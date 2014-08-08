package simulation.world.worldObjects;

/**
 * @author Jarato (Paul David Fährmann)
 * 
 */

public class DNA
{
	private double[]	genes;
	private double		maxValue;
	private double		minValue;

	public int getNumberOfGenes()
	{
		if (genes != null)
		{
			return genes.length;
		}
		return 0;
	}

	public double[] getGenes()
	{
		return genes;
	}

	public void setGenes(double[] genes)
	{
		this.genes = genes;
	}

	public double getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(double maxValue)
	{
		this.maxValue = maxValue;
	}

	public double getMinValue()
	{
		return minValue;
	}

	public void setMinValue(double minValue)
	{
		this.minValue = minValue;
	}

	public double[] normTo(double pMin, double pMax)
	{
		if (pMin > pMax)
		{
			throw new IllegalArgumentException("min-value must be less than max-value!");
		}
		else
		{
			double[] ret = new double[genes.length];
			for (int i = 0; i < genes.length; i++)
			{
				ret[i] = (genes[i] - minValue) / (maxValue - minValue) * (pMax - pMin) + pMin;
			}
			return ret;
		}
	}
}