package de.hdm.ITProjekt.shared.bo;

import java.sql.Date;

public class Profile extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	

	private String firstName = "";
	
	private String lastName = "";
	
	private String email = "";
	
	private String password = "";
	
	private int googleID;
	
	private Date dob; /** müssen nachschauen wie wir das mit dem Geburtsdatum machen*/
	
	private String hairColor = "";
	
	private double bodyHeight;
	
	private boolean smoker = true; /* true = raucher , false = nichtraucher*/
	
	private String religion = "";
	
	private boolean gender = true;  /* true = maennlich , false = weiblich*/
	

	public static final boolean raucher = true;
	public static final boolean nichtraucher = false;
	public static final boolean maennlich = true;
	public static final boolean weiblich = false;

	
	
	public String getFirstName() {
		return this.firstName;	
	}
	
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName (String name) {
		this.lastName = name;
	}
	
	/* geburtsdatum*/
	
	public String getHairColor() {
		return this.hairColor;
	}
	
	public void setHairColor (String haarfarbe) {
		this.hairColor = haarfarbe;
	}
	
	public double getBodyHeight() {
		return this.bodyHeight;
	}
	
	public void setBodyHeight (double bodyheight ){
		this.bodyHeight = bodyheight ;
	}
	
	public boolean Smoker() {
		return this.smoker;
	}
	
	public void setSmoker(boolean smoker){
		this.smoker = raucher;
	}

	
	public String getReligion() { 
		return this.religion;
	}
	
	public void setReligion (String religion) {
		this.religion = religion;
	}
	
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword (String password) {
		this.password = password;
	}
	
	public int getGoogleID() {
		return googleID;
	}
	
	public void setGoogleID(int googleID) {
		this.googleID = googleID;
	}
	

	public boolean gender(){
		return this.gender;	
	}
	
	public void setGender (boolean maennlich){
		this.gender = maennlich;
	}
	
	
	public void setDateOfBirth(Date newDoB){
		dob = newDoB;
	}

	public void setOwnerID(int int1) {
		// TODO Auto-generated method stub
		
	}

	public String getOwnerID() {
		// TODO Auto-generated method stub
		return null;
	}
		
}
