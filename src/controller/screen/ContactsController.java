package controller.screen;

import java.util.Map.Entry;
import java.util.Properties;

import controller.MainController;
import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Contact;
import model.PropertyStream;

public class ContactsController extends Controller {

	private PropertyStream loader;
	private int selectedContact = 0;
	@FXML
	private ListView<Object> contactsListView;

	@FXML
	private Label caller;

	private final ObservableList<Object> contacts = FXCollections.observableArrayList();

	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			if (checkScreenSetter())
				break;
			setScreenToDefault();
			break;
		case DOWN_ARROW:
			changeSelectedContact(ControlButtonType.DOWN_ARROW);
			break;
		case GREEN_PHONE:
			simulateCalling((contactsListView.selectionModelProperty().get().getSelectedItem().toString()));
			break;
		case LEFT_CONTROL:
			if (checkScreenSetter())
				break;
			screenSetter.setScreen(ScreenMode.ADD_CONTACTS);
			break;
		case RIGHT_CONTROL:
			deleteContact();
			loadContacts();
			break;
		case UP_ARROW:
			changeSelectedContact(ControlButtonType.UP_ARROW);
			break;
		default:
			break;
		}
	}

	private void setScreenToDefault() {
		if (caller.isVisible()) {
			caller.setVisible(false);
			contactsListView.setDisable(false);
		} else
			screenSetter.setScreen(ScreenMode.WALLPAPER);
	}

	private void simulateCalling(String string) {
		Platform.runLater(() -> caller.setText("DZWONIE DO: " + string));
		caller.setVisible(true);
		contactsListView.setDisable(true);
	}

	private void changeSelectedContact(ControlButtonType arrows) {
		switch (arrows) {
		case DOWN_ARROW:
			selectedContact++;
			if (selectedContact >= contacts.size())
				selectedContact = 0;
			contactsListView.selectionModelProperty().get().select(selectedContact);
			contactsListView.scrollTo(selectedContact);
			break;
		case UP_ARROW:
			selectedContact--;
			if (selectedContact < 0)
				selectedContact = contacts.size() - 1;
			contactsListView.selectionModelProperty().get().select(selectedContact);
			contactsListView.scrollTo(selectedContact);
			break;
		default:
			break;
		}
	}

	public void loadContacts() {
		loader = new PropertyStream(MainController.CONTACTS_FILE);
		Properties prop = loader.loadData();
		contacts.clear();

		for (Entry<Object, Object> p : prop.entrySet())
			contacts.add(new Contact((String) p.getKey(), (String) p.getValue()));

		contactsListView.setItems(contacts);
		contactsListView.selectionModelProperty().get().select(0);

	}

	private void deleteContact() {
		Object o = contactsListView.selectionModelProperty().get().getSelectedItem();
		loader.deleteData(((Contact) o).getNumber());
	}

}
