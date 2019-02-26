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
				cb.setLetters(cb.getText().replaceAll("[^a-zA-Z.]", ""));
				if (cb.getNumber() == '0')
					cb.setLetters(" ");
			} else {
				cb.setNumber(cb.getText().charAt(0));
				cb.setLetters("");
			}

			cb.addEventFilter(MouseEvent.ANY, (event) -> transferButton(event,cb));

		}

	}

	private void transferButton(MouseEvent event,CustomButton cb) {
		

		if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			startTimeNumericButton = System.currentTimeMillis();
		} else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			if (System.currentTimeMillis() - startTimeNumericButton > 1500) {
				cb.setOnMouseClicked((event2)->changeLetter(event2,cb));
			} else {
				System.out.println(cb.getNumber());
			}
				
		}
	}

	private void changeLetter(MouseEvent event,CustomButton cb) {
		cb.removeEventFilter(MouseEvent.ANY, (event2)->{});
		if(event.getButton().equals(MouseButton.PRIMARY)){
            System.out.println( cb.getLetters().charAt((event.getClickCount()-1)%cb.getLetters().length()));
        }
	}

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
