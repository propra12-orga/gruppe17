	/**
	* Übergibt der Grafik-Klasse, wie weit die Detonation reicht
	* und löscht die Animation nach einer gewissen Zeit wieder
	* 
	* @param x Position der Bombe
	* @param y Position der Bombe
	* @param unten Anzahl der Felder, die die Flamme nach unten reicht
	* @param oben  Anzahl der Felder, die die Flamme nach oben reicht
	* @param links  Anzahl der Felder, die die Flamme nach links reicht
	* @param rechts  Anzahl der Felder, die die Flamme nach rechts reicht
	*
	*/

public class DetonationRadius extends Thread {
	int rechts, links, oben, unten, x, y;

	
	public DetonationRadius(int a, int b, int c, int d, int e, int f){
//		System.out.println("DetonationRadius erstellt");
		unten = a;
		links = b;
		oben = c;
		rechts = d;
		
		x = e;
		y = f;

	}
	
	public void run(){
		Gamepanel.Zeichner.Detonation(unten, links, oben, rechts, x, y);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		Gamepanel.Zeichner.keineDetonation(Anzahl_Bomben);
//		System.out.println("weg");
	}
}
