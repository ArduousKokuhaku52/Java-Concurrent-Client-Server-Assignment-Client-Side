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

public class Message implements Serializable {

	private static final long serialVersionUID = -6291949081120852357L;
	protected int TO;
	protected int FROM;
	protected String BODY;

	/********************************************************
	 * Description: Constructor method that assigns all the member
	 * 	variables.
	 * Precondition: Requires a TO address as an int, a FROM address
	 * 	as an int, and a BODY message as a String
	 ********************************************************/
	public Message(int TO, int FROM, String BODY) {
		this.TO = TO;
		this.FROM = FROM;
		this.BODY = BODY;
	}

	/********************************************************
	 * Description: Overriden toString method
	 * 	Returns a String formatted as:
	 * 	"TO:*address* FROM:*address* BODY:*message*"
	 ********************************************************/
	@Override
	public String toString() {
		return String.format("TO:%d FROM:%d BODY:%s", TO, FROM, BODY);
	}

	/********************************************************
	 * Description: Overriden equals method
	 * Precondition: Requires an Object to be compared
	 * Postcondition: If instance of message, compares the TO
	 * 	fields and either returns true or false.
	 ********************************************************/
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message o) {
			return (this.TO == o.TO);
		}
		else {
			return false;
		}
	}
}
