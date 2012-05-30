
public class Bomberman {
	public double x;
	public double y;
	double speed=1;
	int Leben = 1;
	int BombenReichweite=2;
	int BombenAnzahl = 1;
	boolean Handschuh = false;
	boolean Kicker = false;
	
	public Bomberman(int a, int b){
		this.x=a;
		this.y=b;
	}
	
	public void geheNachOben(){
		this.y += speed;
	}
	
	public void geheNachUnten(){
		this.y -= speed;
	}
	
	public void geheNachRechts(){
		this.x += speed;
	}
	
	public void geheNachLinks(){
		this.x -= speed;
	}
	
	public void setBombenItem() {
		if(BombenAnzahl<=8) BombenAnzahl+=1;
	}
	public void setExtraLeben() {
		if(Leben == 1) Leben = 2;
		
	}
	public void setFeuer() {
		BombenReichweite+=1;
		
	}
	public void setKicker() {
		Kicker = true;
		
	}
	public void setHandschuh() {
		Handschuh = true;
		
	}
	public void setRollschuh() {
		speed+=0.5;
		
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}

}
