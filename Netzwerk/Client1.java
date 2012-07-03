import java.io.*;
import java.net.*;
import java.awt.*;

public class Client1{

	public static String host = "localhost";
	public static int port = 9999;
	ObjectOutputStream out;


	public void run() throws Exception {

		Socket socket = new Socket(host, port);
		
		PrintStream ps = new PrintStream(socket.getOutputStream());
		ps.println("Ich bin da!(client)");

		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String s = br.readLine();
		System.out.println(s);
	}
	
	public static void main (String args[]){
		
		Client1 client = new Client1();
		try {
			client.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
