import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*; 

public class calculator {
	
	String displayString = "0";
	ArrayList<String> actionSymbols = new ArrayList<String>(Arrays.asList("+","-","*","/","="));

	JFrame f;
	JLabel numberDisplay = new JLabel(displayString);
	
	calculator(){
		
		f = new JFrame("Java Swing Calculator"); 
		
		JPanel buttonContainer = new JPanel();
	    
		f.add(numberDisplay);
		numberDisplay.setSize(300,100);
		
		//Create number buttons
	    for (int i = 0; i < 10; i++) {
	    	String number = String.valueOf(i);
	    	JButton numberButton = new JButton(number);
	    	numberButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			buttonPressed(String.valueOf(number.charAt(0)));
	    		}
	    	});
	    	buttonContainer.add(numberButton);
	    }
	    
	    //Create action buttons
	    for (int i = 0; i < actionSymbols.size(); i++) {
	    	String symbol = actionSymbols.get(i);
	    	JButton actionButton = new JButton(String.valueOf(symbol));
	    	
	    	actionButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			if (symbol == "=") {
	    				enterPressed();
	    			} else {
	    				buttonPressed(symbol);
	    			}
	    		}
	    	});
	    	buttonContainer.add(actionButton);
	    };
	    
	    buttonContainer.setLayout(new GridLayout(4,3));  
	    f.setLayout(new GridLayout(2,1));
	    //setting grid layout of 3 rows and 3 columns  
	  
	    buttonContainer.setSize(300,300);
	    buttonContainer.setVisible(true);
	    f.add(buttonContainer);
	    f.setSize(400,300);  
	    f.setVisible(true); 
		
	}

	public void buttonPressed(String digitInput) {
		String digit = String.valueOf(digitInput);
		if (displayString == "0") { 
			displayString = digit;
		} else {
			displayString += digit;
		};
		numberDisplay.setText(displayString);
	}
	
	public void enterPressed() {
		int posOfLastActionSymbol = -1;
		int numberToRemember = 0;
		String lastAction = "";
		
		//Loop through displayString
		for (int i = 0; i < displayString.length(); i++) {
			String currentChar = String.valueOf(displayString.charAt(i));
			
			//Check for action symbol or end of string
			if (actionSymbols.contains(currentChar) || i == displayString.length() -1 ) {
				int numberToUse = 0;
				boolean isSymbol = actionSymbols.contains(currentChar);
				
				if (isSymbol) {
					numberToUse = Integer.parseInt(displayString.substring(posOfLastActionSymbol+1, i));
				} else if (i == displayString.length() -1) {
					numberToUse = Integer.parseInt(displayString.substring(posOfLastActionSymbol+1, i+1));
				}
				
				if (lastAction == "") {
					numberToRemember = numberToUse;
					lastAction = currentChar;
				} else {
					switch(lastAction) {
					case "+":
						numberToRemember += numberToUse;
						break;
					case "-":
						numberToRemember -= numberToUse;
						break;
					case "*":
						numberToRemember *= numberToUse;
						break;
					case "/":
						numberToRemember /= numberToUse;
						break;
					}
				}
				
				if (isSymbol) {
					lastAction = currentChar;
				}
				displayString = String.valueOf(numberToRemember);
				numberDisplay.setText(displayString);
			}
		}
	}
	
	public static void main(String[] args) {
		new calculator();
	}

}
