package model;

import javafx.scene.media.Media;

public class RaceSession {
	private Media media; // video of race session
	private String sessionName;
	
	public RaceSession(Media media, String sessionName){
		this.media = media;
		this.sessionName = sessionName;
	}
	
	public Media getMedia(){
		return media;
	}
	
	public String getSessionName(){
		return sessionName;
	}

}
