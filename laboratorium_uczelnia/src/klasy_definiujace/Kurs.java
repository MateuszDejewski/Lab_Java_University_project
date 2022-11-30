package klasy_definiujace;

public class Kurs {

	private String nazwa;
	private String imie_prowadzacego;
	private String nazwisko_prowadzacego;
	private int ects;
	
	public Kurs(String nazwa, int ects)
	{
		setNazwa(nazwa);
		setEcts(ects);
		setImie_prowadzacego("");
		setNazwisko_prowadzacego("Nieprzydzielono prowadzącego");
	}
	
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
		return nazwa+"  "+nazwisko_prowadzacego+"  "+ects+" ECTS";
	}
	
	public boolean equals(Object o)
	{
		if(!(o instanceof Kurs))
			return false;
		if(((Kurs)o).getNazwa().equals(nazwa) && ((Kurs)o).getEcts()==ects &&((Kurs)o).getImie_prowadzacego().equals(imie_prowadzacego)&&((Kurs)o).getNazwisko_prowadzacego().equals(nazwisko_prowadzacego))
			return true;
		else 
			return false;
	}

}
