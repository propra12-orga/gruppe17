import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Grafik extends JPanel {

	private static final long serialVersionUID = 1L;
	JFrame frame;
	JPanel FeldPanel;
	final public int FeldSize = 40;
	BufferedImage Test;
	File file;
	int hoehe, breite;
	int n; //Groesse des FeldPanels
	public BufferedImage Weg;
	public BufferedImage Stein;
	public BufferedImage uStein;
	public BufferedImage Spieler;
	Bomberman Spieler1;
	
	public Grafik(Bomberman B){
		Spieler1 = B;
		n = Gamepanel.Spielfeld.length;
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setSize(1024,768);
		frame.setSize(FeldSize*(n), FeldSize*(n));
		frame.setPreferredSize(frame.getSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		this.setSize(FeldSize* (n), FeldSize*(n));
		this.setPreferredSize(new Dimension(FeldSize*(n), FeldSize*(n)));
		this.setBackground(Color.WHITE);
		this.setOpaque(false);
		this.setOpaque(false);
		
		FeldPanel = new JPanel();
		FeldPanel.setSize(new Dimension(frame.getSize()));
		FeldPanel.setPreferredSize(new Dimension(frame.getSize()));
		FeldPanel.setLayout(new BorderLayout());
		FeldPanel.setBackground(Color.WHITE);
		FeldPanel.add(this, BorderLayout.EAST);
		
		frame.getContentPane().add(FeldPanel);
		frame.pack();
		frame.setVisible(true);
		
		ladeBilder();
//		repaint();

	}
	
	protected void paintComponent(Graphics g){
		super.paintComponent(g);

			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
						if(Gamepanel.Spielfeld[i][j].begehbar){
							g.drawImage(Weg, j*FeldSize, i*FeldSize, this);
						}
						if(Gamepanel.Spielfeld[i][j].unzerstoerbar){
							g.drawImage(uStein, j*FeldSize, i*FeldSize, this);
						}
						if(Gamepanel.Spielfeld[i][j].zerstoerbar){
							g.drawImage(Stein, j*FeldSize,i*FeldSize, this);
						}
				}
			}
			
			g.drawImage(Spieler,(int)Spieler1.getX(),(int)Spieler1.getY(), this);

	}
	
	
	


public void ladeBilder(){
	//HIER EINFACH ALLE BILDER EINLADEN, DIE GEBRAUCHT WERDEN.
	Weg = ladeBild("end field.png");
	Stein = ladeBild("breakable.png");
	uStein = ladeBild("unbreakable.png");
	Spieler = ladeBild("bomber.gif");
}
	
	
	
	
// LÄDT BILD AUS DEM SOURCE-ORDNER, STRING S = NAME, ANIMATIONEN WERDEN ALS EINZELBILDER EINGELESEN!!!
public BufferedImage ladeBild(String s){ 

		BufferedImage Bild = null;
		
		java.net.URL pic_url = getClass().getClassLoader().getResource(s); 
		
		try {
			Bild = ImageIO.read(pic_url);
		} catch (IOException e) {}
		
		return Bild;
		
	}

}
