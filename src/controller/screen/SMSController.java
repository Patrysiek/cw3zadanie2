package controller.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;

public class SMSController {
	@FXML
	private Label labelHour;
	@FXML private SplitPane smsLayout;

	@FXML
	private void initialize() {
		HourRefresher refresher = new HourRefresher();
		labelHour.textProperty().bind(refresher.messageProperty());
		new Thread(refresher).start();
	}
	
	
	
	public void setDisable(boolean disable) {
		smsLayout.setDisable(disable);
	}
	public void setVisible(boolean visible) {
		smsLayout.setVisible(visible);
	}
	
}
