package view;

import java.util.ArrayList;
import java.util.List;

import application.Main;
import javafx.geometry.Insets;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.RaceSession;

public class MainPane extends BorderPane {
	private List<RaceSessionPane> sessionPanes; // session panes held in centre pane
	private double rspWidth;
	private HBox centrePane;
	public MainPane() {
		sessionPanes = new ArrayList<RaceSessionPane>();
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
		rspWidth = Main.windowWidth/sessionPanes.size();
		for (RaceSessionPane rsp : sessionPanes){
			rsp.setWidths(rspWidth);
		}
		centrePane.getChildren().add(newrsp);		
		
	}
	
}
