import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

	
	/**
	 * Dieses Programm Speichert den aktuellen Spielstand des Spieles
	 * 
	 *
	 *
	 */
public class SpielStandSpeichern {
	 ItemListe liste;
		String file;
		ItemListe Item;
		FlugItemListe FlugItem;
		
		
		/**
		 * Hier wird ein XML-tag über ein Item erstellt
		 * @param a ein beliebiges Item
		 * @return Formattierter String mit den Iteminformationen
		 */
		public String ItemInfo(Item a){
			String s="";
			
			s+="\n\t<Item>";			
			s+="\n\t\t<X>"+a.x+"</X>";
			s+="\n\t\t<Y>"+a.y+"</Y>";
			s+="\n\t\t<Typ>"+a.index+"</Typ>";
			s+="\n\t</Item>";
			
			return s;
		}
		
		
		/**
		 * Hier wird ein XML-tag über ein FlugItem erstellt
		 * @param a ein beliebiges FlugItem
		 * @return Formattierter String mit den FlugIteminformationen
		 */
		public String FlugItemInfo(FlugItem a){
			String s="";
			
			s+="\n\t<FlugItem>";			
			s+="\n\t\t<X>"+a.x+"</X>";
			s+="\n\t\t<Y>"+a.y+"</Y>";
			s+="\n\t\t<Typ>"+a.typ+"</Typ>";
			s+="\n\t\t<Richtung>"+a.typ+"</Richtung>";
			s+="\n\t</FlugItem>";
			
			return s;
		}
		

		/**
		 * Holt den Typ des items
		 * @param x die position x des items
		 * @param y die y-position des items
		 * @return der typ des items
		 */
		
		public 	int einItem(int x, int y){
			
			
			
			for(liste=Gamepanel.ItemHead;liste!=null;liste=liste.next){
				if(liste.getItem().getX()==x&&liste.getItem().getY()==y){
				
					return liste.getItem().getIndex();
										}
									}
							return -1;
						}
		
		/**
		 * Arbeitskonstruktor, speichert in der Zwischenstand.txt
		 */

