package Model;

import java.util.ArrayList;
import java.util.Random;


public class Dice {
	

	public Dice (){
		// default constructor needed as there is one with variable passed
		rollDice();
	}
	
	public Dice (boolean testMode){
		
		if (testMode){
			randomObject.setSeed(0);
		}
		rollDice();
	}
	
	
	public static void rollDice(ArrayList<Dice> dice){
		
		for ( Dice d : dice){
		  d.rollDice();
		}
	}
	
	public void rollDice(){
		
		if (!getSelectedState()){
			face = randomObject.nextInt(SIDES_OF_DICE)+1;
		}
		
	}
	
	public static int[] countDiceRolled(int[] diceRolled){
		int[] numberOfTimesRolled = new int[SIDES_OF_DICE];
		
			//run through dice rolled and count number of times a dice face came up
			// shorthand for loop for collections
			for (int d : diceRolled){
				numberOfTimesRolled[d-1]++;
			}
		return(numberOfTimesRolled);
		
	}
	
	public static int[] countDiceRolled(ArrayList<Dice> diceRoll){
		
		int[] numberOfTimesRolled = new int[SIDES_OF_DICE];
		
		//run through dice rolled and count number of times a dice face came up
		// shorthand for loop for collections
		for (Dice d : diceRoll){
			numberOfTimesRolled[d.getDiceValue()-1]++;
		}
		return(numberOfTimesRolled);
	}

	
	public int getDiceValue(){
		
		return(face);
	}
	
	public void setSelectedState(){
		
		// toggle state of dice to either be rolled or not on call to roll dice
		diceSelected = !diceSelected;
	}
	
	public boolean getSelectedState(){
		
		return(diceSelected);
	}

	public void unselectState(){
		diceSelected = false;
		
	}
	
	
	public static String toString (ArrayList <Dice> diceRoll){
		
		 String returnString = "Dice Roll: ";
		
		// use enhanced for loop where i is not index, but actual value
		for (Dice d: diceRoll){
			returnString = returnString.concat(String.valueOf(d.face) + ", ");
		}
		return(returnString);
	}
	
	public static String toString(Dice dice){
		String returnString = "Dice Roll: ";
		return(returnString.concat(String.valueOf(dice.face)));
	}
	
	public static String toString (int[] diceRoll){
		
		 String returnString = "Dice count: ";
		
		// use enhanced for loop where i is not index, but actual value
		for (int d: diceRoll){
			returnString = returnString.concat(String.valueOf(d) + ", ");
		}
		return(returnString);
	}

	private int face;
	private static Random randomObject = new Random();
	private final static int SIDES_OF_DICE = 6;
	private boolean diceSelected = false;  // used to determine if dice is to be rolled or not
	
}
