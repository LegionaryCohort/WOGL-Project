package simulation;

public enum Constants
{
	/**
	 * EXAMPLE
	 * Contains 6 numerical values and 1 boolean value.
	 * <p>
	 * Numerical values:<br>
	 * 0 - colour-R<br>
	 * 1 - colour-G<br>
	 * 2 - colour-B<br>
	 * 3 - nourishment-value<br>
	 * 4 - growth speed<br>
	 * 5 - size
	 * <p>
	 * Boolean values: <br>
	 * 0 - collision detection enabled
	 */
	PLANT(new double[] { 35, 175, 75, 3.5, 0.45, 1.3 }, new boolean[] { false }),

	/**
	 * EXAMPLE
	 * Contains 3 numerical values and 0 boolean values.
	 * <p>
	 * Numerical values:<br>
	 * 0 - colour-R<br>
	 * 1 - colour-G<br>
	 * 2 - colour-B
	 */
	ROCK(new double[] { 100, 100, 100 }),

	/**
	 * Contains 3 numerical values and 0 boolean values.
	 * <p>
	 * Numerical values:<br>
	 * 0 - number of genes<br>
	 * 1 - minimum value of a gene<br>
	 * 2 - maximum value of a gene
	 */
	DNA(new double[] { 50, -1000, 1000 }),

	/**
	 * Contains 2 numerical values and 0 boolean values.
	 * <p>
	 * Numerical values:<br>
	 * 0 - minimum value of a link<br>
	 * 1 - maximum value of a link
	 */
	NEURAL_CELL(new double[] { -1.0, 1.0 }),

	/**
	 * Contains 4 numerical values and 0 boolean values.
	 * <p>
	 * Numerical valus:<br>
	 * 0 - number of input-cells<br>
	 * 1 - number of cells of the 1. interlayer<br>
	 * 2 - number of cells of the 2. interlayer<br>
	 * 3 - number of output-cells
	 */
	BRAIN(new double[] { 30, 25, 25, 20 }),

	/**
	 * Contains 3 numerical value and 0 boolean values.
	 * <p>
	 * Numerical values:<br>
	 * 0 - number of genes (size(1), color(3), meat/plant-efficiency(2))<br>
	 * 1 - minimum size<br>
	 * 2 - maximum size<br>
	 */
	BODY(new double[] { 5, 100, 300 });

	private double[]	doubleValues;
	private boolean[]	booleanValues;

	private Constants(final double[] pDoubleValues)
	{
		doubleValues = pDoubleValues;
	}

	private Constants(final boolean[] pBooleanValue)
	{
		booleanValues = pBooleanValue;
	}

	private Constants(final double[] pDoublevalue, final boolean[] pBooleanValue)
	{
		doubleValues = pDoublevalue;
		booleanValues = pBooleanValue;
	}

	/**
	 * Looks up the numerical value saved in this constant at the specified index.<br>
	 * Returns the value as an integer if it is found.<br>
	 * Throws an IllegalArgumentException if there are no numerical values for this constant.<br>
	 * Throws an IndexOutOfBoundsException if the specified index is greater than or equal to the number of values.
	 * 
	 * @param pIndex
	 *            the index of the value in the array of numerical values
	 * @return the value at the specified index, if not found an exception is thrown (see below)
	 * @throws IllegalAccessException
	 *             if there are no numerical values for this constant
	 * @throws IndexOutOfBoundsException
	 *             if the index specified is greater than or equal to the number of values
	 */
	public int getIntValue(int pIndex) throws IllegalAccessException
	{
		if (doubleValues == null) throw new IllegalAccessException("This constant has no numerical values!");
		return (int) doubleValues[pIndex];
	}

	/**
	 * Looks up the numerical value saved in this constant at the specified index.<br>
	 * Returns the value as a double if it is found.<br>
	 * Throws an IllegalArgumentException if there are no numerical values for this constant.<br>
	 * Throws an IndexOutOfBoundsException if the specified index is greater than or equal to the number of values.
	 * 
	 * @param pIndex
	 *            the index of the value in the array of numerical values
	 * @return the value at the specified index, if not found an exception is thrown (see below)
	 * @throws IllegalAccessException
	 *             if there are no numerical values for this constant
	 * @throws IndexOutOfBoundsException
	 *             if the index specified is greater than or equal to the number of values
	 */
	public double getDoubleValue(int pIndex) throws IllegalAccessException
	{
		if (doubleValues == null) throw new IllegalAccessException("This constant has no numerical values!");
		return doubleValues[pIndex];
	}

	/**
	 * Looks up the boolean value saved in this constant at the specified index.<br>
	 * Returns the value if it is found.<br>
	 * Throws an IllegalArgumentException if there are no boolean values for this constant.<br>
	 * Throws an IndexOutOfBoundsException if the specified index is greater than or equal to the number of values.
	 * 
	 * @param pIndex
	 *            the index of the value in the array of numerical values
	 * @return the value at the specified index, if not found an exception is thrown (see below)
	 * @throws IllegalAccessException
	 *             if there are no numerical values for this constant
	 * @throws IndexOutOfBoundsException
	 *             if the index specified is greater than or equal to the number of values
	 */
	public boolean getBooleanValue(int pIndex) throws IllegalAccessException
	{
		if (booleanValues == null) throw new IllegalAccessException("This constant has no boolean values!");
		return booleanValues[pIndex];
	}
}