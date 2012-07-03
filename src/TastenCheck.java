import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/**
 * pr�ft, ob Tasten gedr�ckt wurden und setzt die entsprechenden booleans dann auf true
 * neueRichtung wird gesetzt, wenn die Richtung ge�ndert wurde
 * Alle Variable mit einer "2" am Ende, sind f�r den 2. Spieler
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
	public static boolean itembenutzen;
	
	public static boolean speichern;
	/**
	 * Booleans f�r Spieler2
	 */
	public static boolean hoch2;
	public static boolean runter2;
	public static boolean rechts2;
	public static boolean links2;
	public static boolean bombelegen2;
	public static boolean itembenutzen2;
	
	public static boolean eingabe;
	
	public static boolean clear;
	public static int zaehler = 0;
	/**
	 * setzt entsprechende booleans bei Tastendruck auf 'true'
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_UP){	
			if(!hoch){
				hoch = true;
				neueRichtung1 = true;
				zaehler = (zaehler + 3) % 4;
			}
		}
		if(k.getKeyCode() == KeyEvent.VK_DOWN){
			if(!runter){
				runter = true;
				neueRichtung1 = true;
				zaehler = (zaehler + 1) % 4;
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
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP){	
			hoch = false;
			System.out.println("TASTE");
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
		if(e.getKeyCode() == KeyEvent.VK_ENTER){

			eingabe = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_P){
			speichern = true;
		}
		 if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			itembenutzen=true;
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			itembenutzen2=true;
		}
	}


	@Override
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
	@Override
	public void run() {
	
		
	}

}
