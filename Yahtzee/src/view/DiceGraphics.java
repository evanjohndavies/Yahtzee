package view;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;


public class DiceGraphics {

	public DiceGraphics(){
		drawDie();
		drawDiceFaces();
		loadHashMap();
	}
	
	public GObject getGraphicObject(){
		return(dieGraphics);
	}
	
	public void selectDie(){
		
		selected = !selected;
		updateDieSelected(selected);
		
	}
	
	public void unselectDie(){
		selected = false;
		updateDieSelected(selected);
	}
	
	
	private void updateDieSelected(boolean val){
		
		Color color;
		Class<GRect> recCls = GRect.class;  // get class type for GRect to compare object below
		Class<GArc> arcCls = GArc.class;	// get class type for GArc to compare object below
		GRect recTemp;
		GArc arcTemp;
		
		if (val){
			color = highlightColor;
		}else{
			color = fillColor;
		}
		
		for (GObject o: diceParts){
			
			if (recCls.isInstance(o)){
				recTemp = (GRect) o;
				recTemp.setFillColor(color);
				recTemp.setColor(color);
			}else{
				if(arcCls.isInstance(o)){
					arcTemp = (GArc) o;
					arcTemp.setFillColor(color);
				}
			}
		}
		
	}
	
	public void setDiceValue (int value){
		
		boolean[] pattern;
		int index = 0;
		
		
		// bounds check that value passed is not larger
		// than number of total dots which is 9.  Yes, could 
		// be 6 since that is really the max number but didn't do that
		
		if (value <= TOTAL_DOTS){
			pattern = dotPosition.get(value);
			// loop through dice dot objects and set visible or not
			// based on the hashmap that predefines the pattern for a
			// specific number
			
			for (GOval dot: diceDots){
				dot.setVisible(pattern[index++]);
			}		
		}
	}
	
	
	private void drawDiceFaces(){
		
		double distanceBetweenDots = 0;
		double xOffset = 0;
		double yOffset = 0;
		int count = 0;
		int i = 0;
		
		distanceBetweenDots = (((DICE_SIDE_LENGTH+(2*DICE_ARC_LENGTH)) - (3*DOT_SIZE))/4);
		xOffset = distanceBetweenDots;
		yOffset = xOffset;
		
		for (GOval dot : diceDots){
			dot = new GOval(DOT_SIZE, DOT_SIZE);			
			dot.setLocation(xOffset, yOffset);
			dot.setSize(DOT_SIZE, DOT_SIZE);
			dot.setFillColor(Color.BLACK);
			dot.setFilled(true);
			dot.setVisible(true);			
			count++;
			// set position of next dot
			if (count == 3){
				xOffset += (distanceBetweenDots+DOT_SIZE);
				yOffset = distanceBetweenDots;
				count = 0;
			} else {
				yOffset += (distanceBetweenDots + DOT_SIZE); 
			}
			
			dieGraphics.add(dot);
			
			// note that it is necessary to specifically assign the index array value
			// to the newly created GOval as opposed to having it assigned when
			// it was created within the for loop construct
			diceDots[i++] = dot;
		}	
	}
	
