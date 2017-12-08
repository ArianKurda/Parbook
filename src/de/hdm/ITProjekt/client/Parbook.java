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

import de.hdm.ITProjekt.client.gui.editor.Impressum;
import de.hdm.ITProjekt.client.gui.editor.NotepadList;
import de.hdm.ITProjekt.client.gui.editor.UserEditor;
import de.hdm.ITProjekt.shared.LoginInfo;
import de.hdm.ITProjekt.shared.LoginService;
import de.hdm.ITProjekt.shared.LoginServiceAsync;
import de.hdm.ITProjekt.shared.ParbookAdministration;
import de.hdm.ITProjekt.shared.ParbookAdministrationAsync;
import de.hdm.ITProjekt.shared.bo.Profile;

/**
 * In dieser EntryPoint Klasse <class>Parbook</class> wird zunächst einmal der Login Status geprüft,
 * der eigentliche Editor aufgerufen.
 * 
 * @author arian
 *
 */
public class Parbook implements EntryPoint {
	
	public IT_Projekt_Team6() {

	}

	private VerticalPanel loginPanel = new VerticalPanel();
	private static LoginInfo loginInfo = null;
	private Label loginLabel = new Label("Bitte melde dich mit deinem Google Account an um Zugang zu NoteStar zu erhalten.");
	private Anchor signInLink = new Anchor("Einloggen");
	private Button signOutLink = new Button("Abmelden");
	public static User activeUser = new User();

	NotepadAdministrationAsync asyncNote = GWT.create(NotepadAdministration.class);

	public static LoginInfo getLoggedInUser() {
		return loginInfo;
	}


	/**
	 * In dieser EntryPoint Methode <code>onModuleLoad</code> wird über den LoginService geprüft, ob der Nutzer eingeloggt ist.
	 * Ist das der Fall, wird die Methode checkIfUserExist() aufgerufen, andernfalls die Methode loadLogin()
	 * dass der Nutzer sich einloggen kann.
	 */
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				//						handleError(error);
			}

			public void onSuccess(LoginInfo result) {
				loginInfo = result;
				if (loginInfo.isLoggedIn()) {
					checkIfUserExist();
				} else {
					loadLogin();
				}
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
		Label welcomeLabel = new Label("Willkommen zu NoteStar");
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
	public void checkIfUserExist() {

		asyncNote.findByEmail(loginInfo.getEmailAddress(), new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {

			}

			@Override
			public void onSuccess(User result) {

				String s = Integer.toString(result.getId());
				if(s == "0") {

					// Ist der User in der Datenbank nicht vorhanden, wird das Formular zur Erstellung eines Accounts bei NoteStar aufgerufen
					createUser();

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
					activeUser.setId(result.getId());
					activeUser.setGoogleID(result.getGoogleID());
					activeUser.setFirstname(result.getFirstname());
					activeUser.setLastname(result.getLastname());
					activeUser.setEmail(result.getEmail());
					loadMainPage();
				}
			}
		});	
	}
	
	/**
	 * Ist ein User noch nicht in der Datenbank vorhanden. Wird mit der Methode <code>createUser</code>
	 * der UserEditor zum Erstellen eines neuen Nutzers aufgerufen und in den DIV: content mittels
	 * RootPanel.get hinzugefügt.
	 */
	public void createUser() {	
		VerticalPanel ese = new UserEditor().showCreateEditor();
		RootPanel.get("content").add(ese);
	}
	
	
	/**
	 * Die Methode <code>loadMainpage</code> erstellt die entsprechenden Widgets die für den eigentlichen
	 * Editor nötig sind und übergibt es über die DIVs (header, controller) zum RootPanel hinzu.
	 */
	public void loadMainPage() {
		VerticalPanel headerPanel = new VerticalPanel();
		HorizontalPanel headerPanelBtn = new HorizontalPanel();
		headerPanel.setStyleName("headerPanel");
		Label welcomeMsg = new Label("Willkommen zu NoteStar " + activeUser.getFirstname());
		welcomeMsg.setStyleName("welcomeMsg");
		Button editProfil = new Button();
		editProfil.setText("Profil bearbeiten");

		Button impressumbtn = new Button("Impressum");
		
		/**
		 * Klickt man auf den Button Impressum, erscheint im DIV(content) das Impressum.
		 */
		impressumbtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get("content").clear();
				RootPanel.get("content").add(new Impressum().getHtmlImpressum());
			}
		});
		
		
		/**
		 * Klickt man auf "Profil bearbeiten" erscheint eine DialogBox mit dem UserEditor.
		 */
		editProfil.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				DialogBox userEditor = new UserEditor().showEditor();
				userEditor.show();
			}
		});


		// NotepadListe - Enthält Notizbücher und Notizen
		NotepadList notepadList = new NotepadList();

		/**
		 * Klickt man auf den Button "Abmelden" wird der Nutzer von der Seite abgemeldet.
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


		// Widgets zu den Panels hinzufügen
		headerPanel.add(welcomeMsg);
		headerPanelBtn.add(editProfil);
		headerPanelBtn.add(signOutLink);
		headerPanelBtn.add(impressumbtn);
		headerPanel.add(headerPanelBtn);


		//	     Add it to the root panel.
		RootPanel.get("header").add(headerPanel);
		RootPanel.get("controller").add(notepadList);
	}
}
