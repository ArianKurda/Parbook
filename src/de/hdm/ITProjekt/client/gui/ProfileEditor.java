package de.hdm.ITProjekt.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import de.hdm.ITProjekt.client.Parbook;
import de.hdm.ITProjekt.shared.ParbookService;
import de.hdm.ITProjekt.shared.ParbookServiceAsync;
import de.hdm.ITProjekt.shared.bo.Profile;

public class ProfileEditor {

	private DialogBox dB = new DialogBox();
	
	private Label firstNameLabel = new Label("Vorname:");
	private TextBox firstNameTb = new TextBox();
	private Label lastNameLabel = new Label("Nachname:");
	private TextBox lastNameTb = new TextBox();
	private Label emailLabel = new Label("E-Mail Adresse:");
	private TextBox emailTb = new TextBox();
	
	private RadioButton genderm = new RadioButton("genderGroup", "maennlich");
	private RadioButton genderw = new RadioButton("genderGroup", "weiblich");
	
	private Label hairColorLabel = new Label("Haarfarbe");
	private TextBox hairColorTb = new TextBox();
	
	private Label bodyHeightLabel = new Label("Koerpergroesse");
	private TextBox bodyHeightTb = new TextBox();
	
	private Label religionLabel = new Label("Religion");
	private TextBox religionTb = new TextBox();
	
	private RadioButton smokery = new RadioButton("smokerGroup", "Raucher");
	private RadioButton smokern = new RadioButton("smokerGroup", "Nichtraucher");
	
	private Label birthdateLabel = new Label("Geburtsdatum");
	
	private Profile activeProfile = Parbook.activeProfile;
	
	private HorizontalPanel ButtonPanel = new HorizontalPanel();
	
	private Button saveButton = new Button("Speichern");
	private Button abortButton = new Button("Abbrechen");
	private Button deleteButton = new Button("Profil löschen");
	
	private VerticalPanel profilePanel = new VerticalPanel();
	
	private final ParbookServiceAsync asyncParbook = GWT.create(ParbookService.class);

	private Label subline = new Label("Profildaten");
	HTML textEdit = new HTML(" <div class='" + "textEdit"
			+ "'><h4>  Auf dieser Oberfläche kannst du deine Nutzerdaten bearbeiten oder vollständig löschen.  </h4></div> ");
	HTML textCreate = new HTML(" <div class='" + "textCreate"
			+ "'><h4>  Für die Erstanmeldung musst du dein Profil vollständig ausfüllen. </h4></div> ");

	/**
	 * Die Methode <code>createProfile<code> wird aufgerufen sobald ein Profil
	 * in der DB nicht existiert, um ein neues Profil anzulegen.
	 * 
	 * @param p, neues Profil-Objekt
	 */
	public void createProfile(Profile p) {
		asyncParbook.insertProfile(p, new AsyncCallback<Profile>() {

			public void onFailure(Throwable caught) {
				Window.alert("Fehler bei der Profilerstellung");
			}

			@Override
			public void onSuccess(Profile result) {
				Window.alert("Profil erfolgreich erstellt.");
				activeProfile = result;
				Window.Location.reload();
			}

		});

	}

