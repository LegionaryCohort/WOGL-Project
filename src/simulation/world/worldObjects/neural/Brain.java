package simulation.world.worldObjects.neural;

import simulation.Constants;
import util.Pair;
import util.UtilMethods;

public class Brain extends NeuralLayerNet
{

	public Brain() throws IllegalAccessException
	{
		super(Constants.BRAIN.getIntValue(0), Constants.BRAIN.getIntValue(1), Constants.BRAIN.getIntValue(2), Constants.BRAIN.getIntValue(3));
	}

	@Override
	public double[] interpretOutput()
	{
		double[] output = this.getOutput();
		double[] results = new double[11];
		Pair<Integer, Double> groupRes;
		// index: group (outputindex)
		// 0: move to front/back (0,1)
		// 1: move to right/left (2,3)
		// 2: turn counterclockwise/clockwise (4,5)
		for (int i = 0; i < 3; i++)
		{
			groupRes = UtilMethods.getMaximum(output[i * 2], output[i * 2 + 1]);
			if (groupRes.getY() > 0.0)
			{
				results[i] = groupRes.getX() + 1;
			}
			else results[i] = 0;
		}

		// index: group (outputindex)
		// 3: Eat-Action (6)
		// 4: Attack-Action (7)
		// 5: Split-Action (8)
		for (int i = 3; i < 6; i++)
		{
			if (output[i + 3] > 0.0)
			{
				results[i] = 1;
			}
			else results[i] = 0;
		}

		// remembering-cells.
		double absMax = (UtilMethods.getMaximum(Math.abs(output[9]), Math.abs(output[10]), Math.abs(output[11]), Math.abs(output[12]), Math.abs(output[13]))).getY();
		for (int i = 6; i < 11; i++)
		{
			results[i] = output[i + 3] / absMax;
		}
		return results;
	}

	@Override
	public void setInput(double[] pInput)
	{
		// TODO Auto-generated method stub

	}

}