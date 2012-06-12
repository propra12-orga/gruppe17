/**
 *Klasse zum Erstellen und Löschen einer Bombe
 * Ist eine Erweiterung der Klasse Thread,
 *´damit die Bombe nach einer gewissen Zeit 
 *automation gelöscht werden kann
 *
 */
public class BombeErstellen extends Thread {
	
	int x, y, r;
	BombenListe BombNode;
	int time;
	int x_Spieler1, y_Spieler1, x_Spieler2, y_Spieler2;
	int kaputt_rechts, kaputt_links, kaputt_oben, kaputt_unten;
	int BombenCount;
	/**
	 * 
	 * @param a x-Koordinate im Spielfeld
	 * @param b y-Koordinate im Spielfeld
	 * @param c Reichweite der Bombe
	 * @param d Zeit
	 */
	public BombeErstellen(int a, int b, int c, int d){
		x = a; 	
		y = b; 	
		r = c;	
		time = d;
		BombenCount = 0;
		
	}

	/*
	 * erstellt die Bombe, löscht sie nach einiger Zeit wieder und verändert das Spielfeld
	 * 
	 */
	public void run() {
		
		erstelleBombe();
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		loescheBombe(x, y);
		
		
	}
	/*
	 *erstellt eine neue Bombe und fügen sie am Ende unserer BombenListe ein
	 * 
	 */
	public void erstelleBombe(){
		
		Bombe bomb = new Bombe(x, y, r);
		
		if(Gamepanel.BombHead == null){
			Gamepanel.BombHead = new BombenListe(bomb, null);
		}
		else {
			BombNode = Gamepanel.BombHead;
			while(BombNode.next != null){
				BombNode = BombNode.next;
			}
			BombenListe hilfslist = new BombenListe(bomb, null);
			BombNode.setNext(hilfslist);
		}
	}
	
	/**
	 * löscht die Bombe an den Koordinaten a,b
	 * 
	 * @param x x-Koordinate im Spielfeld
	 * @param y y-Koordinate im Spielfeld
	 */
	public void loescheBombe(int a, int b){
		kaputt_rechts = 0;
		kaputt_links = 0;
		kaputt_oben = 0;
		kaputt_unten = 0;
		
		BombNode = Gamepanel.BombHead;
		if(Gamepanel.BombHead != null){
			if(Gamepanel.BombHead.next == null && Gamepanel.BombHead.getBombe().getX() == a && Gamepanel.BombHead.getBombe().getY() == b){
				Gamepanel.BombHead.getBombe().setZeit(Gamepanel.BombHead.getBombe().getZeit() + time);
				Gamepanel.BombHead = null;
				machKaputt(a, b, r);
//				DetonationRadius Detonation = new DetonationRadius(kaputt_unten, kaputt_links, kaputt_oben, kaputt_rechts, a, b);
//				Detonation.start();
			}
				else {
					if(Gamepanel.BombHead.getBombe().getX() == a && Gamepanel.BombHead.getBombe().getY() == b){
						Gamepanel.BombHead.getBombe().setZeit(Gamepanel.BombHead.getBombe().getZeit() + time);
						Gamepanel.BombHead = Gamepanel.BombHead.next;
						machKaputt(a, b, r);
//						DetonationRadius Detonation = new DetonationRadius(kaputt_unten, kaputt_links, kaputt_oben, kaputt_rechts, a, b);
//						Detonation.start();
					}
					BombNode = Gamepanel.BombHead;
					if(BombNode != null){
						while(BombNode.next != null){
						if(BombNode.next.getBombe().getX() == a && BombNode.next.getBombe().getY() == b) break;
						BombNode = BombNode.next;
						}
						if(BombNode.next != null && BombNode.next.getBombe().getX() == a && BombNode.next.getBombe().getY() == b){
							BombNode.getBombe().setZeit(BombNode.getBombe().getZeit() + time);
							BombNode.setNext(BombNode.next.getNext());
							machKaputt(a, b, r);
//							DetonationRadius Detonation = new DetonationRadius(kaputt_unten, kaputt_links, kaputt_oben, kaputt_rechts, a, b);
//							Detonation.start();
						}
					}
				}
		 	}
		}
	
