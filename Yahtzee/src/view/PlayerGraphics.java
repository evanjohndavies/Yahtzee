package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Model.Constants;
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
	
	public void updateDipslayObject(int indexID, int score){
		
		scores[indexID].updateScoreDisplay(score);
	}
	
	
	public boolean getCellProtectedState(int index){
		
		// bounds checking in score array
		if (index < MAX_SCORES){
			return(scores[index].getProtectedState());
		}
		return(true);
		
	}
	
	public void setHighlightUser(boolean state){
		
		for (UserScoreDisplay o: scores ){	
			o.setHightlight(state);
		}	
	}
	
	private void createPlayerGrid(){
				
		for (int i=0; i< MAX_SCORES; i++){
			scores[i] = new UserScoreDisplay();
			scores[i].setIndex(i);
			displayElements.add(scores[i].getScoreDisplayObject());
		}
		
		//  set total fields as protected
		scores[Constants.UPPER_SCORE_SUBTOTAL_ENUM].setProtected();
		scores[Constants.UPPER_SCORE_BONUS_ENUM].setProtected();
		scores[Constants.LOWER_SCORE_SUBTOTAL_ENUM].setProtected();
		scores[Constants.TOTAL_ENUM].setProtected();
		
	}
	
	
	

	
	private static final int MAX_SCORES = 17;

	
	private ArrayList<GObject> displayElements = new ArrayList<GObject>();
	private UserScoreDisplay[] scores = new UserScoreDisplay[MAX_SCORES];
	

}
