package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int id = 0;
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}

}
