/**
 * dynamische, einzeln verkettete Liste. "element" ist die Bombe und "next" die nächste BombenListe
 * 
 *
 *
 */
public class BombenListe {
	
	Bombe element;
	BombenListe next;
	
	public BombenListe(){
		element = null;
		next = null;		
	}
	
	public BombenListe(Bombe ele, BombenListe n){
		element = ele;
		next = n;
	}
	
	public void setBombe(Bombe ele){
		element = ele;
	}
	
	public Bombe getBombe(){
		return element;
	}
	
	public void setNext(BombenListe n){
		next = n;
	}
	
	public BombenListe getNext(){
		return next;
	}

}
