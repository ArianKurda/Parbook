package de.hdm.ITProjekt.server;

import java.util.ArrayList;
import java.sql.Date;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.ITProjekt.server.db.*;
import de.hdm.ITProjekt.shared.ParbookService;
import de.hdm.ITProjekt.shared.bo.*;

public class ParbookServiceImpl extends RemoteServiceServlet implements ParbookService{
	
	/* MARK: Mapperklassen */

	private ProfileMapper profileMapper = null;
	private NotepadMapper notepadMapper = null;
	private InfoMapper infoMapper = null;
	private CharacteristicMapper characteristicMapper = null;
	private BlocklistMapper blocklistMapper = null;

	public ParbookServiceImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.profileMapper = ProfileMapper.profileMapper();
		this.notepadMapper = NotepadMapper.notepadMapper();
		this.infoMapper = InfoMapper.infoMapper();
		this.notepadMapper = NotepadMapper.notepadMapper();
		this.blocklistMapper = BlocklistMapper.blocklistMapper();

	}

	@Override
	public Profile createProfile(String firstName, String lastName, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile editStandardProfileAttributes(Profile p, String firstName, String lastName, String email,
			String password, java.util.Date dob, String hairColor, double bodyHeight, boolean smoker, String religion,
			boolean gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profile editProfile(Profile p, String firstName, String lastName, String email, String password,
			java.util.Date dob, String hairColor, double bodyHeight, boolean smoker, String religion, boolean gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProfile(Profile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Profile> findProfileByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Profile> findMatch() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notepad createNotepad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editNotepad(Notepad n, String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Notepad addProfileToNotepad(Profile p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProfileFromNotepad(Notepad n, Profile p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Notepad> findNotepadByProfileID(int profileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Characteristic createCharacteristic(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCharacteristic(Characteristic c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Description createDescription(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteDescription(Description description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Selection createSelection(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteSelection(Selection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Info createInfo(String text) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Info addCharacteristicToInfo(Characteristic c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Info addDescriptionToInfo(Description description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Info addSelectionToInfo(Selection selection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInfo(Info i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Blocklist createBlocklist(Blocklist b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blocklist addProfileToBlocklist(Profile p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBlocklist(Blocklist b) {
		// TODO Auto-generated method stub
		
	}

}
