package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Model.Dice;
import Model.GameLogic;
import Model.PlayerScores;
import Model.Constants;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import view.CategoryGraphics;
import view.DiceGraphics;
import view.PlayerGraphics;
import view.TextLabel;
import view.UserScoreDisplay;


public class YahtzeeController extends GraphicsProgram{
	
	public YahtzeeController(){
		
		double yOffset = Constants.TOP_BOARDER;
		double xOffset = Constants.SIDE_BOARDER;

		
		game = new GameLogic();
		this.setBackground(Constants.BACKGROUND_COLOR);

		rollAgain = new TextLabel(100,30,"Roll Again");
		add(rollAgain.getLabelDisplay(), xOffset, yOffset);
		rollNumberLabel = new TextLabel(100,30, " Roll Number: " + 1 + " ");
		
		yOffset += rollAgain.getLabelDisplay().getHeight() + 10;
		
		add(rollNumberLabel.getLabelDisplay(), xOffset, yOffset);
		
		yOffset += rollAgain.getLabelDisplay().getHeight() * 2;
		
		for (int i=0; i< Constants.NUMBER_OF_DICE; i++){
			dice.add(new Dice(false));
			diceGraphics.add(new DiceGraphics());
		}
		
		for (DiceGraphics die : diceGraphics){
			add(die.getGraphicObject(), xOffset, yOffset);
			yOffset += die.getGraphicObject().getHeight() + 10;
		}
		
		add(categoryDisplay.getCategoryGridObject(), Constants.X_INDEX_START, Constants.TOP_BOARDER);
		
		addPlayer();
		addPlayer();
		currentPlayer = 0;
		playerListGraphics.get(currentPlayer).setHighlightUser(true);
		setUserCategorySelectState(false);
		resetRollCount();
		addMouseListeners();
		
		userMessage = new TextLabel(500, Constants.DEFAULT_CELL_HEIGHT, Constants.DEFAULT_MESSAGE);
		yOffset = categoryDisplay.getCategoryGridObject().getHeight() + Constants.TOP_BOARDER + Constants.DEFAULT_CELL_HEIGHT * 2;
		add(userMessage.getLabelDisplay(), Constants.SIDE_BOARDER, yOffset);
		
	}
	
	
	
