package shallowhal;

import java.util.*;

public class Board {
	
	private String[] guessRows;
	
	private int rowSize;
	private String guess;
	private String solution;
	private String hintString;
	private char hint[];
	private int pins;
	private char palette[];
	
	private Random random;
	
	public Board(String cryptic, int pinNumber) {
		this.rowSize = 12;
		pins = pinNumber;
		
		hint = new char[pinNumber];
		guessRows = new String[rowSize];
		Arrays.fill(guessRows, " ");
		solution = cryptic;
		guess = "";
		
		initPaletteArray();
		
		random = new Random();
	}
	
	public void initPaletteArray() {
		palette = new char[6];
		// Red, Orange, Yellow, Green, Blue, Indigo
		palette[0] = 'R';
		palette[1] = 'O';
		palette[2] = 'Y';
		palette[3] = 'G';
		palette[4] = 'B';
		palette[5] = 'I';
	}
	
	// This method generates a n sized sequence of numbers representing each colored pin as a solution to try to guess at

	public String secretCode(int max, int min) {
		char code[] = new char[pins];
		for(int i = 0; i < pins; i++) {
				int digit = random.nextInt(max + 1 - min) + min;
				char color = palette[digit];
				code[i] = color;
			}
		String str = new String(code);
		return str;
	}

	
	// This method obtains a String parameter and inputs it into the guess array
	
	public void setGuess(String g) {
		guess = g;
	}
	// This method is called if the user would like to reset the guess he/she made initially
	
	public void resetGuess() {
		guess = "";
	}
	
	// This method compares the guessed code with the actual code and assigns numerics depending on whether the position of a pin is correct, or if
	// the pin is used at all in the code
	
	public String evaluatePrevGuess() {
		boolean isPinChecked[] = new boolean[solution.length()];
		
		// The array is given all false values because none of the pin arrays in question have been checked
		Arrays.fill(isPinChecked, false);
		
		// Empty the array to be filled with hints after this line of code
		Arrays.fill(hint, '-');
		
		// Checking for solution
		for(int i = 0; i < pins; i++) {
			
			// if pin is correct color and correct position, red hint will be given
			if(guess.charAt(i) == solution.charAt(i)) {
				hint[i] = 'R';
				isPinChecked[i] = true;
			}
			for(int j = 0; j < pins; j++) {
				
				// if pin is correct color but not in the correct position, white hint will be given
				if(guess.charAt(i) == solution.charAt(j) && isPinChecked[j] == false) {
					hint[j] = 'W';
					isPinChecked[j] = true;
				}
			}
		}
		
		
		
//		String eval = hint.toString();
		
		String eval = new String(hint);
		
		hintString = eval;
		
		return eval;

	}
	
	// This method determines if a win is achieved by checking the hint array
	
	public boolean isVictory() {
		boolean vicCondition = true;
		for(int i = 0; i < pins; i++) {
			if(hint[i] != 'R')
				vicCondition  = false;
		}
		return vicCondition;
	}
	
	public void printSolution() {
		System.out.println(solution);
	}

	// These methods add or remove elements from their corresponding arrays
	
	public void addToRowsArray(String row, int index) {
		guessRows[index] = row;
	}
	
	public void printGuesses() {
		for(int i = 0; i < guessRows.length; i++) {
			if(guessRows.equals(" ")) {
				System.out.println(guessRows[i] + " test");
			}
		}
	}
	
	// These methods are a set of getters and setters
	
	public String getSolution() {
		return solution;
			
	}
	
	public void setSolution(String s) {
		solution = s;
	}
	
	public char[] getHintArray() {
		return hint;
	}
	
	public String getHintString() {
		String hint = new String(getHintArray());
		return hint;
	}
	
	public int getRowSize() {
		return rowSize;
	}
	
	public void setRowSize(int rs) {
		rowSize = rs;
	}
	
	public char[] getPaletteArray() {
		return palette;
	}

}
