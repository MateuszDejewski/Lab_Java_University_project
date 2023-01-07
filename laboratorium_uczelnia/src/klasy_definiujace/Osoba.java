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
		setImie(imie);
		setNazwisko(nazwisko);
		setPesel(pesel);
		setWiek(wiek);
		setPlec(plec);
	}

	public int getWiek() {
		return wiek;
	}

	public void setWiek(int wiek) throws IllegalArgumentException {
		if(wiek>0)
			this.wiek = wiek;
		else 
			throw new IllegalArgumentException("wiek");
		
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) throws IllegalArgumentException{
		if(pesel.length()==11)
			this.pesel = pesel;
		else {
			throw new IllegalArgumentException("pesel");
		}
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

	public void setPlec(char plec) throws IllegalArgumentException{
		if(plec!='k'&&plec!='K'&&plec!='m'&&plec!='M')
			throw new IllegalArgumentException("plec");
		if(plec=='k' || plec=='K')
			this.plec = 'K';
		if(plec=='m'|| plec=='M')
			this.plec='M';
		
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
