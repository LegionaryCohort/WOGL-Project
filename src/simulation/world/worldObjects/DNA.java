package simulation.world.worldObjects;

import java.util.Random;

/**
 * DNA is an array of values, which can't be less than minValue or greater than maxValue.
 * <p>
 * This class is used to simulate the DNA of living Objects.
 * 
 * @author Jarato (Paul David Fährmann)
 * @version 1.0
 */

public class DNA
{
	private double[]	genes;
	private double		minValue;
	private double		maxValue;

	/**
	 * Returns the number of values contained in this DNA.
	 * <p>
	 * If genes == null, the method will return 0.
	 * 
	 * @return the number of values
	 */
	public int getNumberOfGenes()
	{
		if (genes != null)
		{
			return genes.length;
		}
		return 0;
	}

	/**
	 * Returns the whole array of gene-values
	 * 
	 * @return the array of gene-values
	 */
	public double[] getGenes()
	{
		return genes;
	}

	/**
	 * Overwrites the whole array of gene-values with the given array (pGenes)
	 * 
	 * @param pGenes
	 *            the given array
	 */
	public void setGenes(double[] pGenes)
	{
		this.genes = pGenes;
	}

	/**
	 * Returns the maximum value a gene can take.
	 * 
	 * @return the actual maximum value
	 */
	public double getMaxValue()
	{
		return maxValue;
	}

	/**
	 * Overwrites the maximum value a gene can take.
	 * 
	 * @param pMaxValue
	 *            the new maximum value.
	 */
	public void setMaxValue(double pMaxValue)
	{
		this.maxValue = pMaxValue;
	}

	/**
	 * Creates the object with an empty value-array (every value is 0.0).
	 * 
	 * @param pNumberOfGenes
	 *            number of genes of this DNA-Set
	 * @param pMinValue
	 *            the minimum value
	 * @param pMaxValue
	 *            the maximum value
	 */
	public DNA(int pNumberOfGenes, double pMinValue, double pMaxValue)
	{
		super();
		this.genes = new double[pNumberOfGenes];
		this.minValue = pMinValue;
		this.maxValue = pMaxValue;
	}

	/**
	 * Creates the object with the given value-array (= pGenes)
	 * <p>
	 * It don't check, if any value of the given value-array is less than minValue or greater than maxValue.
	 * 
	 * @param pGenes
	 *            the given value-array
	 * @param pMinValue
	 *            the minimum value
	 * @param pMaxValue
	 *            the maximum value
	 */
	public DNA(double[] pGenes, double pMinValue, double pMaxValue)
	{
		super();
		this.genes = pGenes;
		this.minValue = pMinValue;
		this.maxValue = pMaxValue;
	}

	/**
	 * Returns the minimum value a gene can take.
	 * 
	 * @return the actual minimum value.
	 */
	public double getMinValue()
	{
		return minValue;
	}

	/**
	 * Overwrites the minimum value a gene can take.
	 * 
	 * @param pMinValue
	 *            the new minimum value.
	 */
	public void setMinValue(double pMinValue)
	{
		this.minValue = pMinValue;
	}

	/**
	 * Extracts a sequence/part out of the actual DNA-set and returns a new object containing these values.
	 * <p>
	 * The extracted part includes this.genes[pFrom] to this.genes[pFrom+pLength-1].
	 * 
	 * @param pFrom
	 *            start index
	 * @param pLength
	 *            number of extracted values
	 * @return a DNA-sequence from this.genes[pFrom...pFrom+pLength-1]
	 */
	public DNA getSequence(int pFrom, int pLength)
	{
		double[] seq = new double[pLength];
		System.arraycopy(this.genes, pFrom, seq, 0, pLength);
		return new DNA(seq, this.minValue, this.maxValue);
	}

	/**
	 * Mutates the actual DNA-set with a percentage (pPercentage) and a mutation-strength (pStrength).
	 * <p>
	 * Every value is capped by the maximum and minimum value (maxValue and minValue).
	 * 
	 * @param pPercentage
	 *            The chance of a gene to mutate
	 * @param pStrength
	 *            The potential strength of the mutation
	 * @throws IllegalArgumentException
	 *             if pPercentage is less than 0.0 or greater than 1.0.
	 */
	public void mutate(double pPercentage, double pStrength)
	{
		if ((pPercentage < 0.0) || (pPercentage > 1.0))
		{
			throw new IllegalArgumentException("The chance of a gene to mutate must be between 0.0 and 1.0, both included! actual: " + pPercentage);
		}
		Random rnd = new Random();
		for (int i = 0; i < this.genes.length; i++)
		{
			if (rnd.nextDouble() < pPercentage)
			{
				this.genes[i] += rnd.nextDouble() * (maxValue - minValue) - (maxValue - minValue) / 2.0 * pStrength * 2;
				if (this.genes[i] < minValue) this.genes[i] = minValue;
				else if (this.genes[i] > maxValue) this.genes[i] = maxValue;
			}
		}
	}

	/**
	 * Recombines this DNA-set and the given DNA-set (pDNA) with the xor-recombination.
	 * <p>
	 * The xor-recombination takes the value for the new genes from this DNA-set xor the given DNA-set.
	 * <p>
	 * For every gene, there is a 50% - chance to get the value of this DNA-set and a 50% - chance to get the value of the given DNA-set.
	 * 
	 * @param pDNA
	 *            the given DNA-set.
	 * @return the recombined DNA-set.
	 * @throws IllegalArgumentException
	 *             if the length of the genes-arrays or the minimum or the maximum value don't match.
	 */
	public DNA recombineWith(DNA pDNA)
	{
		if ((this.genes.length != pDNA.genes.length) || (this.minValue != pDNA.minValue) || (this.maxValue != pDNA.maxValue))
		{
			throw new IllegalArgumentException("The (number of genes, minValue, maxValue) didn't match: (" + this.genes.length + ", " + this.minValue + ", " + this.maxValue + ") to ("
					+ pDNA.genes.length + ", " + pDNA.minValue + ", " + pDNA.maxValue + ")");
		}
		else
		{
			Random rnd = new Random();

			double[] newGenes = new double[this.genes.length];
			for (int i = 0; i < newGenes.length; i++)
			{
				if (rnd.nextBoolean())
				{
					newGenes[i] = this.genes[i];
				}
				else
				{
					newGenes[i] = pDNA.genes[i];
				}
			}
			return new DNA(newGenes, this.minValue, this.maxValue);
		}
	}

	/**
	 * norms all values to new minimum and maximum values and returns a double-array.
	 * <p>
	 * The actual minimum and maximum values of the DNA-set will not be changed!
	 * 
	 * @param pMin
	 *            new minimum value.
	 * @param pMax
	 *            new maximum value.
	 * @return a new double-array with the normed values of this DNA-set.
	 */
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