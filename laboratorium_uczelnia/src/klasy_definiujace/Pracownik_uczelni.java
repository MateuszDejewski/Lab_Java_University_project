package klasy_definiujace;

public abstract class Pracownik_uczelni extends Osoba{

	private String stanowisko;
	private int staz_pracy;
	private double pensja;
	
	public Pracownik_uczelni(String imie,String nazwisko,String pesel,int wiek,char plec,String stanowisko,int staz_pracy,double pensja) {
		super(imie, nazwisko, pesel, wiek, plec);
		this.stanowisko=stanowisko;
		setStaz_pracy(staz_pracy);
		setPensja(pensja);
	}
	public String getStanowisko() {
		return stanowisko;
	}
	public void setStanowisko(String stanowisko) {
		this.stanowisko = stanowisko;
	}
	public int getStaz_pracy() {
		return staz_pracy;
	}
	public void setStaz_pracy(int staz_pracy) {
		if(staz_pracy>0)
			this.staz_pracy = staz_pracy;
	}
	public double getPensja() {
		return pensja;
	}
	public void setPensja(double pensja) {
		pensja=Math.round(pensja*100)/100;
		if(pensja>0)
			this.pensja = pensja;
	}
	
	public String toString()
	{
		return super.toString()+" "+stanowisko+" Staż "+staz_pracy+" lat "+pensja+" zł ";
	}

}
