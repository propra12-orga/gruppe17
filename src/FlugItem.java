/**
 * Implementiert Handschuh- und Kickeritem
 * 
 * 
 *  
 */
public class FlugItem{
	
	
		
	int richtung;	
	
	double speed=0.0000175;
	double x,y,i,typ;
	int spieler;
	int modulo, modulo2;
	/**
	 * 
	 * @param x Position in der Horizontalen
	 * @param y	Position in der Vertikalen
	 * @param i	Richtung in die das Item abgefeuert wird
	 * @param j	Typ des Items. Kicker/Handschuh
	 * @param k	Der Spieler der das Item bewegt
	 */
	public FlugItem(int x, int y, int i, int j, int k){
		this.x=x;
		this.y=y;
		richtung=i;
		typ=j;
		spieler = k;
		modulo = (Gamepanel.Spielfeld.length - 1)* 40;
		modulo2 = Gamepanel.Spielfeld.length - 1;
		
	}	

	public void bewege() {
		if((int) (x/40) > modulo2 || (int) (y/40) > modulo2){
			Gamepanel.entferneFlugItem(x,  y);
		} else{
			if(this.typ==1){
				if(Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].begehbar  ){
					switch (richtung){
						case 0: this.x+=speed; break;
						case 1: this.x-=speed;break;
						case 2: this.y-=speed;break;
						case 3: this.y+=speed;break;
					}
					
			} else {
				if(spieler == 1){
					BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Spieler.BombenReichweite, 100, 1);
					neueBombe.start();
					Gamepanel.entferneFlugItem( x, y);
				} else if(spieler == 2){
					BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Spieler2.BombenReichweite, 100, 2);
					neueBombe.start();
					Gamepanel.entferneFlugItem( x, y);
				}
	
				}
			}
				
			 if(this.typ==2){
				 if(Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].unzerstoerbar ||  Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].zerstoerbar ){
						switch (richtung){
							case 0: this.x+=speed;break;
							case 1: this.x-=speed;break;
							case 2: this.y-=speed;break;
							case 3: this.y+=speed;break;
						}
						
				} else {
					if(spieler == 1){
						BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Spieler.BombenReichweite, 100, 1);
						neueBombe.start();
						Gamepanel.entferneFlugItem( x, y);
					} else if(spieler == 2){
						BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Spieler2.BombenReichweite, 100, 2);
						neueBombe.start();
						Gamepanel.entferneFlugItem( x, y);
					}
	
				}
			}
		}
	}
	

	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
}
