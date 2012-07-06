
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
	
	/**
	 * 
	 * @param i je nach input wird das entsprechende feld mit seinen attibuten gesetzt
	 * 1 für zerstörbar, 2 unzerstörbar, 3 begehbar und 4 ausgang
	 */
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
