/**
 * dynamische, einzeln verkettete Liste. "element" ist die Bombe und "next" die nächste BombenListe
 * 
 *
 *
 */
public class BombenListe {
	
	Bombe element;
	BombenListe next;
	/**
	 * 
	 */
	public BombenListe(){
		element = null;
		next = null;		
	}
	/**
	 * 
	 * @param ele bombenelement
	 * @param n bobenliste, hier ist die rekursion(die verkettung)
	 */
	public BombenListe(Bombe ele, BombenListe n){
		element = ele;
		next = n;
	}
	/**
	 * 
	 * @param ele element wird gesetzt
	 */
	public void setBombe(Bombe ele){
		element = ele;
	}
	/**
	 * 
	 * @return gibt die bombe zurück
	 */
	public Bombe getBombe(){
		return element;
	}
	/**
	 * 
	 * @param n setzt das nächste element, übergeben als liste
	 */
	public void setNext(BombenListe n){
		next = n;
	}
	/**
	 * 
	 * @return gibt das nä. zurück
	 */
	public BombenListe getNext(){
		return next;
	}

}
