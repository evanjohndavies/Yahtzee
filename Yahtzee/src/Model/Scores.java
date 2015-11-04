/**
 * 
 */
package Model;

/**
 * @author evandavies
 *
 */
	public enum Scores {
		
		ONES(0,1,"Ones"), TWOS(0,2, "Twos"), THREES(0,3,"Threes"), FOURS(0,4,"Fours"), FIVES(0,5, "Fives"), SIXES(0,6,"Sixes"),
		THREE_OF_A_KIND(0,7,"Three of a Kind"), FOUR_OF_A_KIND(0,8,"Four of a Kind"), FULL_HOUSE(25,9,"Full House"), 
		SM_STRAIGHT(30,7,"Small Straight (30)"), LG_STRAIGHT(40,8,"Large Straight (40)"), YAHTZEE(50,9,"Yahtzee! (50)"),
		CHANCE(0,10,"Chance");

		Scores(int x,int y, String name){
			score = x;
			position = y;
			displayName= name;
		}

		int score, position;
		String displayName;

		public int getPosition(){ return position; }
		public String displayName() { return displayName;}
		public int getScoreValue() {return score;}
	
		
		
		}
