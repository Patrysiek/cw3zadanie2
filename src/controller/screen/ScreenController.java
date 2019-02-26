package controller.screen;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScreenController {
	@FXML
	private Label labelHour;
	private ScreenMode screenMode = ScreenMode.WALLPAPER;
	
	
	@FXML private void initialize() {
		HourRefresher refresher = new HourRefresher();
		labelHour.textProperty().bind(refresher.messageProperty());
		new Thread(refresher).start();
	}


	public void giveButtonData(char number, char letter) {
		System.out.println(number+" "+letter);
		
	}
}
