

	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
/**
 * 
 * Diese Klasse schreibt einen Bestehendes Spiel in XML auf, Tags könne in QUickload eingelasen werden
 *
 */
	public class Schreibe_xml {

		int laenge;
		String file;
		String ausgabe="";

		/**
		 * Der Arbeitskonstruktor
		 * @param laenge Die länge des SPielfeldes
		 * @param level welches level soll es sein, wird automatisch zu levelx.txt verbunden(x ist der paramenet level)
		 */
		
		public Schreibe_xml(int laenge, int level){
			ausgabe+="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
			ausgabe+="\n";
			this.laenge=laenge;
			this.file="level"+level+".txt";
			
			
			ausgabe+="<level>";
			ausgabe+="\n";
			try{
			File schreiber=new File(file);
			FileWriter fwriter=new FileWriter(schreiber);
	
				
				
				
				for(int i=0;i<laenge;i++){
			
						for(int j=0;j<laenge;j++){				
				ausgabe+=("\t<Feld>");
				ausgabe+="\n";
				ausgabe+=("\t\t<Typ>");
				if(i==laenge/2&&j==laenge/2){
					
					ausgabe+=("Ausgang");
				}
				else if((i==1&&j==1)||(i==2&&j==1)||(i==1&&j==2)||(i==(laenge-2)&&j==(laenge-2))||(i==(laenge-3)&&j==(laenge-2))||(i==(laenge-2)&&j==(laenge-3)))
					ausgabe+=("Weg");
				else if(i==0||j==0||i==(laenge-1)||j==(laenge-1))
					ausgabe+=("unzerstoerbar");
				else if(i%2==0&&j%2==0)ausgabe+=("unzerstoerbar");
				else if((i==1&&j==1)||(i==2&&j==1)||(i==1&&j==2)||(i==(laenge-2)&&j==(laenge-2))||(i==(laenge-3)&&j==(laenge-2))||(i==(laenge-2)&&j==(laenge-3)))
					ausgabe+=("Weg");
				else ausgabe+=("zerstoerbar");
				
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
		
		
		/**
		 * die Main
		 * @param args
		 */
		
	public static void main(String[] args){

	new Schreibe_xml(15,4);
		
		
	}
	}


