package controller.screen;

import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WallpaperController extends Controller {

	@FXML
	private TextField numberScreenField;
	@FXML
	private Label rightLabel, leftLabel;


	public void giveButtonData(char number) {
		if (!numberScreenField.isVisible()) {
			numberScreenField.setVisible(true);
			Platform.runLater(() -> rightLabel.setText("USUN"));
		}
		Platform.runLater(() -> numberScreenField.appendText(Character.toString(number)));
	}

	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			setLayoutToDefault();
			break;
		case RIGHT_CONTROL:
			if (!numberScreenField.isVisible()) {
				if (checkScreenSetter())
					break;
				screenSetter.setScreen(ScreenMode.SMS);
			} else {
				deleteOneCharacter();
			}
			break;
		case LEFT_CONTROL:
			setContactsLayout();
			break;
		case GREEN_PHONE:
			simulateCalling();
			break;
		default:
			break;
		}
	}

	private void simulateCalling() {
		if (!numberScreenField.isVisible())
			return;
		if (numberScreenField.getLength() <= 0)
			return;
		Platform.runLater(() -> numberScreenField.setText("DZWONIENIE..."));
		numberScreenField.setDisable(true);
	}

	private void setContactsLayout() {
		if (numberScreenField.isVisible())
			return;
		screenSetter.setScreen(ScreenMode.CONTACTS);
	}

	private void setLayoutToDefault() {
		if (!numberScreenField.isVisible())
			return;
		if (numberScreenField.isDisable())
			numberScreenField.setDisable(false);

		numberScreenField.setVisible(false);
		numberScreenField.setText("");
		rightLabel.setText("SMS");
	}

	private void deleteOneCharacter() {
		if (numberScreenField.getLength() <= 0)
			return;

		Platform.runLater(() -> numberScreenField
				.setText(numberScreenField.getText().substring(0, numberScreenField.getLength() - 1)));
	}


}