	/**
	 * prüft alle Felder, die von der Detonation der Bombe getroffen werden darauf, ob es sich um einen zerstoerbaren/unzerstoerbaren Block handelt		
	 * ändert zerstoerbare Blöcke dann in einen Weg um
	 * 
	 * @param a x-Koordinate im Spielfeld
	 * @param b y-Koordinate im Spielfeld
	 * @param r Reichweite der zu überprüfenden Felder
	 */
	public void machKaputt(int a, int b, int r){
		
		
		x_Spieler1 = (int) (Gamepanel.Spieler.getX()+15)/40;
		y_Spieler1 = (int) (Gamepanel.Spieler.getY()+20)/40;
		if(Gamepanel.Spieler_2){
			x_Spieler2 = (int) (Gamepanel.Spieler2.getX()+15)/40;
			y_Spieler2 = (int) (Gamepanel.Spieler2.getY()+15)/40;
		}

		if(x_Spieler1 == a && y_Spieler1 == b){
			Gamepanel.Spieler.stirb();
		}
		if(x_Spieler2 == a && y_Spieler2 == b){
			Gamepanel.Spieler2.stirb();
		}
		
		
		for(int i = 1; i<=r; i++){
			if(Gamepanel.Spielfeld[b][a+i].zerstoerbar){
				Gamepanel.Spielfeld[b][a+i].setWeg();
				Gamepanel.erstelleItem(a+i, b);
				kaputt_rechts++;
				break;
			}
			if(Gamepanel.Spielfeld[b][a+i].unzerstoerbar) break;
			if(x_Spieler1 == a+i && y_Spieler1 == b){
				Gamepanel.Spieler.stirb();
			}
			if(x_Spieler2 == a+i && y_Spieler2 == b){
				Gamepanel.Spieler2.stirb();
			}
			
//			if (liegtBombe(a+i,b)){
////				int Reichweite = BombeAnStelle(a+i,b).getReichweite();
//				
//				loescheBombe(a+i,b);
////				BombenCount++;
//			}
			kaputt_rechts++;
		}
		for(int i = 1; i<=r; i++){
			if(Gamepanel.Spielfeld[b][a-i].zerstoerbar){
				Gamepanel.Spielfeld[b][a-i].setWeg();
				Gamepanel.erstelleItem(a-i, b);
				kaputt_links++;
				break;
			}
			if(Gamepanel.Spielfeld[b][a-i].unzerstoerbar) break;
			if(x_Spieler1 == a-i && y_Spieler1 == b){
				Gamepanel.Spieler.stirb();
				}
			if(x_Spieler2 == a-i && y_Spieler2 == b){
				Gamepanel.Spieler2.stirb();
				}
//			if (liegtBombe(a-i,b)){
////				int Reichweite = BombeAnStelle(a-i,b).getReichweite();
//				loescheBombe(a-i,b);
////				BombenCount++;
//			}
			kaputt_links++;
		}
		for(int i = 1; i<=r; i++){
			if(Gamepanel.Spielfeld[b+i][a].zerstoerbar){
				Gamepanel.Spielfeld[b+i][a].setWeg();
				Gamepanel.erstelleItem(a, b+i);
				kaputt_oben++;
				break;
			}
			if(Gamepanel.Spielfeld[b+i][a].unzerstoerbar) break;
			if(x_Spieler1 == a && y_Spieler1 == b+i){
				Gamepanel.Spieler.stirb();
				}
			if(x_Spieler2 == a && y_Spieler2 == b+i){
				Gamepanel.Spieler2.stirb();
				}
//			if (liegtBombe(a,b+i)){
////				int Reichweite = BombeAnStelle(a,b+i).getReichweite();
//				loescheBombe(a,b+i);
////				BombenCount++;
//			}
			kaputt_oben++;
		}
		for(int i = 1; i<=r; i++){
			if(Gamepanel.Spielfeld[b-i][a].zerstoerbar){
				Gamepanel.Spielfeld[b-i][a].setWeg();
				Gamepanel.erstelleItem(a, b-i);
				kaputt_unten++;
				break;
			}
			
			if(Gamepanel.Spielfeld[b-i][a].unzerstoerbar) break;
			if(x_Spieler1 == a && y_Spieler1 == b+i){
				Gamepanel.Spieler.stirb();
			}
			if(x_Spieler2 == a && y_Spieler2 == b+i){
				Gamepanel.Spieler2.stirb();
			}
//			if (liegtBombe(a,b-i)){
////				int Reichweite = BombeAnStelle(a,b-i).getReichweite();
//				loescheBombe(a,b-i);
////				BombenCount++;
//			}
			kaputt_unten++;
		}
		DetonationRadius Detonation = new DetonationRadius(kaputt_unten, kaputt_links, kaputt_oben, kaputt_rechts, a, b);
		Detonation.start();
//		kaputt_rechts = 0;
//		kaputt_links = 0;
//		kaputt_oben = 0;	
//		kaputt_unten = 0;
		
		Kettenreaktion(kaputt_unten, kaputt_links, kaputt_oben, kaputt_rechts, a, b);
		
	}
	
	
	// Vorsicht, oben und unten vertauscht!
	public void Kettenreaktion(int unten, int links, int oben, int rechts, int x, int y){
		for(int i = 0; i <= unten; i++){
			if(liegtBombe(x, y - i)){
				loescheBombe(x, y - i);
				System.out.println("BLA");

			}
		}
		for(int i = 0; i <= oben; i++){
			if(liegtBombe(x, y + i)){
				loescheBombe(x, y + i);
				System.out.println("BLA");

			}
		}
		for(int i = 0; i <= links; i++){
			if(liegtBombe(x - i, y)){
				loescheBombe(x - i, y);
				System.out.println("BLA");
			}
		}
		for(int i = 0; i <= rechts; i++){
			if(liegtBombe(x + i, y)){
				loescheBombe(x + i, y);
				System.out.println("BLA");

			}
		}
	}
	
