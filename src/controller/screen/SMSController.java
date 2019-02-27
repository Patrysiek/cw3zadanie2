package controller.screen;

import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SMSController extends Controller{

	@FXML
	private TextField contactField;
	@FXML
	private TextArea smsContent;
	private boolean editContent = false;

	public void giveButtonData(char number, char letter) {

		if (editContent)
			smsContent.appendText(Character.toString(letter));
		else
			contactField.appendText(Character.toString(letter));
	}

	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			Platform.runLater(() -> {
				contactField.setText("");
				smsContent.setText("");
			});
			if (screenSetter == null) {
				System.out.println("Set screen setter");
				break;
			}
			screenSetter.setScreen(ScreenMode.WALLPAPER);
			break;
		case DOWN_ARROW:
			editContent = true;
			break;
		case GREEN_PHONE:
			break;
		case LEFT_CONTROL:
			break;
		case L_ARROW:
			break;
		case OK:
			break;
		case RIGHT_CONTROL:
			if (editContent) {
				if (smsContent.getLength() > 0)
					smsContent.setText(smsContent.getText().substring(0, smsContent.getLength() - 1));
			}
			else {
				if (contactField.getLength() > 0)
					contactField.setText(contactField.getText().substring(0, contactField.getLength() - 1));
			}
			break;
		case R_ARROW:
			break;
		case UP_ARROW:
			editContent = false;
			break;
		default:
			break;

		}
	}


}
