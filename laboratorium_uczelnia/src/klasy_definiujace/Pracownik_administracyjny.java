package klasy_definiujace;

public class Pracownik_administracyjny extends Pracownik_uczelni {

	private static final long serialVersionUID = -2235723450099558515L;
	public static String[] mozliwe_stanowiska= {"Referent", "Specjalista", "Starszy_Specjalista"};
	private int liczba_nadgodzin;
	
	public Pracownik_administracyjny(String imie,String nazwisko,String pesel,int wiek,char plec,String stanowisko,int staz_pracy,double pensja,int liczba_nadgodzin) {	
		super(imie, nazwisko, pesel, wiek, plec);
		setLiczba_nadgodzin(liczba_nadgodzin);
		Pracownik_uczelni.mozliwe_stanowiska=Pracownik_administracyjny.mozliwe_stanowiska;
		setStanowisko(stanowisko);
		setStaz_pracy(staz_pracy);
		setPensja(pensja);
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
