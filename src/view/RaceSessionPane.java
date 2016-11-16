package view;

import application.Main;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import model.RaceSession;

public class RaceSessionPane extends BorderPane{
	private RaceSession raceSession;
	private MediaView mediaView;
	private VBox mediaPlayerGUI;
	public RaceSessionPane(RaceSession raceSession){
		this.raceSession = raceSession;
		Media media = raceSession.getMedia();
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaView = new MediaView(mediaPlayer);
		mediaPlayerGUI = new VBox();
		createMediaPlayerGUI();
		this.setCenter(mediaPlayerGUI);
		LapsPane lapPane = new LapsPane();
		lapPane.setPrefHeight(Main.windowHeight-mediaView.getFitHeight());
		this.setBottom(lapPane);
	}
	  
	private void createMediaPlayerGUI(){
		HBox mediaControls = new HBox();
		Button playButton = new Button(">");
		playButton.setOnAction((ActionEvent e)->{
			mediaView.getMediaPlayer().play();
		});
		Button pauseButton = new Button("||");
		pauseButton.setOnAction((ActionEvent e)->{
			mediaView.getMediaPlayer().pause();
		});
		Slider slider = new Slider();
		slider.setMin(0);
		slider.setMax(mediaView.getMediaPlayer().getMedia().getDuration().toSeconds());
		
		mediaControls.getChildren().addAll(playButton, pauseButton, slider);
		mediaPlayerGUI.getChildren().addAll(mediaView,mediaControls);
		
	}
	public void setWidths(double value){
		mediaView.setFitWidth(value-10);
		mediaPlayerGUI.setPrefWidth(value);
		this.setPrefWidth(value);
	}
}
