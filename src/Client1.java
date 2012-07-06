import java.io.*;
import java.net.*;
import java.awt.*;
/**
 * 
 * die Clientklasse, betrachtet den Spieler, der in dem fall als Client FUnngiert(der andere Fungiert als Server, daneben wird bei ihm das SPiel als Server 
 * betrieben)
 *
 */
public class Client1 extends Thread{

	public static String host = "localhost";
	public static int port = 9999;
	static Socket socket;
	
	OutputStream output;
	InputStream input;
	
	int Player_x_input, Player_y_input;
	int inputt, outputt;
	
	
//	byte[] inputt = new byte[8];
//	byte[] outputt = new byte[8];
	int Player_x_teilbar, Player_y_teilbar;
	int i;
	
	/**
	 * der konstruktor, öffnet die verbindung
	 */
	public Client1() {

		try {
			socket = new Socket(host, port);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fahler");
			e.printStackTrace();
		}
		System.out.println("Client ist da");
		
		

	}
	/**
	 * Die run, sendet und empfängt
	 */

	
	public void run(){
		while(true){		
			
			sendToServer();
			getFromServer();

		}
	}
	/**
	 * diese Methode sendet die Dateien zum Server
	 */
	public void sendToServer(){
		
//		for(int i = 0; i < 4; ){
//			shift = i << 3;
//			outputt[3-i] = (byte)(((int) Gamepanel.Player[1].x & (0xff << shift)) >>>shift);
//		}
//		for(int i = 4; i < 8; ){
//			shift = i << 3;
//			outputt[8-i] = (byte)(((int) Gamepanel.Player[1].y & (0xff << shift)) >>>shift);
//		}
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			output = socket.getOutputStream();

			Player_x_teilbar = (int)(Gamepanel.Player[1].x / 254); //Weil OutputStrem nur Zahlen bis 255 überträgt
			output.write(((int)Gamepanel.Player[1].x) % 254);
			for(i = 0; i < Player_x_teilbar; i++){
				output.write(254);
			}
			for(i = Player_x_teilbar; i < 2; i++){
				output.write(0);
			}
			
			Player_y_teilbar = (int)(Gamepanel.Player[1].y / 254); //Weil OutputStrem nur Zahlen bis 255 überträgt
			output.write(((int)Gamepanel.Player[1].y) % 254);
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
	 * Die Gegenrichtung
	 */
	public void getFromServer(){
		try{
			input = socket.getInputStream();
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
			
			Gamepanel.Player[0].x = Player_x_input;
			Gamepanel.Player[0].y = Player_y_input;
			
			
//			Gamepanel.Player[1].x = input.read();
//			Gamepanel.Player[1].y = input.read();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
	}

}
