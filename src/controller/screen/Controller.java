package controller.screen;

import controller.IScreenSetter;
import controller.keyboard.ControlButtonType;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;

public abstract class Controller {
	protected IScreenSetter screenSetter;
	@FXML protected SplitPane mainSplitPane;
	public abstract void chooseFunction(ControlButtonType type);

	
	protected boolean checkScreenSetter() {
		boolean b = screenSetter == null;
		if(b)
		System.out.println("Set screen setter");
		return b;
	}
	
	public void setScreenSetter(IScreenSetter screenSetter) {
		this.screenSetter = screenSetter;
	}
	
	public void setDisable(boolean set) {
		mainSplitPane.setDisable(set);
	}

	public void setVisible(boolean set) {
		mainSplitPane.setVisible(set);
	}
}
