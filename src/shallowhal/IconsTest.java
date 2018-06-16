package shallowhal;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import org.junit.Test;

public class IconsTest {
	
	Icons ic0 = new Icons();
	Icons ic1 = new Icons();

	@Test
	public void test() {
		assertElementInit();
		assertElementIconsInit();
		assertInit(ic0.getHints());
		assertInit(ic1.getPrismed());
		assertHintIconsInit();
	}
	
	public void assertInit(ArrayList<ImageIcon> icons) {
		for(int index = 0; index < icons.size(); index++) {
			assertNotNull(icons.get(index));
		}
	}
	public void assertElementInit() {
		assertNotNull(new ImageIcon("templates/pressMe.png"));
	}
	
	public void assertElementIconsInit() {
		assertNotNull(ic0.getTheButton());
	}
	public void assertHintIconsInit() {
		assertNotNull(ic0.getHintFromIndex(0));
		assertNotNull(ic0.getHintFromIndex(1));
		assertNotNull(ic0.getHintFromIndex(2));
		
	}
}

