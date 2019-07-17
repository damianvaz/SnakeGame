package view;

import app.Snake;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Layout extends GridPane
{
	private Label[][] tiles = new Label[40][40];
	// Seting colors here instead of in css to load faster, since its an animation
	private String headColorCss = " #62d6e8;";
	private String taleColorCss = "#b45bcf;";
	private String tileBackground = "#626483;";
	
	public Layout()
	{
		setHgap(1);
		setVgap(1);
		getStylesheets().add("/css/style.css");
		makeTiles();
	}

	private void makeTiles()
	{
		for (int i = 0; i < 40; i++)
		{
			for (int j = 0; j < 40; j++)
			{
				tiles[i][j] = new Label();
				tiles[i][j].setMinSize(10, 10);
				tiles[i][j].setMaxSize(10, 10);
				add(tiles[i][j], j, i);
			}
		}
	}

	public void updateTiles(Snake snake)
	{
		int headRow = snake.getHead()[0];
		int headCol = snake.getHead()[1];
		int[][] tale = snake.getTale();		

		// Update head
		tiles[headRow][headCol].setStyle("-fx-background-color: " + headColorCss);
		// Update tale
		for (int i = 0; i < snake.getTaleSize(); i++)
		{
			int taleX = tale[i][0];
			int taleY = tale[i][1];

			tiles[taleX][taleY].setStyle("-fx-background-color: " + taleColorCss);
		}
		int row = snake.getPreviousLastTalePiece()[0];
		int col = snake.getPreviousLastTalePiece()[1];
		if (snake.isAteFood())
		{
			tiles[row][col].setStyle("-fx-background-color: " + taleColorCss);
		}
		else
		{
			tiles[row][col].setStyle("-fx-background-color: " + tileBackground);
		}
		
	}
	public void makeFood(int row, int col)
	{
		tiles[row][col].setStyle("-fx-background-color: red");
	}

	public void cleanTiles()
	{
		for (int i = 0; i < 40; i++)
		{
			for (int j = 0; j < 40; j++)
			{
				tiles[i][j].setStyle("-fx-background-color: " + tileBackground);
			}
		}
	}
}
