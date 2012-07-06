import java.io.*;
import java.net.*;
/**
 * Der Aufgebaute Server, kommuniziert mit dem Client
 * 
 *
 */
public class Server2 extends Thread{
	
	InputStream input;
	OutputStream output;
	Socket ssocket;
	
	int Player_x_input, Player_y_input;
	int Player_x_teilbar, Player_y_teilbar;
	int inputt, outputt;
	int i;
//	int[] inputt = new int[8];
/**
 * Arbeitskonstruktor
 * @throws Exception fehler, wenn keine verbindung Möglich ist
 */
	public Server2() throws Exception{
		
		ServerSocket serversocket = new ServerSocket(9999);
		System.out.println("server is waiting");
		ssocket = serversocket.accept();
		System.out.println("found client");
		

	}
	/**
	 * die Run, hier wird geschickt und empfangen
	 */
	public void run() {
		
		while(true){
			
			sendToClient();
			getFromClient();
		}

	}
	/**
	 * schicke an client
	 */
	public void sendToClient(){
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			output = ssocket.getOutputStream();

			Player_x_teilbar = (int)(Gamepanel.Player[0].x / 254); //Weil OutputStrem nur Zahlen bis 255 überträgt
			output.write(((int)Gamepanel.Player[0].x) % 254);
			for(i = 0; i < Player_x_teilbar; i++){
				output.write(254);
			}
			for(i = Player_x_teilbar; i < 2; i++){
				output.write(0);
			}
			
			Player_y_teilbar = (int)(Gamepanel.Player[0].y / 254); //Weil OutputStrem nur Zahlen bis 255 überträgt
			output.write(((int)Gamepanel.Player[0].y) % 254);
			for(i = 0; i < Player_y_teilbar; i++){
				output.write(254);
			}
			for(i = Player_y_teilbar; i < 2; i++){
				output.write(0);
			}

			
			
//			output.write((int)Gamepanel.Player[1].x);
//			output.write((int)Gamepanel.Player[1].y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}

	}
	/**
	 * empfange vom client
	 */
	public void getFromClient(){
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		try{
			input = ssocket.getInputStream();
			Player_x_input = 0;
			Player_y_input = 0;
			
			for(i = 0; i < 3; i++){
				inputt = input.read();
				if(inputt == 255){
					System.out.println("BOMBEbekommen");
					Gamepanel.bombelegen2 = true;
					inputt = input.read();
				}
				
				Player_x_input+= inputt;
			}
			for(i = 0; i < 3; i++){
				inputt = input.read();
				if(inputt == 255){
					Gamepanel.bombelegen2 = true;
					inputt = input.read();
				}
				Player_y_input+= inputt;
			}
			
			Gamepanel.Player[1].x = Player_x_input;
			Gamepanel.Player[1].y = Player_y_input;
			
			
//			Gamepanel.Player[1].x = input.read();
//			Gamepanel.Player[1].y = input.read();

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

	


}
