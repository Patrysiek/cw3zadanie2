package controller.screen;

import controller.MainController;
import controller.keyboard.ControlButtonType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

public class WallpaperController {
	@FXML
	private Label labelHour;
	@FXML
	private ScreenMode screenMode = ScreenMode.WALLPAPER;
	@FXML
	private TextField numberScreenField;
	@FXML
	private Label rightLabel, leftLabel, middleLabel;
	@FXML
	private SplitPane wallpaperLayout;
	private MainController mainController;

	@FXML
	private void initialize() {
		HourRefresher refresher = new HourRefresher();
		labelHour.textProperty().bind(refresher.messageProperty());
		new Thread(refresher).start();
	}

	public void giveButtonData(char number) {
		if (!numberScreenField.isVisible()) {
			numberScreenField.setVisible(true);
			Platform.runLater(() -> rightLabel.setText("USUN"));
		}
		Platform.runLater(() -> numberScreenField.appendText(Character.toString(number)));
	}

	public void chooseWallpaperFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:
			setLayoutToDefault();
			break;
		case RIGHT_CONTROL:
			if (!numberScreenField.isVisible()) {
				setDisable(true);
				setVisible(false);
				setSMSLayout();
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
		setDisable(true);
		setVisible(false);
		mainController.setScreen(ScreenMode.CONTACTS);

	}

	private void setLayoutToDefault() {
		if (!numberScreenField.isVisible())
			return;
		if(numberScreenField.isDisable())
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

	private void setSMSLayout() {
		if (mainController == null) {
			System.out.println("Wstaw controller xd");
			return;
		}
		mainController.setScreen(ScreenMode.SMS);
	}

	public void setVisible(boolean visible) {
		this.wallpaperLayout.setVisible(visible);
	}

	public void setDisable(boolean disable) {
		this.wallpaperLayout.setDisable(disable);
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
}
