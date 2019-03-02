package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyStream {

	private String fileName;
	private Properties properties;

	public PropertyStream(String fileName, Properties properties) {
		this.properties = properties;
		this.fileName = fileName;
	}

	public PropertyStream(String fileName) {
		this(fileName, new Properties());
	}

	public void saveData(String key, String data) throws Exception {
		if (key == null) {
			System.out.println("key is null");
			return;
		}
		FileOutputStream in = new FileOutputStream(fileName, true);
		properties.putIfAbsent(key, data);
		properties.store(in,null);
		in.close();
	}

	public Properties loadData() {
		try {
			FileInputStream in = new FileInputStream(fileName);
			properties.load(in);
			in.close();
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deleteData(String key) {
		properties.remove(key);
		try {
			properties.store(new FileOutputStream(fileName, false), null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
