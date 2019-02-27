package controller;

import Model.HourRefresher;
import controller.keyboard.ControlButtonType;
import controller.keyboard.KeyboardController;
import controller.screen.AddContactController;
import controller.screen.ContactsController;
import controller.screen.SMSController;
import controller.screen.ScreenMode;
import controller.screen.WallpaperController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController implements IScreenSetter {
	@FXML
	private KeyboardController keyboardLayoutController;
	@FXML
	private Label labelHour;
	@FXML
	private WallpaperController wallpaperLayoutController;
	@FXML
	private SMSController smsLayoutController;
	@FXML
	private ContactsController contactsLayoutController;
	@FXML
	private AddContactController addContactLayoutController;
	
	
	private ScreenMode screenMode = ScreenMode.WALLPAPER;

	@FXML
	private void initialize() {
		keyboardLayoutController.setMainController(this);
		wallpaperLayoutController.setScreenSetter(this);
		smsLayoutController.setScreenSetter(this);
		contactsLayoutController.setScreenSetter(this);
		addContactLayoutController.setScreenSetter(this);
		
		
		HourRefresher refresher = new HourRefresher();
		labelHour.textProperty().bind(refresher.messageProperty());
		new Thread(refresher).start();
	}

	public void transferButton(char number, char letter) {
		switch (screenMode) {
		case WALLPAPER:
			wallpaperLayoutController.giveButtonData(number);
			break;
		case SMS:
			smsLayoutController.giveButtonData(number, letter);
			break;
		case CONTACTS:
			break;
		default:
			break;
		}
	}

	public void transferControlButton(ControlButtonType type) {
		switch (screenMode) {
		case WALLPAPER:
			wallpaperLayoutController.chooseFunction(type);
			break;
		case SMS:
			smsLayoutController.chooseFunction(type);
			break;
		case CONTACTS:
			contactsLayoutController.chooseFunction(type);
			break;
		case ADD_CONTACTS:
			addContactLayoutController.chooseFunction(type);
			break;
		default:
			break;
		}
	}

	@Override
	public void setScreen(ScreenMode mode) {
		this.screenMode = mode;
		switch (screenMode) {
		case SMS:
			setWallpaperLayout(false);
			setSMSLayout(true);
			setContactsLayout(false);
			setAddContactsLayout(false);
			break;
		case WALLPAPER:
			setWallpaperLayout(true);
			setSMSLayout(false);
			setContactsLayout(false);
			setAddContactsLayout(false);
			break;
		case CONTACTS:
			setWallpaperLayout(false);
			setSMSLayout(false);
			setContactsLayout(true);
			setAddContactsLayout(false);
			break;
		case ADD_CONTACTS:
			setWallpaperLayout(false);
			setSMSLayout(false);
			setContactsLayout(false);
			setAddContactsLayout(true);
			break;
		default:
			break;
		}
	}

	private void setWallpaperLayout(boolean set) {
		wallpaperLayoutController.setDisable(!set);
		wallpaperLayoutController.setVisible(set);
	}

	private void setSMSLayout(boolean set) {
		smsLayoutController.setDisable(!set);
		smsLayoutController.setVisible(set);
	}

	private void setContactsLayout(boolean set) {
		contactsLayoutController.setDisable(!set);
		contactsLayoutController.setVisible(set);
	}
	
	private void setAddContactsLayout(boolean set) {
		addContactLayoutController.setDisable(!set);
		addContactLayoutController.setVisible(set);
	}

}
