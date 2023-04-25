/***********************************************************
 * Name: Damien Gill
 * Assignment: Assignment 9
 * Description: This assignment simulates multiple clients
 * 	connecting to multiple servers concurrently and
 * 	accessing/manipulating a shared resource with no loss
 * 	of data.
 ***********************************************************/
package messages;

import java.io.Serializable;

public class Searcher implements Serializable {

	private static final long serialVersionUID = -5963532776501565164L;
	protected int TO;

	/********************************************************
	 * Description: Constructor method that sets the TO member
	 * 	variable
	 * Precondition: Requires a TO address as an int
	 ********************************************************/
	public Searcher(int TO) {
		this.TO = TO;
	}

	/********************************************************
	 * Description: Overriden equals method
	 * Precondition: Requires an Object to be compared
	 * Postcondition: If object is instance of Searcher or Message
	 * 	compares the TO address and returns true or false. Else,
	 * 	returns false
	 ********************************************************/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Searcher s) {
			return (this.TO == s.TO);
		}
		else if(obj instanceof Message m) {
			return (this.TO == m.TO);
		}
		else {
			return false;
		}
	}

}
