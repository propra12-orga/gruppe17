import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
 


/**
 * LIEST XML DATEI EIN UND GIBT NEUES SPIELFELD-ARRAY VOM TYP FELD[][] ZURUECK. SPIELFELD.LENGTH IST MINDESTENS 2 UND MAXIMAL 40;
 * 
 * 
 *
 */
public class SpielFeldEinlesen {
	
	File XMLFile;
	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	
	Feld[][] Spielfeld;
	String s, pos_x, pos_y, typ;
	int a, n, b;
	String args;
	int x_pos, y_pos, int_typ;
	
	double pl_1_x, pl_1_y, pl_2_x, pl_2_y, pl_1_speed, pl_2_speed;
	int pl_1_anzbomb, pl_2_anzbomb, pl_1_anzleben, pl_2_anzleben, pl_1_bombreichweite, pl_2_bombreichweite;
	boolean pl_1_handschuh, pl_2_handschuh, pl_1_kicker, pl_2_kicker;
	
	
	public SpielFeldEinlesen(String s){
		args = s;
		try {
			XMLFile = new File(args);
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(XMLFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("Feld");
			a = nList.getLength();
			n=2;
			while(n<=40){
				if(n*n == a) break;
				n++;				
			}
			
			Spielfeld = new Feld[n][n];
			
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					Node nNode = nList.item(i*n+j);
					if(nNode.getNodeType() == Node.ELEMENT_NODE){
						Element eElement = (Element) nNode;
						
						s = getTagValue("Typ", eElement);						
						if(s.equals("Weg")){
							Spielfeld[i][j] = new Feld(3);						
							continue;
						}
						if(s.equals("unzerstoerbar")) {
							Spielfeld[i][j] = new Feld(2);
							continue;
						}
						if(s.equals("zerstoerbar")) {
							Spielfeld[i][j] = new Feld (1);
							continue;
						}
						if(s.equals("Ausgang")) {
							Spielfeld[i][j] = new Feld (4);
							continue;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		  }
		
	}
	/**
	 * hole die bestehenden items aus der xml-datei und speichere die im Itemhead des Gamepanels
	 */
	public void getItems(){

		try {

			NodeList nList_item = doc.getElementsByTagName("Item");
			
			
			a = nList_item.getLength();
			
			
			for(int i = 0; i < a; i++){
				Node nNode = nList_item.item(i);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					
					pos_x = getTagValue("X", eElement);
					x_pos = Integer.parseInt(pos_x);
					pos_y = getTagValue("Y", eElement);
					y_pos = Integer.parseInt(pos_y);
					typ = getTagValue("Typ", eElement);
					int_typ = Integer.parseInt(typ);
					
					Gamepanel.erstellefestesItem(int_typ,  x_pos, y_pos);
				}
			}
		}	catch (Exception e) {
				e.printStackTrace();
		  }
	}

/**
 * Hole die Daten von Spieler 1
 */
	public void getSpieler1(){
		try {
			
			NodeList nList_player = doc.getElementsByTagName("Spieler1");

				Node nNode = nList_player.item(0);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					
					s = getTagValue("X", eElement);
					pl_1_x = Double.parseDouble(s);
					s = getTagValue("Y", eElement);
					pl_1_y = Double.parseDouble(s);
					s = getTagValue("Speed", eElement);
					pl_1_speed = Double.parseDouble(s);
					s = getTagValue("AnzBomb", eElement);
					pl_1_anzbomb = Integer.parseInt(s);
					s = getTagValue("AnzLeben", eElement);
					pl_1_anzleben = Integer.parseInt(s);
					s = getTagValue("BombReichweite", eElement);
					pl_1_bombreichweite= Integer.parseInt(s);
					s = getTagValue("Handschuh", eElement);
					pl_1_handschuh= Boolean.parseBoolean(s);
					s = getTagValue("Kicker", eElement);
					pl_1_kicker= Boolean.parseBoolean(s);
					
				}
			
		}	catch (Exception e) {
				e.printStackTrace();
		  }
		
		
	}
	/**
	 * Hole die DAten von Spieler 2
	 */
	public void getSpieler2(){
		try {
			
			NodeList nList_player = doc.getElementsByTagName("Spieler2");

				Node nNode = nList_player.item(0);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					
					s = getTagValue("X", eElement);
					pl_2_x = Double.parseDouble(s);
					s = getTagValue("Y", eElement);
					pl_2_y = Double.parseDouble(s);
					s = getTagValue("Speed", eElement);
					pl_2_speed = Double.parseDouble(s);
					s = getTagValue("AnzBomb", eElement);
					pl_2_anzbomb = Integer.parseInt(s);
					s = getTagValue("AnzLeben", eElement);
					pl_2_anzleben = Integer.parseInt(s);
					s = getTagValue("BombReichweite", eElement);
					pl_2_bombreichweite= Integer.parseInt(s);
					s = getTagValue("Handschuh", eElement);
					pl_2_handschuh= Boolean.parseBoolean(s);
					s = getTagValue("Kicker", eElement);
					pl_2_kicker= Boolean.parseBoolean(s);
					
				}
			
		}	catch (Exception e) {
				e.printStackTrace();
		  }
		
		
	}
	
	
	/*
	 * gibt das Spielfeld zurück
	 * 
	 */
	public Feld[][] getSpielfeld(){
		return Spielfeld;
	}
	/**
	 * Hilfsmethode, um besser mit xml-tags arbeitne zu können(fordert den tag und das betreffenden elementtyp
	 * @param sTag der tag
	 * @param eElement das element
	 * @return der in ihm enthaltende tag
	 */
	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
	 
	        Node nValue = nlList.item(0);
	 
		return nValue.getNodeValue();
	  }

}