	/**
	 * Mit der Methode <code>updateProfile</code> kann der User sein Profil
	 * bearbeiten.
	 * 
	 * @param p, Profil-Objekt
	 */
	public void updateProfile(Profile p) {

		asyncParbook.updateProfile(p, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim aktualisieren");

			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Profil erfolgreich aktualisiert");

			}

		});

	}

	/**
	 * Mit der Methode <code>deleteProfile</code> hat der User die Möglichkeit
	 * sein Profil zu löschen.
	 * 
	 * @param p, Profil-Objekt
	 */
	public void deleteProfile(Profile p) {
		asyncParbook.deleteProfile(p, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fehler beim Profil löschen");

			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Profil gelöscht");
				Window.Location.assign(Parbook.getLoggedInUser().getLogoutUrl());

			}

		});
	}

	/**
	 * Über diese Methode <code>showEditor</code> wird eine Dialogbox erstellt
	 * mit den entsprechend benötigten/gesetzten Widgets. Der User sieht darin
	 * seine Profil-Informationen und hat über den "Speichern"-Button die
	 * Möglichkeit sein Profil anzupassen oder über den "Profil löschen"-Button
	 * kann er sein Profil löschen.
	 * 
	 * @return dB, DialogBox
	 */
	public DialogBox showEditor() {

		dB.setGlassEnabled(true);
		dB.setAutoHideEnabled(true);
		dB.setText("Profil bearbeiten");
		dB.center();

		emailTb.setText(Parbook.getLoggedInUser().getEmailAddress());
		emailTb.setReadOnly(true);

		profilePanel.add(subline);
		profilePanel.add(firstNameLabel);
		profilePanel.add(firstNameTb);
		profilePanel.add(lastNameLabel);
		profilePanel.add(lastNameTb);
		profilePanel.add(emailLabel);
		profilePanel.add(emailTb);
		profilePanel.add(genderm);
		profilePanel.add(genderw);
		profilePanel.add(birthdateLabel);
		profilePanel.add(bodyHeightLabel);
		profilePanel.add(bodyHeightTb);
		profilePanel.add(religionLabel);
		profilePanel.add(religionTb);
		profilePanel.add(smokery);
		profilePanel.add(smokern);
		profilePanel.add(hairColorLabel);
		profilePanel.add(hairColorTb);

		ButtonPanel.add(saveButton);
		ButtonPanel.add(deleteButton);
		ButtonPanel.add(abortButton);

		/**
		 * Ein ClickHandler, um die Profilattribute vom User abzuspeichern.
		 */
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (firstNameTb.getValue().isEmpty() || lastNameTb.getValue().isEmpty()) {
					Window.alert("Bitte alle Felder richtig befüllen");
				} else {
					activeProfile.setFirstName(firstNameTb.getText());
					activeProfile.setLastName(lastNameTb.getText());
					updateProfile(activeProfile);
				}
			}
		});

		/**
		 * Ein ClickHandler, um das User-Profil zu löschen.
		 */
		deleteButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				deleteProfile(activeProfile);
			}
		});

		/**
		 * Ein ClickHandler, um die DialogBox auszublenden.
		 */
		abortButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dB.hide();
			}
		});

		VerticalPanel vP = new VerticalPanel();
		vP.add(textEdit);
		vP.add(profilePanel);
		vP.add(ButtonPanel);
		vP.setStyleName("profile-verticalPanel");
		dB.add(vP);
		return dB;
	}

	/**
	 * Die Methode <code>showCreateEditor</code> wird aufgerufen, sobald ein
	 * Profil noch nicht in der Datenbank vorhanden ist. Der neue User kann
	 * darin seinen Vor- und Nachnamen setzen. Die E-Mail vom User wird vom
	 * LoginService bereits übernommen.
	 * 
	 * @return vP - VerticalPanel
	 */
	public VerticalPanel showCreateEditor() {
		emailTb.setText(Parbook.getLoggedInUser().getEmailAddress());

		DatePicker datePicker = new DatePicker();
		datePicker.setYearArrowsVisible(true);
		datePicker.setYearAndMonthDropdownVisible(true);
		// show 51 years in the years dropdown. The range of years is centered
		// on the selected date
		datePicker.setVisibleYearCount(51);

		final Label text2 = new Label();

		text2.getElement().getStyle().setMarginTop(15, Unit.PX);
		// Set the value in the text box when the user selects a date
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat("MM/dd/yyyy").format(date);
				text2.setText(dateString);
			}
		});

		// Set the default value
		datePicker.setValue(new Date(), true);

		profilePanel.add(subline);
		profilePanel.add(firstNameLabel);
		profilePanel.add(firstNameTb);
		profilePanel.add(lastNameLabel);
		profilePanel.add(lastNameTb);
		profilePanel.add(emailLabel);
		profilePanel.add(emailTb);
		profilePanel.add(genderm);
		profilePanel.add(genderw);
		profilePanel.add(birthdateLabel);
		profilePanel.add(text2);
		profilePanel.add(datePicker);
		profilePanel.add(bodyHeightLabel);
		profilePanel.add(bodyHeightTb);
		profilePanel.add(religionLabel);
		profilePanel.add(religionTb);
		profilePanel.add(smokery);
		profilePanel.add(smokern);
		profilePanel.add(hairColorLabel);
		profilePanel.add(hairColorTb);

		emailTb.setReadOnly(true);
		ButtonPanel.add(saveButton);

		/**
		 * Ein Clickhandler, um die Profilattribute abzuspeichern.
		 */
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (firstNameTb.getValue().isEmpty() || lastNameTb.getValue().isEmpty()) {
					Window.alert("Bitte alle Felder richtig befüllen");
				} else {
					activeProfile.setEmail(emailTb.getText());
					activeProfile.setFirstName(firstNameTb.getText());
					activeProfile.setLastName(lastNameTb.getText());

					createProfile(activeProfile);
				}
			}
		});

		VerticalPanel vP = new VerticalPanel();
		vP.setStyleName("profil-verticalPanel");
		vP.setSpacing(10);
		vP.add(textCreate);
		vP.add(profilePanel);
		vP.add(ButtonPanel);
		return vP;
	}
}
