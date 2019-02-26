package controller.keyboard;

import controller.MainController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class KeyboardController {

	@FXML
	private GridPane downNumericGridPane;
	private MainController mainController;
	private long startTimeNumericButton;
	private int choosedLetter = 0;
	private boolean canClick = true;

	@FXML
	private void initialize() {
		initDownNumericButtons();
		initControlButtons();
	}

	private void initControlButtons() {
	}

	private void initDownNumericButtons() {
		for (Node n : downNumericGridPane.getChildren()) {
			if (!n.getClass().equals(CustomButton.class))
				continue;

			CustomButton cb = (CustomButton) n;

			if (!cb.getText().matches("^\\D*$")) {
				cb.setNumber(cb.getText().charAt(0));
			}

			if (cb.getText().length() > 1) {
				cb.setLetters(cb.getText().replaceAll("[^a-zA-Z.]", "") + cb.getNumber());
				if (cb.getNumber() == '0')
					cb.setLetters(" ");
			} else {
				cb.setNumber(cb.getText().charAt(0));
				cb.setLetters("");
			}

			cb.setOnMouseClicked((event) -> changeLetter(event, cb));

		}

	}

	private void setLetter(CustomButton cb) {
		mainController.transferButton(cb.getNumber(), cb.getLetters().charAt(choosedLetter % cb.getLetters().length()));
		choosedLetter = 0;
		canClick = true;
	}

	private void changeLetter(MouseEvent event, CustomButton cb) {

		if (!canClick)
			return;

		startTimeNumericButton = System.currentTimeMillis();
		new Thread(() -> {
			canClick = false;
			while (System.currentTimeMillis() - startTimeNumericButton < 1000)
				cb.setOnMouseClicked((event3) -> choosedLetter++);

			cb.setOnMouseClicked((event2) -> changeLetter(event, cb));
			setLetter(cb);

		}).start();

	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
