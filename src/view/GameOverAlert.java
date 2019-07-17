package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverAlert extends Stage
{
	public static final int NEWGAME = 0;
	public static final int CLOSE = 1;
	private ButtonListener listener;
	
	public GameOverAlert(String title, String message)
	{
		super();
	
		getIcons().add(new Image("/images/icon.png"));
		initModality(Modality.APPLICATION_MODAL);
		setTitle(title);
		setMinWidth(500);

		Label messageLabel = new Label(message);

		Button newGame = new Button("New Game");
		Button closeButton = new Button("Close");
		newGame.setDefaultButton(true); // making it respond to Enter press
		closeButton.setCancelButton(true); // making it respond to esc press
		newGame.setMinWidth(80);
		closeButton.setMinWidth(80);
		
		newGame.setOnAction(e ->
		{
			if(listener != null)
			{
				listener.buttonPressed(NEWGAME);
			}
			close();
		});
		closeButton.setOnAction(e -> 
		{
			if(listener != null)
			{
				listener.buttonPressed(CLOSE);
			}
			close();
		});
		VBox layout = new VBox(20);
		//layout.getStylesheets().add("/css/style.css");
		layout.setPadding(new Insets(50, 10, 20, 10));
		layout.getStylesheets().add("/css/style.css");
		HBox hBox = new HBox(10);
		hBox.setPadding(new Insets(0, 0, 10, 10));
		hBox.setAlignment(Pos.BASELINE_RIGHT);
		hBox.getChildren().addAll(newGame, closeButton);
		layout.getChildren().addAll(messageLabel, hBox);
		layout.setAlignment(Pos.BASELINE_CENTER);
		
		Scene scene = new Scene(layout);
		setScene(scene);
		// can't use showAndWait() method with animation. instead the Made the App listen to this window
		//alertWindow.showAndWait();
		show();
	}
	public void setListener(ButtonListener listener)
	{
		this.listener = listener;
	}

}
