package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Model.Dice;
import Model.GameLogic;
import Model.PlayerScores;
import Model.Constants;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import view.CategoryGraphics;
import view.DiceGraphics;
import view.PlayerGraphics;
import view.TextLabel;


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
		

		currentPlayer = 0;
		resetRollCount();
		addMouseListeners();
		
		userMessage = new TextLabel(500, Constants.DEFAULT_CELL_HEIGHT, Constants.GAME_START_MESSAGE);
		yOffset = categoryDisplay.getCategoryGridObject().getHeight() + Constants.TOP_BOARDER + Constants.DEFAULT_CELL_HEIGHT * 2;
		add(userMessage.getLabelDisplay(), Constants.SIDE_BOARDER, yOffset);
		
		
		endButton.addActionListener(this);
		add(endButton,SOUTH);
		endButton.setVisible(false);
		
		
		startButton.addActionListener(this);
		add(startButton,SOUTH);

		add(label, SOUTH);
		
		textEntryField = new JTextField("", 15);
		add(textEntryField, SOUTH);
		textEntryField.addActionListener(this);
	}		
		

	
	
	public void actionPerformed(ActionEvent e){
		String command = e.getActionCommand();
		
		if(command.compareTo("Start") == 0){
			if (numberOfPlayers < 2){
				userMessage.setLabel(Constants.ADD_MORE_USERS_MESSAGE);
			}else{
				startGame();
				userMessage.setLabel(Constants.ROLL_DICE_MESSAGE);
			}
			
		} else {
			if(command.compareTo("Stop") == 0){
			endGame();
			} else{
				if(!gameStartSelected){
					addPlayer(command);
					textEntryField.setText("");
					}
				}
			}
		}
	
		
	
	public void mouseClicked(MouseEvent mouseEvent){

		Point coordinate = mouseEvent.getPoint();
		
		// don't process mouse events on canvas till game started
		if (gameStartSelected){
			GObject gameElement = getElementAt(coordinate.getX(), coordinate.getY());

			if(gameElement != null){
				takeActionOnClick(gameElement);
				}
		}
	}
	
	private void addPlayer(String name){
		
		double xOffset = 0;
		double yOffset = 0;
		PlayerScores newPlayer = new PlayerScores();
		ArrayList<GObject> scoreDisplayObjectList;
		
		playerNames.add(new TextLabel(Constants.DEFAULT_CELL_WIDTH, Constants.DEFAULT_CELL_HEIGHT, name));
		newPlayer.setPlayerName(name);
		playerListScores.add(newPlayer);

		
		playerListGraphics.add(new PlayerGraphics());
		
		scoreDisplayObjectList = playerListGraphics.get(numberOfPlayers).getPlayerGridObjects();
		
		xOffset = Constants.X_INDEX_START + categoryDisplay.getCategoryGridObject().getWidth()
		+ (numberOfPlayers*scoreDisplayObjectList.get(0).getWidth());
		
		yOffset = Constants.TOP_BOARDER;
		add(playerNames.get(numberOfPlayers).getLabelDisplay(), xOffset, yOffset);
		
		yOffset = Constants.TOP_BOARDER + playerNames.get(0).getLabelDisplay().getHeight();
		
		
		for (GObject o: scoreDisplayObjectList){
			add(o,xOffset, yOffset);
			yOffset += o.getHeight();
		}
		
		numberOfPlayers++;
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
					//System.out.println("can't overwrite selected cell");
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
				userMessage.setLabel(Constants.SELECT_SCORE_MESSAGE);

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
		playerNames.get(currentPlayer).setFillColor(Constants.INACTIVE_DEFAULT_FILL_COLOR);
		
		// update scores and display updated totals
		playerListScores.get(currentPlayer).calculateScores();
		playerListGraphics.get(currentPlayer).updateDipslayObject(Constants.UPPER_SCORE_BONUS_ENUM, 
				playerListScores.get(currentPlayer).getScore(Constants.UPPER_SCORE_BONUS_ENUM));
		playerListGraphics.get(currentPlayer).updateDipslayObject(Constants.UPPER_SCORE_SUBTOTAL_ENUM, 
				playerListScores.get(currentPlayer).getScore(Constants.UPPER_SCORE_SUBTOTAL_ENUM));
		playerListGraphics.get(currentPlayer).updateDipslayObject(Constants.LOWER_SCORE_SUBTOTAL_ENUM, 
				playerListScores.get(currentPlayer).getScore(Constants.LOWER_SCORE_SUBTOTAL_ENUM));
		playerListGraphics.get(currentPlayer).updateDipslayObject(Constants.TOTAL_ENUM, 
				playerListScores.get(currentPlayer).getScore(Constants.TOTAL_ENUM));
		
		
		if (currentPlayer < numberOfPlayers -1){
			currentPlayer++;
		} else{
			currentPlayer = 0;
			turns++;
		}
		
		
		if (turns < Constants.NUMBER_OF_TURNS){
			resetRollCount();
			playerListGraphics.get(currentPlayer).setHighlightUser(true);
			playerNames.get(currentPlayer).setFillColor(Constants.CELL_SELCTED_USER_COLOR);
			setUserCategorySelectState(false);
			userMessage.setLabel(Constants.ROLL_DICE_MESSAGE);
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
			return (game.scoreSameDice(dice, 1));
			
		case(Constants.TWOES_ENUM): 
			return (game.scoreSameDice(dice, 2));
			
		case(Constants.THREES_ENUM): 
			return (game.scoreSameDice(dice, 3));
			
		case(Constants.FOURS_ENUM): 
			return (game.scoreSameDice(dice, 4));
			
		case(Constants.FIVES_ENUM): 
			return (game.scoreSameDice(dice, 5));
			
		case(Constants.SIXES_ENUM): 
			return (game.scoreSameDice(dice, 6));
			
		case(Constants.THREE_OF_A_KIND_ENUM): 
			return (game.scoreOfAKind(dice, 3));
						
		case(Constants.FOUR_OF_A_KIND_ENUM): 
			return (game.scoreOfAKind(dice, 4));
			
		case(Constants.YAHTZEE_ENUM): 
			return (game.scoreOfAKind(dice, 5));	
			
		case(Constants.FULL_HOUSE_ENUM): 
			return (game.scoreFullHouse(dice));
		
		case(Constants.SMALL_STRAIGHT_ENUM): 
			return (game.scoreSmallStraight(dice));	
			
		case(Constants.LARGE_STRAIGHT_ENUM): 
			return (game.scoreLargeStraight(dice));	

		case(Constants.CHANCE_ENUM): 
			return (game.scoreChance(dice));				
		
		default:
			break;
		}
		
		return(0);
			
	}
	
	private void startGame(){
		
		gameStartSelected = true;
		startButton.setVisible(false);
		textEntryField.setVisible(false);
		label.setVisible(false);
		endButton.setVisible(true);
			
		playerListGraphics.get(currentPlayer).setHighlightUser(true);
		playerNames.get(currentPlayer).setFillColor(Constants.CELL_SELCTED_USER_COLOR);
	
		setUserCategorySelectState(false);
	}



	private void endGame(){
		
		PlayerScores winner = null;
		int highScore = 0;
		
		for (PlayerScores p : playerListScores){
			if(p.getScore(Constants.TOTAL_ENUM) > highScore){
				highScore = p.getScore(Constants.TOTAL_ENUM);
				winner = p;
			}
		}
		
		userMessage.setLabel("The Winner is: " + winner.getPlayerName() + 
				"  with " + highScore + "  points");

		
	}
	

	
	private GameLogic game; 
	
	// objects to track dice graphics and dice logic
	private ArrayList<Dice> dice = new ArrayList<Dice>();
	private ArrayList<DiceGraphics> diceGraphics = new ArrayList<DiceGraphics>();
		
	// objects to track player graphics and player scores
	private ArrayList<PlayerScores> playerListScores = new ArrayList<PlayerScores>();
	private ArrayList<PlayerGraphics> playerListGraphics = new ArrayList<PlayerGraphics>();
	private ArrayList<TextLabel> playerNames = new ArrayList<TextLabel>();
	private PlayerScores currentPlayerScore = null;
	private PlayerGraphics currentPlayerGraphics = null;
	private int currentPlayer = 0;
	private int numberOfPlayers = 0;
	private int rollNumber = 0;
	private int turns = 0;
	private boolean categorySelectState = false;
	private boolean gameStartSelected = false;

	
	
	private Dice die = new Dice(true);
	private PlayerScores player1 = new PlayerScores();
	private TextLabel rollAgain; 
	private TextLabel rollNumberLabel;
	private CategoryGraphics categoryDisplay = new CategoryGraphics();
	
	private TextLabel userMessage;
	
	
	JLabel label = new JLabel("Enter Name");
	
	JTextField inputText = new JTextField("Enter Name", 40); 
	JTextArea textArea = new JTextArea(5,20);
	
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("Stop");
	
	JTextField textEntryField;
}
