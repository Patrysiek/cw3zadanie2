package controller.keyboard;

import javafx.scene.control.Button;

public class ControlButton extends Button{
	private ControlButtonType type;
	
	public void setType(ControlButtonType type) {
		this.type = type;
	}
	public ControlButtonType getType() {
		return type;
	}
	
}
