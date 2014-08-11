package util;

public final class UtilMethods
{
	/**
	 * calculates the index and the value of the maximum of the specified array (pArray)<br>
	 * If the length of the array is 0, the method returns null
	 * 
	 * @param pArray
	 *            the specified array
	 * @return a pair (index of maximum, value of maximum)
	 */
	public static Pair<Integer, Double> getMaximum(double... pArray)
	{
		if (pArray.length == 0)
		{
			return null;
		}
		double max = pArray[0];
		int index = 0;
		for (int i = 1; i < pArray.length; i++)
		{
			if (pArray[i] > max)
			{
				max = pArray[i];
				index = i;
			}
		}
		return new Pair<Integer, Double>(index, max);
	}
}
