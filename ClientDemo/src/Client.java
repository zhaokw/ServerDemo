import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	public int serverPort;
	public String ipAdr;//IP address for the server
	public Socket soc = null;
	public BufferedReader br = null;
	public PrintStream ps = null;
	public Client(String ipAdr) {
		serverPort = 8802;
		this.ipAdr = ipAdr;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("please type in the server's IP:");//get server IP from user input
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		Client c = new Client(s.nextLine());
		c.run();
	}

	public BufferedReader getReader(Socket soc) throws Exception {
		InputStreamReader ipsr = new InputStreamReader(soc.getInputStream());
		BufferedReader br = new BufferedReader(ipsr);
		return br;
	}

	public PrintStream getWriter(Socket soc) throws Exception {
		PrintStream ps = new PrintStream(soc.getOutputStream());
		return ps;
	}

	public void run() throws Exception {
		//set up Socket connection and in/output stream
		soc = new Socket(ipAdr, serverPort);
		br = this.getReader(soc);
		ps = this.getWriter(soc);
		
		String input = "default";//input string from client, set to 'default' by default, naturally
		String message=null;//message received from server, set to null by default, naturally
		while (!(input.equals("bye"))) {
			// send user id to the server
			System.out.println("");
			System.out.println("If you want the server to return 1, type in 'a', if you want 2, type in 'b', press any other key to get a 3, and type 'bye' to exit");
			Scanner scn = new Scanner(System.in);
			input = scn.nextLine();
			ps.println(input);
			ps.flush();
			
			// Print server's message
			message = br.readLine();
			System.out.println("Message received from server:");
			System.out.println(message);
		}
		br.close();
		ps.close();
		soc.close();

	}
}
