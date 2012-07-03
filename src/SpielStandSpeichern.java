import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Speichert das Spielfeld	
 * 
 *
 */
	
public class SpielStandSpeichern {
	 ItemListe liste;
		String file;
		
		
		public 	int einItem(int x, int y){
			
			
			
			for(liste=Gamepanel.ItemHead;liste!=null;liste=liste.next){
				if(liste.getItem().getX()==x&&liste.getItem().getY()==y){
				
					return liste.getItem().getIndex();
										}
									}
							return -1;
						}

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
			
			
		
	








	

