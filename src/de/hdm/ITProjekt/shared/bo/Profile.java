package de.hdm.ITProjekt.shared.bo;

public class Profile extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	

	private String firstName = "";
	
	private String lastName = "";
	
	private int birthDay = 0; /** müssen nachschauen wie wir das mit dem Geburtsdatum machen*/
	
	private String hairColor = "";
	
	private int bodyHeight = 0;
	
	/** private boolean smoker */
	
	private String religiousDenomination = "";
	
	private String email = "";
	
	private String password = "";
	
	/** private boolean gender oder mit enum KLasse müssen uns da einlesen*/
	
	
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
	
	/* Körpergröße*/
	
	/*Raucherstatus*/
	
	public String getReligiousDenomination() { /* wir sollten glaub das anders benennen*/
		return this.religiousDenomination;
	}
	
	public void setReligiousDenomination (String religion) {
		this.religiousDenomination = religion;
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
	
	/*Geschlecht*/
	
		
}
