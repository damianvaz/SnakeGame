package view;

import app.Snake;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Window extends Stage
{
	private Layout layout = new Layout();
	private Scene scene;

	public Window(String title)
	{
		setTitle(title);
		scene = new Scene(layout);
		setScene(scene);
		
		show();
	}

	public void updateWindow(Snake snake)
	{
		layout.updateTiles(snake);
	}
	public void setListener(EventHandler<KeyEvent> e)
	{
		scene.addEventHandler(KeyEvent.KEY_PRESSED, e);
	}

	public void makeFood(int row, int col)
	{
		layout.makeFood(row, col);
	}

	public void cleanTiles()
	{
		layout.cleanTiles();
	}
}
