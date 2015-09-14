package Model;

import java.awt.Color;
import java.awt.Font;

public interface Constants {
	
	public static final int NUMBER_OF_DICE = 5;
	public static final int ONES_ENUM = 0;
	public static final int TWOES_ENUM = 1;
	public static final int THREES_ENUM = 2;
	public static final int FOURS_ENUM = 3;
	public static final int FIVES_ENUM = 4;
	public static final int SIXES_ENUM = 5;
	public static final int THREE_OF_A_KIND_ENUM = 8;
	public static final int FOUR_OF_A_KIND_ENUM = 9;
	public static final int FULL_HOUSE_ENUM = 10;
	public static final int SMALL_STRAIGHT_ENUM = 11;
	public static final int LARGE_STRAIGHT_ENUM = 12;
	public static final int YAHTZEE_ENUM = 13;
	public static final int CHANCE_ENUM = 14;
	public static final int TOP_BOARDER = 25;
	public static final int SIDE_BOARDER = 25;
	public static final int X_INDEX_START = 150;
	
	public static final double PLAYER_SCORE_CELL_HEIGHT = 30;
	public static final double PLAYER_SCORE_CELL_WIDTH = 100;
	
	
	public static final Font DEFAULT_FONT = new Font("TimesRoman", Font.BOLD, 14);
	public static final Color CELL_DEFAULT_COLOR = Color.WHITE;
	public static final Color CELL_HIGHTLIGHT_COLOR = Color.LIGHT_GRAY;
	public static final Color DEFUALT_FONT_COLOR = Color.BLACK;

	
	public static final int DEFAULT_CELL_WIDTH = 100;
	public static final int DEFAULT_CELL_HEIGHT = 30;

	
}