	private void drawDie(){
		
		double xOffset = 0;
		double yOffset = 0;
		double width = 0;
		double height = 0;
		
		GCompound dice = new GCompound();

		GLine rhSide = new GLine(0,0,0,DICE_SIDE_LENGTH);
		rhSide.setVisible(true);
		
		GLine lhSide = new GLine(0,0,0,DICE_SIDE_LENGTH);
		lhSide.setVisible(true);

		GLine topSide = new GLine(0,0,DICE_SIDE_LENGTH,0);
		topSide.setVisible(true);
		GLine bottomSide = new GLine(0,0,DICE_SIDE_LENGTH,0);
		topSide.setVisible(true);
		
		// fill dice area in with rectangles
		width = DICE_SIDE_LENGTH+(2*DICE_ARC_LENGTH);
		height = DICE_SIDE_LENGTH;
		GRect fillHorizontal = new GRect(0,0,width,height);
		diceParts.add(fillHorizontal);
		fillHorizontal.setFillColor(fillColor);
		fillHorizontal.setColor(fillColor);
		fillHorizontal.setFilled(true);
		fillHorizontal.setVisible(true);
		
		GRect fillVertical = new GRect(0,0,height, width);
		fillVertical.setFillColor(fillColor);
		fillVertical.setColor(fillColor);
		diceParts.add(fillVertical);
		fillVertical.setFilled(true);
		fillVertical.setVisible(true);
		
		

		// top left dice corner
		GArc tlArc = new GArc(DICE_ARC_LENGTH*2, DICE_ARC_LENGTH*2,90,90);	
		diceParts.add(tlArc);
		tlArc.setFillColor(fillColor);
		tlArc.setFilled(true);
		tlArc.setVisible(true);

		// top right dice corner
		GArc trArc = new GArc(0,0, DICE_ARC_LENGTH*2, DICE_ARC_LENGTH*2,0,90);
		diceParts.add(trArc);
		trArc.setFillColor(fillColor);
		trArc.setFilled(true);
		trArc.setVisible(true);

		// bottom left corner
		GArc blArc = new GArc(0,0, DICE_ARC_LENGTH*2, DICE_ARC_LENGTH*2,180,90);		
		diceParts.add(blArc);
		blArc.setFillColor(fillColor);
		blArc.setFilled(true);
		blArc.setVisible(true);	
		
		// bottom right corner
		GArc brArc = new GArc(0,0, DICE_ARC_LENGTH*2, DICE_ARC_LENGTH*2,270,90);
		diceParts.add(brArc);
		brArc.setFillColor(fillColor);
		brArc.setFilled(true);
		brArc.setVisible(true);		
		
		// Add Corners
		dice.add(tlArc);
		dice.add(trArc, DICE_SIDE_LENGTH, 0);
		dice.add(blArc, 0, DICE_SIDE_LENGTH);
		dice.add(brArc, DICE_SIDE_LENGTH, DICE_SIDE_LENGTH);
		
		// add fill areas 
		
		dice.add(fillHorizontal,0,DICE_ARC_LENGTH);
		dice.add(fillVertical,DICE_ARC_LENGTH,0);	
		
		
		yOffset = DICE_ARC_LENGTH;
		dice.add(lhSide,xOffset,yOffset);
		
		xOffset = DICE_SIDE_LENGTH + (2*DICE_ARC_LENGTH);
		dice.add(rhSide,xOffset,yOffset);
		xOffset = DICE_ARC_LENGTH;
		dice.add(topSide,xOffset,0);
		
		yOffset = DICE_SIDE_LENGTH + (2*DICE_ARC_LENGTH);
		dice.add(bottomSide,xOffset,yOffset);
		dice.setVisible(true);
				
		dieGraphics.add(dice);

	}
	
	private static void loadHashMap(){
		
		boolean[] one = 	{false, false, false, false, true, false, false, false, false};
		boolean[] two = 	{false, false, true, false, false, false, true, false, false};
		boolean[] three = 	{false, false, true, false, true, false, true, false, false};
		boolean[] four = 	{true, false, true, false, false, false, true, false, true};
		boolean[] five = 	{true, false, true, false, true, false, true, false, true};
		boolean[] six = 	{true, true, true, false, false, false, true, true, true};
		boolean[] seven = 	{true, true, true, false, true, false, true, true, true};
		boolean[] eight = 	{true, true, true, true, false, true, true, true, true};
		boolean[] nine = 	{true, true, true, true, true, true, true, true, true};

		dotPosition.put(1, one);
		dotPosition.put(2, two);
		dotPosition.put(3, three);
		dotPosition.put(4, four);
		dotPosition.put(5, five);
		dotPosition.put(6, six);
		dotPosition.put(7, seven);
		dotPosition.put(8, eight);
		dotPosition.put(9, nine);
		
	}
	
	private static final double DICE_SIDE_LENGTH = 40;
	private static final double ARC_RATIO = .30;
	private static final double DICE_ARC_LENGTH = DICE_SIDE_LENGTH*ARC_RATIO;
	private static final double DOT_SIZE = DICE_SIDE_LENGTH/3;
	
	private static final int TOTAL_DOTS = 9;
	
	private GOval[] diceDots = new GOval[TOTAL_DOTS];
	private GCompound dieGraphics = new GCompound();
	private static HashMap<Integer,boolean[]> dotPosition = new HashMap<Integer, boolean[]>();
	private boolean selected = false;
	private ArrayList<GObject> diceParts = new ArrayList<GObject>();
	private Color fillColor = Color.WHITE;
	private Color highlightColor = Color.LIGHT_GRAY;

	
}
