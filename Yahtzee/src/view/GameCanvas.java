package view;


import acm.graphics.GLabel;
import acm.graphics.GObject;

public class GameCanvas {
	
	public GameCanvas(){
		
		
	}
	
	
	public boolean processMouseClicked(GObject item){
		
		if(item.equals(rollAgain)){
			return(true);
		}
		return(false);
	}

		GLabel rollAgain = new GLabel("Roll Again");
	
}
