package util;

/**
 * @author Jarato (Paul David Fährmann)
 * 
 * @param <K>
 *            The X-type
 * @param <V>
 *            The Y-type
 */
public class Pair<K, V>
{

	private K	x;
	private V	y;

	public Pair(K sX, V sY)
	{
		this.x = sX;
		this.y = sY;
	}

	public K getX()
	{
		return x;
	}

	public V getY()
	{
		return y;
	}

	public void setX(K pX)
	{
		this.x = pX;
	}

	public void setY(V pY)
	{
		this.y = pY;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Pair other = (Pair) obj;
		if (x == null)
		{
			if (other.x != null) return false;
		}
		else if (!x.equals(other.x)) return false;
		if (y == null)
		{
			if (other.y != null) return false;
		}
		else if (!y.equals(other.y)) return false;
		return true;
	}

}