	private void addPlayer(){
		
		double xOffset = 0;
		double yOffset = 0;
		PlayerGraphics temp;
		ArrayList<GObject> scoreDisplayObjectList;
		

		
		playerListScores.add(new PlayerScores());
		
		temp = new PlayerGraphics();
		playerListGraphics.add(temp);
		
		scoreDisplayObjectList = temp.getPlayerGridObjects();
		
		xOffset = Constants.X_INDEX_START + categoryDisplay.getCategoryGridObject().getWidth()
		+ (numberOfPlayers*scoreDisplayObjectList.get(0).getWidth());
		
		yOffset = Constants.TOP_BOARDER + scoreDisplayObjectList.get(0).getHeight();
		

		
		for (GObject o: scoreDisplayObjectList){
			add(o,xOffset, yOffset);
			yOffset += o.getHeight();
		}
		
		numberOfPlayers++;

	}
	
	
	

	
	public void run(){
		


		
		PlayerScores playerScore;
		
		
			
		endButton.addActionListener(this);
		startButton.addActionListener(this);
		add(startButton,SOUTH);
		add(endButton, SOUTH);

		
		selection1.setSelected(true);
		add(selection1, SOUTH);
		
		numPlayers.add(option1);
		numPlayers.add(option2);
		numPlayers.add(option3);
		numPlayers.add(option4);

		option1.setSelected(true);
		
		add(option1, SOUTH);
		add(option2, SOUTH);
		add(option3, SOUTH);
		add(option4, SOUTH);

		
		pick.addItem("Blue");
		pick.addItem("Green");
		pick.addItem("purple");
		pick.addItem("grey");

		pick.setEditable(false);
		pick.setSelectedItem("Blue");
		add(pick, SOUTH);
		
		
		textEntryField = new JTextField("enter name", 20);
		add(textEntryField, CENTER);
		textEntryField.addActionListener(this);
		
		
		while(true){
					
			
		}
	}
	
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		
		System.out.println(" action listener " + command);
		
		
		
	}
	
	public void mouseClicked(MouseEvent mouseEvent){

		Point coordinate = mouseEvent.getPoint();
		
		GObject gameElement = getElementAt(coordinate.getX(), coordinate.getY());

		if(gameElement != null){
			takeActionOnClick(gameElement);
			}
	}
	
	private void takeActionOnClick(GObject gameElement){
		
		
		// Check if user clicked on a dice
		if (CheckDiceObjectClicked(gameElement)){
			return;
		}
		
		// check to see if user clicked on a score cell if active
		if (categorySelectState){
			checkScoreCategorySelected(gameElement);
		}
	}
	
	private void checkScoreCategorySelected(GObject gameElement){
		
		Integer scoreCellIndex;
		
		if (getCurrentUserGraphics().checkScoreObjectSelected(gameElement)){
			
			scoreCellIndex = getCurrentUserGraphics().getIndexScoreObjectSelected(gameElement);
			if (scoreCellIndex != null){
				if (!getCurrentUserGraphics().getCellProtectedState(scoreCellIndex)){
					getCurrentUserGraphics().setDipslayObject(scoreCellIndex, scoreDice(dice,scoreCellIndex));
					getCurrentUserScores().setScore(scoreCellIndex,scoreDice(dice,scoreCellIndex));
					setNextUser();
				} else{
					System.out.println("can't overwrite selected cell");
				}
			}
		}		
	}
	
	
	private boolean CheckDiceObjectClicked(GObject gameElement){
		
		
		if (gameElement.equals(rollAgain.getLabelDisplay())){
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

		
	
	private void rollDice(){
		
		int i =0;	
		
		
		if (rollNumber < Constants.NUMBER_OF_ROLLS){
		
			Dice.rollDice(dice);
		
			for (Dice d: dice){
				diceGraphics.get(i++).setDiceValue(d.getDiceValue());
			}
			//update roll count label
			rollNumber++;
			rollNumberLabel.setLabel(" Roll Number: " + rollNumber + " ");

			if (rollNumber >= Constants.NUMBER_OF_ROLLS){
				setUserCategorySelectState(true);
				rollAgain.setFillColor(Constants.INACTIVE_DEFAULT_FILL_COLOR);
			}
		}	
	}



	private void setUserCategorySelectState(boolean state){
		
		if(state){
			currentPlayerScore = playerListScores.get(currentPlayer);
			currentPlayerGraphics = playerListGraphics.get(currentPlayer);
			categorySelectState = true;
		} else{
			currentPlayerScore = playerListScores.get(currentPlayer);
			currentPlayerGraphics = null;
			categorySelectState = false;
		}
	}
	
	private void setNextUser(){
		
		
		playerListGraphics.get(currentPlayer).setHighlightUser(false);
		if (currentPlayer < numberOfPlayers -1){
			currentPlayer++;
		} else{
			currentPlayer = 0;
			turns++;
		}
		
		if (turns < Constants.NUMBER_OF_TURNS){
			resetRollCount();
			playerListGraphics.get(currentPlayer).setHighlightUser(true);
			setUserCategorySelectState(false);
		} else
			endGame();

	}
	
	private PlayerGraphics getCurrentUserGraphics(){
		
		return (playerListGraphics.get(currentPlayer));
		
	}
	
	private PlayerScores getCurrentUserScores(){
		return(playerListScores.get(currentPlayer));
		
	}
	
	private void resetRollCount(){
		
		for (DiceGraphics d: diceGraphics){
			d.unselectDie();
			dice.get(diceGraphics.indexOf(d)).unselectState();
		}
		rollNumber = 0;
		rollDice();
		rollAgain.setFillColor(Constants.ACTIVE_DEFAULT_FILL_COLOR);
		rollNumberLabel.setLabel(" Roll #: " + rollNumber + " ");

	}
	
	
	private int scoreDice(ArrayList<Dice> dice, int scoreType){
		
		switch (scoreType){
		
		case(Constants.ONES_ENUM): 
			System.out.println("1s score = " +game.scoreSameDice(dice, 1));
			return (game.scoreSameDice(dice, 1));
			
		case(Constants.TWOES_ENUM): 
			System.out.println("2s score = " +game.scoreSameDice(dice, 2));
			return (game.scoreSameDice(dice, 2));
			
		case(Constants.THREES_ENUM): 
			System.out.println("3s score = " +game.scoreSameDice(dice, 3));
			return (game.scoreSameDice(dice, 3));
			
		case(Constants.FOURS_ENUM): 
			System.out.println("4s score = " +game.scoreSameDice(dice, 4));
			return (game.scoreSameDice(dice, 4));
			
		case(Constants.FIVES_ENUM): 
			System.out.println("5s score = " +game.scoreSameDice(dice, 5));
			return (game.scoreSameDice(dice, 5));
			
		case(Constants.SIXES_ENUM): 
			System.out.println("6s score = " +game.scoreSameDice(dice, 6));
			return (game.scoreSameDice(dice, 6));
			
		case(Constants.THREE_OF_A_KIND_ENUM): 
			System.out.println("3K score = " +game.scoreOfAKind(dice, 3));
			return (game.scoreOfAKind(dice, 3));
						
		case(Constants.FOUR_OF_A_KIND_ENUM): 
			System.out.println("4K score = " +game.scoreOfAKind(dice, 4));
			return (game.scoreOfAKind(dice, 4));
			
		case(Constants.YAHTZEE_ENUM): 
			System.out.println("Yahtzee score = " +game.scoreOfAKind(dice, 5));
			return (game.scoreOfAKind(dice, 5));	
			
		case(Constants.FULL_HOUSE_ENUM): 
			System.out.println("3K score = " +game.scoreFullHouse(dice));
			return (game.scoreFullHouse(dice));
		
		case(Constants.SMALL_STRAIGHT_ENUM): 
			System.out.println("SS score = " +game.scoreSmallStraight(dice));
			return (game.scoreSmallStraight(dice));	
			
		case(Constants.LARGE_STRAIGHT_ENUM): 
			System.out.println("LS score = " +game.scoreLargeStraight(dice));
			return (game.scoreLargeStraight(dice));	

		case(Constants.CHANCE_ENUM): 
			System.out.println("Chance score = " +game.scoreChance(dice));
			return (game.scoreChance(dice));				
		
		default:
			System.out.println("No score Type = " );
			break;
		}
		
		return(0);
			
	}
	
	private void endGame(){
		
		for (PlayerScores p : playerListScores){
			
			p.calculateScores();
			p.printScores();
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
			
		player1.setScore(Constants.ONES_ENUM, game.scoreSameDice(test12,1));
		player1.setScore(Constants.TWOES_ENUM, game.scoreSameDice(test22,2));
		player1.setScore(Constants.THREES_ENUM, game.scoreSameDice(test33,3));
		player1.setScore(Constants.FOURS_ENUM, game.scoreSameDice(test44,4));
		player1.setScore(Constants.FIVES_ENUM, game.scoreSameDice(test55,5));
		player1.setScore(Constants.SIXES_ENUM, game.scoreSameDice(test66,6));
		
		player1.setScore(Constants.FULL_HOUSE_ENUM, game.scoreFullHouse(testFH));
		player1.setScore(Constants.SMALL_STRAIGHT_ENUM, game.scoreSmallStraight(testSS));
		player1.setScore(Constants.LARGE_STRAIGHT_ENUM, game.scoreLargeStraight(testLS));
		player1.setScore(Constants.THREE_OF_A_KIND_ENUM, game.scoreOfAKind(test3K,3));
		player1.setScore(Constants.FOUR_OF_A_KIND_ENUM, game.scoreOfAKind(test4K,4));
		player1.setScore(Constants.YAHTZEE_ENUM, game.scoreOfAKind(testYZ,5));
		
		player1.calculateScores();
		player1.printScores();
		
	}

	
	private GameLogic game; 
	
	// objects to track dice graphics and dice logic
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<DiceGraphics> diceGraphics = new ArrayList<DiceGraphics>();
		
	// objects to track player graphics and player scores
	private ArrayList<PlayerScores> playerListScores = new ArrayList<PlayerScores>();
	private ArrayList<PlayerGraphics> playerListGraphics = new ArrayList<PlayerGraphics>();
	private PlayerScores currentPlayerScore = null;
	private PlayerGraphics currentPlayerGraphics = null;
	private int currentPlayer = 0;
	private int rollNumber = 0;
	private int turns = 0;
	private boolean categorySelectState = false;
	
	
	private Dice die = new Dice(true);
	private PlayerScores player1 = new PlayerScores();
	private TextLabel rollAgain; 
	private TextLabel rollNumberLabel;
	private CategoryGraphics categoryDisplay = new CategoryGraphics();
	private int numberOfPlayers = 0;
	
	private TextLabel userMessage;
	
	
	
	JTextField inputText = new JTextField("Press Return", 40); 
	JTextArea textArea = new JTextArea(5,20);
	
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("Stop");
		
	JCheckBox selection1 = new JCheckBox("selection 1");
		
	JRadioButton option1 = new JRadioButton("1 Player");
	JRadioButton option2 = new JRadioButton("2 Players");
	JRadioButton option3 = new JRadioButton("3 Players");
	JRadioButton option4 = new JRadioButton("4 Players");
	ButtonGroup numPlayers = new ButtonGroup();

	JComboBox pick = new JComboBox();
	
	JTextField textEntryField;
}
