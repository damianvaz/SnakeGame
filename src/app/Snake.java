package app;

public class Snake
{
	private int[][] tale;
	private int taleSize;
	private int direction;
	private int[] headPosition;
	private int[] previousLastTalePiece = new int[2];
	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;
	public boolean ateFood = false;
	
	public Snake()
	{
		taleSize = 3;
		headPosition = new int[] {10,10};
		tale = new int[][] { {10, 7}, {10,8}, {10,9}};

		direction = RIGHT;
	}
	public void updateSnake()
	{
		// Store the last tale piece
		previousLastTalePiece[0] = tale[0][0];
		previousLastTalePiece[1] = tale[0][1];
		// store the last head Position
		int storedHeadPos[] = new int[2];
		storedHeadPos[0] = headPosition[0];
		storedHeadPos[1] = headPosition[1];
		//update Tale
		for (int i = 0; i < tale.length - 1; i++)
		{
			tale[i] = tale[i+1];
		}
		tale[taleSize - 1] = storedHeadPos;		
		
		// Update headPosition
		switch(direction)
		{
			case(UP):
			{
				if(headPosition[0] > 0)
				{
					headPosition[0]--;					
				}
				else
				{
					headPosition[0] = 39;
				}
				break;
			}
			case(DOWN):
			{
				if(headPosition[0] < 39)
				{
					headPosition[0]++;					
				}
				else
				{
					headPosition[0] = 0;
				}
				break;
			}
			case(LEFT):
			{
				if(headPosition[1] > 0)
				{
					headPosition[1]--;					
				}
				else
				{
					headPosition[1] = 39;
				}
				break;
			}
			case(RIGHT):
			{
				if(headPosition[1] < 39)
				{
					headPosition[1]++;
				}
				else
				{
					headPosition[1] = 0;
				}
				break;
			}
		}
	}
	public int[] getHead()
	{
		return this.headPosition;
	}
	public int[][] getTale()
	{
		return tale;
	}

	public int getTaleSize()
	{
		return taleSize;
	}
	public int getDirection()
	{
		return this.direction;
	}
	public int[] getPreviousLastTalePiece()
	{
		return previousLastTalePiece;
	}
	public void changeDirection(int direction)
	{
		this.direction = direction;
	}
	
	public void Ate()
	{
		taleSize++;
		int[][] newTale = new int[taleSize][2];
		newTale[0] = previousLastTalePiece;
		for (int i = 0; i < taleSize - 1; i++)
		{
			int row = tale[i][0];
			int col = tale[i][1];
			
			newTale[i + 1][0] = row;
			newTale[i + 1][1] = col;
		}
		tale = new int[taleSize][2];
		tale = newTale;
		ateFood = true;
	}
	public boolean isAteFood()
	{
		return ateFood;
	}
	public void setAteFood(boolean ateFood)
	{
		this.ateFood = ateFood;
	}
	public void resetSnake()
	{
		taleSize = 3;
		headPosition = new int[] {10,10};
		tale = new int[][] { {10, 7}, {10,8}, {10,9}};

		direction = RIGHT;
	}
	
}
