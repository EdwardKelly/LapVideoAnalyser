package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.RaceSession;

public class MainPane extends BorderPane {
	private List<RaceSessionPane> sessionPanes; // session panes held in centre pane
	private double rspHeight;
	private HBox centrePane;
	public MainPane() {
		sessionPanes = new ArrayList<RaceSessionPane>();
		rspHeight = Main.windowHeight/sessionPanes.size();
		centrePane = new HBox();
		this.setCenter(centrePane);
	}
	
	/**
	 *  Creates new race session pane and adds to the others sessions
	 * @param newSession
	 */
	public void addSession(RaceSession newSession){
		RaceSessionPane newrsp = new RaceSessionPane(newSession);
		sessionPanes.add(newrsp);
		for (RaceSessionPane rsp : sessionPanes){
			rsp.setPrefHeight(rspHeight);
		}
		centrePane.getChildren().add(newrsp);		
		
	}
	
}
