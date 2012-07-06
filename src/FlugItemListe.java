
public class FlugItemListe {

/**
 * dynamische, einzeln verkettete Liste, element ist das Flugitem und next die darauf folgende FlugItemListe
 * 
 * 
 *
 */
	FlugItem element;
	FlugItemListe next;
/**
 * 	
 * @param ele das FlugItemElement
 * @param n Die Rekursion
 */
	public FlugItemListe(FlugItem ele, FlugItemListe n){
		element = ele;
		next = n;
	}
	/**
	 * 
	 * @param ele das item wird gesetzt
	 */
	public void setItem(FlugItem ele){
		element = ele;
	}
	/**
	 * 
	 * @return gib das Item zurück
	 */
	public FlugItem getItem(){
		return element;
	}
	/**
	 * 
	 * @param n setze das nächste, wobei dies die FlugItemLste ist
	 */
	public void setNext(FlugItemListe n){
		next = n;
	}
	/**
	 * 
	 * @return gib das nächste element zurück
	 */
	public FlugItemListe getNext(){
		return next;
	}

}


