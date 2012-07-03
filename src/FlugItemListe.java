/**
 * dynamische, einzeln verkettete Liste, element ist das Flugitem und next die darauf folgende FlugItemListe
 * 
 * 
 *
 */
public class FlugItemListe {

	FlugItem element;
	FlugItemListe next;
	
	public FlugItemListe(FlugItem ele, FlugItemListe n){
		element = ele;
		next = n;
	}
	
	public void setItem(FlugItem ele){
		element = ele;
	}
	
	public FlugItem getItem(){
		return element;
	}
	
	public void setNext(FlugItemListe n){
		next = n;
	}
	
	public FlugItemListe getNext(){
		return next;
	}

}


