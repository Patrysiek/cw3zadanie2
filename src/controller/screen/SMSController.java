package controller.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SMSController {
	@FXML
	private Label labelHour;

	@FXML
	private void initialize() {
		HourRefresher refresher = new HourRefresher();
		labelHour.textProperty().bind(refresher.messageProperty());
		new Thread(refresher).start();
	}
}