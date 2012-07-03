import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	public ItemListe hilfslist_item;
	public static boolean init;
	public static boolean Spieler1_wins;
	public static boolean Spieler2_wins;
	public static boolean truu = true;
	
	public static FlugItemListe FlugItemHead;
	public static FlugItemListe FlugItemNode;

	public Gamepanel(String args){
//		Spieler = new Bomberman(41,41);
		SpielFeldEinlesen A = new SpielFeldEinlesen(args);
		Spielfeld = A.getSpielfeld();
		n = Spielfeld.length;
		Zeichner = new Grafik();
		Zeichner.frame.addKeyListener(this);
		run();

	}
	
	public void Initialisieren(int a, int b){
		Spieler = new Bomberman(a*40, b*40);
		Zeichner.Spieler1 = Spieler;
		Zeichner.Init();
		init = true;
//		run();
	}
	
	public void Initialisieren(int a1, int b1, int a2, int b2){
		Spieler_2 = true;
		Spieler = new Bomberman(a1*40, b1*40);
		Spieler2 = new Bomberman(a2*40, b2*40);
		Zeichner.Spieler1 = Spieler;
		Zeichner.Spieler2 = Spieler2;
		Zeichner.Init();
		init = true;
//		run();
	}
	
	
	/**
	 * Hier lassen wir einfach eine Endlosschleifen durchlaufen, checken, ob die Tasten für hoch, runter etc. gedrückt sind und führen die entsprechenden Aktionen aus
	 * 
	 */
	@Override
	public void run(){
		new SoundCheckLoop("Sunny Day.wav");
		
		
		while(truu){

			if(init){
				if(speichern){
					new SpielStandSpeichern();
					speichern = false;
				}
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
						if(Spieler.BombenAnzahl != 0 && !BombeErstellen.liegtBombe((int) ((Spieler.x+15)/40), (int) ((Spieler.y+15)/40))){
							Spieler.BombenAnzahl-=1;
							BombeErstellen neueBombe = new BombeErstellen((int) ((Spieler.x+15)/40), (int) ((Spieler.y+20)/40), Spieler.BombenReichweite, 2500, 1);
							neueBombe.start();
							new SoundCheck("BombeLegen1.wav");
						}
						bombelegen = false;
					}
					if(itembenutzen){
						  if(Spieler.Handschuh){
						      erstelleFlugItem((int)Gamepanel.Spieler.getX(), (int)Spieler.getY(), 1, 1);
						      Spieler.Handschuh=false;
						     }
						       if(Spieler.Kicker){
						     Spieler.Kicker=false;
						      erstelleFlugItem((int)Gamepanel.Spieler.getX(), (int)Spieler.getY(), 2, 1);
						      
						     }
						     itembenutzen=false;
						    }
						
						
					}
				else if (Spieler_2){
					Zeichner.setFrame();
					Zeichner.Spieler2_wins = true;
					truu = false;
				} else {
					Zeichner.setFrame();
					Zeichner.Spieler1_stirbt = true;
					truu = false;
				}
				if(Spieler_2){
					if(!Spieler2.tot){
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
							if(Spieler2.BombenAnzahl != 0 && !BombeErstellen.liegtBombe((int) ((Spieler2.x+15)/40), (int) ((Spieler2.y+15)/40))){
								Spieler2.BombenAnzahl-=1;
								BombeErstellen neueBombe = new BombeErstellen((int) ((Spieler2.x+15)/40), (int) ((Spieler2.y+20)/40), Spieler2.BombenReichweite, 2500, 2);
								neueBombe.start();
								new SoundCheck("BombeLegen1.wav");
							}
							bombelegen2 = false;
						}
						if(itembenutzen2){
							 if(Spieler2.Handschuh){
							      erstelleFlugItem((int)Gamepanel.Spieler2.getX(), (int)Spieler2.getY(), 1, 2);
							      Spieler2.Handschuh=false; 
							   }
							   if(Spieler2.Kicker){
							      erstelleFlugItem((int)Gamepanel.Spieler.getX(), (int)Spieler.getY(), 2, 2);
							      Spieler2.Kicker=false;
							    }
							     itembenutzen2=false;
						 }
						
						if(clear){
							Ausgabe();
						}
					
					} else {
						Zeichner.setFrame();
						Zeichner.Spieler1_wins = true;
						truu = false;
					}
	
					}

				if(FlugItemHead != null){
					bewegeFlugItem();
				}
				
				
				
				Zeichner.repaint(); //nach jeder Änderung Am Spielfeld, Bomberman, ItemListe oder BombenListe muss ein repaint durchgeführt werden
			}else {
				if(eingabe){
					if(zaehler == 0) Initialisieren(1, 1);
					if(zaehler == 1) Initialisieren(1, 1, n-2, n-2);
					eingabe = false;
				}
				Zeichner.repaint();
			}
			if(Ende){
				Zeichner.setFrame();
				Zeichner.winner = true;
				truu = false;
			}
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
		hilfslist_item = Gamepanel.ItemHead;

		while(hilfslist_item != null){
			System.out.println("Index: " + hilfslist_item.getItem().getIndex() + " X: " + hilfslist_item.getItem().getX() + " Y: " + hilfslist_item.getItem().getY());
	
			hilfslist_item = hilfslist_item.next;
		}
		
	}
	
	/**
	 * Wir haben unsere ItemListe "ItemHead" und fügen am Ende der Liste ein zufällig erstelltes Item ein.
	 * 
	 * @param x x-koordinate im Spielfeld
	 * @param y y-koordinate im Spielfeld
	 */
	public static void erstelleItem(int x, int y){
		
		if(!Spielfeld[y][x].Ausgang){
		
			int i = (int)(Math.random()*100);
			if(i <= 58){
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
				Spielfeld[y][x].liegtItem = true;
				new SoundCheck("ring.wav");
			}
			
		}
	}

	
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
	
	/**
	 * 
	 * @param x Position Horizontal
	 * @param y	Position Vertikal
	 * @param typ	1 ist Handschuh, 2 ist Kicker
	 * @param spieler	Spieler der das Item erstellt
	 */
	public static void erstelleFlugItem(int x, int y, int typ, int spieler){
		  int itemrichtung;
		  if(rechts) {
			  itemrichtung=0;
			  x = ((int) ((x+15)/40)+1) * 40;
			  y = ((int) ((y+15)/40)) * 40;
		  }
		  else if(links){
			  itemrichtung=1;
			  x = ((int) ((x+15)/40)-1) * 40;
			  y = ((int) ((y+15)/40)) * 40;
		  }
		  else if(hoch){
			  itemrichtung=2;
			  x = ((int) ((x+15)/40)) * 40;
			  y = ((int) ((y+15)/40)-1) * 40;
		  }
		  else {
			  itemrichtung=3;
			  x = ((int) ((x+15)/40)) * 40;
			  y = ((int) ((y+15)/40)+1) * 40;
		  }
//		  else if(runter&&rechts)itemrichtung=4;
//		  else if(runter&&links)itemrichtung=5;
//		  else if(hoch&&rechts)itemrichtung=6;
//		  else itemrichtung = 7;
		  System.out.println("Itemrichtung :"+itemrichtung); 
		  FlugItem Flugitem=new FlugItem((x+15),(y+15),itemrichtung, typ, spieler);
		  
		  if(Gamepanel.FlugItemHead == null){
		   Gamepanel.FlugItemHead = new FlugItemListe(Flugitem, null);
		  }
		  else {
		   FlugItemNode = FlugItemHead;
		   while(FlugItemNode.next != null){
		    FlugItemNode = FlugItemNode.next;
		   }
		   FlugItemListe hilfslist = new FlugItemListe(Flugitem, null);
		   FlugItemNode.setNext(hilfslist);
		  }
	}

	public void bewegeFlugItem(){
		for(FlugItemNode = FlugItemHead; FlugItemNode != null; FlugItemNode = FlugItemNode.next){
			FlugItemNode.getItem().bewege();
		}
	}
	/**
	 * Entfernt das Flugitem an der Stelle a,b
	 * @param a X-Position
	 * @param b	Y-Position
	 */
	public static void entferneFlugItem(double a, double b){

		 FlugItemNode = Gamepanel.FlugItemHead;
		 if(Gamepanel.FlugItemHead != null){
		  if(Gamepanel.FlugItemHead.next == null && Gamepanel.FlugItemHead.getItem().getX() == a && Gamepanel.FlugItemHead.getItem().getY() == b){
		   Gamepanel.FlugItemHead = null;
		  }
		   else {
		    if(Gamepanel.FlugItemHead.getItem().getX() == a && Gamepanel.FlugItemHead.getItem().getY() == b){
		     Gamepanel.FlugItemHead = Gamepanel.FlugItemHead.next;
		    }
		    FlugItemNode = Gamepanel.FlugItemHead;
		    if(FlugItemNode != null){
		     while(FlugItemNode.next != null){
		     if(FlugItemNode.next.getItem().getX() == a && FlugItemNode.next.getItem().getY() == b) break;
		     FlugItemNode = FlugItemNode.next;
		     }
		     if(FlugItemNode.next != null && FlugItemNode.next.getItem().getX() == a && FlugItemNode.next.getItem().getY() == b){
		      FlugItemNode.setNext(FlugItemNode.next.getNext());
		      }
		     }
		    }
		   }
		  }
	
}
