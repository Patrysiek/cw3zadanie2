package controller.keyboard;

import javafx.scene.control.Button;

public class CustomButton extends Button{

	private char number;
	private String letters;
	
	
	public void setNumber(char number) {
		this.number = number;
	}
	public char getNumber() {
		return number;
	}
	public String getLetters() {
		return letters;
	}
	public void setLetters(String letters) {
		this.letters = letters;
	}

	
	
}
