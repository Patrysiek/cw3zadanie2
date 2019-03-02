package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.concurrent.Task;

public class HourRefresher extends Task<String> {

	private String pattern = "MM-dd-yyyy HH:mm";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	@Override
	protected String call() throws Exception {
		
		
		while(!isCancelled()) {
			updateMessage(simpleDateFormat.format(new Date(System.currentTimeMillis())));
			Thread.sleep(6000);
		}
		return simpleDateFormat.format(new Date(System.currentTimeMillis()));
	}

} 
