package view;


import java.awt.Color;
import java.awt.Font;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;


public class CategoryGraphics {
	
	public CategoryGraphics(){
		
		createCategoryGrid();

	}
	
	
	
	public GObject getCategoryGridObject(){
		
		return(categoryGrid);
		
	}
	
	private void createCategoryGrid(){
		
		double xOffset = 0;
		double yOffset = 0;
		double labelYOffset = 0;
		double labelXOffset = 0;
		GRect tempRect;
		GLabel tempLabel;
		
		
		tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
		tempRect.setFillColor(titleColor);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Categories");
		tempLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + ((CELL_WIDTH/2) - (tempLabel.getWidth()/2));
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		
		yOffset += CELL_HEIGHT;

		
		for (String s : upperCategoryNames){
			
			tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
			tempRect.setFillColor(categoryColor);
			tempRect.setFilled(true);
			tempRect.setVisible(true);
			categoryGrid.add(tempRect, xOffset, yOffset);
			
			tempLabel = new GLabel (s);
			tempLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			tempLabel.setVisible(true);
			tempLabel.setColor(categoryLabelColor);
			
			labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());

			categoryGrid.add(tempLabel, xOffset + CATEGORY_INDENT_WIDTH, labelYOffset);
			
			yOffset += CELL_HEIGHT;
			
		}
		
		tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
		tempRect.setFillColor(titleColor);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Upper Score");
		tempLabel.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += CELL_HEIGHT;	
		
		tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
		tempRect.setFillColor(titleColor);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Upper Bonus");
		tempLabel.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += CELL_HEIGHT;		
		
		
		for (String s : LowerCategoryNames){
			
			tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
			tempRect.setFillColor(categoryColor);
			tempRect.setFilled(true);
			tempRect.setVisible(true);
			categoryGrid.add(tempRect, xOffset, yOffset);
			
			tempLabel = new GLabel (s);
			tempLabel.setFont(new Font("TimesRoman", Font.PLAIN, 14));
			tempLabel.setVisible(true);
			tempLabel.setColor(categoryLabelColor);

			labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());

			categoryGrid.add(tempLabel, xOffset + CATEGORY_INDENT_WIDTH, labelYOffset);
			
			yOffset += CELL_HEIGHT;
			
		}
		
		
		tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
		tempRect.setFillColor(titleColor);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Lower Score");
		tempLabel.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += CELL_HEIGHT;	
		
		tempRect = new GRect(xOffset, yOffset, CELL_WIDTH, CELL_HEIGHT );
		tempRect.setFillColor(titleColor);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("TOTAL");
		tempLabel.setFont(new Font("TimesRoman BOLD", Font.BOLD, 14));
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* CELL_HEIGHT) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);

	}
	
	
	
	private static final double CELL_HEIGHT = 30;
	private static final double CELL_WIDTH = 150;
	private static final double CATEGORY_INDENT_WIDTH = 15;
	private static final double TOTALS_INDENT_WIDTH = 5;

	
	
	
	private static GCompound categoryGrid = new GCompound ();
	private String[] LowerCategoryNames = {"Three of a Kind", "Four of a Kind", "Full House (25)",
			"Small Straight(30)", "Large Straight(40)", "Yahtzee!(50)", "Chance"};

	private String[] upperCategoryNames = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes"};
	private static Color categoryColor = new Color(255,255,200);
	private static Color titleColor = Color.WHITE;
	private static Color categoryLabelColor = Color.RED;
	
}
