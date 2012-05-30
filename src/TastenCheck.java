import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TastenCheck implements Runnable, KeyListener {
	static boolean hoch;
	static boolean runter;
	static boolean rechts;
	static boolean links;

	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP){	
			hoch = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			runter = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT){
			rechts = true;
		}
		if(k.getKeyCode() == KeyEvent.VK_LEFT){
			links = true;
		}
//		if(k.getKeyCode() == KeyEvent.VK_S){
//			bomb = true;
//		}

		
	}


	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){	
			hoch = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			runter = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rechts = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			links = false;
		}

		
	}


	public void keyTyped(KeyEvent e) {

		
	}


	public void run() {
	
		
	}

}
