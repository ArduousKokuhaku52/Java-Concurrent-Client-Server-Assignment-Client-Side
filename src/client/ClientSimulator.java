/***********************************************************
 * Name: Damien Gill
 * Assignment: Assignment 9
 * Description: This assignment simulates multiple clients
 * 	connecting to multiple servers concurrently and
 * 	accessing/manipulating a shared resource with no loss
 * 	of data.
 ***********************************************************/
package client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

import messages.Message;
import messages.Searcher;

public class ClientSimulator extends Thread{

	private Socket client;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private String filename;
	private final String IP;
	private final int RECEIVER_PORT;
	private final int SENDER_PORT;

	/********************************************************
	 * Description: Constructor method that sets the filename,
	 * 	IP, RECEIVER_PORT, and SENDER_PORT member variables.
	 * Precondition: Requires a filename as a String
	 ********************************************************/
	public ClientSimulator(String filename) {
		this.filename = filename;
		this.IP = "localhost";
		this.RECEIVER_PORT = 4242;
		this.SENDER_PORT = 4343;
	}

	/********************************************************
	 * Description: Overriden run method from the Thread class.
	 *  This method first reads in the file from
	 * 	the passed in filename through the constructor. Then
	 * 	based on the file either starts a connection with the
	 * 	receiver server or the sender server. If it connects
	 * 	with the receiver server, it sends the server a message
	 * 	to be stored. Then receives a message back to print to
	 * 	the console. If it connects to the sender server, it
	 * 	sends a searcher object to the server to check for a
	 * 	message stored on the server. Then waits for a response
	 * 	from the server and outputs the response to the console.
	 * 	Repeats until end of file.
	 ********************************************************/
	@Override
	public void run() {
		try {
			Scanner scnr = new Scanner(new File(filename));
			while(scnr.hasNext()) {		//runs until the end of the file
				StringTokenizer st = new StringTokenizer(scnr.nextLine(), ",");
				if(st.nextToken().equals("s")) {	//"s" indicates sending a message to input to the server
					Message message = new Message(Integer.parseInt(st.nextToken()),
											Integer.parseInt(st.nextToken()),
											st.nextToken());

					//Connecting to the server
					this.client = new Socket(IP, RECEIVER_PORT);
					this.oos = new ObjectOutputStream(client.getOutputStream());
					this.ois = new ObjectInputStream(client.getInputStream());

					//Writes message to server then waits for server response; outputs at end
					this.oos.writeObject(message);
					String response = (String) this.ois.readObject();

					System.out.println(response);
				} else {
					Searcher searcher = new Searcher(Integer.parseInt(st.nextToken()));

					//Connecting to the server
					this.client = new Socket(IP, SENDER_PORT);
					this.oos = new ObjectOutputStream(client.getOutputStream());
					this.ois = new ObjectInputStream(client.getInputStream());

					//Sends a searcher object to find a message from the server prints response from server
					this.oos.writeObject(searcher);
					Object response = this.ois.readObject();

					if (response instanceof String s) {
						System.out.println(s);
					}
					else if (response instanceof Message m) {
						System.out.println(m);
					}
				}
				//Closes connection when finished
				this.client.close();
				sleep(0, 1);
			}
			scnr.close();  

		} catch(IOException | ClassNotFoundException |InterruptedException e) {
			System.out.println(e);
		}
	}
}
