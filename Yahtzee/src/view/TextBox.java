package view;
import acm.graphics.*;
	import acm.program.*;
	import java.awt.*;
	import acm.util.*;
	import java.util.*;

import javax.swing.JTextField;
	


public class TextBox extends GRect {
	
	GLabel DisplayText;

	public TextBox(double arg0, double arg1, double arg2, double arg3, String text) {
		super(arg0, arg1, arg2, arg3);
		this.DisplayText = new GLabel(text);
		DisplayText.setVisible(true);

	}
	
	public void setText(String text){
		
		DisplayText.setLabel("text");
	}
	
	
/*	
	
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
	add(textEntryField, SOUTH);
	textEntryField.addActionListener(this);
	
*/	
	
	
	
	
	
	

}
