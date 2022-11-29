package klasy_definiujace;

public class Kurs {

	private String nazwa;
	private String imie_prowadzacego;
	private String nazwisko_prowadzacego;
	private int ects;
	
	public Kurs(String nazwa,String imie_prowadzacego,String nazwisko_prowadzacego,int ects) {
		setNazwa(nazwa);
		setImie_prowadzacego(imie_prowadzacego);
		setNazwisko_prowadzacego(nazwisko_prowadzacego);
		setEcts(ects);
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getImie_prowadzacego() {
		return imie_prowadzacego;
	}

	public void setImie_prowadzacego(String imie_prowadzacego) {
		this.imie_prowadzacego = imie_prowadzacego;
	}

	public String getNazwisko_prowadzacego() {
		return nazwisko_prowadzacego;
	}

	public void setNazwisko_prowadzacego(String nazwisko_prowadzacego) {
		this.nazwisko_prowadzacego = nazwisko_prowadzacego;
	}

	public int getEcts() {
		return ects;
	}

	public void setEcts(int ects) {
		this.ects = ects;
	}
	
	public String toString()
	{
		return nazwa+"   "+ects+" ECTS";
	}

}
