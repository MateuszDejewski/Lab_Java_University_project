package klasy_definiujace;

import java.io.Serializable;

public abstract class Osoba implements Serializable{

	private static final long serialVersionUID = -4557361733872384258L;
	private String imie;
	private String nazwisko;
	private String pesel;
	private int wiek;
	private char plec;
	
	public Osoba(String imie,String nazwisko,String pesel,int wiek,char plec)
	{
		this.setImie(imie);
		this.setNazwisko(nazwisko);
		this.setPesel(pesel);
		setWiek(wiek);
		this.setPlec(plec);
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) {
		if(wiek>0)
			this.wiek = wiek;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public char getPlec() {
		return plec;
	}

	public void setPlec(char plec) {
		this.plec = plec;
	}
	
	public boolean equals (Object o)
	{
		if(o instanceof Osoba && ((Osoba)o).getPesel()==pesel)
			return true;
		else {
			return false;
		}
	}
	
	public String toString()
	{
		return imie+" "+nazwisko+" "+pesel+" "+plec+" "+wiek+" lat";
	}
	
}
