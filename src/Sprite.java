import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class Sprite extends Rectangle2D.Double implements Bewegungen, Zeichnungen {

	private static final long serialVersionUID = 1L;
	long delay; // Insanzvariable um das Umschalten zwischen den Bildern unseres Arrays zu steuern
	long animation = 0; // Vorerst nur die Variable hinterlegt, welche wir später für die Animationen benötigen
	Gamepanel parent; // Referenz auf unser Gamepanel
	BufferedImage [] pics; // zum Speichern der Animationen in Einzelbilder
	int currentpic = 0; // Zähler für das aktuell anzuzeigende Bild
	
	protected double dx;    // Dies wird unsere Bewegungsvariable (dx = horizontal bewegen, dy = vertikal bewegen)
	protected double dy;
	
	static boolean abbruch=false;
	
	static int[][] array=new int[600][600];
	
	int loop_from;
	int loop_to;
	
	boolean entfernen; //benötigen wir um Objekte, die nicht mehr benötigt werden zu entfernen damit wir den benötigten Arbeitsspeicher nicht kontinuierlich erhöhen
	
	public Sprite (BufferedImage[] i, double x, double y, long delay, Gamepanel p) {
		
		pics = i;
		this.x = x;
		this.y = y;
		this.delay = delay;
		this.width = pics[0].getWidth();
		this.height = pics[0].getHeight();
		parent = p;
		// Dies ist der Konstruktor mit der Übergabe des Image-Arrays für die Animation, Positionswerten, Verzögerung der Animation und der Referenz zum Gamepanel.
		// Zusätzlich erhalten wir aus dem ersten Bild die Höhe und Breite, unter der Annahme, das alle folgenden die gleichen Werte besitzen
		loop_from = 0;
		loop_to = pics.length-1;
		
	}
	
	public void setArray(int x,int y){
		for(int i=x;i<x+20;i++){
			for(int j=y;j<y+20;j++){
				array[i][j]=1;
			}
		}
	}
	
	
	public void zeichneObjekte(Graphics g) {
		g.drawImage(pics[currentpic], (int) x, (int) y, null);   // Beim x und y Parametern beachten, dass die Zahlen auf Ganze Zahlen herunter gebrochen werden!! Das ist notwendig, weil Graphics Ganze Zahlen verlangt
		
	}                                                            

	public void dologik(long delta) {
		animation += (delta/1000000);  // delta/1000000, ich benutze hier Millisekunden, da wir die Animationsgeschwindigkeit so besser einstellen können
		
		if (animation > delay){
			animation = 0;
			computeAnimation();
		}
		
	}	
	private void computeAnimation(){
		currentpic++;        // Hier lassen wir unsere Bilder durchlaufen. Jetzt unter Berücksichtigung von loop_from und loop_to
							 // gegebenenfalls wird dadurch nicht mehr das komplette Bild-Array berücksichtigt
		
		if (currentpic >= loop_to){    
			currentpic = loop_from;
		}
		
	}

	public void setLoop(int from, int to){
		
		loop_from = from;
		loop_to = to;
		currentpic = from;
		
	}
	public void bewegen(long delta) {  
		// Wenn unser Delta nicht null ist, verändern wir so die Position unseres Objektes in die entsprechende Richtung
		// Durch die Berücksichtigung der Durchlaufzeit, müsste somit eine gleichförmige Bewegung gewährleistet sein
		// >>>> Hoffentlich auch dann, wenn umfangreichere Berechnungen anfallen sollten <<<<
		if (dx != 0){
			x += dx * (delta/1e9);
		}
		
		if (dy != 0){
			
			y += dy * (delta/1e9);
		}
	}

	public double ghorizontalspeed() {
		return dx;
	}
	
	public void shorizontalspeed(double dx) {
		this.dx = dx;
	}
	
	public double gvertikalspeed () {
		return dy;		
	}
	
	public void svertikalspeed(double dy) {
		this.dy = dy;
	}
	
	public int getObj(){
		
		return 0;
		
	}

	
}
