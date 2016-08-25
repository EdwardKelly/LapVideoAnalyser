package view;

import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import model.RaceSession;

public class RaceSessionPane extends BorderPane{
	private RaceSession raceSession;
	public RaceSessionPane(RaceSession raceSession){
		this.raceSession = raceSession;
		Media media = raceSession.getMedia();
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		MediaView mediaView = new MediaView(mediaPlayer);
		this.setCenter(mediaView);
	}
	
}
