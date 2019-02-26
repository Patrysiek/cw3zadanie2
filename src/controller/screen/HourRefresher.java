package controller.screen;

import java.util.Date;

import javafx.concurrent.Task;

public class HourRefresher extends Task<String> {

	@Override
	protected String call() throws Exception {
		
		
		while(!isCancelled()) {
			Thread.sleep(1000);
			updateMessage(new Date(System.currentTimeMillis()).toString());
		}
		return new Date(System.currentTimeMillis()).toString();
	}

} 
