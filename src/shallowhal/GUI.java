package shallowhal;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GUI extends JFrame {

	private Board board;
	private JToggleButton[][] buttons;
	private JToggleButton[][] hints;
	private Border emptyBorder = BorderFactory.createEmptyBorder();
	private GridLayout myGrid = new GridLayout(12, 6);
	private GridLayout hintGrid = new GridLayout(24, 2);
	private JPanel panel1;
	private JPanel panel2;
	private Icons iconSelection;
	private JFrame frame;
	private char[] seq = new char[4];
	private char[] palette;
	private String guess;
	private int currentHintRow;
	private int currentSelectRow;
	
	public GUI(Board b) {
		setResizable(false);
		board = b;
		palette = board.getPaletteArray();
		Arrays.fill(seq, '-');
		guess = new String(seq);
		Container c = getContentPane();
		frame = new JFrame("MasterMind David Jones Assignment 3");
		buttons = new JToggleButton[12][6];
		hints = new JToggleButton[24][2];
		panel1 = new JPanel();
		panel1.setLayout(myGrid);
		panel2 = new JPanel();
		panel2.setLayout(hintGrid);
		iconSelection = new Icons();
		initButtonGrid();
		frame.add(panel1, BorderLayout.WEST);
		frame.add(panel2, BorderLayout.EAST);
		frame.setPreferredSize(new Dimension(325, 624));
		frame.pack();
		frame.setVisible(true);
		currentHintRow = 0;
	}
	
	// This method is called in the constructor to initialize the buttons for the guessing and hinting sequences
	public void initButtonGrid() {
		int even = 0;
		while(even != 24) {
			initHintGrid(even, 0, iconSelection.getHintFromIndex(2));
			even += 2;
		}
		for (int row = 0; row < 12; row++) {
			for (int col = 0; col < 5; col++) {
				if(col == 0)
					buttons[row][col] = new JToggleButton(iconSelection.getTheButton());
				else
					buttons[row][col] = new JToggleButton(iconSelection.getEmptyIcon());
				
					buttons[row][col].setPreferredSize(new Dimension(52, 52));
					buttons[row][col].addActionListener(new Listener());
					panel1.add(buttons[row][col]);
					buttons[row][col].setBorder(emptyBorder);
			}
		}
	}
	public void initHint(int row, int col, ImageIcon ic0) {
		hints[row][col] = new JToggleButton(ic0);
		hints[row][col].setPreferredSize(new Dimension(25, 25));
		panel2.add(hints[row][col]);
		hints[row][col].setBorder(emptyBorder);
	}
	
	public void changeHint(int row, int col, ImageIcon ic0) {
		hints[row][col].setIcon(ic0);
	}

	public void initHintGrid(int row, int col, ImageIcon ic) {
		// init top left 
		initHint(row, col, ic);
		
		// init top right
		initHint(row, col+1, ic);
		
		// init lower left
		initHint(row+1, col, ic);
		
		// init lower right
		initHint(row+1, col+1, ic);
	}
	
	public void changeHintGrid(int row, int col, ImageIcon[] ic) {
		// update top left
		changeHint(row, col, ic[0]);
		
		// update top right
		changeHint(row, col+1, ic[1]);
		
		// update lower left
		changeHint(row+1, col, ic[2]);
		
		// update lower right
		changeHint(row+1, col+1, ic[3]);
	}
	
	// This method enters the sequence of colored pins to be shown in the GUI
	public void enterSequence(String seq) {
		
		board.setGuess(seq);
		
	}
	
	// This method enters the hint of right color, location, or both to be shown in the GUI
	public void enterHint(String hint, int row) {
	
		ImageIcon[] ic = iconSelection.createHintArray(hint);
		changeHintGrid(row, 0, ic);
	}
	
	public class Listener implements ActionListener{
		private int currentColor = 0;
		
		public void actionPerformed(ActionEvent event) {
			currentColor++;
			if(currentColor == 6)
				currentColor = 0;
			for (int r = 11; r >= 0; r--) {
				for (int c = 5; c >= 0; c--) {
					if(event.getSource() == buttons[r][c] && c != 0 && c != 5) {
						seq[c-1] = palette[currentColor];
						buttons[r][c].setIcon(iconSelection.getIconFromIndex(currentColor));
						currentSelectRow++;
					}
					if(event.getSource() == buttons[r][0]) {
						String guess = new String(seq);
						enterSequence(guess);
						String hint = board.evaluatePrevGuess();
						changeHintGrid(currentHintRow, 0, iconSelection.createHintArray(hint));
						currentHintRow = currentHintRow + 2;
						break;
					}
				}
			}
		}
	}
}
