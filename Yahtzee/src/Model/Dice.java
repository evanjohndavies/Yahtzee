package Model;

import java.util.Random;


public class Dice {
	
	

	public Dice (){
		// default constructor needed as there is one with variable passed
	}
	
	public Dice (boolean testMode){
		
		if (testMode){
			randomObject.setSeed(0);
		}
	}
	
	
	
	public int rollDice(){
		return (randomObject.nextInt(SIDES_OF_DICE)+1);
	}
	

	
	private Random randomObject = new Random();
	private final static int SIDES_OF_DICE = 6;
	
}
