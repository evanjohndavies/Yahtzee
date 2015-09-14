package Model;

import java.util.ArrayList;

public class GameLogic {
	
	
	

	
	public int scoreSameDice(ArrayList<Dice> dice, int diceFace){
		
			return(scoreSameDice(Dice.countDiceRolled(dice), diceFace));
	}
	
	public int scoreSameDice(int[] diceRoll, int diceFace){
		// need to have called count dice before calling this method
		// multiply dice face number by number of dice rolled

		//bounds testing to make sure input parameters are ok
		if (diceRoll.length >= diceFace){
			return(diceRoll[diceFace-1]*diceFace);
		}
		return(0);	
	}
	

	public int scoreOfAKind(ArrayList<Dice> dice, int numberOfDice){
		
			return(scoreOfAKind(Dice.countDiceRolled(dice), numberOfDice));
	}
	
	public int scoreOfAKind(int[] numberOfTimesRolled, int numberOfDice){
	
		
			// calculate score by seeing if any of the dice index is equal
			// to or greater than the number of dice needed which is passed 
			// in as numberOfDice
		
			for (int i = 0; i< numberOfTimesRolled.length; i++){

				if (numberOfTimesRolled[i] >= numberOfDice){
					//test if passing for Yahtzee if that case flat score
					if (numberOfDice == YAHTZEE_DICE){
						return(YAHTZEE_SCORE);
					}
					// else calculate score by multiplying dice count by dice face (i)
					return((numberOfTimesRolled[i]* (i+1)));
				}
			}
		// get here because not sufficient number of dice 
		return(0);
	}
	
	
	public int scoreFullHouse(ArrayList<Dice> dice){

		return(scoreFullHouse(Dice.countDiceRolled(dice)));
	}
	
	public int scoreFullHouse(int[] numberOfTimesRolled){
		
	
		// check if have 3 of one kind
		for (int i = 0; i< numberOfTimesRolled.length; i++){
			if (numberOfTimesRolled[i] == 3){
					
				// now check for 2 of a kind
				for (int j = 0; j< numberOfTimesRolled.length; j++){
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
	
	
	public int scoreSmallStraight(ArrayList<Dice> dice){
		
		return(scoreSmallStraight(Dice.countDiceRolled(dice)));
	}
	
	
	public int scoreSmallStraight(int[] numberOfTimesRolled){
		
		return(scoreStraight(numberOfTimesRolled, SMALL_STRAIGHT_ENUM));
	}

	
	
	public int scoreLargeStraight(ArrayList<Dice> dice){
		
		return(scoreLargeStraight(Dice.countDiceRolled(dice)));
	}
	
	
	public int scoreLargeStraight(int[] numberOfTimesRolled){
		
		return(scoreStraight(numberOfTimesRolled, LARGE_STRAIGHT_ENUM));

	}
	
	private int scoreStraight(int[] numberOfTimesRolled, int type){
	
		int consecutiveCount = 0;
		
		// run through the dice to see how many numbers were NOT rolled
		// if there is a missing number 
		for (int i : numberOfTimesRolled){
			if (i == 0){
				consecutiveCount =0;
			} else{
				consecutiveCount++;
				// check if condition is met

				if (type == LARGE_STRAIGHT_ENUM){
					if (consecutiveCount == 5){
						return(LARGE_STRAIGHT_SCORE);
					}
				}
						
				if (type == SMALL_STRAIGHT_ENUM){
					if (consecutiveCount == 4 ){
						return(SMALL_STRAIGHT_SCORE);
					}
				}				
			}
		}
		
		//get here as qualifications for straight not met so return 0
		return(0);
	}

	public int scoreChance(ArrayList<Dice> dice){
		return(scoreChance(Dice.countDiceRolled(dice)));
		
	}
	
	public int scoreChance(int[] numberOfTimesRolled){
		int value = 1;
		int score = 0;
		
		for( int d: numberOfTimesRolled){
			score += d*value++;
		}	
		return(score);
		
	}	
	

	private static final int SMALL_STRAIGHT_ENUM = 11;
	private static final int LARGE_STRAIGHT_ENUM = 12;
	private static final int LARGE_STRAIGHT_SCORE = 40;
	private static final int SMALL_STRAIGHT_SCORE = 30;
	private static final int FULL_HOUSE_SCORE = 25;
	private static final int YAHTZEE_DICE = 5;
	private static final int YAHTZEE_SCORE = 50;
	

}
