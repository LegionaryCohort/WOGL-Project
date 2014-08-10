package simulation.world.worldObjects;

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

	public Body(double pLife, double pFood, Pair<Double, Double> pPosition)
	{
		super();
		this.life = new BoundedValue(pLife, new double[] { 0.0, 0.0 }, new boolean[] { true, false });
		this.food = new BoundedValue(pFood);
		this.velocity = new Pair<Double, Double>(0.0, 0.0);
		this.position = pPosition;
	}
}
