import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * prüft, ob Tasten gedrückt wurden und setzt die entsprechenden booleans dann auf true
 * neueRichtung wird gesetzt, wenn die Richtung geändert wurde
 * Alle Variable mit einer "2" am Ende, sind für den 2. Spieler
 *
 */
public class TastenCheck extends TimerTask implements Runnable, KeyListener {
	
	public static boolean hoch;
	public static boolean runter;
	public static boolean rechts;
	public static boolean links;
	public static boolean neueRichtung1;
	public static boolean neueRichtung2;
	public static boolean bombelegen;
	/**
	 * Booleans für Spieler2
	 */
	public static boolean hoch2;
	public static boolean runter2;
	public static boolean rechts2;
	public static boolean links2;
	public static boolean bombelegen2;
	
	public static boolean clear;
	
	/**
	 * setzt entsprechende booleans bei Tastendruck auf 'true'
	 * 
	 */
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP){	
			if(!hoch){
				hoch = true;
				neueRichtung1 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			if(!runter){
				runter = true;
				neueRichtung1 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_RIGHT){
			if(!rechts){
				rechts = true;
				neueRichtung1 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_LEFT){
			if(!links){
				links = true;
				neueRichtung1 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_W){	
			if(!hoch2){
				hoch2 = true;
				neueRichtung2 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_S){
			if(!runter2){
				runter2 = true;
				neueRichtung2 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_D){
			if(!rechts2){
				rechts2 = true;
				neueRichtung2 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_A){
			if(!links2){
				links2 = true;
				neueRichtung2 = true;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_C){
			clear = true;
		}
//		if(k.getKeyCode() == KeyEvent.VK_S){
////			bombelegen = true;
//		}

		
	}

	/**
	 * setzt entsprechende booleans bei Loslassen der jeweiligen Taste wieder auf false
	 * 
	 */
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
		if(e.getKeyCode() == KeyEvent.VK_L){
			bombelegen = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W){	
			hoch2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S){
			runter2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D){
			rechts2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A){
			links2 = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			bombelegen2 = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_C){
			clear = false;
		}
		
	}


	public void keyTyped(KeyEvent e) {
//		if(e.getKeyCode() == KeyEvent.VK_UP){	
//			neueRichtung1 = true;
//		}
//		if(e.getKeyCode() == KeyEvent.VK_DOWN){
//			neueRichtung1 = true;
//		}
//		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//			neueRichtung1 = true;
//		}
//		if(e.getKeyCode() == KeyEvent.VK_LEFT){
//			neueRichtung1 = true;
//		}
//		

		
	}

	/**
	 * startet den Thread
	 * 
	 */
	public void run() {
	
		
	}

}
