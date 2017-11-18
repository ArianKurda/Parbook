package de.hdm.ITProjekt.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private static final boolean BusinessObject = false;
	
	
	private int id = 0;
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
public boolean equals(Object o) {
	
	if (o != null && o instanceof BusinessObject) {
		BusinessObject bo = (BusinessObject) o;
		try {
			if(bo.getID() == this.id)
				return true;
		}
		catch (IllegalArgumentException e) {
			
			return false;
		}
	}
	return false;
}

public int hashCode(){
	return this.id;
	
}
	}

