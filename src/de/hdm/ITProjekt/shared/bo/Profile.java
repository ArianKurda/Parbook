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
	
	private boolean smoker;
	
	private boolean nonsmoker;
	
	private String religion = "";
	
	private boolean male;
	
	private boolean female;
	


	
	
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
	
	public boolean isSmoker() {
		return smoker;
	}
	
	public void setSmoker(boolean raucher){
		this.smoker = raucher;
	}
	
	public boolean isNonsmoker() {
		return nonsmoker;
	}
	
	public void setNonSmoker (boolean nichtraucher){
		this.nonsmoker = nichtraucher;
	}
	
	/* ich wei� nicht ob wir noch ne setmethode brauchen f�r die boolean Sachen*/
	
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
	

	public boolean isMale(){
		return male;	
	}
	
	public void setMale (boolean maennlich){
		this.male = maennlich;
	}
	
	public boolean isFemale(boolean weiblich) {
		return female;
	}
	
	public void setFemale (boolean weiblich){
		this.female = weiblich;
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
