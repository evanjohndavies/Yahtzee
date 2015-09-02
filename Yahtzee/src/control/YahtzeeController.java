package control;

import Model.Dice;
import Model.GameLogic;
import acm.program.GraphicsProgram;

public class YahtzeeController extends GraphicsProgram{
	
	public void run(){
		
		for (int i = 0; i< 100; i++){
			System.out.println(dice.rollDice());
		}
			
		}

		
		private Dice dice = new Dice(true);
}
