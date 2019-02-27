package controller.screen;

import controller.keyboard.ControlButtonType;


public class ContactsController extends Controller{


	public void chooseFunction(ControlButtonType type) {
		switch (type) {
		case RED_PHONE:

			if (checkScreenSetter())
				break;
			screenSetter.setScreen(ScreenMode.WALLPAPER);
			break;
		case DOWN_ARROW:
			break;
		case GREEN_PHONE:
			break;
		case LEFT_CONTROL:
			if (checkScreenSetter())
				break;
			screenSetter.setScreen(ScreenMode.ADD_CONTACTS);
			break;
		case L_ARROW:
			break;
		case OK:
			break;
		case RIGHT_CONTROL:
			break;
		case R_ARROW:
			break;
		case UP_ARROW:
			break;
		default:
			break;

		}
	}

}
