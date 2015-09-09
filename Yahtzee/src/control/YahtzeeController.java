package control;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Model.Dice;
import Model.GameLogic;
import Model.PlayerScores;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import view.CategoryGraphics;
import view.DiceGraphics;
import view.GameCanvas;
import view.PlayerGraphics;


public class YahtzeeController extends GraphicsProgram{
	
	public YahtzeeController(){
		
		double yOffset = TOP_BOARDER;
		double xOffset = SIDE_BOARDER;
		GRect temp;

		

		temp = new GRect(0,0,80,20);
		add(temp,xOffset, yOffset);
		
		add(rollAgain, (xOffset + (temp.getWidth() - rollAgain.getWidth())/2), 
						yOffset+ rollAgain.getHeight() + (rollAgain.getHeight()-temp.getHeight())/2);
		
		yOffset += temp.getHeight() + 20;
		
		for (int i=0; i< NUMBER_OF_DICE; i++){
			dice.add(new Dice(false));
			diceGraphics.add(new DiceGraphics());
		}
		
		for (DiceGraphics die : diceGraphics){
			add(die.getGraphicObject(), xOffset, yOffset);
			yOffset += die.getGraphicObject().getHeight() + 10;
		}
		
		add(categoryDisplay.getCategoryGridObject(), X_INDEX_START, TOP_BOARDER);
		
		addPlayer();
		currentPlayer = 0;
		currentPlayerScore = playerList.get(currentPlayer);
		currentPlayerGraphics = playerListGraphics.get(currentPlayer);
		
		addMouseListeners();
	}
	
	
	
	private void addPlayer(){
		
		double xOffset = 0;
		double yOffset = 0;
		PlayerGraphics temp;
		ArrayList<GObject> scoreDisplayObjectList;
		

		
		playerList.add(new PlayerScores());
		
		temp = new PlayerGraphics();
		playerListGraphics.add(temp);
		
		scoreDisplayObjectList = temp.getPlayerGridObjects();
		
		xOffset = X_INDEX_START + categoryDisplay.getCategoryGridObject().getWidth()
		+ (numberOfPlayers*scoreDisplayObjectList.get(0).getWidth());
		
		yOffset = TOP_BOARDER + scoreDisplayObjectList.get(0).getHeight();
		
		for (GObject o: scoreDisplayObjectList){
			add(o,xOffset, yOffset);
			yOffset += o.getHeight();
		}
		
		numberOfPlayers++;

	}
	
	
	
	public void run(){
		
		PlayerScores playerScore;
		
		addPlayer();

		rollDice();
		
		
		while(true){
			for(PlayerGraphics playerGraphics: playerListGraphics){
				
				
			}
			
		}
		
	}
	
	private void rollDice(){
		
		int i =0;	
		Dice.rollDice(dice);
		
		for (Dice d: dice){
			diceGraphics.get(i++).setDiceValue(d.getDiceValue());
		}
		
	}
	
	
	
	public void mouseClicked(MouseEvent mouseEvent){

		Point coordinate = mouseEvent.getPoint();
		
		GObject gameElement = getElementAt(coordinate.getX(), coordinate.getY());

		if(gameElement != null){
			takeActionOnClick(gameElement);
			}
	}
	
	private void takeActionOnClick(GObject gameElement){
		
		if (CheckDiceObjectClicked(gameElement)){
			return;
		}
		
		if (getCurrentUserGraphics().checkScoreObjectSelected(gameElement)){
			System.out.println("game score element clicked");
		}
		
		
	}
	
	
	private boolean CheckDiceObjectClicked(GObject gameElement){
		
		
		if (gameElement.equals(rollAgain)){
			rollDice();
			return(true);
		}
		
		for (DiceGraphics d: diceGraphics){
			if (gameElement.equals(d.getGraphicObject())){
				d.selectDie();
				dice.get(diceGraphics.indexOf(d)).setSelectedState();
				return(true);
			}
		}
		return(false);
		
	}
	
	
	
	
	private void setNextUser(){
		
		
		if (currentPlayer < numberOfPlayers){
			currentPlayer++;
		} else{
			currentPlayer = 0;
		}
		
		currentPlayer++;
		currentPlayerScore = playerList.get(currentPlayer);
		currentPlayerGraphics = playerListGraphics.get(currentPlayer);
		
	}
	
