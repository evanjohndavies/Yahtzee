package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import Model.Constants;
import Model.Scores;
import acm.graphics.*;


public class PlayerGraphics {
	
	public PlayerGraphics(){
		
		createPlayerGrid();
		
	}
	
	
	public ArrayList<GObject> getPlayerGridObjects(){
		
		return(displayElements);
		
	}
	
	public UserScoreDisplay checkScoreObjectSelected(GObject gameElement){
				
		for ( UserScoreDisplay object: scores){
			
			if(gameElement.equals(object.getScoreDisplayObject())){
				return(object);
			}
			
		}
		return(null);
	}
	
/*	public Scores getIndexScoreObjectSelected(GObject gameElement){
		
		for ( UserScoreDisplay object: scores){
			
			if(gameElement.equals(object.getScoreDisplayObject())){
				return(object.getCategory());
			}
			
		}
		return(null);
	}
	*/
	
	
	
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
		
		for (Scores s: Scores.values()){
			scores[s.getIndex()]= new UserScoreDisplay();
			scores[s.getIndex()].setCategory(s);
			displayElements.add(scores[s.getIndex()].getScoreDisplayObject());
		}
		
/*		for (int i=0; i< MAX_SCORES; i++){
			scores[i] = new UserScoreDisplay();
			scores[i].setIndex(i);
			displayElements.add(scores[i].getScoreDisplayObject());
		}
		
		*/
		
		//  set total fields as protected
		scores[Scores.UPPER_SCORES_SUBTOTAL.getIndex()].setProtected();
		scores[Scores.UPPER_SCORE_BONUS.getIndex()].setProtected();
		scores[Scores.LOWER_SCORES_SUBTOTAL.getIndex()].setProtected();
		scores[Scores.TOTAL.getIndex()].setProtected();
		
	}
	
	
	

	
	private static final int MAX_SCORES = 17;

	
	private ArrayList<GObject> displayElements = new ArrayList<GObject>();
	private UserScoreDisplay[] scores = new UserScoreDisplay[MAX_SCORES];
	

}
