
/**
 * dynamische, einzeln verkettete Liste, element ist das Item und next die darauf folgende ItemListe
 * 
 * 
 *
 */
public class ItemListe {
	
	Item element;
	ItemListe next;
	/**
	 * Arbeitskonstruktor, setzt neues Item
	 * @param ele das itemelement
	 * @param n die rekursion, die weitere liste
	 */
	public ItemListe(Item ele, ItemListe n){
		element = ele;
		next = n;
	}
	/**
	 * setze das item
	 * @param ele element Item
	 */
	public void setItem(Item ele){
		element = ele;
	}
	/**
	 * 
	 * @return Item an aktueler stelle
	 */
	public Item getItem(){
		return element;
	}
	/**
	 * 
	 * @param n setze das nächste
	 */
	public void setNext(ItemListe n){
		next = n;
	}
	/**
	 * 
	 * @return gib das nächste item zurück, allerdings als liste
	 */
	public ItemListe getNext(){
		return next;
	}

}