	private PlayerGraphics getCurrentUserGraphics(){
		
		return (playerListGraphics.get(currentPlayer));
		
	}
	
	
	
	
	private void testDice (){
		
		int[] test12 = {5,0,0,0,0,0};
		int[] test22 = {0,4,0,0,0,1};
		int[] test33 = {0,1,4,0,0,0};
		int[] test44 = {0,0,1,4,0,1};
		int[] test55 = {0,0,0,0,4,1};
		int[] test66 = {0,0,0,1,0,4};

		int[] testFH = {3,2,0,0,0,0};
		int[] testSS = {1,1,0,1,1,1};
		int[] testLS = {1,1,1,1,1,1};
		int[] test3K = {1,1,3,0,0,0};
		int[] test4K = {0,0,1,4,0,0};
		int[] testYZ = {0,5,0,0,0,0};

		
		System.out.println(Dice.toString(dice));
		System.out.println(Dice.toString(Dice.countDiceRolled(dice)));
			
		player1.setScore(ONES -1, game.scoreSameDice(test12,ONES));
		player1.setScore(TWOES -1, game.scoreSameDice(test22,TWOES));
		player1.setScore(THREES-1, game.scoreSameDice(test33,THREES));
		player1.setScore(FOURS-1, game.scoreSameDice(test44,FOURS));
		player1.setScore(FIVES-1, game.scoreSameDice(test55,FIVES));
		player1.setScore(SIXES-1, game.scoreSameDice(test66,SIXES));
		
		player1.setScore(FULL_HOUSE, game.scoreFullHouse(testFH));
		player1.setScore(SMALL_STRAIGHT, game.scoreSmallStraight(testSS));
		player1.setScore(LARGE_STRAIGHT, game.scoreLargeStraight(testLS));
		player1.setScore(THREE_OF_A_KIND, game.scoreOfAKind(test3K,3));
		player1.setScore(FOUR_OF_A_KIND, game.scoreOfAKind(test4K,4));
		player1.setScore(YAHTZEE, game.scoreOfAKind(testYZ,5));
		
		player1.calculateScores();
		player1.printScores();
		
	}

	
	private static final int NUMBER_OF_DICE = 5;
	private static final int ONES = 1;
	private static final int TWOES = 2;
	private static final int THREES = 3;
	private static final int FOURS = 4;
	private static final int FIVES = 5;
	private static final int SIXES = 6;
	private static final int THREE_OF_A_KIND = 8;
	private static final int FOUR_OF_A_KIND = 9;
	private static final int FULL_HOUSE = 10;
	private static final int SMALL_STRAIGHT = 11;
	private static final int LARGE_STRAIGHT = 12;
	private static final int YAHTZEE = 13;
	private static final int TOP_BOARDER = 25;
	private static final int SIDE_BOARDER = 25;
	private static final int X_INDEX_START = 150;

	
	private GameLogic game = new GameLogic();
	
	// objects to track dice graphics and dice logic
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<DiceGraphics> diceGraphics = new ArrayList<DiceGraphics>();
		
	// objects to track player graphics and player scores
	private ArrayList<PlayerScores> playerList = new ArrayList<PlayerScores>();
	private ArrayList<PlayerGraphics> playerListGraphics = new ArrayList<PlayerGraphics>();
	private PlayerScores currentPlayerScore = null;
	private PlayerGraphics currentPlayerGraphics = null;
	private int currentPlayer = 0;
	
	
	private Dice die = new Dice(true);
	private PlayerScores player1 = new PlayerScores();
	private GameCanvas canvas;
	private GLabel rollAgain = new GLabel("Roll Again");
	private CategoryGraphics categoryDisplay = new CategoryGraphics();
	private int numberOfPlayers = 0;

	


}
