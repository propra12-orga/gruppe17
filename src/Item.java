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
		if(i < 8){ Feuer=true; index = 0; }
		else if(i < 12){ Handschuh=true; index = 1; }
		else if(i < 16){ Kicker=true; index = 2; }
		else if(i < 20){ ExtraLeben=true; index = 3; }
		else if(i < 28){ BombenItem=true; index = 4; }
		else if(i <= 35){ Rollschuh=true; index = 5; }
		this.x=x;
		this.y=y;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
}
