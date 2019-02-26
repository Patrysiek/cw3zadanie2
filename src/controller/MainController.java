package controller;

import controller.keyboard.ControlButtonType;
import controller.keyboard.KeyboardController;
import controller.screen.ContactsController;
import controller.screen.SMSController;
import controller.screen.ScreenMode;
import controller.screen.WallpaperController;
import javafx.fxml.FXML;

public class MainController {
	@FXML
	private KeyboardController keyboardLayoutController;
	@FXML
	private WallpaperController wallpaperLayoutController;
	@FXML
	private SMSController smsLayoutController;
	@FXML
	private ContactsController contactsLayoutController;
	private ScreenMode screenMode = ScreenMode.WALLPAPER;

	@FXML
	private void initialize() {
		keyboardLayoutController.setMainController(this);
		wallpaperLayoutController.setMainController(this);
	}

	public void transferButton(char number, char letter) {
		switch (screenMode) {
		case WALLPAPER:
			wallpaperLayoutController.giveButtonData(number);
			break;
		case SMS:
			///transferButtonForSMS
			break;
		case CONTACTS:
			///transferButtonForContacts
			break;
		default:
			break;
		}
	}

	public void transferControlButton(ControlButtonType type) {
		switch (screenMode) {
		case WALLPAPER:
			wallpaperLayoutController.chooseWallpaperFunction(type);
			break;
		case SMS:
			///transferControlButtonForSMS
			break;
		case CONTACTS:
			///transferControlButtonForContacts
			break;
		default:
			break;
		}
	}

	public void setScreen(ScreenMode mode) {
		this.screenMode = mode;
	}

}
