public class Feld {

	boolean zerstoerbar = false;
	boolean unzerstoerbar = false;
	boolean begehbar = false;
	
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
	}
	
	public void setWeg(){
		begehbar = true;
		zerstoerbar = false;
	}
}
