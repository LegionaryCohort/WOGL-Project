package simulation.world.worldObjects;

import simulation.world.worldObjects.neural.Brain;

public class Creature
{
	private Body	body	= null;
	private Brain	brain	= null;
	private DNA		dna		= null;
	private int		id;
	private int		generationNumber;

	public Creature(int pId)
	{
		body = new Body();
		brain = new Brain();
		dna = new DNA(20, -10, 10);
		this.id = pId;
		this.generationNumber = 0;
	}
}