import java.awt.image.BufferedImage;

public class Bomber extends Sprite {

	private static final long serialVersionUID = 1L;
	
	public Bomber (BufferedImage[] i , double x, double y, long delay, Gamepanel p){
		super(i,x,y,delay,p);
	}
	
	public void dologik(long delta){
		super.dologik(delta);       
		 // Hier drunter ist die logische Programmierung, wo wir einfach angeben, dass wenn die Randwerte überschritten werden, wir unsere Geschwindigkeit auf 0 setzen
		if(getX()<0){
			shorizontalspeed(0);    
			x=0;
		}
		
		if(getY()<0){
			shorizontalspeed(0);
			y=0;
		}
		// Hier benutze ich variablen, da wir aus Rectangle 2D erben, das erleichtert es uns in sofern, dass wenn wir andere Bildgrößen benutzen, diese automatisch mit einbezogen werden
		if (getX()+getWidth()>parent.getWidth()){
			svertikalspeed(0);
			x =  parent.getWidth()-getWidth();
		}
		
		if (getY()+getHeight()>parent.getHeight()){
			svertikalspeed(0);
			y = parent.getHeight()-getHeight();
			
		}
		
	}
	
}
