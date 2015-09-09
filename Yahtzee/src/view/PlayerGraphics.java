package view;

import java.awt.Color;
import java.awt.Font;

import acm.graphics.*;


public class PlayerGraphics {
	
	public PlayerGraphics(){
		
		createPlayerGrid();
		
	}
	
	
	public GObject getPlayerGridObject(){
		
		return(playerGrid);
		
	}
	
	
	private void createPlayerGrid(){
		
		double yOffset = 0;
		
		
		for (int i=0; i< MAX_SCORES; i++){
			
			scores[i] = new UserScoreDisplay();
			scores[i].setIndex(i);
			playerGrid.add(scores[i].getScoreDisplayObject(),0,yOffset);
			yOffset += CELL_HEIGHT;
		}
		
	}
	

	
	
	private static final double CELL_HEIGHT = 30;
	private static final int MAX_SCORES = 17;

	
	private GCompound playerGrid = new GCompound ();

	private UserScoreDisplay[] scores = new UserScoreDisplay[MAX_SCORES];
	

}
