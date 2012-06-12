/**
 * die Items erscheinen im Spielfeld und können vom Bomberman aufgenommen werden
 * 
 * 
 *
 */
public  class Item {
	boolean Feuer=false;
	boolean Handschuh=false;
	boolean Kicker=false;
	boolean ExtraLeben=false;
	boolean BombenItem=false;
	boolean Rollschuh=false;
	int x;
	int y;
	int index;
	
	/**
	 * Das Item wird an der Stelle x,y im Spielfeld erstellt
	 * 
	 * @param i Je nachdem, welche Zahl der Parameter i ist, wird der entsprechende boolean auf true gesetzt
	 * @param x x-Koordinate im Spielfeld
	 * @param y y-Koordinate im Spielfeld
	 */
	//
	public Item(int i, int x, int y){
		if(i == 0) Feuer=true;
		if(i == 1) Handschuh=true;
		if(i == 2) Kicker=true;
		if(i == 3) ExtraLeben=true;
		if(i == 4) BombenItem=true;
		if(i == 5) Rollschuh=true;		
		this.x=x;
		this.y=y;
		index = i;
	}
	
	public int getIndex(){
		return index;
	}
}
