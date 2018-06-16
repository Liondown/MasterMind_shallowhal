package shallowhal;

import javax.swing.ImageIcon;
import java.util.ArrayList;


public class Icons {
	
	private ImageIcon theButton;
	private ImageIcon empty;
	private int[] hintSize;
	private String template;
	
	private ArrayList<ImageIcon> Hints = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> Prismed = new ArrayList<ImageIcon>();
	
	public Icons() {
		theButton = new ImageIcon("templates/pressMe.png");
		empty = new ImageIcon("templates/Button_Empty_2.png");
		initPrismed();
		initHints();
		hintSize = new int[Hints.size()];
		
	}
	
	public void initPrismed() {
		Prismed.add(new ImageIcon("templates/Button_RED.png"));
		Prismed.add(new ImageIcon("templates/Button_ORANGE.png"));
		Prismed.add(new ImageIcon("templates/Button_YELLOW.png"));
		Prismed.add(new ImageIcon("templates/Button_GREEN.png"));
		Prismed.add(new ImageIcon("templates/Button_BLUE.png"));
		Prismed.add(new ImageIcon("templates/Button_INDIGO.png"));
	}
	
	public void initHints() {
		Hints.add(new ImageIcon("templates/R.png"));
		Hints.add(new ImageIcon("templates/W.png"));
		Hints.add(new ImageIcon("templates/-.png"));
		Hints.add(new ImageIcon("templates/empty_3.png"));
		
	}
	
	// These methods are getters and setters
	
	public ImageIcon getEmptyIcon() {
		return empty;
	}
	
	public ImageIcon getTheButton() {
		return theButton;
	}
	
	public ImageIcon getInconclusiveHint() {
		return Hints.get(2);
	}
	
	public ImageIcon getBlankHint() {
		return Hints.get(3);
	}
	
	public ImageIcon[] getEmptyHintArray() {
		ImageIcon[] ic = new ImageIcon[4];
		for(int i = 0; i < 4; i++) {
			ic[i] = getBlankHint();
		}
		return ic;
	}
	
	public ImageIcon getHintFromIndex(int index) {
		ImageIcon c0 = Hints.get(index);
		return c0;
	}
	
	public ImageIcon[] createHintArray(String hint) {
		ImageIcon[] icons = new ImageIcon[4];
		char[] hintArray = hint.toCharArray();
		for(int index = 0; index < hint.length(); index++) {
			char letterHint = hintArray[index];
			if(letterHint == 'R') {
				icons[index] = Hints.get(0);
			}
			else if(letterHint == 'W') {
				icons[index] = Hints.get(1);
			}
			else
				icons[index] = Hints.get(2);
		}
		return icons;
	}
	
	public ImageIcon getIconFromIndex(int index) {
		ImageIcon c0 = Prismed.get(index);
		return c0;
	}
	
	public ArrayList<ImageIcon> getHints(){
		return Hints;
	}
	
	public ArrayList<ImageIcon> getPrismed(){
		return Prismed;
	}

}
