package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;
import java.sql.Timestamp;

public class BusinessObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Brauchen wir diese ID hier?
	private int id;

	protected Timestamp timestamp = new Timestamp(System.currentTimeMillis());



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

}
