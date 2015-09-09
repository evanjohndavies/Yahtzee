package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import acm.graphics.*;


public class PlayerGraphics {
	
	public PlayerGraphics(){
		
		createPlayerGrid();
		
	}
	
	
	public ArrayList<GObject> getPlayerGridObjects(){
		
		return(displayElements);
		
	}
	
	public boolean checkScoreObjectSelected(GObject gameElement){
		
		GObject temp;
		
		for ( UserScoreDisplay object: scores){
			
			temp = object.getScoreDisplayObject();
			if(gameElement.equals(object.getScoreDisplayObject())){
				System.out.println("clicked on score object");
				// TO-DO
				return(true);
			}
			
		}
		return(false);
	}
	
	
	private void createPlayerGrid(){
				
		for (int i=0; i< MAX_SCORES; i++){
			scores[i] = new UserScoreDisplay();
			scores[i].setIndex(i);
			displayElements.add(scores[i].getScoreDisplayObject());
		}
		
	}
	

	
	
	private static final double CELL_HEIGHT = 30;
	private static final int MAX_SCORES = 17;

	
	private ArrayList<GObject> displayElements = new ArrayList<GObject>();
	private UserScoreDisplay[] scores = new UserScoreDisplay[MAX_SCORES];
	

}
