import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 
 * Diese Klasse erstellt einen File per zufall
 *
 */

public class Zufallsgenerator {
	String file;
	/**
	 * Der Arbeitskonstruktor(DIeser macht schon die Komplette arbeit, die verteilung der einzelnlen Positionen(der Felder) ist an das reale Bombermna angelehnt
	 */
	public Zufallsgenerator(){//der arbeitskonstruktor, der alles setzt
		
		file="Zwischenstand.txt";
		int x=Gamepanel.Spielfeld.length;//hol die spielfeld-l‰nge

		String ausgabe="";
		ausgabe+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
		ausgabe+="\n";
		
		ausgabe+="<level>";
		ausgabe+="\n";
		try{
		File schreiber=new File(file);
		FileWriter fwriter=new FileWriter(schreiber);
		
		/**
		 * Die Methode muss mehrmals verwendet werden, da sonst der String oft schnell zu groﬂ wird
		 */
		for(int k=0;k<ausgabe.length();k++){
			char c=ausgabe.charAt(k);
			if(c=='\n')
				fwriter.append( System.getProperty("line.separator") );
			else fwriter.write(c);
		}
		ausgabe="";
		double ran;
		
		for(int i=0;i<x;i++){//methode aus dem schreibe_xml
			
			for(int j=0;j<x;j++){				
				ausgabe+=("\t<Feld>");
				ausgabe+="\n";
				ausgabe+=("\t\t<Typ>");
				
				if(i == 0 || i == 14 || j == 0 || j == 14){
					ausgabe+="unzerstoerbar";
				} else if((i == 1 && j == 1) || (i == 1 && j == 2) || (i == 2 && j == 1) || (i == 13 && j == 13)
						|| (i == 13 && j == 12) || (i == 12 && j == 13)) {
					ausgabe+="Weg";
				} else if(i == 7 && j == 7){
					ausgabe +="Ausgang";
				} else {
					ran = (int) (Math.random()*3);
					if(ran == 0) ausgabe += "Weg";
					if(ran == 1) ausgabe += "zerstoerbar";
					if(ran == 2) ausgabe += "zerstoerbar";	
				}
				
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
			ausgabe+="\n\t\t<X>"+40+"</X>";
			ausgabe+="\n\t\t<Y>"+40+"</Y>";
			ausgabe+="\n\t\t<AnzBomb>"+1+"</AnzBomb>";
			ausgabe+="\n\t\t<AnzLeben>"+1+"</AnzLeben>";
			ausgabe+="\n\t\t<Handschuh>"+false+" </Handschuh>";
			ausgabe+="\n\t\t<Kicker>"+false+ "</Kicker>";
			ausgabe+="\n\t\t<Speed>"+0.0000275+"</Speed>"; 
			ausgabe+="\n\t\t<BombReichweite>"+2+"</BombReichweite>";
			ausgabe +="\n\t</Spieler1>";
			
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

				
				
				for(int i=0;i<ausgabe.length();i++){
					char c=ausgabe.charAt(i);
					if(c=='\n')
						fwriter.append( System.getProperty("line.separator") );
					else fwriter.write(c);
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
		} catch(IOException e){
			e.printStackTrace();
		
		}
		
	}

}
