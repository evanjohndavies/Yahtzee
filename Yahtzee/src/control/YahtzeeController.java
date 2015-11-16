package control;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Model.Dice;
import Model.GameLogic;
import Model.PlayerScores;
import Model.Scores;
import Model.Constants;
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
			}
			
		if(!gameStartSelected && e.getSource() == textEntryField){
					addPlayer(command);
					textEntryField.setText("");
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
		
		Scores scoreType;
		PlayerGraphics player = playerListGraphics.get(currentPlayer);
		UserScoreDisplay scoreObj = player.checkScoreObjectSelected(gameElement);
		
		
		if (scoreObj != null && !scoreObj.getProtectedState()){
			
				scoreType = scoreObj.getCategory();
				scoreObj.setScoreDisplay(scoreDice(dice,scoreType));
				getCurrentUserScores().setScore(scoreType.getIndex(),scoreDice(dice,scoreType));
				setNextUser();
			} else{
				//System.out.println("can't overwrite selected cell");
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
		
		for ( Scores s: sumCategories){
			
			playerListGraphics.get(currentPlayer).updateDipslayObject(s.getIndex(),
					playerListScores.get(currentPlayer).getScore(s.getIndex()));
			
		}
		
		
		
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
	
	
	
	private int scoreDice(ArrayList<Dice> dice, Scores scoreType){
		
		
		switch (scoreType){
		
		case ONES: 
			return (game.scoreSameDice(dice, 1));
			
		case TWOS: 
			return (game.scoreSameDice(dice, 2));
			
		case THREES: 
			return (game.scoreSameDice(dice, 3));
			
		case FOURS: 
			return (game.scoreSameDice(dice, 4));
			
		case FIVES: 
			return (game.scoreSameDice(dice, 5));
			
		case SIXES: 
			return (game.scoreSameDice(dice, 6));
			
		case THREE_OF_A_KIND: 
			return (game.scoreOfAKind(dice, 3));
						
		case FOUR_OF_A_KIND: 
			return (game.scoreOfAKind(dice, 4));
			
		case YAHTZEE: 
			return (game.scoreOfAKind(dice, 5));	
			
		case FULL_HOUSE: 
			return (game.scoreFullHouse(dice));
		
		case SM_STRAIGHT: 
			return (game.scoreSmallStraight(dice));	
			
		case LG_STRAIGHT: 
			return (game.scoreLargeStraight(dice));	

		case CHANCE: 
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
		boolean tie = false;
		
		for (PlayerScores p : playerListScores){
			if(p.getScore(Scores.TOTAL.getIndex()) > highScore){
				highScore = p.getScore(Scores.TOTAL.getIndex());
				winner = p;
			}
			if(p.getScore(Scores.TOTAL.getIndex()) == highScore){
				tie = true;
			}
		}
		
		if (tie){
			userMessage.setLabel("Tie Game with " + highScore + "  points");
		} else{

			userMessage.setLabel("The Winner is: " + winner.getPlayerName() + 
					"  with " + highScore + "  points");
		}
		
	}
	

	
	private GameLogic game; 
	
	private Scores[] sumCategories = {Scores.UPPER_SCORE_BONUS, Scores.UPPER_SCORES_SUBTOTAL, 
										 Scores.LOWER_SCORES_SUBTOTAL, Scores.TOTAL};
	
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
	
	//JTextField inputText = new JTextField("Enter Name", 40); 
	//JTextArea textArea = new JTextArea(5,20);
	
	JButton startButton = new JButton("Start");
	JButton endButton = new JButton("Stop");
	
	JTextField textEntryField;
}
