
public class BombenListe {
	Bombe element;
	BombenListe next;
	
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
