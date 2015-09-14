package view;


import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

import Model.Constants;


public class UserScoreDisplay {
	
	public UserScoreDisplay(){
		scoreBox = new TextLabel(Constants.PLAYER_SCORE_CELL_WIDTH, Constants.PLAYER_SCORE_CELL_HEIGHT, "  ");
		index = 0;
	}
	
	
	public GObject getScoreDisplayObject(){
		
		return(scoreBox.getLabelDisplay());
			
	}
	
	public boolean setScoreDisplay(int input){
		
		if (!cellProtected){
			cellProtected = true;
			scoreBox.setLabel(String.valueOf(input));
			scoreBox.setFillColor(Constants.CELL_HIGHTLIGHT_COLOR);
			return(true);
		}
		return(false);
	}
	
	
	public void setIndex(int value){
		
		index = value;
	}
	
	public int getIndex(){
		return(index);
		
	}
	
	private TextLabel scoreBox;
	// private GCompound cellGraphic = new GCompound();
	private int index;
	private boolean cellProtected = false;
	// private GLabel score = new GLabel("  ");
	// private GRect box = new GRect(0,0,Constants.PLAYER_SCORE_CELL_WIDTH, Constants.PLAYER_SCORE_CELL_HEIGHT );
}
