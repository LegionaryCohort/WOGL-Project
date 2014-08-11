package simulation.world.worldObjects.neural;

import simulation.Constants;

public class Brain extends NeuralLayerNet
{

	public Brain() throws IllegalAccessException
	{
		super(Constants.BRAIN.getIntValue(0), Constants.BRAIN.getIntValue(1), Constants.BRAIN.getIntValue(2), Constants.BRAIN.getIntValue(3));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void interpretOutput()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setInput()
	{
		// TODO Auto-generated method stub

	}

}