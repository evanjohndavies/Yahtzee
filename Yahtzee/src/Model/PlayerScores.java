package Model;

import java.util.ArrayList;

public class PlayerScores {
	
	
	public void setPlayerName(String name){
		playerName = name;
	}
	
	public String getPlayerName(){
		return(playerName);
	}
	
	public void setScore(int position, int score){
		// bound check
		if (position <= scores.length){
			scores[position] = score;
		}
	}
	
	public int getScore(int position){
		// bound check
		if (position < NUMBER_SCORES){
			return(scores[position]);
		}
		return (-1);
	}
	
	
	
	public void calculateScores(){
		
	// calculate upper score
		scores[UPPER_SCORE_POSITION]=0;
		for (int i = 0; i < UPPER_SCORE_POSITION; i++){
			scores[UPPER_SCORE_POSITION] += scores[i];
		}
	// Determine bonus
		if(scores[UPPER_SCORE_POSITION] >= BONUS_MIN_SCORE){
			scores[BONUS_SCORE_POSITION]= BONUS_SCORE;
		} else
			scores[BONUS_SCORE_POSITION] = 0;
	// Calculate lower score
		scores[LOWER_SCORE_POSITION]=0;
		for (int i = BONUS_SCORE_POSITION+1; i < LOWER_SCORE_POSITION; i++){
			scores[LOWER_SCORE_POSITION] += scores[i];
		}
		
	// Calculate total
		
		scores[TOTAL_POSITION]= scores[UPPER_SCORE_POSITION] + 
								scores[BONUS_SCORE_POSITION] + 
								scores[LOWER_SCORE_POSITION]; 
		
	}
	
	public void printScores(){
		int i=0;
		
		
		for (String category: scoreName){
					System.out.println(category + ": " + scores[i++]);
			
		}

		
	}
	
	
	private String playerName = null;
	private  int[] scores = new int[NUMBER_SCORES];
	private static final int NUMBER_SCORES = 17;
	private static final int UPPER_SCORE_POSITION = 6;
	private static final int BONUS_SCORE_POSITION = 7;
	private static final int BONUS_MIN_SCORE = 63;
	private static final int BONUS_SCORE = 35;
	private static final int LOWER_SCORE_POSITION = 15;
	private static final int TOTAL_POSITION = 16;

	private static final String[] scoreName = { "Ones","Twos", "Threes", "Fours", "Fives", "Sixes", "Upper Score", "Upper Bonus (35)", "Three of a Kind", "Four of a Kind", "Full House", "Small Straight", "Large Straight", "Yahtzee", "Chance", "Lower Score", "Total"};

}
