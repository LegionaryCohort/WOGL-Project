package util;

public class BoundedValue
{
	private double		value;
	private double[]	boundary		= null;
	private boolean[]	activeBoundary	= null;

	/**
	 * Creates the object with two boundaries.
	 * 
	 * @param pValue
	 *            the actual value
	 * @param pMinValue
	 *            the lower boundary
	 * @param pMaxValue
	 *            the upper boundary
	 */
	public BoundedValue(double pValue, double pMinValue, double pMaxValue)
	{
		this.value = pValue;
		this.boundary = new double[] { pMinValue, pMaxValue };
		this.activeBoundary = new boolean[] { true, true };
		checkBounds();
	}

	/**
	 * Creates the object without any boundaries.
	 * 
	 * @param pValue
	 *            the new value
	 */
	public BoundedValue(double pValue)
	{
		this.value = pValue;
		this.boundary = new double[] { 0.0, 0.0 };
		this.activeBoundary = new boolean[] { false, false };
	}

	/**
	 * Sets new boundaries.
	 * 
	 * @param pBoundaries
	 *            the array contains the values of the boundaries
	 * @param pActiveBoundaries
	 *            the array contains whether a boundary is active or not
	 * @return the value of how much the current value went over the boundaries.
	 */
	public double setBoundaries(double[] pBoundaries, boolean[] pActiveBoundaries)
	{
		if (pBoundaries == null || pBoundaries.length != 2 || pActiveBoundaries == null || pActiveBoundaries.length != 2)
		{
			throw new IllegalArgumentException("pBoundaries and pActiveBoundaries must have length 2!");
		}
		this.boundary = pBoundaries;
		this.activeBoundary = pActiveBoundaries;
		return checkBounds();
	}

	/**
	 * Creates the object with eventually two boundaries.<br>
	 * The first index of each array contains information about the lower boundary.<br>
	 * The second index of each array contains information about the upper boundary.
	 * <p>
	 * The first array pBoundaries contains the actual values of the boundaries. <br>
	 * The second array pActiveBoundaries contains booleans whether a boundary is active or not. <br>
	 * 0 => the boundary is inactive.<br>
	 * 1 => the boundary is active.
	 * 
	 * @param pValue
	 *            the actual value
	 * @param pBoundaries
	 *            the array contains the values of the boundaries
	 * @param pActiveBoundaries
	 *            the array contains whether a boundary is active or not
	 */
	public BoundedValue(double pValue, double[] pBoundaries, boolean[] pActiveBoundaries)
	{
		if (pBoundaries == null || pBoundaries.length != 2 || pActiveBoundaries == null || pActiveBoundaries.length != 2)
		{
			throw new IllegalArgumentException("pBoundaries and pActiveBoundaries must have length 2!");
		}
		this.value = pValue;
		this.boundary = pBoundaries;
		this.activeBoundary = pActiveBoundaries;
		checkBounds();
	}

	/**
	 * Checks whether the current value is between its bounds or not.<br>
	 * If not it returns the value of how much the current value went over a boundary.<br>
	 * If it went over the lower boundary, this value is negative.<br>
	 * If it went over the upper boundary, this value is positive.
	 * 
	 * @return the value of how much the current value went over a boundary.
	 */
	public double checkBounds()
	{
		double overstep = 0.0;
		if (activeBoundary[0] && value < boundary[0])
		{
			overstep = value - boundary[0];
			value = boundary[0];
		}
		else if (activeBoundary[1] && value > boundary[1])
		{
			overstep = value - boundary[1];
			value = boundary[1];
		}
		return overstep;
	}

	/**
	 * Changes the current value without checking if the changed value went over a boundary.<br>
	 * changed value = current value + given value.
	 * 
	 * @param pValue
	 *            the given value.
	 */
	public void uncheckedChange(double pValue)
	{
		value += pValue;
	}

	/**
	 * Changes the current value and returns the value of how much the changed value went over a boundary.<br>
	 * changed value = current value + given value.
	 * 
	 * @param pValue
	 *            the given value.
	 * @return the value of how much the changed value went over a boundary.
	 */
	public double checkedChange(double pValue)
	{
		value += pValue;
		return checkBounds();
	}

	/**
	 * Overwrites the current value without checking if the changed value went over a boundary.
	 * 
	 * @param pValue
	 *            the new value
	 */
	public void setValue(double pValue)
	{
		value = pValue;
	}

	/**
	 * Returns the current value.
	 * 
	 * @return the current value
	 */
	public double getValue()
	{
		return value;
	}

}