			public SpielStandSpeichern(){//der arbeitskonstruktor, der alles setzt
		
				file="Zwischenstand.txt";
				int x=Gamepanel.Spielfeld.length;//hol die spielfeld-länge
				

				
		
		
		String ausgabe="";
		ausgabe+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		ausgabe+="\n";
		
	
		
		
		ausgabe+="<level>";
		ausgabe+="\n";
		try{
		File schreiber=new File(file);
		FileWriter fwriter=new FileWriter(schreiber);
		
		/**
		 * Die Methode muss mehrmals verwendet werden, da sonst der String oft schnell zu groß wird
		 */
		for(int k=0;k<ausgabe.length();k++){
			char c=ausgabe.charAt(k);
			if(c=='\n')
				fwriter.append( System.getProperty("line.separator") );
			else fwriter.write(c);
		}
			ausgabe="";
			
			
			
			
			for(int i=0;i<x;i++){//methode aus dem schreibe_xml
				
				for(int j=0;j<x;j++){				
		ausgabe+=("\t<Feld>");
		ausgabe+="\n";
		ausgabe+=("\t\t<Typ>");
		if(Gamepanel.Spielfeld[i][j].Ausgang)
		ausgabe+="Ausgang";
		else if(Gamepanel.Spielfeld[i][j].unzerstoerbar)
			ausgabe+="unzerstoerbar";
		else if(Gamepanel.Spielfeld[i][j].zerstoerbar)
			ausgabe+="zerstoerbar";
		else ausgabe+="Weg";
		
		ausgabe+=("</Typ>");
		ausgabe+="\n";
		ausgabe+=("\t\t<Position><X>"+j+"</X><Y>"+i+"</Y></Position>\n");
		ausgabe+=("\t</Feld>\n");
		
										}
			for(int k=0;k<ausgabe.length();k++){
				char c=ausgabe.charAt(k);
				if(c=='\n')
					fwriter.append( System.getProperty("line.separator") );
				else fwriter.write(c);
			}
				ausgabe="";
			
														

					}
			
			
					ausgabe+="\n\t<Spieler1>";			
					ausgabe+="\n\t\t<X>"+Gamepanel.Player[Gamepanel.Player_Index[0]].getX()+"</X>";
					ausgabe+="\n\t\t<Y>"+Gamepanel.Player[Gamepanel.Player_Index[0]].getY()+"</Y>";
					ausgabe+="\n\t\t<AnzBomb>"+Gamepanel.Player[Gamepanel.Player_Index[0]].BombenAnzahl+"</AnzBomb>";
					ausgabe+="\n\t\t<AnzLeben>"+Gamepanel.Player[Gamepanel.Player_Index[0]].Leben+"</AnzLeben>";
					ausgabe+="\n\t\t<Handschuh>"+Gamepanel.Player[Gamepanel.Player_Index[0]].Handschuh+" </Handschuh>";
					ausgabe+="\n\t\t<Kicker>"+Gamepanel.Player[Gamepanel.Player_Index[0]].Kicker+ "</Kicker>";
					ausgabe+="\n\t\t<Speed>"+Gamepanel.Player[Gamepanel.Player_Index[0]].speed+"</Speed>"; 
					ausgabe+="\n\t\t<BombReichweite>"+Gamepanel.Player[Gamepanel.Player_Index[0]].BombenReichweite+"</BombReichweite>";
					ausgabe +="\n\t</Spieler1>";
					
					if(Gamepanel.Spieler2!=null){
						ausgabe+="\n\t<Spieler2>";			
						ausgabe+="\n\t\t<X>"+Gamepanel.Player[Gamepanel.Player_Index[1]].getX()+"</X>";
						ausgabe+="\n\t\t<Y>"+Gamepanel.Player[Gamepanel.Player_Index[1]].getY()+"</Y>";
						ausgabe+="\n\t\t<AnzBomb>"+Gamepanel.Player[Gamepanel.Player_Index[1]].BombenAnzahl+"</AnzBomb>";
						ausgabe+="\n\t\t<AnzLeben>"+Gamepanel.Player[Gamepanel.Player_Index[1]].Leben+"</AnzLeben>";
						ausgabe+="\n\t\t<Handschuh>"+Gamepanel.Player[Gamepanel.Player_Index[1]].Handschuh+" </Handschuh>";
						ausgabe+="\n\t\t<Kicker>"+Gamepanel.Player[Gamepanel.Player_Index[1]].Kicker+ "</Kicker>";
						ausgabe+="\n\t\t<Speed>"+Gamepanel.Player[Gamepanel.Player_Index[1]].speed+"</Speed>"; 
						ausgabe+="\n\t\t<BombReichweite>"+Gamepanel.Player[Gamepanel.Player_Index[1]].BombenReichweite+"</BombReichweite>";
						ausgabe +="\n\t</Spieler2>";
					} else {
						ausgabe+="\n\t<Spieler2>";			
						ausgabe+="\n\t\t<X>"+520+"</X>";
						ausgabe+="\n\t\t<Y>"+520+"</Y>";
						ausgabe+="\n\t\t<AnzBomb>"+1+"</AnzBomb>";
						ausgabe+="\n\t\t<AnzLeben>"+1+"</AnzLeben>";
						ausgabe+="\n\t\t<Handschuh>"+false+" </Handschuh>";
						ausgabe+="\n\t\t<Kicker>"+false+ "</Kicker>";
						ausgabe+="\n\t\t<Speed>"+0.0000275+"</Speed>"; 
						ausgabe+="\n\t\t<BombReichweite>"+2+"</BombReichweite>";
						ausgabe +="\n\t</Spieler2>";	
					}
					
					
					for(int i=0;i<ausgabe.length();i++){
						char c=ausgabe.charAt(i);
						if(c=='\n')
							fwriter.append( System.getProperty("line.separator") );
						else fwriter.write(c);
					}
					
					ausgabe="";
					for(Item=Gamepanel.ItemHead;Item!=null;Item=Item.next){
						ausgabe+=ItemInfo(Item.getItem());
						for(int i=0;i<ausgabe.length();i++){
							char c=ausgabe.charAt(i);
							if(c=='\n')
								fwriter.append( System.getProperty("line.separator") );
							else fwriter.write(c);
						}
						ausgabe="";
						
						
					}
					
					for(FlugItem=Gamepanel.FlugItemHead;FlugItem!=null;FlugItem=FlugItem.next){
						ausgabe+=FlugItemInfo(FlugItem.getItem());
						for(int i=0;i<ausgabe.length();i++){
							char c=ausgabe.charAt(i);
							if(c=='\n')
								fwriter.append( System.getProperty("line.separator") );
							else fwriter.write(c);
						}
						ausgabe="";
						
						
					}
					
	
			ausgabe+="\n\n\n</level>";
			for(int i=0;i<ausgabe.length();i++){
				char c=ausgabe.charAt(i);
				if(c=='\n')
					fwriter.append( System.getProperty("line.separator") );
				else fwriter.write(c);
			}
			
		
				
			fwriter.flush();
			fwriter.close();
			}
			
			
			
		catch(IOException e){
			e.printStackTrace();
		
		}
	
			}


}