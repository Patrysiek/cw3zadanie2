package Model;

public class User {
	private String nazwa;
	private String numer;

	public User() {
		this("", "0");
	}
	
	public User(String nazwa, String numer) {
		setNumer(numer);
		setNazwa(nazwa);
	}

	public String getNazwa() {
		return nazwa;
	}

	public String getNumer() {
		return numer;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public boolean setNumer(String numer) {
		if (!numer.matches("/^[0-9]*$/"))
			return false;
		this.numer = numer;
		return true;
	}
}
