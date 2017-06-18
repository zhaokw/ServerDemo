import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	
	public static ArrayList<Socket> socL = new ArrayList<Socket>();// list for Socket objects
	public int port;// port number
	public Server() {
		//set port num to 8802
		port = 8802;
	}

	public static void main(String[] args) throws Exception {// main method for
																// the server
		Server srv = new Server();
		srv.run();

	}

	public BufferedReader getReader(Socket soc) throws Exception {// Read from
																	// client's
																	// output
		InputStreamReader ipsr = new InputStreamReader(soc.getInputStream());
		BufferedReader br = new BufferedReader(ipsr);
		return br;
	}

	public PrintStream getWriter(Socket soc) throws Exception {// get output
																// stream
		PrintStream ps = new PrintStream(soc.getOutputStream());
		return ps;
	}

	public void run() throws Exception {
		//set up Socket connection and in/output stream
		Scanner s = new Scanner(System.in);
		ServerSocket srvsoc = new ServerSocket(port);
		System.out.println("server is running......");
		Socket soc = srvsoc.accept();
		BufferedReader br = this.getReader(soc);
		PrintStream ps = this.getWriter(soc);
		
		String message = "default";//message string from client, set to 'default' by default, naturally
		
		while (message != null) {//while loop ensures that the service can respond to multiple requests in a session
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

			// analyses client's input 
			String out="";
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
