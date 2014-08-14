package simulation.world;

import util.Pair;

/**
 * 
 * @author Jarato (Paul David Fährmann)
 * @version 1.0
 */
public abstract class WorldObject
{
	private Pair<Double, Double>	position;
	private double					radius;

	/**
	 * Returns the position of the worldobject
	 * 
	 * @return
	 */
	public Pair<Double, Double> getPosition()
	{
		return position;
	}

	/**
	 * overwrites the current position with the new specified position
	 * 
	 * @param pPosition
	 *            the new position
	 */
	public void setPosition(Pair<Double, Double> pPosition)
	{
		this.position = pPosition;
	}

	/**
	 * Returns the radius of the worldobject
	 * 
	 * @return the radius of the worldobject
	 */
	public double getRadius()
	{
		return radius;
	}

	/**
	 * Overwrites the current position with the new specified radius
	 * 
	 * @param pRadius
	 *            the new radius
	 */
	public void setRadius(double pRadius)
	{
		this.radius = pRadius;
	}

	/**
	 * Creates the object with a position and a radius
	 * 
	 * @param position
	 *            the position of the object
	 * @param radius
	 *            the radius of the object
	 */
	public WorldObject(Pair<Double, Double> position, double radius)
	{
		super();
		this.position = position;
		this.radius = radius;
	}

	/**
	 * Moves the object with the specified vector.<br>
	 * The new position.x = current position.x + specified vector.x <br>
	 * The new position.y = current position.y + specified vector.y
	 * 
	 * @param pVector
	 *            the specified move-vector
	 */
	public void move(Pair<Double, Double> pVector)
	{
		if (pVector != null)
		{
			position.setX(position.getX() + pVector.getX());
			position.setY(position.getY() + pVector.getY());
		}
	}

	/**
	 * Returns whether this object collides with the specified worldobject or not.<br>
	 * If the specified worldobject (pWorldObject) is null, the method returns false.
	 * 
	 * @param pWorldObject
	 *            the specified worldobject.
	 * @return whether this object collides with the specified worldobject or not
	 */
	public boolean collide(WorldObject pWorldObject)
	{
		if (pWorldObject == null)
		{
			return false;
		}
		return Math.sqrt(Math.pow(pWorldObject.position.getX() - this.position.getX(), 2) + Math.pow(pWorldObject.position.getY() - this.position.getY(), 2)) < (pWorldObject.radius + this.radius);
	}
}