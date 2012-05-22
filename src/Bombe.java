import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Bombe extends Sprite{
	
	
	private static final long serialVersionUID = 1L;
	
	int vertikalspeed = 0;
	Rectangle2D.Double target;
	
	Bombe bomb;
	
	void entferne(){
		bomb=null;
	}
	
	
	public Bombe(BufferedImage[] i, double x, double y, long delay, Gamepanel p){
		
		super(i,x,y,delay,p);
		this.entfernen=true;
		
//		if(getY()<parent.getHeight()/2){
//			
//			svertikalspeed(vertikalspeed);
//			
//		}else{
//			
//			svertikalspeed(vertikalspeed);
//			
//		}
		
	}
	
//	public void dologik(long delta){
//		
//		super.dologik(delta);
//		
//		
//		if(ghorizontalspeed()>0){
//			target = new Rectangle2D.Double(getX()+getWidth(),getY(),parent.getWidth()-getX(),getHeight());
//			
//		}else{
//			
//			target = new Rectangle2D.Double(0,getY(),getX(),getHeight());
//			
//		}
//		
//	}
	
//	public void shorizontspeed(double d){
//		
//		super.shorizontalspeed(d);
//		
//		if(ghorizontalspeed()>0){
//			
//			setLoop(4,7);
//			
//		}else{
//			
//			setLoop(0,3);
//			
//		}
//	}
	public int getObj(){
		
		return 1;
	}
	
	
}
