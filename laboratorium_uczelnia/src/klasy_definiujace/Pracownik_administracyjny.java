package klasy_definiujace;

public class Pracownik_administracyjny extends Pracownik_uczelni {

	public static String[] mozliwe_stanowiska= {"Referent", "Specjalista", "Starszy Specjalista"};
	private int liczba_nadgodzin;
	
	public Pracownik_administracyjny(String imie,String nazwisko,String pesel,int wiek,char plec,String stanowisko,int staz_pracy,double pensja,int liczba_nadgodzin) {
		super(imie, nazwisko, pesel, wiek, plec, stanowisko, staz_pracy, pensja);
		setLiczba_nadgodzin(liczba_nadgodzin);
	}
	
	public int getLiczba_nadgodzin() {
		return liczba_nadgodzin;
	}
	public void setLiczba_nadgodzin(int liczba_nadgodzin) {
		if(liczba_nadgodzin>0)
			this.liczba_nadgodzin = liczba_nadgodzin;
	}

	public String toString()
	{
		return "Pracownik administracyjny  "+super.toString()+liczba_nadgodzin+" nadgodzin";
	}
}
