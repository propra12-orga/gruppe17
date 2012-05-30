
public  class Item {
	boolean Feuer=false;
	boolean Handschuh=false;
	boolean Kicker=false;
	boolean ExtraLeben=false;
	boolean BombenItem=false;
	boolean Rollschuh=false;
	int x;
	int y;
	
	public Item(String args, int x, int y){
		if(args=="Feuer") Feuer=true;
		if(args=="Handschuh") Handschuh=true;
		if(args=="Kicker") Kicker=true;
		if(args=="ExtraLeben") ExtraLeben=true;
		if(args=="BombenItem") BombenItem=true;
		if(args=="Rollschuh") Rollschuh=true;		
		this.x=x;
		this.y=y;
	}
}
