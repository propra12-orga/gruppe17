
/**
 * dynamische, einzeln verkettete Liste, element ist das Item und next die darauf folgende ItemListe
 * 
 * 
 *
 */
public class ItemListe {
	
	Item element;
	ItemListe next;
	
	public ItemListe(Item ele, ItemListe n){
		element = ele;
		next = n;
	}
	
	public void setItem(Item ele){
		element = ele;
	}
	
	public Item getItem(){
		return element;
	}
	
	public void setNext(ItemListe n){
		next = n;
	}
	
	public ItemListe getNext(){
		return next;
	}

}
