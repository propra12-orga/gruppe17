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
public class Quickload {
	
	Feld[][] Spielfeld;
	String s;
	int a, n;
	int inttest;
	double doubletest;
	
	public Quickload(String args){
		
		try {
			File XMLFile = new File(args);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XMLFile);
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
			nList = doc.getElementsByTagName("Spieler");
			Element nNode = null;
			Element eElement = (Element) nNode;
			s = getTagValue("X", eElement);
			
			
			
			
	
	
			
			
		} catch (Exception e) {
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
