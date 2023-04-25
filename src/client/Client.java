/***********************************************************
 * Name: Damien Gill
 * Assignment: Assignment 9
 * Description: This assignment simulates multiple clients
 * 	connecting to multiple servers concurrently and
 * 	accessing/manipulating a shared resource with no loss
 * 	of data.
 ***********************************************************/
package client;

public class Client {

	/********************************************************
	 * Description: Main method that instantiates all clients
	 * 	then starts a thread to run them.
	 ********************************************************/
	public static void main(String []args) {
		
		ClientSimulator []clients = new ClientSimulator[10];
		
		for(int i = 0; i < clients.length; i++) {
			String filename = "files/messages" + (i+1) + ".txt";
			clients[i] = new ClientSimulator(filename);
			clients[i].start();
		}

	}

}