	/**
	 * 
	 * @param a x-Koordinate im Spielfeld
	 * @param b y-Koordinate im Spielfeld
	 * @return gibt true zurück, falls sich auf dem geprüften Feld eine Bombe befindet
	 */
	public static boolean liegtBombe(int a, int b){
		BombenListe BombNode = Gamepanel.BombHead;
		if(BombNode == null){
			return false;
		}
			else {
				if(BombNode.getBombe().getX() == a && BombNode.getBombe().getY() == b){
					return true;
				}
				while(BombNode.next != null){
				if(BombNode.next.getBombe().getX() == a && BombNode.next.getBombe().getY() == b) return true;
				BombNode = BombNode.next;
				}
			}
		return false;
	}
	/**
	 * 
	 * @param a x-Koordinate im Spielfeld
	 * @param b y-Koordinate im Spielfeld
	 * @return gibt Bombe im Feld zurück
	 */
	public Bombe BombeAnStelle(int a, int b){
		BombNode = Gamepanel.BombHead;
		if(BombNode == null){
			return null;
		}
			else {
				if(BombNode.getBombe().getX() == a && BombNode.getBombe().getY() == b){
					return BombNode.getBombe();
				}
				while(BombNode.next != null){
				if(BombNode.next.getBombe().getX() == a && BombNode.next.getBombe().getY() == b) return BombNode.getBombe();
				BombNode = BombNode.next;
				}
			}
		return null;
		
		
	}
}
