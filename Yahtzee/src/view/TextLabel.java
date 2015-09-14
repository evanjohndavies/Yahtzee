package view;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;

import java.awt.Color;

import Model.Constants;

public class TextLabel {
	
	public TextLabel(){
		
		new TextLabel(Constants.DEFAULT_CELL_WIDTH, Constants.DEFAULT_CELL_HEIGHT,"");
		 
	}
	
	public TextLabel (double width, double height, String text){
		
		double xOffset = 0;
		double yOffset = 0;
		
	
		textBox = new GRect(0,0, width, height);
		textBox.setColor(Constants.DEFUALT_FONT_COLOR);
		textBox.setFillColor(Constants.CELL_DEFAULT_COLOR);	
		textBox.setFilled(true);
		textBox.setVisible(true);

		
		message = new GLabel(text);	
		message.setFont(Constants.DEFAULT_FONT);
		message.setColor(Constants.DEFUALT_FONT_COLOR);
		message.setVisible(true);
		
		yOffset = (.5* height) + (.5* message.getHeight());
		xOffset = ((Constants.PLAYER_SCORE_CELL_WIDTH/2) - (message.getWidth()/2));
		label.setVisible(true);
		
		label.add(textBox);
		label.add(message,xOffset, yOffset);	
	
	}
	
	public GObject getLabelDisplay(){
		
		return(label);
			
	}
	
	public void setLabel(String text){
		this.message.setLabel(text);
	}
	
	public void setFillColor(Color color){
		
		this.textBox.setFillColor(color);
	}
	

	private GLabel message;
	private GRect textBox;
	private GCompound label = new GCompound();
	

}
