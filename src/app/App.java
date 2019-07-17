package app;

import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.GameOverAlert;
import view.Window;

public class App extends Application
{
	private Window mainWindow;
	private Snake snake;
	private int foodX;
	private int foodY;
	private Timeline timeline;
	
	public static void main(String[] args)
	{
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		mainWindow = new Window("SNAKE by Damian Vaz");
		mainWindow.getIcons().add(new Image("/images/icon.png"));
		primaryStage = mainWindow;
		snake = new Snake();
		
		makeNewFood();
		mainWindow.setListener(e ->
		{
			if (e.getCode() == KeyCode.UP)
			{
				if(snake.getDirection() != Snake.DOWN)
				{
					snake.changeDirection(Snake.UP);
				}
			}
			else if(e.getCode() == KeyCode.DOWN)
			{
				if(snake.getDirection() != Snake.UP)
				{
					snake.changeDirection(Snake.DOWN);
				}
			}
			else if(e.getCode() == KeyCode.RIGHT)
			{
				if(snake.getDirection() != Snake.LEFT)
				{
					snake.changeDirection(Snake.RIGHT);
				}
			}
			else if(e.getCode() == KeyCode.LEFT)
			{
				if(snake.getDirection() != Snake.RIGHT)
				{
					snake.changeDirection(Snake.LEFT);
				}
			}
		});

		mainWindow.updateWindow(snake);

		timeline = new Timeline(new KeyFrame(Duration.millis(60), e ->
		{
			snake.updateSnake();
			if(checkSnakeRanIntoItSelf())
			{
				timeline.stop();
				String message = "You've made the tale: " + snake.getTaleSize() + " squares big"; 
				GameOverAlert alert = new GameOverAlert("GAME OVER", message);
				alert.setListener(choice ->
				{
					if(choice == GameOverAlert.NEWGAME)
					{
						snake.resetSnake();
						mainWindow.cleanTiles();
						mainWindow.updateWindow(snake);
						makeNewFood();
						timeline.play();
					}
					else
					{
						mainWindow.close();
						System.exit(0);
					}
				});
				
			}
			// check if food is eaten
			int[] snakeHead = snake.getHead();
			if (snakeHead[0] == foodX && snakeHead[1] == foodY)
			{
				makeNewFood();
				snake.Ate();
			}
			mainWindow.updateWindow(snake);
			if (snake.isAteFood())
			{
				snake.setAteFood(false);
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	private boolean checkSnakeRanIntoItSelf()
	{
		int[] head = snake.getHead();
		int[][] tale = snake.getTale();
		int taleSize = snake.getTaleSize();
		boolean isOnItself = false;
		
		for(int i = 0; i < taleSize; i++)
		{
			if (tale[i][0] == head[0] && tale[i][1] == head[1])
			{
				isOnItself = true;
			}
		}
		return isOnItself;
	}

	private void makeNewFood()
	{
		// TODO test if new food doesnt spawn in the snake
		Random random = new Random();
		
		// Testing to see if new food location spawned on the snake body
		boolean isInside = false;
		int taleSize = snake.getTaleSize();
		
		// testing if food spawned in its head
		do
		{
			foodX = random.nextInt(39);
			foodY = random.nextInt(39);
			isInside = false;
		
			if(snake.getHead()[0] == foodX && snake.getHead()[1] == foodY)
			{
				isInside = true;
			}
			for(int i = 0; i < taleSize; i++)
			{
				if(snake.getTale()[i][0] == foodX && snake.getTale()[i][1] == foodY)
				{
					isInside = true;
				}
			}
		}  while(isInside);
		
		mainWindow.makeFood(foodX, foodY);
	}
}
