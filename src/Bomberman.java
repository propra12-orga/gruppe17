/**
 * Spielfigur mit Position, Geschwindigkeit, Leben, Bomben, Items 
 * 
 *
 *
 */
public class Bomberman {
	public double x;
	public double y;
	double speed=0.000675;
	int Leben = 1;
	int BombenReichweite=2;
	int BombenAnzahl = 1;
	boolean Handschuh = false;
	boolean Kicker = false;
	int toleranz = 1;
	boolean tot;
	
	/**
	 * Position des Bomberman
	 * 
	 * @param a x-Koordinate im Spielfeld
	 * @param b y-Koordinate im Spielfeld
	 */
	public Bomberman(int a, int b){
		this.x=a;
		this.y=b;
	}
	
	public void geheNachOben(){
		this.y-=speed;
		if(!FeldBegehbar(1)) this.y+=speed;
//		System.out.println("POS: " + x + y);
	}
	
	public void geheNachUnten(){
		this.y+=speed;
		if(!FeldBegehbar(2)) this.y-=speed;
//		System.out.println("POS: " + x + y);
	}
	
	public void geheNachRechts(){
		this.x+=speed;
		if(!FeldBegehbar(3)) this.x-=speed;
//		System.out.println("POS: " + x + y);
	}
	
	public void geheNachLinks(){
		this.x-=speed;
		if(!FeldBegehbar(4)) this.x+=speed;
//		System.out.println("POS: " + x + y);
	}
	
	/**
	 * 
	 * @param i i = 1,2,3,4 gibt die Richtung an, in die wir gehen wollen
	 * @return prüft, ob das Feld, auf das wir gehen wollen, ein Weg ist und gibt dann true zurück, ansonsten false
	 */
	public boolean FeldBegehbar(int i){
		 
		

		if(i == 1){
			if(Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].begehbar) {
				if(Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].Ausgang){ Gamepanel.Ende = true;}
					return true;
				}
			}
			else if(i == 2){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].Ausgang){ Gamepanel.Ende = true;}
				return true;
				}
			}
			else if(i == 3){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].Ausgang){ Gamepanel.Ende = true;}
				return true;
				}
			}
			else if(i == 4){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].Ausgang){ Gamepanel.Ende = true;}
				return true;
				}

		}
		return false;
	}
	/*
	 *es können bis zu 8 Bomben mit Hilfe des Bombenitemupgrades getragen werden 
	 * 
	 */
	public void setBombenItem() {
		if(BombenAnzahl<=8) BombenAnzahl+=1;
	}
	/*
	 * es wird ein Lebenspunkt abgezogen. Sobald die Leben des Bomberman auf 0 gesetzt wird wird 'tot' als 'true' zurückgegeben
	 * 
	 */
	public void stirb(){
		Leben -=1;
		if (Leben == 0) tot = true;
	}
	/*
	 * mit Hilfe des Extralebens erhält man ein zusätzliches Leben
	 * 
	 */
	public void setExtraLeben() {
		if(Leben == 1) Leben = 2;
		
	}
	/*
	 * mit Hilfe des Feueritems wird die Bombenreichweite um 1 erhöht
	 * 
	 */
	public void setFeuer() {
		BombenReichweite+=1;
		
	}
	/*
	 * das Kickeritem kann benutzt werden
	 * 
	 */
	public void setKicker() {
		Kicker = true;
		
	}
	/*
	 * das Handschuhitem kann benutzt werden
	 * 
	 */
	public void setHandschuh() {
		Handschuh = true;
		
	}
	/*
	 * mit Hilfe des Rollschuhitems wird die Bewegungsgeschwindigkeit erhöht
	 * 
	 */
	public void setRollschuh() {
		speed+=0.5;
		
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}

}
