package controller.screen;

import controller.MainController;
import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.PropertyStream;

public class AddContactController extends Controller {

	@FXML
	private TextField nameField, numberField;
	private PropertyStream saver;
	private boolean editNumber = false;

	@FXML
	private void initialize() {
		saver = new PropertyStream(MainController.CONTACTS_FILE);
	}

	public void giveButtonData(char number, char letter) {

		if (editNumber)
			numberField.appendText(String.valueOf(number));
		else
			nameField.appendText(Character.toString(letter));
	}

	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			setNewScreen(ScreenMode.WALLPAPER);
			break;
		case DOWN_ARROW:
			editNumber = true;
			break;
		case GREEN_PHONE:
			break;
		case LEFT_CONTROL:
			try {
				saver.saveData(numberField.getText(), nameField.getText());
			} catch (Exception e) {
				break;
			}
			setNewScreen(ScreenMode.CONTACTS);
			break;

		case RIGHT_CONTROL:
			if (editNumber) {
				if (numberField.getLength() > 0)
					numberField.setText(numberField.getText().substring(0, numberField.getLength() - 1));
			} else {
				if (nameField.getLength() > 0)
					nameField.setText(nameField.getText().substring(0, nameField.getLength() - 1));
			}
			break;
		case UP_ARROW:
			editNumber = false;
			break;
		default:
			break;
		}
	}

	private void setNewScreen(ScreenMode mode) {
		Platform.runLater(() -> {
			nameField.setText("");
			numberField.setText("");
		});
		if (checkScreenSetter()) {
			System.out.println("Set screen setter");
		}
		screenSetter.setScreen(mode);
		
	}

}
