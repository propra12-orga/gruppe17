import java.io.*;
import java.net.*;

public class Server2 {

	public void run() throws Exception{
		
		ServerSocket serversocket = new ServerSocket(9999);
		Socket ssocket = serversocket.accept();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(ssocket.getInputStream()));
		String s = br.readLine();
		System.out.println(s);
		
		if (s != null){
		PrintStream ps = new PrintStream(ssocket.getOutputStream());
		ps.println("Message received");
		}
	}

	public static void main(String[] args) throws Exception {

		Server2 server = new Server2();
		server.run();
	}

}
