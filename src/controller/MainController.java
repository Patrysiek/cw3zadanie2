package controller;

import controller.keyboard.KeyboardController;
import controller.screen.ScreenController;
import javafx.fxml.FXML;

public class MainController {
	@FXML private KeyboardController keyboardLayoutController;
	@FXML private ScreenController screenLayoutController;
	
	
	@FXML private void initialize() {
		keyboardLayoutController.setMainController(this);
	}
	
	public void transferButton(char number, String letters) {
		
	}
	
	
}
