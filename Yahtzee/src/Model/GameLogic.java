package Model;

public class GameLogic {
	
	
	public int rollTurn(){
		return (dice.rollDice());
		
	}
	
	
	
	public int[] countDiceRolled(int[] diceRoll){
		
		int[] numberOfTimesRolled = new int[NUMBER_OF_SIDES_OF_DICE];
		
		//run through dice rolled and count number of times a dice face came up
		for (int i = 0; i < NUMBER_OF_DICE; i++){
			numberOfTimesRolled[diceRoll[i]]++;
		}
		return(numberOfTimesRolled);
	}
	
	public int scoreSameDice(int[] diceRoll, int diceFace){
		int score = 0;
		
		// sum dice for the diceFace parameter passed
		for (int i = 0; i< NUMBER_OF_DICE; i++) {
			if (diceRoll[i] == diceFace){
				score += diceRoll[i];
			}
		}
		return(score);
	}
	
	public int scoreOfAKind(int[] numberOfTimesRolled, int numberOfDice){
	
		// calculate score by seeing if any of the dice index is equal
		// to or greater than the number of dice needed which is passed 
		// in as numberOfDice
		
		for (int i = 0; i< NUMBER_OF_DICE; i++){
			if (numberOfTimesRolled[i] >= numberOfDice){
				// calculate score by multiplying dice count by dice face (i)
				return(numberOfTimesRolled[i]* (i+1));
			}
			// get here because not sufficient number of dice rolled
		}
		
		// get here because not sufficient number of dice rolled
		return(0);
	}
	
	public int scoreFullHouse(int[] numberOfTimesRolled){
		// check if have 3 of one kind
		for (int i = 0; i< NUMBER_OF_DICE; i++){
			if (numberOfTimesRolled[i] == 3){
				// now check for 2 of a kind
				for (int j = 0; i< NUMBER_OF_DICE; j++){
					if (numberOfTimesRolled[j] == 2){
						// get here because conditions satisfied for full house return score 
						return(FULL_HOUSE_SCORE);
					}
				}
			}
		}
		// get here because not sufficient number of dice rolled
		return(0);
	}
	
	public int scoreSmallStraight(int[] numberOfTimesRolled){
		return(scoreStraight(numberOfTimesRolled, SMALL_STRAIGHT_ENUM));
	}
	
	public int scoreLargeStraight(int[] numberOfTimesRolled){
		return(scoreStraight(numberOfTimesRolled, LARGE_STRAIGHT_ENUM));
		
	}
	
	private int scoreStraight(int[] numberOfTimesRolled, int type){
	
		int missedDice = 0;
		
		// run through the dice to see how many numbers were NOT rolled
		// if there is a missing number 
		for (int i = 0; i < NUMBER_OF_DICE; i++){
			if (numberOfTimesRolled[i] == 0){
				missedDice++;
			}
		}
		
		// check which straight we are testing for and return score
		
		if (type == LARGE_STRAIGHT_ENUM){
			if (missedDice == 0){
				return(LARGE_STRAIGHT_SCORE);
			}
		}
		
		if (type == SMALL_STRAIGHT_ENUM){
			if (missedDice <= 1){
				return(SMALL_STRAIGHT_SCORE);
			}
		}
		//get here as qualifications for straight not met so return 0
		return(0);
	}
	
	public int scoreYahtzee(int[] numberOfTimesRolled){
		
		// run through the dice to see if there are 5 on one kind rolled
		// if there is a missing number 
		for (int i = 0; i < NUMBER_OF_DICE; i++){
			if (numberOfTimesRolled[i] == 5){
				return(YAHTZEE_SCORE);
			}
		}
		// get here as there was not Yahtzee condition met return 0
		return(0);	
	}
		


	private static final int NUMBER_OF_DICE = 5;
	private static final int NUMBER_OF_SIDES_OF_DICE = 6;
	private static final int SMALL_STRAIGHT_ENUM = 12;
	private static final int LARGE_STRAIGHT_ENUM = 13;
	private static final int LARGE_STRAIGHT_SCORE = 40;
	private static final int SMALL_STRAIGHT_SCORE = 30;
	private static final int FULL_HOUSE_SCORE = 25;
	private static final int YAHTZEE_SCORE = 50;

	
	
	private Dice dice = new Dice();

}
