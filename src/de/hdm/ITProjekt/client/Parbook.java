package de.hdm.ITProjekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.ITProjekt.client.gui.Impressum;
import de.hdm.ITProjekt.client.gui.ProfileEditor;
import de.hdm.ITProjekt.shared.LoginInfo;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.LoginServiceAsync;
import de.hdm.ITProjekt.shared.ParbookService;
import de.hdm.ITProjekt.shared.ParbookServiceAsync;
import de.hdm.ITProjekt.shared.bo.Profile;


/**
 * 
 * In dieser EntryPoint Klasse <class>Parbook</class> wird zunächst einmal der Login Status geprüft,
 * anschließend wird der eigentliche Editor aufgerufen.
 * 
 * @author kurda
 */

public class Parbook implements EntryPoint {
	private static LoginInfo loginInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label ("Bist du bereit dich zu paaren?");
	private Anchor signInLink = new Anchor("Sign In");
	private Anchor signOutLink = new Anchor("Sign Out");
	public static Profile activeProfile = new Profile();
	
	ParbookServiceAsync asyncParbook = GWT.create(ParbookService.class);
	
	public static LoginInfo getLoggedInUser() {
		return loginInfo;
		}

/*Bsp*/
//private Button saveButton = new Button("Speichern");

/**
 * In dieser EntryPoint Methode <code>onModuleLoad</code> wird über den LoginService geprüft, ob der Nutzer eingeloggt ist.
 * Ist das der Fall, wird die Methode checkIfUserExist() aufgerufen, andernfalls die Methode loadLogin()
 * dass der Nutzer sich einloggen kann.
 */
@Override
public void onModuleLoad() {
	LoginServiceAsync loginService = GWT.create(LoginService.class);
	loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
		
		public void onFailure(Throwable error) {	
		}
		
		public void onSuccess(LoginInfo result) {
			loginInfo = result;
			if(loginInfo.isLoggedIn()) {
				loadParbook();
			} else {
				loadLogin();
				}
			}
		
		private void loadParbook() {
			// Set up sign out hyperlink.
			signOutLink.setHref(loginInfo.getLogoutUrl());
			signOutLink.setStyleName("gwt-sign-out");
			
			RootPanel.get().add(signOutLink);
			}
		});
	   }

/**
 * Die Methode loadLogin() setzt die nötigen Elemente in ein Login Panel, damit sich der
 * Nutzer erfolgreich anmelden kann. 
 */

private void loadLogin() {
	// Assemble login panel.
	signInLink.setHref(loginInfo.getLoginUrl());
	Label welcomeLabel = new Label ("Willkommen zu Parbook");
	welcomeLabel.setStyleName("welcomeLabel");
	loginPanel.add(welcomeLabel);
	loginPanel.add(loginLabel);
	loginPanel.add(signInLink);
	loginPanel.setStyleName("loginPanel");
	RootPanel.get("header").add(loginPanel);
}

/**
 * Mit dieser Methode checkIfUserExist wird in der Datenbank geprüft, ob ein Nutzer bereits existiert.
 * Sollte der Nutzer vorhanden sein wird die Methode loadMainPage aufgerufen, andernfalls die Methode
 * createUser um einen neuen Nutzer zu erstellen. 
 */
public void checkIfProfileExist() {
	asyncParbook.findByEmail(loginInfo.getEmailAddress(), new AsyncCallback<Profile>() {

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			}

		@Override
		public void onSuccess(Profile result) {
			
			String s = Integer.toString(result.getId());
			if (s == "0") {
				
				//Ist das Profil in der DB nicht vorhanden, wird das Formular zum Erstellen eines Profils bei Parbook aufgerufen
				createProfile();
				
				// SignOut Button
				signOutLink.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.Location.assign(loginInfo.getLogoutUrl());
						RootPanel.get("content").clear();
						RootPanel.get("right-view").clear();
						RootPanel.get("footer").clear();
						loadLogin();
						}
					});
				RootPanel.get("footer").add(signOutLink);	
			} else {
				activeProfile.setId(result.getId());
				activeProfile.setGoogleID(result.getGoogleID());
				activeProfile.setFirstName(result.getFirstName());
				activeProfile.setLastName(result.getLastName());
				activeProfile.setEmail(result.getEmail());
				loadMainPage();
				}
			}
	});
}
		/**
		 * Ist ein User noch nicht in der Datenbank vorhanden. Wird mit der Methode <code>createProfile</code>
		 * der ProfileEditor zum Erstellen eines neuen Profils aufgerufen und in den DIV: content mittels
		 * RootPanel.get hinzugefügt.
		 */
		private void createProfile() {
			VerticalPanel ese = new ProfileEditor().showCreateEditor();
			RootPanel.get("content").add(ese);
			}
		
		/**
		 * Die Methode <code>loadMainpage</code> erstellt die entsprechenden Widgets die für den eigentlichen
		 * Editor nötig sind und übergibt es über die DIVs (header, controller) zum RootPanel hinzu.
		 */
		private void loadMainPage() {
			VerticalPanel headerPanel = new VerticalPanel();
			HorizontalPanel headerPanelBtn = new HorizontalPanel();
			headerPanel.setStyleName("headerPanel");
			Label welcomeMsg = new Label("Willkommen zu Parbook " + activeProfile.getFirstName());
			welcomeMsg.setStyleName("welcomeMsg");
			Button editProfile = new Button();
			editProfile.setText("Profil bearbeiten");

			Button impressumbtn = new Button("Impressum");
			
			/**
			 * Klickt man auf den Button Impressum, erscheint im DIV(content) das Impressum.
			 */
			impressumbtn.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					RootPanel.get("content").clear();
					RootPanel.get("content").add(new Impressum().getHtmlImpressum());
					}
				});
			
			/**
			 * Klickt man auf "Profil bearbeiten" erscheint eine DialogBox mit dem UserEditor.
			 */
			editProfile.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					DialogBox profileEditor = new ProfileEditor().showEditor();
					profileEditor.show();
					}
				});
			
			// Profil - Enthält die Profilattribute
			//Profile p = new Profile();
			
			/**
			 * Klickt man auf den Button "Abmelden" wird der User von der Seite abgemeldet.
			 */
			signOutLink.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Window.Location.assign(loginInfo.getLogoutUrl());
					RootPanel.get("content").clear();
					RootPanel.get("right-view").clear();
					RootPanel.get("footer").clear();
					loadLogin();
					}
				});
			
			//Widgets zu den Panels hinzufügen
			headerPanel.add(welcomeMsg);
			headerPanelBtn.add(editProfile);
			headerPanelBtn.add(signOutLink);
			headerPanelBtn.add(impressumbtn);
			headerPanel.add(headerPanelBtn);

			//Add it to the root panel.
			RootPanel.get("header").add(headerPanel);
			//RootPanel.get("controller").add(p);
		}
	}