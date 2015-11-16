package Model;

import java.awt.Color;
import java.awt.Font;

public interface Constants {
	
	public static final int NUMBER_OF_DICE = 5;
	/*public static final int ONES_ENUM = 0;
	public static final int TWOES_ENUM = 1;
	public static final int THREES_ENUM = 2;
	public static final int FOURS_ENUM = 3;
	public static final int FIVES_ENUM = 4;
	public static final int SIXES_ENUM = 5;
	public static final int UPPER_SCORE_SUBTOTAL_ENUM = 6;
	public static final int UPPER_SCORE_BONUS_ENUM = 7;

	public static final int THREE_OF_A_KIND_ENUM = 8;
	public static final int FOUR_OF_A_KIND_ENUM = 9;
	public static final int FULL_HOUSE_ENUM = 10;
	public static final int SMALL_STRAIGHT_ENUM = 11;
	public static final int LARGE_STRAIGHT_ENUM = 12;
	public static final int YAHTZEE_ENUM = 13;
	public static final int CHANCE_ENUM = 14;
	public static final int LOWER_SCORE_SUBTOTAL_ENUM = 15;
	public static final int TOTAL_ENUM = 16;
	*/


	
	public static final int TOP_BOARDER = 25;
	public static final int SIDE_BOARDER = 25;
	public static final int X_INDEX_START = 150;
	
	public static final int NUMBER_OF_ROLLS = 3;
	public static final int NUMBER_OF_TURNS = 13;

	
	public static final double PLAYER_SCORE_CELL_HEIGHT = 30;
	public static final double PLAYER_SCORE_CELL_WIDTH = 100;
	
	
	public static final Font DEFAULT_FONT = new Font("TimesRoman", Font.BOLD, 14);
	public static final Font DEFAULT_TITLE_FONT = new Font("TimesRoman", Font.BOLD, 18);
	public static final Font DEFAULT_SUBTOTAL_FONT = new Font("TimesRoman", Font.BOLD, 16);


	
	public static final Color CELL_DEFAULT_COLOR = Color.WHITE;
	public static final Color CELL_HIGHTLIGHT_COLOR = Color.LIGHT_GRAY;
	public static final Color CELL_SELCTED_USER_COLOR = new Color(230,255,255);

	public static final Color DEFUALT_FONT_COLOR = Color.BLACK;
	public static final Color INACTIVE_DEFAULT_FILL_COLOR = Color.WHITE;
	public static final Color ACTIVE_DEFAULT_FILL_COLOR = Color.YELLOW;
	public static final Color BACKGROUND_COLOR = new Color(20,100,20);
	
	public static final Color CATEGORY_COLOR = new Color(255,255, 230);
	public static final Color TITLE_COLOR = Color.WHITE;
	public static final Color CATEGORY_LABEL_COLOR = Color.RED;
	
	public static final String ROLL_DICE_MESSAGE = "Select Dice, click Roll Again to roll remaning dice";
	public static final String GAME_START_MESSAGE = "Enter User Names, Click Start to Begin Play";
	public static final String SELECT_SCORE_MESSAGE = "Click on the Category You Want to Use";
	public static final String ADD_MORE_USERS_MESSAGE = "Need to Enter at Least 2 Players";

	

	
	public static final int DEFAULT_CELL_WIDTH = 100;
	public static final int DEFAULT_CATEGORY_WIDTH = 150;
	public static final int DEFAULT_CELL_HEIGHT = 30;

	
}
