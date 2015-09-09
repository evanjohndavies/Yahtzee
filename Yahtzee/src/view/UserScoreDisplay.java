package view;


import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;


public class UserScoreDisplay {
	
	public UserScoreDisplay(){
		
		double xOffset = 0;
		double yOffset = 0;
		
		
		box.setFillColor(cellColor);
		cellGraphic.add(box,0,0);
		score.setFont(scoreFont);
		score.setColor(scoreColor);
		
		yOffset = (.5* PLAYER_SCORE_CELL_HEIGHT) + (.5* score.getHeight());
		xOffset = ((PLAYER_SCORE_CELL_WIDTH/2) - (score.getWidth()/2));
		cellGraphic.add(score, xOffset, yOffset);
		index = 0;
	}
	
	
	public GObject getScoreDisplayObject(){
		
		return(cellGraphic);
			
	}
	
	public void setScoreDisplay(int input){
		score.setLabel(String.valueOf(input));
		cellProtected = true;
		box.setFillColor(cellSetColor);
		
	}
	
	
	public void setIndex(int value){
		
		index = value;
	}
	
	public int getIndex(){
		return(index);
		
	}
	
	
	
	
	private static final double PLAYER_SCORE_CELL_HEIGHT = 30;
	private static final double PLAYER_SCORE_CELL_WIDTH = 100;
	
	private GCompound cellGraphic = new GCompound();
	private int index;
	private boolean cellProtected = false;
	private GLabel score = new GLabel("  ");
	private GRect box = new GRect(0,0,PLAYER_SCORE_CELL_WIDTH, PLAYER_SCORE_CELL_HEIGHT );
	private Color cellColor = Color.WHITE;
	private Color cellSetColor = Color.LIGHT_GRAY;
	private Color scoreColor = Color.BLACK;
	private Font scoreFont = new Font("TimesRoman", Font.BOLD, 14);

}
