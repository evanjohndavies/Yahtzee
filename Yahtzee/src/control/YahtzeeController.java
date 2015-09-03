package control;

import java.util.ArrayList;

import Model.Dice;
import Model.GameLogic;
import acm.program.GraphicsProgram;

public class YahtzeeController extends GraphicsProgram{
	
	public YahtzeeController(){
		
		for (int i=0; i< NUMBER_OF_DICE; i++){
			dice.add(new Dice(true));
		}
		
	}
	
	
	
	
	
	public void run(){
		
		
		Dice.rollDice(dice);
		System.out.println(Dice.toString(dice));
		Dice.rollDice(die);
		System.out.println(Dice.toString(die));
		Dice.rollDice(die);
		System.out.println(Dice.toString(die));
		Dice.rollDice(die);
		System.out.println(Dice.toString(die));
		Dice.rollDice(die);
		System.out.println(Dice.toString(die));
		Dice.rollDice(die);
		System.out.println(Dice.toString(die));
		System.out.println(Dice.toString(Dice.countDiceRolled(dice)));
		

		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),ONES)));
		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),TWOES)));
		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),THREES)));
		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),FOURS)));
		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),FIVES)));
		System.out.println("score= "+(game.scoreSameDice(Dice.countDiceRolled(dice),SIXES)));
		
		System.out.println("score Full House= "+(game.scoreFullHouse(Dice.countDiceRolled(dice))));
		System.out.println("score Full House= "+(game.scoreFullHouse(testFH)));

		System.out.println("score Small Straight= "+(game.scoreSmallStraight(Dice.countDiceRolled(dice))));
		System.out.println("score Small Straight= "+(game.scoreSmallStraight(testSS)));

		System.out.println("score Large Straingt = "+(game.scoreLargeStraight(Dice.countDiceRolled(dice))));
		System.out.println("score Large Straingt = "+(game.scoreLargeStraight(testLS)));


		System.out.println("score 3 of a Kind= "+(game.scoreOfAKind(Dice.countDiceRolled(dice),3)));
		System.out.println("score 3 of a Kind= "+(game.scoreOfAKind(test3K,3)));

		
		System.out.println("score 4 of a Kind= "+(game.scoreOfAKind(Dice.countDiceRolled(dice),4)));
		System.out.println("score 4 of a Kind= "+(game.scoreOfAKind(test4K,4)));

		System.out.println("score Yahtzee= "+(game.scoreOfAKind(Dice.countDiceRolled(dice),5)));
		System.out.println("score Yahtzee= "+(game.scoreOfAKind(testYZ,5)));

	
		

}

	
	private static final int NUMBER_OF_DICE = 5;
	private static final int ONES = 1;
	private static final int TWOES = 2;
	private static final int THREES = 3;
	private static final int FOURS = 4;
	private static final int FIVES = 5;
	private static final int SIXES = 6;
	
	
	private int[] testFH = {3,2,0,0,0,0};
	private int[] testSS = {1,1,0,1,1,1};
	private int[] testLS = {1,1,1,1,1,1};
	private int[] test3K = {1,1,3,0,0,0};
	private int[] test4K = {0,0,1,4,0,0};
	private int[] testYZ = {0,5,0,0,0,0};


	
	private GameLogic game = new GameLogic();
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private Dice die = new Dice(true);

}
