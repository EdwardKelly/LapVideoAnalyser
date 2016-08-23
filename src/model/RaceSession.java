package model;

import javafx.scene.media.Media;

public class RaceSession {
	private Media media; // video of race session
	
	public RaceSession(Media media){
		this.media = media;
	}
	
	public Media getMedia(){
		return media;
	}

}
