package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.RaceSession;
import view.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


public class Main extends Application {
	private MainPane mainPane;
	private Stage stage;
	public static final double windowWidth = Screen.getPrimary().getBounds().getWidth();
	public static final double windowHeight = Screen.getPrimary().getBounds().getHeight()-70;
	
	@Override
	public void start(Stage stage) {
		mainPane = new MainPane();
		mainPane.setPrefSize(windowWidth, windowHeight);
		Scene scene = new Scene(mainPane);
		setUp();
		stage.setScene(scene);
		stage.show();
	}
	private RaceSession newSession; 
	/**
	 * Sets up initial state
	 */
	private void setUp(){
		Button addSession = new Button("Add Session");
		addSession.setOnAction((ActionEvent e)->{
				newSession = createNewSession();
				mainPane.addSession(newSession);
		});
		mainPane.setTop(addSession);
		
		
	}
	
	private Media media;
	/**
	 * Creates new race session
	 */
	private RaceSession createNewSession(){
		// create dialog for user to add race session
		Dialog dialog = new Dialog();
		dialog.setTitle("Add New Race Session");
		GridPane pane = new GridPane();
		pane.setPrefWidth(300);
		
		// create nodes
		Label sessionNameLabel = new Label("Session Name");
		Label videoURLLabel = new Label("No file selected");
		TextField sessionName = new TextField();
		Button openVideo = new Button("Choose Video");
		openVideo.setOnAction((ActionEvent e)->{
			media = chooseMediaFile();
			if (media != null){
				videoURLLabel.setText("File: "+shortenURI(media.getSource()));
			}
		});
		// add nodes to grid pane
		pane.setHgap(10);
		pane.setVgap(10);
		pane.add(sessionNameLabel, 1, 1);
		pane.add(sessionName, 2, 1);
		pane.add(videoURLLabel, 1, 2);
		pane.add(openVideo, 2, 2);
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
		dialog.getDialogPane().setContent(pane);
		dialog.showAndWait();
		// create and return new race session based on user entry
		return new RaceSession(media, sessionName.getText());
		
	}
	/**
	 * Shortens the given URI string so that it is only the file name
	 * @param str file directory to be shortened
	 * @return file name, null if not a directory
	 */
	public String shortenURI(String str){
		String shortStr;
		for (int i = str.length()-2; i>=0; i--){
			if (str.substring(i,i+1).equals("/")){
				shortStr = str.substring(i+1);
				if (shortStr.length()>10)shortStr = shortStr.substring(0, 10)+"...";
				return shortStr;
			}
		}
		return null;
	}
	/**
	 * Allows user to chose a media file
	 * @return 
	 */
	public Media chooseMediaFile(){
		File file = new FileChooser().showOpenDialog(stage);
		if (file != null){
			return new Media(file.toURI().toString());
		}
		return null;
	}
	
	/**
	 * Shows an error dialog
	 * @param title title of dialog box
	 * @param msg message to be displayed
	 */
	public void showError(String title, String msg){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();	
	}
	/**
	 * Shows an information dialog
	 * @param title title of dialog box
	 * @param msg message to be displayed
	 */
	public void showInfo(String title, String msg){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
