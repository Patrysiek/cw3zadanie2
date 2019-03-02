package model;

public class Contact {

	private String number,name;
	
	public Contact() {
		this("","");
	}
	public Contact(String number,String name) {
		this.name = name;
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name+" "+number; 
	}
}
