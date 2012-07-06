import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramSocket;


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
	public static DatagramSocket socket;
	public static FlugItemListe FlugItemHead;
	public static FlugItemListe FlugItemNode;
	public Server2 server;
	public Client1 client;
	public static boolean CLIENT;
	public static boolean SERVER;
	public static String host = "localhost";
	public static int port = 9999;
	public static int op;
	static boolean network;
	
	
	
	public static Bomberman[] Player = new Bomberman[2];
	public static int[] Player_Index = new int[2];
	
	SpielFeldEinlesen A;
/**
 * 
 * @param args das grundlevel,
 * es wird hiermit nur die run und das eigentliche gestartez
 */
	public Gamepanel(String args){
//		Spieler = new Bomberman(41,41);
//		A = new SpielFeldEinlesen(args);
//		Spielfeld = A.getSpielfeld();
//		n = Spielfeld.length;
//		Zeichner = new Grafik();
//		Zeichner.frame.addKeyListener(this);

		Zeichner = new Grafik();
		Zeichner.frame.addKeyListener(this);
		run();

	}
	/**
	 * der Eigentliche Arbeitskonstruktor(zumindest für das Spiel)
	 * @param multiplayer ist es ein spiel mit mehr als einem Spieler?
	 * @param net	Ist es netzwerkfähig?
	 * @param args Spieldatei und Speicherort
	 */
	public void Initialisieren(boolean multiplayer, boolean net, String args){
//		System.out.println(args);
//		
		A = new SpielFeldEinlesen(args);
		Spielfeld = A.getSpielfeld();
		n = Spielfeld.length;
		
		
		A.getItems();
		
		A.getSpieler1();
		
		network = net;
		
		Player[0] = new Bomberman(A.pl_1_x, A.pl_1_y);
		Player[0].BombenAnzahl = A.pl_1_anzbomb;
		Player[0].Leben = A.pl_1_anzleben;
		Player[0].Handschuh = A.pl_1_handschuh;
		Player[0].Kicker = A.pl_1_kicker;
		Player[0].speed = A.pl_1_speed;
		Player[0].BombenReichweite = A.pl_1_bombreichweite;
		
//		Zeichner.Spieler1 = Player[0];
		Player_Index[0] = 0;
		Player_Index[1] = 1;
		
		if(multiplayer){
			A.getSpieler2();
			
			Player[1] = new Bomberman(A.pl_2_x, A.pl_2_y);
			Player[1].BombenAnzahl = A.pl_2_anzbomb;
			Player[1].Leben = A.pl_2_anzleben;
			Player[1].Handschuh = A.pl_2_handschuh;
			Player[1].Kicker = A.pl_2_kicker;
			Player[1].speed = A.pl_2_speed;
			Player[1].BombenReichweite = A.pl_2_bombreichweite;
			
//			Zeichner.Spieler2 = Player[1];
			
			Spieler_2 = true;
		}
		
		if(network){
			   
//			SERVER = true;
			   
			   if(!SERVER){
				   System.out.println("CLIENT");
			    //Hier setze ich den jeweiligen Player_Index: Der Server muss beim Client die "normalen" hoch/runter.....booleans setzen
			    //Der Client muss beim Server die jeweiligen hoch2/runter2....booleans setzen usw.

			     Player_Index[0] = 1;
			     Player_Index[1] = 0;
			  
			    A.getSpieler2();
			    
			    Player[1] = new Bomberman(A.pl_2_x, A.pl_2_y);
			    Player[1].BombenAnzahl = A.pl_2_anzbomb;
			    Player[1].Leben = A.pl_2_anzleben;
			    Player[1].Handschuh = A.pl_2_handschuh;
			    Player[1].Kicker = A.pl_2_kicker;
			    Player[1].speed = A.pl_2_speed;
			    Player[1].BombenReichweite = A.pl_2_bombreichweite;
			       
//			    Zeichner.Spieler2 = Player[1];
			    
			    Spieler_2 = true;
			    
			    Zeichner.Spieler1 = Player[Player_Index[0]];
			    Zeichner.Spieler2 = Player[Player_Index[1]];
			    
			    
			    Zeichner.Init();
			    init = true;
			    
			    //Es reicht, nur abzufragen, ob man "Client" ist, da für den Server der Player_Index oben schon richtig gesetzt wurde.
			    
		
			     try {
			    	client = new Client1();
		
					client.start();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			   }
			   if(SERVER){
				   System.out.println("SERVER");
				 //Hier setze ich den jeweiligen Player_Index: Der Server muss beim Client die "normalen" hoch/runter.....booleans setzen
				    //Der Client muss beim Server die jeweiligen hoch2/runter2....booleans setzen usw.

				     Player_Index[0] = 0;
				     Player_Index[1] = 1;
				  
				    A.getSpieler2();
				    
				    Player[1] = new Bomberman(A.pl_2_x, A.pl_2_y);
				    Player[1].BombenAnzahl = A.pl_2_anzbomb;
				    Player[1].Leben = A.pl_2_anzleben;
				    Player[1].Handschuh = A.pl_2_handschuh;
				    Player[1].Kicker = A.pl_2_kicker;
				    Player[1].speed = A.pl_2_speed;
				    Player[1].BombenReichweite = A.pl_2_bombreichweite;
				       
//				    Zeichner.Spieler2 = Player[1];
				    
				    Spieler_2 = true;
				    
				    Zeichner.Spieler1 = Player[Player_Index[0]];
				    Zeichner.Spieler2 = Player[Player_Index[1]];
				    
				    
				    Zeichner.Init();
				    init = true;
				    
				    try {
				    	server = new Server2();
				    	server.start();
						client.start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    
			   } 
		
//			   if(SERVER) SERVER = false;
//			   else SERVER = true;
			   
			   

			  }
//		if(network){
//			
//			
//			
//			CLIENT = true;
//			SERVER = false;
//			if(CLIENT){
//				try {
//					Client1.con();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if(SERVER){
//				
//			}	
//			
//			//Hier setze ich den jeweiligen Player_Index: Der Server muss beim Client die "normalen" hoch/runter.....booleans setzen
//			//Der Client muss beim Server die jeweiligen hoch2/runter2....booleans setzen usw.
//			if(CLIENT){
//				Player_Index[0] = 1;
//				Player_Index[1] = 0;
//			}
//			//Es reicht, nur abzufragen, ob man "Client" ist, da für den Server der Player_Index oben schon richtig gesetzt wurde.
//			
//			
//			
//			A.getSpieler2();
//			
//			Player[1] = new Bomberman(A.pl_2_x, A.pl_2_y);
//			Player[1].BombenAnzahl = A.pl_2_anzbomb;
//			Player[1].Leben = A.pl_2_anzleben;
//			Player[1].Handschuh = A.pl_2_handschuh;
//			Player[1].Kicker = A.pl_2_kicker;
//			Player[1].speed = A.pl_2_speed;
//			Player[1].BombenReichweite = A.pl_2_bombreichweite;
//						
////			Zeichner.Spieler2 = Player[1];
//			
//			Spieler_2 = true;
//		}
		
		Zeichner.Spieler1 = Player[Player_Index[0]];
		Zeichner.Spieler2 = Player[Player_Index[1]];

		
		Zeichner.Init();
	
		init = true;
//		run();
	}
	
//	public void Initialisieren(int a1, int b1, int a2, int b2){
//		Spieler_2 = true;
//		Spieler = new Bomberman(a1*40, b1*40);
//		Spieler2 = new Bomberman(a2*40, b2*40);
//		Zeichner.Spieler1 = Spieler;
//		Zeichner.Spieler2 = Spieler2;
//		Zeichner.Init();
//		init = true;
////		run();
//	}
	
	
	/**
	 * Hier lassen wir einfach eine Endlosschleifen durchlaufen, checken, ob die Tasten für hoch, runter etc. gedrückt sind und führen die entsprechenden Aktionen aus
	 * 
	 */
	@Override
	/**
	 * hier läuft der gesamte testencheck und die logik ab, außerdem findet hier das startmenü statt
	 */
	public void run(){
	//	new SoundCheckLoop("Sunny Day.wav");


		
		while(truu){

			if(init){


//				op=42;
				if(speichern){
					new SpielStandSpeichern();
					speichern = false;
				}
				if(zufall){
					new Zufallsgenerator();
					zufall = false;
				}
				if(!Player[Player_Index[0]].tot){
					if(neueRichtung1){
						Zeichner.setNeueRichtung();
						neueRichtung1 = false;
					}
					if(neueRichtung2){
						Zeichner.setNeueRichtung2();
						neueRichtung2 = false;
						}
//					if(hoch){
//						if(CLIENT){
//							try {
//								Client1.con(0);
//							} catch (Exception e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						if(SERVER){
//							
//						}	
//					}
//					if(hoch){
//						System.out.println("kakapups");
//					}
					if(hoch){
						Player[Player_Index[0]].geheNachOben();
						
					}
					if(runter){
						Player[Player_Index[0]].geheNachUnten();
						
					}
					if(links){
						Player[Player_Index[0]].geheNachLinks();
			
					}
					if(rechts){
						Player[Player_Index[0]].geheNachRechts();
					
					}
					if(bombelegen){
						if(Player[Player_Index[0]].BombenAnzahl != 0 && !BombeErstellen.liegtBombe((int) ((Player[Player_Index[0]].x+15)/40), (int) ((Player[Player_Index[0]].y+15)/40))){
							Player[Player_Index[0]].BombenAnzahl-=1;
							BombeErstellen neueBombe = new BombeErstellen((int) ((Player[Player_Index[0]].x+15)/40), (int) ((Player[Player_Index[0]].y+20)/40), Player[Player_Index[0]].BombenReichweite, 2500, 1);
							neueBombe.start();
							//new SoundCheck("BombeLegen1.wav");
						}
						bombelegen = false;
						if(network){
							if(SERVER){
								try {
									server.output.write(255);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								try {
									client.output.write(255);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					if(itembenutzen){
						  if(Player[Player_Index[0]].Handschuh){
						      erstelleFlugItem(((int) (Player[Player_Index[0]].x+15)/40*40), (int)((Player[Player_Index[0]].y+15)/40*40), 1, 1);
						      Player[Player_Index[0]].Handschuh=false;
						     }
						       if(Player[Player_Index[0]].Kicker){
						     Player[Player_Index[0]].Kicker=false;
						      erstelleFlugItem(((int) (Player[Player_Index[0]].x+15)/40*40), (int)((Player[Player_Index[0]].y+15)/40*40), 2, 1);
						      
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
					if(!Player[Player_Index[1]].tot){
						if(hoch2){
							Player[Player_Index[1]].geheNachOben();
							}
						if(runter2){
							Player[Player_Index[1]].geheNachUnten();
						}
						if(links2){
							Player[Player_Index[1]].geheNachLinks();
						}
						if(rechts2){
							Player[Player_Index[1]].geheNachRechts();
						}
						if(bombelegen2){
							if(Player[Player_Index[1]].BombenAnzahl != 0 && !BombeErstellen.liegtBombe((int) ((Player[Player_Index[1]].x+15)/40), (int) ((Player[Player_Index[1]].y+15)/40))){
								Player[Player_Index[1]].BombenAnzahl-=1;
								BombeErstellen neueBombe = new BombeErstellen((int) ((Player[Player_Index[1]].x+15)/40), (int) ((Player[Player_Index[1]].y+20)/40), Player[Player_Index[1]].BombenReichweite, 2500, 2);
								neueBombe.start();
						//		new SoundCheck("BombeLegen1.wav");
							}
							bombelegen2 = false;
						}
						if(itembenutzen2){
							 if(Player[Player_Index[1]].Handschuh){
							      erstelleFlugItem((int)((Player[Player_Index[1]].x+15)/40*40), (int)((Player[Player_Index[1]].y+15)/40*40), 1, 2);
							      Player[Player_Index[1]].Handschuh=false; 
							   }
							   if(Player[Player_Index[1]].Kicker){
							      erstelleFlugItem((int)((Player[Player_Index[1]].x+15)/40*40), (int)((Player[Player_Index[1]].y+15)/40*40), 2, 2);
							      Player[Player_Index[1]].Kicker=false;
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
					if(zaehler == 0) Initialisieren(false,false, "level5.txt");
					if(zaehler == 1) Initialisieren(true,false, "level5.txt");
					if(zaehler == 2) {
						SERVER = true;
						Initialisieren(false,true, "level5.txt");
						
					}
					if(zaehler == 3) Initialisieren(true, false, "Zwischenstand.txt");
					if(zaehler == 4) {
						SERVER = false;
						Initialisieren(false,true, "level5.txt");
						
					}
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
		System.out.println("Player2: X: " + Player[1].x + " Y: " + Player[1].y);
//		System.out.println("Player1: X: " + Player[0].x + " Y: " + Player[0].y);
		
	}
	
	/**
	 * Wir haben unsere ItemListe "ItemHead" und fügen am Ende der Liste ein zufällig erstelltes Item ein.
	 * 
	 * @param x x-koordinate im Spielfeld
	 * @param y y-koordinate im Spielfeld
	 */
	public static void erstelleItem(int x, int y){
		if(!network){
		if(!Spielfeld[y][x].Ausgang){
		
			int i = (int)(Math.random()*100);
			if(i <= 35){
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
				//new SoundCheck("ring.wav");
				}
			}
		}
	}
	/**
	 * die klasse kann mit einem festen itemtyp das item setzen
	 * @param i der Typ des items
	 * @param x seine position x
	 * @param y seine positon y
	 */
public static void erstellefestesItem(int i, int x, int y){
	Item item;
	
	switch(i){
	case 0: item = new Item(0, x, y); break;
	case 1: item = new Item(8, x, y); break;
	case 2: item = new Item(12, x, y); break;
	case 3: item = new Item(16, x, y); break;
	case 4: item = new Item(20, x, y); break;
	default: item = new Item(28, x, y); break;
	
	}


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

	}

	/**
	 * 
	 * @return gibt den kopf der liste zurück
	 */
	public BombenListe getBombenListe(){
		return BombHead;
	}
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Erstellt ein Flugitem
	 * @param x Positon x
	 * @param y Position y
	 * @param typ der Typ(1 Für Handschuh, 2 für Kicker)
	 * @param spieler (der betreffende Spieler)
	 */
	public static void erstelleFlugItem(int x, int y, int typ, int spieler){
		  int itemrichtung;
		  System.out.println("x: "+x+"y: "+y);
		  if(rechts) {
			  itemrichtung=0;
			  if(typ==1)x+=40;
		  }
		  else if(links){
			  itemrichtung=1;
			 if(typ==1) x-=40;
		  }
		  else if(hoch){
			  itemrichtung=2;
			  if(typ==1) y-=40;
		  }
		  else {
			  itemrichtung=3;
			  if(typ==2){ y+=40;
			  System.out.println("a");}
		  }
//		  else if(runter&&rechts)itemrichtung=4;
//		  else if(runter&&links)itemrichtung=5;
//		  else if(hoch&&rechts)itemrichtung=6;
//		  else itemrichtung = 7;
		  	System.out.println("x: "+x+"y: "+y);
		  FlugItem Flugitem=new FlugItem(x,y,itemrichtung, typ, spieler);
		  
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
	/**
	 * Die Flugitemliste wird durchgegangen und es wird die bewege-Methode benutzt
	 */
	public void bewegeFlugItem(){
		for(FlugItemNode = FlugItemHead; FlugItemNode != null; FlugItemNode = FlugItemNode.next){
			FlugItemNode.getItem().bewege();
		}
	}
	/**
	 * entferne das flugitem
	 * @param a position x
	 * @param b position y
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
	
	/**
	 * die main-Methode ruft das Gamepanel auf
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		Gamepanel A=new Gamepanel("level5.txt");
	}
	
	
}
