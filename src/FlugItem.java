
public class FlugItem{
	
	
		
	int richtung;	
	
	double speed=0.0000675;
	double x,y,i,typ;
	int spieler;
	int modulo, modulo2;
	/**
	 * 
	 * @param x x-position
	 * @param y y-position
	 * @param i richtung des gelegten flugitems(0=rechts, 1 =liks, 2=hoch, 3=runter)
	 * @param j (der Typ des Flugitems(1 für Handschuh, 2 für Kicker))
	 * @param k (der spieler, von wem es kommt)
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
/**
 * bewegt das flugitem um eine bestimmte länge, prüft, ob es noch bewegt werden darf
 */
	public void bewege() {
//		if((int) (x/40) > modulo2 || (int) (y/40) > modulo2){
//			Gamepanel.entferneFlugItem(x,  y);
//		} else{
			if(this.typ==1){
				if(!Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].begehbar  ){
					switch (richtung){
						case 0: this.x+=speed; break;
						case 1: this.x-=speed;break;
						case 2: this.y-=speed;break;
						case 3: this.y+=speed;break;
					}
					x%=modulo;
					y%=modulo;
									
			} else {
				if(spieler == 1){
					BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Player[Gamepanel.Player_Index[0]].BombenReichweite, 2500, 1);
					neueBombe.start();
					Gamepanel.entferneFlugItem( x, y);
				} else if(spieler == 2){
					BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Player[Gamepanel.Player_Index[1]].BombenReichweite, 2500, 2);
					neueBombe.start();
					Gamepanel.entferneFlugItem( x, y);
				}
	
				}
			}
				
			 if(this.typ==2){
				 if(Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].unzerstoerbar ||  Gamepanel.Spielfeld[(int) ((x/40))][(int) ((y/40))].zerstoerbar ){
						switch (richtung){
							case 0: this.x+=speed; break;
							case 1: this.x-=speed;break;
							case 2: this.y-=speed;break;
							case 3: this.y+=speed;break;
						}
						x%=40;
						x%=40;
						
				} else {
					if(spieler == 1){
						BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Player[Gamepanel.Player_Index[0]].BombenReichweite, 2500, 1);
						neueBombe.start();
						Gamepanel.entferneFlugItem( x, y);
					} else if(spieler == 2){
						BombeErstellen neueBombe = new BombeErstellen((int) (x/40), (int) (y/40), Gamepanel.Player[Gamepanel.Player_Index[1]].BombenReichweite, 2500, 2);
						neueBombe.start();
						Gamepanel.entferneFlugItem( x, y);
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
