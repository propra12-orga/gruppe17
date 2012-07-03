import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
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
	public BufferedImage[] Schaf_run_re = new BufferedImage[4];
	public BufferedImage[] Schaf_run_li = new BufferedImage[4];
	public BufferedImage[] Schaf_run = new BufferedImage[4];
	public BufferedImage[] Schaf_ho_li = new BufferedImage[4];
	public BufferedImage[] Schaf_ho_re = new BufferedImage[4];
	public BufferedImage[] Schaf_ho = new BufferedImage[4];
	public BufferedImage[] Schaf_li = new BufferedImage[4];
	public BufferedImage[] Schaf_re = new BufferedImage[4];
	public BufferedImage[] Hirte_run = new BufferedImage[4];
	public BufferedImage[] Hirte_li = new BufferedImage[4];
	public BufferedImage[] Hirte_re = new BufferedImage[4];
	public BufferedImage[] Hirte_ho = new BufferedImage[4];
	public BufferedImage[] Hirte_run_re = new BufferedImage[4];
	public BufferedImage[] Hirte_run_li = new BufferedImage[4];
	public BufferedImage[] Hirte_ho_re = new BufferedImage[4];
	public BufferedImage[] Hirte_ho_li = new BufferedImage[4];
	public BufferedImage[] Items = new BufferedImage[6];
	public BufferedImage[] Menu = new BufferedImage[4];
	public BufferedImage[] Winner = new BufferedImage[4];
	int Schaf_Index; //Gibt an, welches Animations-Bild an der Reihe ist
	int Hirte_Index;
	
	
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
	
	public boolean Spieler1_wins;
	public boolean Spieler2_wins;
	public boolean Spieler1_stirbt;
	public boolean winner;

	long last, fps, delta = 0;
	BombenListe bomblist;
	BombenListe hilfslist;
	int Bomb_Index;
	ItemListe Itemlist;
	ItemListe hilfslist_item;
	FlugItemListe FlugItemList;
	FlugItemListe hilfslist_flugitem;

	
	
	
	public Grafik(){

		frame = new JFrame("Bomberman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024,768);
		frame.setPreferredSize(frame.getSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		
		this.setSize(1024,768);
		this.setPreferredSize(new Dimension(1024,768));
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
		repaint();
		
	}
	
	public void Init(){
		n = Gamepanel.Spielfeld.length;
		frame.setSize(FeldSize*(n), FeldSize*(n)+30);
		frame.setPreferredSize(frame.getSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		this.setSize(FeldSize* (n), FeldSize*(n));
		this.setPreferredSize(new Dimension(FeldSize*(n), FeldSize*(n)));
		this.setBackground(Color.WHITE);
		this.setOpaque(false);
		this.setOpaque(false);
		
		FeldPanel.setSize(new Dimension(frame.getSize()));
		FeldPanel.setPreferredSize(new Dimension(frame.getSize()));
		FeldPanel.setLayout(new BorderLayout());
		FeldPanel.setBackground(Color.WHITE);
		FeldPanel.add(this, BorderLayout.EAST);
		
		frame.getContentPane().add(FeldPanel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Unsere eigentliche Zeichenmethode, in der alles graphisch dargestellt wird
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
			/*
			 * Spielfeld-Darstellung
			 */
		
		system_millis = System.currentTimeMillis();


		if(!Spieler1_wins && !Spieler2_wins && !Spieler1_stirbt && !winner){
			if(Gamepanel.init){
	
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
				paintFlugItems(g);
				/*
				 * Detonations-Darstellung
				 */
				if(detonation>0){
					paintDetonation(g);
				}
				
				
			} else g.drawImage(Menu[Gamepanel.zaehler],0,0,this);	
		} else if(Spieler1_wins){
			g.drawImage(Winner[0],0,0,this);
		} else if(Spieler2_wins){
			g.drawImage(Winner[1],0,0,this);
		} else if(Spieler1_stirbt){
			g.drawImage(Winner[2],0,0,this);
		} else {
			g.drawImage(Winner[3],0,0,this);
		}
	}
	
	public void paintSpieler1(Graphics g){
		Spieler1_x = (int)Spieler1.getX();
		Spieler1_y = (int)Spieler1.getY();
		Schaf_Index = (int) ((system_millis - neueRichtung) / 200) % 4;
		
		if(TastenCheck.runter){
			if(TastenCheck.rechts){
				g.drawImage(Schaf_run_re[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else if(TastenCheck.links){
				g.drawImage(Schaf_run_li[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else {
				g.drawImage(Schaf_run[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			}
		} else if(TastenCheck.hoch){
			if(TastenCheck.rechts){
				g.drawImage(Schaf_ho_re[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else if(TastenCheck.links){
				g.drawImage(Schaf_ho_li[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else {
				g.drawImage(Schaf_ho[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			}
			} else if(TastenCheck.links){
				g.drawImage(Schaf_li[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else if(TastenCheck.rechts){		
				g.drawImage(Schaf_re[Schaf_Index], Spieler1_x, Spieler1_y, this);	
			} else g.drawImage(Schaf_run[0], Spieler1_x, Spieler1_y, this);	
	}
	
	public void paintSpieler2(Graphics g){
		Spieler2_x = (int)Spieler2.getX();
		Spieler2_y = (int)Spieler2.getY();
		Hirte_Index = (int) ((system_millis - neueRichtung2) / 200) % 4;
		
		if(TastenCheck.runter2){
			if(TastenCheck.links2) g.drawImage(Hirte_run_li[Hirte_Index], Spieler2_x, Spieler2_y, this);	
			else if(TastenCheck.rechts2) g.drawImage(Hirte_run_re[Hirte_Index], Spieler2_x, Spieler2_y, this);	
			else g.drawImage(Hirte_run[Hirte_Index], Spieler2_x, Spieler2_y, this);	
		} else if(TastenCheck.hoch2){
			if(TastenCheck.links2) g.drawImage(Hirte_ho_li[Hirte_Index], Spieler2_x, Spieler2_y, this);	
			else if(TastenCheck.rechts2) g.drawImage(Hirte_ho_re[Hirte_Index], Spieler2_x, Spieler2_y, this);	
			else g.drawImage(Hirte_ho[Hirte_Index], Spieler2_x, Spieler2_y, this);	
		} else if(TastenCheck.rechts2){
			 g.drawImage(Hirte_re[Hirte_Index], Spieler2_x, Spieler2_y, this);	
		} else if(TastenCheck.links2){
			 g.drawImage(Hirte_li[Hirte_Index], Spieler2_x, Spieler2_y, this);	
		} else g.drawImage(Hirte_run[0], Spieler2_x, Spieler2_y, this);
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
						g.drawImage(Weg, j*FeldSize, i*FeldSize, this);
						if(Gamepanel.Spielfeld[i][j].Ausgang) {
							g.drawImage(Ausgang, j*FeldSize, i*FeldSize, this);
							continue;
						}
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
	/**
	 * Zeichnet die Detonation
	 * @param g
	 */
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
			if (system_millis - det_time[i] > 500){
				detonation -=1;
			}
		}
	}
	
	public void paintItems(Graphics g){
		hilfslist_item = Gamepanel.ItemHead;

		while(hilfslist_item != null){
			
			g.drawImage(Items[hilfslist_item.getItem().getIndex()], hilfslist_item.getItem().getX() * FeldSize ,hilfslist_item.getItem().getY() * FeldSize , this);
			
			hilfslist_item = hilfslist_item.next;
		}
		
	}
	
	public void paintFlugItems(Graphics g){
		  
		  hilfslist_flugitem=Gamepanel.FlugItemHead;
		  
		  while(hilfslist_flugitem!=null){
		   
		   
		   g.drawImage(Bombe[0],(int) hilfslist_flugitem.getItem().getX(),(int)hilfslist_flugitem.getItem().getY(),this );
		   hilfslist_flugitem=hilfslist_flugitem.next;
		  }
		  
		 }
	
	public void Detonation(int a, int b, int c, int d, int e, int f){
		
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
	
	private void berechneFPS() {

		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1e9)/delta;
		
	}
	
	public void setFrame(){
		System.out.println("BLABLABLA");
		frame.setSize(1024,768);
		frame.setPreferredSize(frame.getSize());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		this.setSize(1024,768);
		this.setPreferredSize(new Dimension(1024,768));
		this.setBackground(Color.WHITE);
		this.setOpaque(false);
		this.setOpaque(false);

		FeldPanel.setSize(new Dimension(frame.getSize()));
		FeldPanel.setPreferredSize(new Dimension(frame.getSize()));
		FeldPanel.setLayout(new BorderLayout());
		FeldPanel.setBackground(Color.WHITE);
		FeldPanel.add(this, BorderLayout.EAST);

		frame.getContentPane().add(FeldPanel);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	
/**
 * HIER EINFACH ALLE BILDER EINLADEN, DIE GEBRAUCHT WERDEN.
 * 
 */
public void ladeBilder(){
	
	Menu[0] = ladeBild("Menu1.jpg");
	Menu[1] = ladeBild("Menu2.jpg");
	Menu[2] = ladeBild("Menu3.jpg");
	Menu[3] = ladeBild("Menu4.jpg");
	Weg = ladeBild("spielfeld.png");
	Stein = ladeBild("Brick2.png");
	uStein = ladeBild("Block.png");
//	Spieler = ladeBild("Schaf.png");
	Bombe[0] = ladeBild("bomb1.png");
	Bombe[1] = ladeBild("bomb2.png");
	Bombe[2] = ladeBild("bomb3.png");
	Bombe[3] = ladeBild("bomb4.png");
	Bombe[4] = ladeBild("bomb5.png");
	Ausgang = ladeBild("exit.png");
	FlammeCenter = ladeBild("center.png");
	Flamme[0] = ladeBild("fire-block-down.png");
	Flamme[1] = ladeBild("fire-block-left.png");
	Flamme[2] = ladeBild("fire-block-up.png");
	Flamme[3] = ladeBild("fire-block-right.png");
	FlammenSpitze[0] = ladeBild("spitze-down.png");
	FlammenSpitze[1] = ladeBild("spitze-left.png");
	FlammenSpitze[2] = ladeBild("spitze-up.png");
	FlammenSpitze[3] = ladeBild("spitze-right.png");
	Schaf_ho_li[0] = ladeBild("Schaf_ho_li1.png");
	Schaf_ho_li[2] = ladeBild("Schaf_ho_li1.png");
	Schaf_ho_li[1] = ladeBild("Schaf_ho_li2.png");
	Schaf_ho_li[3] = ladeBild("Schaf_ho_li2.png");
	Schaf_ho_re[0] = ladeBild("Schaf_ho_re1.png");
	Schaf_ho_re[2] = ladeBild("Schaf_ho_re1.png");
	Schaf_ho_re[1] = ladeBild("Schaf_ho_re2.png");
	Schaf_ho_re[3] = ladeBild("Schaf_ho_re2.png");
	Schaf_ho[0] = ladeBild("Schaf_ho1.png");
	Schaf_ho[2] = ladeBild("Schaf_ho1.png");
	Schaf_ho[1] = ladeBild("Schaf_ho2.png");
	Schaf_ho[3] = ladeBild("Schaf_ho2.png");
	Schaf_li[0] = ladeBild("Schaf_li1.png");
	Schaf_li[2] = ladeBild("Schaf_li1.png");
	Schaf_li[1] = ladeBild("Schaf_li2.png");
	Schaf_li[3] = ladeBild("Schaf_li2.png");
	Schaf_re[0] = ladeBild("Schaf_re1.png");
	Schaf_re[2] = ladeBild("Schaf_re1.png");
	Schaf_re[1] = ladeBild("Schaf_re2.png");
	Schaf_re[3] = ladeBild("Schaf_re2.png");
	Schaf_run_li[0] = ladeBild("Schaf_run1.png");
	Schaf_run_li[2] = ladeBild("Schaf_run1.png");
	Schaf_run_li[1] = ladeBild("Schaf_run1.png");
	Schaf_run_li[3] = ladeBild("Schaf_run1.png");
	Schaf_run_re[0] = ladeBild("Schaf_run1.png");
	Schaf_run_re[2] = ladeBild("Schaf_run1.png");
	Schaf_run_re[1] = ladeBild("Schaf_run1.png");
	Schaf_run_re[3] = ladeBild("Schaf_run1.png");
	Schaf_run[0] = ladeBild("Schaf_run1.png");
	Schaf_run[2] = ladeBild("Schaf_run1.png");
	Schaf_run[1] = ladeBild("Schaf_run2.png");
	Schaf_run[3] = ladeBild("Schaf_run2.png");
	Hirte_run[0] = ladeBild("Hirte_run1.png");
	Hirte_run[2] = ladeBild("Hirte_run2.png");
	Hirte_run[1] = Hirte_run[0];
	Hirte_run[3] = ladeBild("Hirte_run3.png");
	Hirte_ho[0] = ladeBild("Hirte_ho1.png");
	Hirte_ho[2] = ladeBild("Hirte_ho2.png");
	Hirte_ho[1] = Hirte_ho[0];
	Hirte_ho[3] = ladeBild("Hirte_ho3.png");
	Hirte_re[0] = ladeBild("Hirte_re1.png");
	Hirte_re[2] = ladeBild("Hirte_re1.png");
	Hirte_re[1] = ladeBild("Hirte_re2.png");
	Hirte_re[3] = ladeBild("Hirte_re2.png");
	Hirte_li[0] = ladeBild("Hirte_li1.png");
	Hirte_li[2] = ladeBild("Hirte_li1.png");
	Hirte_li[1] = ladeBild("Hirte_li2.png");
	Hirte_li[3] = ladeBild("Hirte_li2.png");
	Hirte_run_re[0] = ladeBild("Hirte_run_re.png");
	Hirte_run_re[2] = ladeBild("Hirte_run_re.png");
	Hirte_run_re[1] = ladeBild("Hirte_run_re2.png");
	Hirte_run_re[3] = ladeBild("Hirte_run_re2.png");
	Hirte_run_li[0] = ladeBild("Hirte_run_li.png");
	Hirte_run_li[2] = ladeBild("Hirte_run_li.png");
	Hirte_run_li[1] = ladeBild("Hirte_run_li2.png");
	Hirte_run_li[3] = ladeBild("Hirte_run_li2.png");
	Hirte_ho_re[0] = ladeBild("Hirte_ho_re.png");
	Hirte_ho_re[2] = ladeBild("Hirte_ho_re.png");
	Hirte_ho_re[1] = ladeBild("Hirte_ho_re2.png");
	Hirte_ho_re[3] = ladeBild("Hirte_ho_re2.png");
	Hirte_ho_li[0] = ladeBild("Hirte_ho_li.png");
	Hirte_ho_li[2] = ladeBild("Hirte_ho_li.png");
	Hirte_ho_li[1] = ladeBild("Hirte_ho_li2.png");
	Hirte_ho_li[3] = ladeBild("Hirte_ho_li2.png");
	Items[0] = ladeBild("feuer.png");
	Items[1] = ladeBild("handschuh.png");
	Items[2] = ladeBild("kicker.png");
	Items[3] = ladeBild("extraleben.png");
	Items[4] = ladeBild("extrabombe.png");
	Items[5] = ladeBild("rollschuh.png");
	Winner[0] = ladeBild("gewinnerbildschirm_schaf.jpg");
	Winner[1] = ladeBild("gewinnerbildschirm_hirte.jpg");
	Winner[2] = ladeBild("Looser1.jpg");
	Winner[3] = ladeBild("Schaf_wins.jpg");
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
