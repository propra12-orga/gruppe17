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

/**
 * in der Grafik-Klasse werden sämtliche Grafiken erstellt und auf das Gamepanel übertragen
 * 
 * 
 *
 */
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
	public BufferedImage[] Bombe = new BufferedImage[5];
	public BufferedImage Ausgang;
	public BufferedImage FlammeCenter;
	public BufferedImage[] Flamme = new BufferedImage[4];
	public BufferedImage[] FlammenSpitze = new BufferedImage[4];
	public BufferedImage[] Kenny_run_re = new BufferedImage[4];
	public BufferedImage[] Kenny_run_li = new BufferedImage[4];
	public BufferedImage[] Kenny_run = new BufferedImage[4];
	public BufferedImage[] Kenny_ho_li = new BufferedImage[4];
	public BufferedImage[] Kenny_ho_re = new BufferedImage[4];
	public BufferedImage[] Kenny_ho = new BufferedImage[4];
	public BufferedImage[] Kenny_li = new BufferedImage[4];
	public BufferedImage[] Kenny_re = new BufferedImage[4];
	public BufferedImage[] Cartman_run = new BufferedImage[4];
	public BufferedImage[] Cartman_li = new BufferedImage[4];
	public BufferedImage[] Cartman_re = new BufferedImage[4];
	public BufferedImage[] Cartman_ho = new BufferedImage[4];
	public BufferedImage[] Items = new BufferedImage[6];
	int Kenny_Index; //Gibt an, welches Animations-Bild an der Reihe ist
	int Cartman_Index;
	
	
	Bomberman Spieler1;
	Bomberman Spieler2;
	int detonation = 0;
	int[] det_oben = new int[64];
	int[] det_unten = new int[64]; 
	int[] det_rechts = new int[64];
	int[] det_links = new int[64];
	int[] det_x = new int[64];
	int[] det_y = new int[64];
	long[] det_time = new long[64];
	int Spieler1_x, Spieler1_y, Spieler2_x, Spieler2_y;
	long system_millis;
	long bomb_time;
	long neueRichtung, neueRichtung2;

	long last, fps, delta;
	BombenListe bomblist;
	BombenListe hilfslist;
	int Bomb_Index;
	ItemListe Itemlist;
	ItemListe hilfslist_item;

	
	
	
	public Grafik(){
//		Spieler1 = Gamepanel.Spieler;
//		if(Gamepanel.Ende){
//			Spieler2 = Gamepanel.Spieler2;
//		}
		n = Gamepanel.Spielfeld.length;
		frame = new JFrame("Test");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.setSize(1024,768);
		frame.setSize(FeldSize*(n), FeldSize*(n)+30);
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
	

	/**
	 * Unsere eigentliche Zeichenmethode, in der alles graphisch dargestellt wird
	 * 
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
			/*
			 * Spielfeld-Darstellung
			 */
		
		system_millis = System.currentTimeMillis();
		
		if(!Gamepanel.Ende){
				paintSpielfeld(g);
			
			/*
			 * Bomben-Darstellung
			 */
			if(Gamepanel.BombHead != null){
				paintBomben(g);
			}
			/*
			 * Spieler-Darstellung
			 */
			if(Spieler1 != null && !Spieler1.tot){
				paintSpieler1(g);
			}
			if(Spieler2 != null && !Spieler2.tot){
				paintSpieler2(g);	
			}
			paintItems(g);
			/*
			 * Detonations-Darstellung
			 */
			if(detonation>0){
				paintDetonation(g);
			}
			
		}	
	}
	
	public void paintSpieler1(Graphics g){
		Spieler1_x = (int)Spieler1.getX();
		Spieler1_y = (int)Spieler1.getY();
		Kenny_Index = (int) ((system_millis - neueRichtung) / 200) % 4;
		
		if(Gamepanel.runter){
			if(Gamepanel.rechts){
				g.drawImage(Kenny_run_re[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else if(Gamepanel.links){
				g.drawImage(Kenny_run_li[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else {
				g.drawImage(Kenny_run[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			}
		} else if(Gamepanel.hoch){
			if(Gamepanel.rechts){
				g.drawImage(Kenny_ho_re[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else if(Gamepanel.links){
				g.drawImage(Kenny_ho_li[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else {
				g.drawImage(Kenny_ho[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			}
			} else if(Gamepanel.links){
				g.drawImage(Kenny_li[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else if(Gamepanel.rechts){		
				g.drawImage(Kenny_re[Kenny_Index], Spieler1_x, Spieler1_y, this);	
			} else g.drawImage(Kenny_run[0], Spieler1_x, Spieler1_y, this);	
	}
	
	public void paintSpieler2(Graphics g){
		Spieler2_x = (int)Spieler2.getX();
		Spieler2_y = (int)Spieler2.getY();
		Cartman_Index = (int) ((system_millis - neueRichtung2) / 200) % 4;
		
		if(Gamepanel.runter2){
			g.drawImage(Cartman_run[Cartman_Index], Spieler2_x, Spieler2_y, this);	
		} else if(Gamepanel.hoch2){
			g.drawImage(Cartman_ho[Cartman_Index], Spieler2_x, Spieler2_y, this);	
		} else if(Gamepanel.rechts2){
			 g.drawImage(Cartman_re[0], Spieler2_x, Spieler2_y, this);	
		} else if(Gamepanel.links2){
			 g.drawImage(Cartman_li[0], Spieler2_x, Spieler2_y, this);	
		} else g.drawImage(Cartman_run[0], Spieler2_x, Spieler2_y, this);
	}
	
	public void paintBomben(Graphics g){
		hilfslist = Gamepanel.BombHead;

		while(hilfslist != null){
			Bomb_Index = (int) ((system_millis - hilfslist.getBombe().getZeit()) / 500) % 5;
			if(Bomb_Index >=0){ //Bomb_Index = -1 möglich, wenn der entsprechende Thread die Bombe zu spät löscht!
				g.drawImage(Bombe[Bomb_Index], hilfslist.getBombe().x * FeldSize ,hilfslist.getBombe().y * FeldSize , this);
			}
			hilfslist = hilfslist.next;
		}
	}
	
	public void paintSpielfeld(Graphics g){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
					if(Gamepanel.Spielfeld[i][j].begehbar){
						if(Gamepanel.Spielfeld[i][j].Ausgang) {
							g.drawImage(Ausgang, j*FeldSize, i*FeldSize, this);
							continue;
						}
						g.drawImage(Weg, j*FeldSize, i*FeldSize, this);
						continue;
					}
					if(Gamepanel.Spielfeld[i][j].unzerstoerbar){
						g.drawImage(uStein, j*FeldSize, i*FeldSize, this);
						continue;
					}
					if(Gamepanel.Spielfeld[i][j].zerstoerbar){
						g.drawImage(Stein, j*FeldSize,i*FeldSize, this);
						continue;
					}
			}
		}
	}
	
	public void paintDetonation(Graphics g){
		for(int i = 0; i < detonation; i++){
			g.drawImage(FlammeCenter, det_x[i] * FeldSize, det_y[i] * FeldSize, this);
			for(int j = 1; j < det_unten[i]; j++){
				g.drawImage(Flamme[2], det_x[i] * FeldSize, (det_y[i] - j) * FeldSize, this);
			}
			if (det_unten[i] != 0) {
				g.drawImage(FlammenSpitze[2], det_x[i] * FeldSize, (det_y[i] - det_unten[i]) * FeldSize, this);
			}
			for(int j = 1; j < det_links[i]; j++){
				g.drawImage(Flamme[1], (det_x[i] - 1) * FeldSize, det_y[i] * FeldSize, this);
			}
			if (det_links[i] != 0) {
				g.drawImage(FlammenSpitze[1], (det_x[i] - det_links[i]) * FeldSize, det_y[i] * FeldSize, this);
			}
			for(int j = 1; j < det_oben[i]; j++){
				g.drawImage(Flamme[0], det_x[i] * FeldSize, (det_y[i] + j) * FeldSize, this);
			}
			if (det_oben[i] != 0) {
				g.drawImage(FlammenSpitze[0], det_x[i] * FeldSize, (det_y[i] + det_oben[i]) * FeldSize, this);
			}
			for(int j = 1; j < det_rechts[i]; j++){
				g.drawImage(Flamme[3], (det_x[i] + 1) * FeldSize, det_y[i] * FeldSize, this);
			}
			if (det_rechts[i] != 0) {
				g.drawImage(FlammenSpitze[3], (det_x[i] + det_rechts[i]) * FeldSize, det_y[i] * FeldSize, this);
			}
			if (system_millis - det_time[i] > 1000){
				detonation -=1;
//				System.out.println("Detonation: "+ detonation);
			}
		}
	}
	
	public void paintItems(Graphics g){
		hilfslist_item = Gamepanel.ItemHead;

		while(hilfslist_item != null){
			
			g.drawImage(Items[hilfslist_item.getItem().getIndex()], hilfslist_item.getItem().x * FeldSize ,hilfslist_item.getItem().y * FeldSize , this);
			
			hilfslist_item = hilfslist_item.next;
		}
		
	}
	
	public void Detonation(int a, int b, int c, int d, int e, int f){
		System.out.println("GrafikMethode Detonation, oben: " + a + " links: " + b + " unten: " + c + " rechts: " + d);
		
		det_time[detonation] = System.currentTimeMillis();
		det_unten[detonation] = a;
		det_links[detonation] = b;
		det_oben[detonation] = c;
		det_rechts[detonation] = d;
		det_x[detonation] = e;
		det_y[detonation] = f;
		

		detonation += 1;
//		System.out.println("Detonation: " + detonation);
		repaint();
	}
	
	public void keineDetonation(int n){
		
//		System.out.println("Detonation: " + detonation);
		repaint();
	}
	
	public void setNeueRichtung(){
		neueRichtung = System.currentTimeMillis();
	}
	
	public void setNeueRichtung2(){
		neueRichtung2 = System.currentTimeMillis();
	}
	
/**
 * HIER EINFACH ALLE BILDER EINLADEN, DIE GEBRAUCHT WERDEN.
 * 
 */
public void ladeBilder(){
	
	Weg = ladeBild("spielfeld.png");
	Stein = ladeBild("Brick2.png");
	uStein = ladeBild("Block.png");
	Spieler = ladeBild("kenny.png");
	Bombe[0] = ladeBild("bomb1.png");
	Bombe[1] = ladeBild("bomb2.png");
	Bombe[2] = ladeBild("bomb3.png");
	Bombe[3] = ladeBild("bomb4.png");
	Bombe[4] = ladeBild("bomb5.png");
	Ausgang = ladeBild("Ausgang.png");
	FlammeCenter = ladeBild("center.png");
	Flamme[0] = ladeBild("fire-block-down.png");
	Flamme[1] = ladeBild("fire-block-left.png");
	Flamme[2] = ladeBild("fire-block-up.png");
	Flamme[3] = ladeBild("fire-block-right.png");
	FlammenSpitze[0] = ladeBild("spitze-down.png");
	FlammenSpitze[1] = ladeBild("spitze-left.png");
	FlammenSpitze[2] = ladeBild("spitze-up.png");
	FlammenSpitze[3] = ladeBild("spitze-right.png");
	Kenny_ho_li[0] = ladeBild("Kenny_ho_li1.png");
	Kenny_ho_li[1] = ladeBild("Kenny_ho_li2.png");
	Kenny_ho_li[2] = Kenny_ho_li[0];
	Kenny_ho_li[3] = ladeBild("Kenny_ho_li3.png");
	Kenny_ho_re[0] = ladeBild("Kenny_ho_re1.png");
	Kenny_ho_re[1] = ladeBild("Kenny_ho_re2.png");
	Kenny_ho_re[2] = Kenny_ho_re[0];
	Kenny_ho_re[3] = ladeBild("Kenny_ho_re3.png");
	Kenny_ho[0] = ladeBild("Kenny_ho1.png");
	Kenny_ho[1] = ladeBild("Kenny_ho2.png");
	Kenny_ho[2] = Kenny_ho[0];
	Kenny_ho[3] = ladeBild("Kenny_ho3.png");
	Kenny_li[0] = ladeBild("Kenny_li1.png");
	Kenny_li[1] = ladeBild("Kenny_li2.png");
	Kenny_li[2] = ladeBild("Kenny_li1.png");
	Kenny_li[3] = ladeBild("Kenny_li2.png");
	Kenny_re[0] = ladeBild("Kenny_re1.png");
	Kenny_re[1] = ladeBild("Kenny_re2.png");
	Kenny_re[2] = ladeBild("Kenny_re1.png");
	Kenny_re[3] = ladeBild("Kenny_re2.png");
	Kenny_run_li[0] = ladeBild("Kenny_ru_li1.png");
	Kenny_run_li[1] = ladeBild("Kenny_ru_li2.png");
	Kenny_run_li[2] = Kenny_run_li[0];
	Kenny_run_li[3] = ladeBild("Kenny_ru_li3.png");
	Kenny_run_re[0] = ladeBild("Kenny_ru_re1.png");
	Kenny_run_re[1] = ladeBild("Kenny_ru_re2.png");
	Kenny_run_re[2] = Kenny_run_re[0];
	Kenny_run_re[3] = ladeBild("Kenny_ru_re3.png");
	Kenny_run[0] = ladeBild("Kenny_run1.png");
	Kenny_run[1] = ladeBild("Kenny_run2.png");
	Kenny_run[2] = Kenny_run[0];
	Kenny_run[3] = ladeBild("Kenny_run3.png");
	Cartman_run[0] = ladeBild("Cartman_run1.png");
	Cartman_run[1] = ladeBild("Cartman_run2.png");
	Cartman_run[2] = Cartman_run[0];
	Cartman_run[3] = ladeBild("Cartman_run3.png");
	Cartman_ho[0] = ladeBild("Cartman_ho1.png");
	Cartman_ho[1] = ladeBild("Cartman_ho2.png");
	Cartman_ho[2] = Cartman_ho[0];
	Cartman_ho[3] = ladeBild("Cartman_ho3.png");
	Cartman_re[0] = ladeBild("Cartman_re1.png");
	Cartman_li[0] = ladeBild("Cartman_li1.png");
	Items[0] = ladeBild("handschuh.png");
}
	
/**
 *  LÄDT BILD AUS DEM BOMBERMAN-ORDNER, STRING S = NAME, ANIMATIONEN WERDEN ALS EINZELBILDER EINGELESEN!!!
 *  
 * @param s Name des Bildes welches geladen werden soll
 * @return Bild wird erstellt
 */
public BufferedImage ladeBild(String s){ 

		BufferedImage Bild = null;
		
		java.net.URL pic_url = getClass().getClassLoader().getResource(s);
		
		try {
			Bild = ImageIO.read(pic_url);
		} catch (IOException e) {}
		return Bild;
	}
}
