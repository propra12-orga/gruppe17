/**
 * Klasse mit Bombeninformationen
 * 
 * 
 *
 */
public class Bombe {
	int x,y;
	int Reichweite;
	long Zeit;
	/**
	 * Position und Reichweite der Bombe
	 * 
	 * @param x x-Koordinate des Spielfelds
	 * @param y y-Koordinate des Spielfelds
	 * @param r Reichweite der Bombe 
	 * @param Zeit Aktuelle Systemzeit
	 */
	public Bombe(int x,int y, int r){
		this.x = x;
		this.y = y;
		Reichweite = r;
		Zeit = System.currentTimeMillis();
	}

	public int getX(){
		return x;
	}
	/**
	 * 
	 * @return
	 */
	public int getY(){
		return y;
	}
	/**
	 * 
	 * @return
	 */
	public int getReichweite(){
		return Reichweite;
	}
	/**
	 * 
	 * @return
	 */
	public long getZeit(){
		return Zeit;
	}
	/**
	 * 
	 * @param a entsprechende zeit
	 */
	public void setZeit(long a){
		Zeit = a;
	}

}
