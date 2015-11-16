/**
 * 
 */
package Model;

/**
 * @author evandavies
 *
 */
	public enum Scores {
	
		ONES(0,0,"Ones"), TWOS(0,1, "Twos"), THREES(0,2,"Threes"), FOURS(0,3,"Fours"), 
		FIVES(0,4, "Fives"), SIXES(0,5,"Sixes"),
		UPPER_SCORES_SUBTOTAL(0,6,"Upper SubTotal"), UPPER_SCORE_BONUS(25,7,"Upper Bonus"),
		THREE_OF_A_KIND(0,8,"Three of a Kind"), FOUR_OF_A_KIND(0,9,"Four of a Kind"), 
		FULL_HOUSE(25,10,"Full House"), SM_STRAIGHT(30,11,"Small Straight (30)"), 
		LG_STRAIGHT(40,12,"Large Straight (40)"), YAHTZEE(50,13,"Yahtzee! (50)"), CHANCE(0,14,"Chance"),
		LOWER_SCORES_SUBTOTAL(0,15,"Lower SubTotal"), TOTAL(0,16,"Total");


		Scores(int x,int y, String name){
		score = x;
		index = y;
		displayName = name;
	}

	int score, index;
	String displayName;

	public int getIndex(){ return index; }
	public String displayName() { return displayName;}
	public int getScoreValue() {return score;}

	
	
	}
