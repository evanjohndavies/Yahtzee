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
				
		for ( UserScoreDisplay object: scores){
			
			if(gameElement.equals(object.getScoreDisplayObject())){
				return(true);
			}
			
		}
		return(false);
	}
	
	public Integer getIndexScoreObjectSelected(GObject gameElement){
		
		for ( UserScoreDisplay object: scores){
			
			if(gameElement.equals(object.getScoreDisplayObject())){
				return(object.getIndex());
			}
			
		}
		return(null);
	}
	
	
	public void setDipslayObject(int indexID, int score){
		
			scores[indexID].setScoreDisplay(score);
	}
	
	
	private void createPlayerGrid(){
				
		for (int i=0; i< MAX_SCORES; i++){
			scores[i] = new UserScoreDisplay();
			scores[i].setIndex(i);
			displayElements.add(scores[i].getScoreDisplayObject());
		}
		
	}
	

	
	private static final int MAX_SCORES = 17;

	
	private ArrayList<GObject> displayElements = new ArrayList<GObject>();
	private UserScoreDisplay[] scores = new UserScoreDisplay[MAX_SCORES];
	

}
