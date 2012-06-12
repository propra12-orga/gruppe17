import java.awt.DisplayMode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


/**
 * hier wird das gesamte eigentliche Spielgeschehen dargestellt
 *  
 * 
 *
 */
public class Gamepanel extends TastenCheck implements ActionListener {
	
	int n;
	public static Feld[][] Spielfeld;
	public static Bomberman Spieler;
	public static Bomberman Spieler2;
	public static Grafik Zeichner;
	public static ItemListe ItemHead;
//	ItemListe ItemNode;
	public static BombenListe BombHead;
	BombenListe BombNode;
	public static int[] BombenReichweite = new int[4]; //Reichweite der Bombe in jede Richtung
	public static boolean Ende;
	public static boolean Spieler_2;

	public Gamepanel(String args){
//		Spieler = new Bomberman(41,41);
		SpielFeldEinlesen A = new SpielFeldEinlesen(args);
		Spielfeld = A.getSpielfeld();
		n = Spielfeld.length;
		Zeichner = new Grafik();
		Zeichner.frame.addKeyListener(this);
//		Initialisieren(1, 1);
		Initialisieren(1, 1, n-2, n-2);
	}
	
	public void Initialisieren(int a, int b){
		Spieler = new Bomberman(a*40, b*40);
		Zeichner.Spieler1 = Spieler;
		run();
	}
	
	public void Initialisieren(int a1, int b1, int a2, int b2){
		Spieler_2 = true;
		Spieler = new Bomberman(a1*40, b1*40);
		Spieler2 = new Bomberman(a2*40, b2*40);
		Zeichner.Spieler1 = Spieler;
		Zeichner.Spieler2 = Spieler2;
		run();
	}
	
	/**
	 * Hier lassen wir einfach eine Endlosschleifen durchlaufen, checken, ob die Tasten für hoch, runter etc. gedrückt sind und führen die entsprechenden Aktionen aus
	 * 
	 */
	public void run(){
		
		while(true){
			
			if(!Spieler.tot){
				if(neueRichtung1){
					Zeichner.setNeueRichtung();
					neueRichtung1 = false;
				}
				
				if(neueRichtung2){
					Zeichner.setNeueRichtung2();
					neueRichtung2 = false;
					
				}
				
	
				if(hoch){
					Spieler.geheNachOben();
					}
				if(runter){
					Spieler.geheNachUnten();
				}
				if(links){
					Spieler.geheNachLinks();
				}
				if(rechts){
					Spieler.geheNachRechts();
				}
				if(bombelegen){
					if(!BombeErstellen.liegtBombe((int) ((Spieler.x+15)/40), (int) ((Spieler.y+15)/40))){
//						BombeErstellen neueBombe = new BombeErstellen((int) ((Spieler.x+15)/40), (int) ((Spieler.y+15)/40), 2, 2500);
						BombeErstellen neueBombe = new BombeErstellen((int) ((Spieler.x+15)/40), (int) ((Spieler.y+20)/40), 2, 2500);
						neueBombe.start();
					}
					bombelegen = false;
				}
				if(hoch2){
					Spieler2.geheNachOben();
					}
				if(runter2){
					Spieler2.geheNachUnten();
				}
				if(links2){
					Spieler2.geheNachLinks();
				}
				if(rechts2){
					Spieler2.geheNachRechts();
				}
				if(bombelegen2){
					if(!BombeErstellen.liegtBombe((int) ((Spieler2.x+15)/40), (int) ((Spieler2.y+15)/40))){
						BombeErstellen neueBombe = new BombeErstellen((int) ((Spieler2.x+15)/40), (int) ((Spieler2.y+20)/40), 2, 2500);
						neueBombe.start();
					}
					bombelegen2 = false;
				}
			}
			
			
			Zeichner.repaint(); //nach jeder Änderung Am Spielfeld, Bomberman, ItemListe oder BombenListe muss ein repaint durchgeführt werden
		}
	}	

	/**
	 * ruft das Spielfeld im Gamepanel auf
	 * 
	 * @return gibt das Spielfeld zurück
	 */
	public Feld[][] getFeld(){
		return Spielfeld;
	}
	
	/**
	 * erzeugt einen Bomberman auf dem Gamepanel
	 * 
	 * @return gibt einen Spieler zurück
	 */
	public Bomberman getBomberman(){
		return Spieler;
	}
	
	public void Ausgabe(){
//		for(int i=0;i<n;i++){
//			for(int j=0;j<n;j++){
//				if(Spielfeld[i][j].begehbar == true) System.out.print("W ");
//				if(Spielfeld[i][j].zerstoerbar == true)System.out.print("Z ");
//				if(Spielfeld[i][j].unzerstoerbar == true)System.out.print("U ");
//			}
//			System.out.println("");
//		}
	}
	
	/**
	 * Wir haben unsere ItemListe "ItemHead" und fügen am Ende der Liste ein zufällig erstelltes Item ein.
	 * 
	 * @param x x-koordinate im Spielfeld
	 * @param y y-koordinate im Spielfeld
	 */
	public static void erstelleItem(int x, int y){
		
		int i = (int)(Math.random()*8);
		if(i <= 5){
			Item item = new Item(i, x, y);
			if(ItemHead == null){
				ItemHead = new ItemListe(item, null);
			}
			else {
				ItemListe ItemNode = ItemHead;
				while(ItemNode.next != null){
					ItemNode = ItemNode.next;
				}
				ItemListe hilfslist = new ItemListe(item, null);
				ItemNode.setNext(hilfslist);
			}
		}
	}
	
	
	
//	//Prüfen, ob unser Bomberman über ein Item läuft, x und y sind die Koord. vom Spieler
//	public void checkItem(int x, int y){
//		while(ItemNode!=null){
//			item = ItemNode.getItem();
//
//			if(x == item.x && y == item.y){
//				if(item.BombenItem) Spieler.setBombenItem();
//				if(item.ExtraLeben) Spieler.setExtraLeben();
//				if(item.Feuer) Spieler.setFeuer();
//				if(item.Handschuh) Spieler.setHandschuh(); 
//				if(item.Kicker) Spieler.setKicker();
//				if(item.Rollschuh) Spieler.setRollschuh(); 
//				
//			}
//		}
//		
//	}


	
	public BombenListe getBombenListe(){
		return BombHead;
	}
	
	/**
	 * die main-Methode ruft das Gamepanel auf
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Gamepanel A=new Gamepanel("level4.txt");
	
	}
	
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
