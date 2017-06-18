import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	
	public int port;//port number
	public Server() {
		port = 8802;//set the port num to 8802
	}

	public static void main(String[] args) throws Exception {//main method for the server, must be started before you start that of the clients
		Server srv = new Server();
		srv.run();

	}

	public BufferedReader getReader(Socket soc) throws Exception {//get reader object from client input
		InputStreamReader ipsr = new InputStreamReader(soc.getInputStream());
		BufferedReader br = new BufferedReader(ipsr);
		return br;
	}

	public PrintStream getWriter(Socket soc) throws Exception {//get printer stream to send out to the client
		PrintStream ps = new PrintStream(soc.getOutputStream());
		return ps;
	}

	public void run() throws Exception {
		// set up socket connection and input stream & outputStream
		ServerSocket srvsoc = new ServerSocket(port);
		System.out.println("server is running......");
		Socket soc = srvsoc.accept();
		BufferedReader br = this.getReader(soc);
		PrintStream ps = this.getWriter(soc);
		
		String message = "default";//message received from the client, set to 'default' by default naturally
		while (message != null) {
			message = br.readLine();
			if (message.equals("bye")) {//when the client types 'bye', service terminates
				ps.println("Server closed");
				br.close();
				soc.close();
				System.out.println("Server closed");
				break;
			}
			System.out.println("Server is connected to " + soc.getLocalPort());
			System.out.println("");

			// Analyse input
			String out="";//output stream
			if(message.equals("a"))
				out+="1";
			else if(message.equals("b"))
				out+="2";
			else
				out+="3";
			ps.println(out);
		}
	}

}
