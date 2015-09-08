package control;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Model.Dice;
import Model.GameLogic;
import Model.PlayerScores;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import view.DiceGraphics;
import view.GameCanvas;

public class YahtzeeController extends GraphicsProgram{
	
	public YahtzeeController(){
		
		double yOffset = 0;

		
		for (int i=0; i< NUMBER_OF_DICE; i++){
			dice.add(new Dice(false));
			diceGraphics.add(new DiceGraphics());
		}
		
		for (DiceGraphics die : diceGraphics){
			add(die.getGraphicObject(), 0, yOffset);
			yOffset += die.getGraphicObject().getHeight() + 10;
		}
		
		rollAgain.setVisible(true);
		add(rollAgain, 100, 100);
		
		addMouseListeners();
	}
	
	
	
	public void run(){

		rollDice();
		
		while(true){
			
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
			if (gameElement.equals(rollAgain)){
				rollDice();
			}
			
			for (DiceGraphics d: diceGraphics){
				if (gameElement.equals(d.getGraphicObject())){
					d.selectDie();
				}
			}
		}		
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

	
	


	
	private GameLogic game = new GameLogic();
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<DiceGraphics> diceGraphics = new ArrayList<DiceGraphics>();
	private Dice die = new Dice(true);
	private PlayerScores player1 = new PlayerScores();
	private GameCanvas canvas;
	private GLabel rollAgain = new GLabel("Roll Again");

}
