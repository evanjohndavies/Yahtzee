package view;



import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import Model.Constants;


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
		
		
		tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT  );
		tempRect.setFillColor(Constants.TITLE_COLOR);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Categories");
		tempLabel.setFont(Constants.DEFAULT_TITLE_FONT);
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + ((Constants.DEFAULT_CATEGORY_WIDTH/2) - (tempLabel.getWidth()/2));
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		
		yOffset += Constants.DEFAULT_CELL_HEIGHT;

		
		for (String s : upperCategoryNames){
			
			tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT );
			tempRect.setFillColor(Constants.CATEGORY_COLOR);
			tempRect.setFilled(true);
			tempRect.setVisible(true);
			categoryGrid.add(tempRect, xOffset, yOffset);
			
			tempLabel = new GLabel (s);
			tempLabel.setFont(Constants.DEFAULT_FONT);
			tempLabel.setVisible(true);
			tempLabel.setColor(Constants.CATEGORY_LABEL_COLOR);
			
			labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT) + (.5* tempLabel.getHeight());

			categoryGrid.add(tempLabel, xOffset + CATEGORY_INDENT_WIDTH, labelYOffset);
			
			yOffset += Constants.DEFAULT_CELL_HEIGHT ;
			
		}
		
		tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT );
		tempRect.setFillColor(Constants.TITLE_COLOR);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Upper Score");
		tempLabel.setFont(Constants.DEFAULT_SUBTOTAL_FONT);
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += Constants.DEFAULT_CELL_HEIGHT ;	
		
		tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT  );
		tempRect.setFillColor(Constants.TITLE_COLOR);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Upper Bonus");
		tempLabel.setFont(Constants.DEFAULT_SUBTOTAL_FONT);
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += Constants.DEFAULT_CELL_HEIGHT ;		
		
		
		for (String s : LowerCategoryNames){
			
			tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT  );
			tempRect.setFillColor(Constants.CATEGORY_COLOR);
			tempRect.setFilled(true);
			tempRect.setVisible(true);
			categoryGrid.add(tempRect, xOffset, yOffset);
			
			tempLabel = new GLabel (s);
			tempLabel.setFont(Constants.DEFAULT_FONT);
			tempLabel.setVisible(true);
			tempLabel.setColor(Constants.CATEGORY_LABEL_COLOR);

			labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());

			categoryGrid.add(tempLabel, xOffset + CATEGORY_INDENT_WIDTH, labelYOffset);
			
			yOffset += Constants.DEFAULT_CELL_HEIGHT ;
			
		}
		
		
		tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT  );
		tempRect.setFillColor(Constants.TITLE_COLOR);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("Lower Score");
		tempLabel.setFont(Constants.DEFAULT_SUBTOTAL_FONT);
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);
		
		yOffset += Constants.DEFAULT_CELL_HEIGHT ;	
		
		tempRect = new GRect(xOffset, yOffset, Constants.DEFAULT_CATEGORY_WIDTH, Constants.DEFAULT_CELL_HEIGHT  );
		tempRect.setFillColor(Constants.TITLE_COLOR);
		tempRect.setFilled(true);
		tempRect.setVisible(true);
		categoryGrid.add(tempRect, xOffset, yOffset);
		
		tempLabel = new GLabel ("TOTAL");
		tempLabel.setFont(Constants.DEFAULT_FONT);
		tempLabel.setVisible(true);
		
		labelYOffset = yOffset + (.5* Constants.DEFAULT_CELL_HEIGHT ) + (.5* tempLabel.getHeight());
		labelXOffset = xOffset + TOTALS_INDENT_WIDTH;
		categoryGrid.add(tempLabel, xOffset + labelXOffset, labelYOffset);

	}
	

	private static final double CATEGORY_INDENT_WIDTH = 15;
	private static final double TOTALS_INDENT_WIDTH = 5;

	
	
	
	private static GCompound categoryGrid = new GCompound ();
	private String[] LowerCategoryNames = {"Three of a Kind", "Four of a Kind", "Full House (25)",
			"Small Straight(30)", "Large Straight(40)", "Yahtzee!(50)", "Chance"};

	private String[] upperCategoryNames = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes"};
	
}
