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
	public ItemListe ItemNode;
	int Index;
	
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
	}
	
	public void geheNachUnten(){
		this.y+=speed;
		if(!FeldBegehbar(2)) this.y-=speed;
	}
	
	public void geheNachRechts(){
		this.x+=speed;
		if(!FeldBegehbar(3)) this.x-=speed;
	}
	
	public void geheNachLinks(){
		this.x-=speed;
		if(!FeldBegehbar(4)) this.x+=speed;
	}
	
	/**
	 * 
	 * @param i i = 1,2,3,4 gibt die Richtung an, in die wir gehen wollen
	 * @return prüft, ob das Feld, auf das wir gehen wollen, ein Weg ist und gibt dann true zurück, ansonsten false
	 */
	public boolean FeldBegehbar(int i){
		 
		

		if(i == 1){
			if(Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].begehbar) {
				if(Gamepanel.Spielfeld[(int) (y+5)/40][(int) (x+5)/40].liegtItem){
					setItem((int) (x+5)/40, (int) (y+5)/40);
				}
				if(Gamepanel.Spielfeld[(int) (y+5)/40][(int) (x+25)/40].liegtItem){
					setItem((int) (x+25)/40, (int) (y+5)/40);
				}
				if(Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].Ausgang){ Gamepanel.Ende = true;}
					return true;
				}
			}
			else if(i == 2){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+30)/40][(int) (x+25)/40].liegtItem){
						setItem((int) (x+25)/40, (int) (y+30)/40);
						System.out.println("BLA");
					}
					if(Gamepanel.Spielfeld[(int) (y+30)/40][(int) (x+5)/40].liegtItem){
						setItem((int) (x+5)/40, (int) (y+30)/40);
						System.out.println("BLAbla");
					}
					if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].Ausgang){ Gamepanel.Ende = true;}
				return true;
				}
			}
			else if(i == 3){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+30)/40][(int) (x+25)/40].liegtItem){
						setItem((int) (x+25)/40, (int) (y+30)/40);
					}
					if(Gamepanel.Spielfeld[(int) (y+5)/40][(int) (x+25)/40].liegtItem){
						setItem((int) (x+25)/40, (int) (y+5)/40);
					}
					if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x+30)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x+30)/40].Ausgang){ Gamepanel.Ende = true;}
				return true;
				}
			}
			else if(i == 4){
				if(Gamepanel.Spielfeld[(int) (y+35)/40][(int) (x)/40].begehbar && Gamepanel.Spielfeld[(int) (y+20)/40][(int) (x)/40].begehbar) {
					if(Gamepanel.Spielfeld[(int) (y+30)/40][(int) (x+5)/40].liegtItem){
						setItem((int) (x+5)/40, (int) (y+30)/40);
					}
					if(Gamepanel.Spielfeld[(int) (y+5)/40][(int) (x+5)/40].liegtItem){
						setItem((int) (x+5)/40, (int) (y+5)/40);
					}
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
		if(BombenAnzahl < 8) BombenAnzahl+=1;
	}
	/*
	 * es wird ein Lebenspunkt abgezogen. Sobald die Leben des Bomberman auf 0 gesetzt wird wird 'tot' als 'true' zurückgegeben
	 * 
	 */
	public void stirb(){
		Leben -=1;
		if (Leben == 0) tot = true;
	}
	/**
	 * mit Hilfe des Extralebens erhält man ein zusätzliches Leben
	 * 
	 */
	public void setExtraLeben() {
		if(Leben == 1) Leben = 2;
		
	}
	/**
	 * mit Hilfe des Feueritems wird die Bombenreichweite um 1 erhöht
	 * 
	 */
	public void setFeuer() {
		BombenReichweite+=1;
		
	}
	/**
	 * das Kickeritem kann benutzt werden
	 * 
	 */
	public void setKicker() {
		if(!Handschuh) Kicker = true;
	}
	/**
	 * das Handschuhitem kann benutzt werden
	 * 
	 */
	public void setHandschuh() {
		if(!Kicker)  Handschuh = true;
	}
	/**
	 * mit Hilfe des Rollschuhitems wird die Bewegungsgeschwindigkeit erhöht
	 * 
	 */
	public void setRollschuh() {
		speed+=0.0000025;
		
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setItem(int a, int b){
		Index = ItemAnStelle(a,b);
		
		System.out.println("A: " + a + " B: " + b + " Index: " + Index);
		
		switch(Index){
			case 0 : setFeuer();
					 System.out.println("Feuer");
					 break;
			case 1 : setHandschuh();
					 System.out.println("Handschuh");
					 break;
			case 2 : setKicker();
					 System.out.println("Kicker");
					 break;
			case 3 : setExtraLeben();
					 System.out.println("ExtraLeben");
					 break;
			case 4 : setBombenItem();
					 System.out.println("BombenItem");
					 break;
			case 5 : setRollschuh();
					 System.out.println("Rollschuh");
					 break;
		}
		entferneItem(a,b);
	}
	
	public int ItemAnStelle(int a, int b){
//		ItemNode = Gamepanel.ItemHead;
//		if(ItemNode == null){
//			return 6;
//		}
//			if(ItemNode.getItem().getX() == a && ItemNode.getItem().getY() == b){
//				return ItemNode.getItem().getIndex();
//			}
//			while(ItemNode.next != null){
//				if(ItemNode.next.getItem().getX() == a && ItemNode.next.getItem().getY() == b){ 
//					return ItemNode.next.getItem().getIndex();
//					}
//			ItemNode = ItemNode.next;
//			}
//		return -1;
		
		for(ItemNode = Gamepanel.ItemHead; ItemNode != null; ItemNode = ItemNode.next){		
			if(ItemNode.getItem().getX() == a && ItemNode.getItem().getY() == b){
				return ItemNode.getItem().getIndex();
			}
		}
			return -1;
			
	}
	
	public void entferneItem(int a, int b){

		ItemNode = Gamepanel.ItemHead;
		if(Gamepanel.ItemHead != null){
			if(Gamepanel.ItemHead.next == null && Gamepanel.ItemHead.getItem().getX() == a && Gamepanel.ItemHead.getItem().getY() == b){
				Gamepanel.ItemHead = null;
			}
				else {
					if(Gamepanel.ItemHead.getItem().getX() == a && Gamepanel.ItemHead.getItem().getY() == b){
						Gamepanel.ItemHead = Gamepanel.ItemHead.next;
					}
					ItemNode = Gamepanel.ItemHead;
					if(ItemNode != null){
						while(ItemNode.next != null){
						if(ItemNode.next.getItem().getX() == a && ItemNode.next.getItem().getY() == b) break;
						ItemNode = ItemNode.next;
						}
						if(ItemNode.next != null && ItemNode.next.getItem().getX() == a && ItemNode.next.getItem().getY() == b){
							ItemNode.setNext(ItemNode.next.getNext());
						}
					}
				}
		 	}
		Gamepanel.Spielfeld[b][a].liegtItem = false;
		}

	
	
	
}
