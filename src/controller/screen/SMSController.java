package controller.screen;

import java.util.Map.Entry;

import controller.MainController;
import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Contact;
import model.PropertyStream;

public class SMSController extends Controller {

	@FXML
	private ComboBox<Object> contactComboBox;
	@FXML
	private TextArea smsContent;
	@FXML
	private Label leftLabel, sendingLabel;
	private boolean editContent = false, isComboBoxCollapsed = false;
	ObservableList<Object> contactsList = FXCollections.observableArrayList();

	public void giveButtonData(char number, char letter) {

		if (editContent)
			smsContent.appendText(Character.toString(letter));
		else {
			Platform.runLater(() -> contactComboBox.getEditor().appendText(String.valueOf(letter)));
		}
	}

	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			if (sendingLabel.isVisible()) {
				sendingLabel.setVisible(false);
				break;
			}
			Platform.runLater(() -> {
				contactComboBox.getEditor().setText("");
				smsContent.setText("");
			});
			if (screenSetter == null) {
				System.out.println("Set screen setter");
				break;
			}
			screenSetter.setScreen(ScreenMode.WALLPAPER);
			break;
		case DOWN_ARROW:
			if (isComboBoxCollapsed) {
				contactComboBox.getSelectionModel().selectNext();
			} else {
				editContent = true;
				Platform.runLater(() -> leftLabel.setText("Wyslij"));
			}

			break;
		case UP_ARROW:
			if (isComboBoxCollapsed) {
				contactComboBox.getSelectionModel().selectPrevious();
			} else {
				editContent = false;
				Platform.runLater(() -> leftLabel.setText("Wybierz"));
			}
			break;
		case LEFT_CONTROL:
			if (editContent) {
				sendSms(contactComboBox.getEditor().getText());
			} else {
				if (isComboBoxCollapsed) {
					contactComboBox.hide();
					isComboBoxCollapsed = false;
				} else {
					contactComboBox.show();
					isComboBoxCollapsed = true;
				}
			}
			break;
		case RIGHT_CONTROL:

			if (editContent) {
				if (smsContent.getLength() > 0)
					smsContent.setText(smsContent.getText().substring(0, smsContent.getLength() - 1));
			} else
				deleteContactComboBoxCharacter();
			break;
		default:
			break;
		}
	}

	private void sendSms(String text) {
		if (text == null || text.length() == 0)
			return;

		sendingLabel.setText("Wysylanie do: " + text);
		sendingLabel.setVisible(true);

		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(1000);
				Platform.runLater(() -> sendingLabel.setText("Wyslano !"));
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sendingLabel.setVisible(false);
		}, "Thread");

		t1.start();
	}

	private void deleteContactComboBoxCharacter() {
		if (contactComboBox.getEditor().getText().length() <= 0)
			return;
		contactComboBox.getEditor().setText(
				contactComboBox.getEditor().getText().substring(0, contactComboBox.getEditor().getText().length() - 1));
	}

	public void loadContacts() {
		contactComboBox.setVisibleRowCount(3);
		contactComboBox.getItems().clear();

		PropertyStream stream = new PropertyStream(MainController.CONTACTS_FILE);
		for (Entry<Object, Object> e : stream.loadData().entrySet())
			contactsList.add(new Contact(e.getKey().toString(), e.getValue().toString()));

		contactComboBox.getItems().addAll(contactsList);

	}
}
