package shallowhal;

import java.util.*;

public class Game {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int pinNumber = 4;
		int remainderTurns = 12;
		Board board = new Board("ROYG", pinNumber);
		String nextLevel = board.secretCode(3, 0);
		board.setSolution(nextLevel);
		GUI gui = new GUI(board);
	}
}
