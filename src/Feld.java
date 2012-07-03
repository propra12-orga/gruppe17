
/**
 * Je nachdem welcher Parameter i ist, wird der entsprechende boolean auf true gesetzt
 * 
 * 
 *
 */
public class Feld {

	boolean zerstoerbar = false;
	boolean unzerstoerbar = false;
	boolean begehbar = false;
	boolean Ausgang = false;
	boolean liegtItem = false;
	
	
	public Feld(int i){
		if(i == 1) {
			zerstoerbar = true;
		}
		if(i == 2) {
			unzerstoerbar = true;
		}
		if(i == 3) {
			begehbar = true;
		}
		if(i == 4) {
			Ausgang = true;
			zerstoerbar = true;
		}
	}
	/*
	 * der Weg auf dem sich der Bomberman bewegt
	 * 
	 */
	public void setWeg(){
		begehbar = true;
		zerstoerbar = false;
	}
}
