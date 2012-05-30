

public class Gamepanel extends TastenCheck {
	
	int n;
	public static Feld[][] Spielfeld;
	Bomberman Spieler;
	Grafik Zeichner;
	ItemListe head, node;
	Item item;
			
	public Gamepanel(String args){
		Spieler = new Bomberman(1,1);
		SpielFeldEinlesen A = new SpielFeldEinlesen(args);
		Spielfeld = A.getSpielfeld();
		n = Spielfeld.length;

//		BENUTZUNG DER ITEMLISTE:
//		head = new ItemListe(new Item("Kicker", 3,2), null);
//		node = new ItemListe(new Item("Rollschuh", 5,4), head);
//		head = node;
//		node = new ItemListe(new Item("Handschuh", 7,6), head);
//		head = node;
		
		Zeichner = new Grafik(Spieler);
		run();
	}
	
	public void Initialisieren(){

		
	}
	
	public void run(){
		while(true){
			if(hoch){
				Spieler.geheNachOben();
				System.out.println("NachOben");
			}
			if(runter){
				Spieler.geheNachUnten();
				System.out.println("NachOben");
			}
			if(links){
				Spieler.geheNachLinks();
			}
			if(rechts){
				Spieler.geheNachRechts();
			}
			Zeichner.repaint();
		}
	}	

	public Feld[][] getFeld(){
		return Spielfeld;
	}
	
	public Bomberman getBomberman(){
		return Spieler;
	}
	
	public void Ausgabe(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(Spielfeld[i][j].begehbar == true) System.out.print("W ");
				if(Spielfeld[i][j].zerstoerbar == true)System.out.print("Z ");
				if(Spielfeld[i][j].unzerstoerbar == true)System.out.print("U ");
			}
			System.out.println("");
		}
		
//		AUSGABE DER ITEMLISTE
//		while(node!=null){
//			item = node.getItem();
//			if(item.Feuer==true) System.out.println("Feuer");
//			if(item.Handschuh==true) System.out.println("Handschuh");
//			if(item.Kicker==true) System.out.println("Kicker");
//			if(item.ExtraLeben==true) System.out.println("ExtraLeben");
//			if(item.BombenItem==true) System.out.println("BombenItem");
//			if(item.Rollschuh==true) System.out.println("Rollschuh");
//			node=node.getNext();
//			
//		}
		

		
		
		
	}
	
	//Prüfen, ob unser Bomberman über ein Item läuft, x und y sind die Koord. vom Spieler
	public void checkItem(int x, int y){
		while(node!=null){
			item = node.getItem();

			if(x == item.x && y == item.y){
				if(item.BombenItem) Spieler.setBombenItem();
				if(item.ExtraLeben) Spieler.setExtraLeben();
				if(item.Feuer) Spieler.setFeuer();
				if(item.Handschuh) Spieler.setHandschuh(); 
				if(item.Kicker) Spieler.setKicker();
				if(item.Rollschuh) Spieler.setRollschuh(); 
				
			}
		}
		
	}
	
	public static void main(String[] args){
		Gamepanel A=new Gamepanel("level2.txt");

	}
	
	
	

}
