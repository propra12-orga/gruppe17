
public class Bombe {
	int x,y;
	int Reichweite;
	public Bombe(int x,int y, int r){
		this.x = x;
		this.y = y;
		Reichweite = r;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getReichweite(){
		return Reichweite;
	}

}
