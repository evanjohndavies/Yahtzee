package view;
import acm.graphics.*;
	import acm.program.*;
	import java.awt.*;
	import acm.util.*;
	import java.util.*;
	


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
	
	
	

}
