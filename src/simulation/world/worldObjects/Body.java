package simulation.world.worldObjects;

import simulation.Constants;
import simulation.world.WorldObject;
import util.BoundedValue;
import util.Pair;

public class Body extends WorldObject
{
	private BoundedValue			life;
	private BoundedValue			food;
	private double					size;
	private Pair<Double, Double>	velocity;
	private Pair<Double, Double>	position;
	private java.awt.Color			color;
	private double					meatEfficiency;
	private double					plantEfficiency;

	public Body(double pLife, double pFood, Pair<Double, Double> pPosition)
	{
		super();
		this.life = new BoundedValue(pLife, new double[] { 0.0, 1.0 }, new boolean[] { true, false });
		this.food = new BoundedValue(pFood, new double[] { 0.0, 1.0 }, new boolean[] { true, false });
		this.velocity = new Pair<Double, Double>(0.0, 0.0);
		this.position = pPosition;
	}

	/**
	 * Calculates movement of the body.
	 */
	public void move()
	{
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
	}

	/**
	 * Calculates the number of genes that are necessary for the construction of a Body (method: construct(DNA)).
	 * <p>
	 * part of the body: number of needed genes<br>
	 * size: 1<br>
	 * color: 3<br>
	 * meat/plant-efficiency: 2
	 * 
	 * @return number of needed genes
	 */
	public int getNumberOfNeededGenes() throws IllegalAccessException
	{
		return Constants.BODY.getIntValue(0);
	}

	/**
	 * Constructs the body with genes that determine about size, color and meat/plant-efficiency.
	 * 
	 * @param pDNA
	 *            the given DNA
	 * @throws IllegalArgumentException
	 *             if the number of genes in pDNA don't match the number of needed genes of the body
	 */
	public void construct(DNA pDNA) throws IllegalAccessException
	{
		if (pDNA.getNumberOfGenes() != this.getNumberOfNeededGenes())
		{
			throw new IllegalArgumentException("length mismatch: number of genes (" + pDNA.getNumberOfGenes() + ") must match number of needed genes (" + this.getNumberOfNeededGenes() + ")");
		}
		else
		{
			// size
			this.size = pDNA.getNormedGene(0, Constants.BODY.getDoubleValue(1), Constants.BODY.getDoubleValue(2));
			this.life.setBoundaries(new double[] { 0.0, this.size }, new boolean[] { true, true });
			this.food.setBoundaries(new double[] { 0.0, this.size }, new boolean[] { true, true });

			// color
			this.color = new java.awt.Color((int) Math.round(pDNA.getNormedGene(1, 0, 255)), (int) Math.round(pDNA.getNormedGene(2, 0, 255)), (int) Math.round(pDNA.getNormedGene(3, 0, 255)));

			// meat/plant-efficiency
			meatEfficiency = pDNA.getNormedGene(4, 0, 1);
			plantEfficiency = 1 - meatEfficiency;
			if (meatEfficiency > Constants.BODY.getDoubleValue(3))
			{
				meatEfficiency = Constants.BODY.getDoubleValue(4);
				plantEfficiency = 0;
			}
			else if (plantEfficiency > Constants.BODY.getDoubleValue(3))
			{
				meatEfficiency = 0;
				plantEfficiency = Constants.BODY.getDoubleValue(4);
			}
		}
	}
}
