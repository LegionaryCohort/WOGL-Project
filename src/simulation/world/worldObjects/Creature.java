package simulation.world.worldObjects;

import simulation.Constants;
import simulation.world.worldObjects.neural.Brain;
import util.Pair;

public class Creature
{
	private Body	body	= null;
	private Brain	brain	= null;
	private DNA		dna		= null;
	private int		id;
	private int		generationNumber;

	public Creature(int pId) throws IllegalAccessException
	{
		body = new Body(0.0, 0.0, new Pair<Double, Double>(0.0, 0.0));
		brain = new Brain();
		dna = new DNA(Constants.DNA.getIntValue(0), Constants.DNA.getDoubleValue(1), Constants.DNA.getDoubleValue(2));
		this.id = pId;
		this.generationNumber = 0;
	}
}