package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

public class Parbook implements EntryPoint {
	
	private Button saveButton = new Button("Speichern");
	
	
	

	@Override
	public void onModuleLoad() {
		
		saveButton.setStyleName("gwt-save-button");
		RootPanel.get().add(saveButton);
		// TODO Auto-generated method stub
		
	}
	
}