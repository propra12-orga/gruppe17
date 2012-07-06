
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
	 * @return gib das Item zur�ck
	 */
	public FlugItem getItem(){
		return element;
	}
	/**
	 * 
	 * @param n setze das n�chste, wobei dies die FlugItemLste ist
	 */
	public void setNext(FlugItemListe n){
		next = n;
	}
	/**
	 * 
	 * @return gib das n�chste element zur�ck
	 */
	public FlugItemListe getNext(){
		return next;
	}

}


