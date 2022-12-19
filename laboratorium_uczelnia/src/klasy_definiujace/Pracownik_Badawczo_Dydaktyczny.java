package klasy_definiujace;

public class Pracownik_Badawczo_Dydaktyczny extends Pracownik_uczelni {
	
	private static final long serialVersionUID = -8699237165247498303L;
	public static String[] mozliwe_stanowiska ={ "Asystent", "Adiunkt", "Profesor_Nadzwyczajny", "Profesor_Zwyczajny", "Wykładowca" };
	private int liczba_publikacji;
	
	public Pracownik_Badawczo_Dydaktyczny(String imie,String nazwisko,String pesel,int wiek,char plec,String stanowisko,int staz_pracy,double pensja,int liczba_publikacji) {
	
			super(imie, nazwisko, pesel, wiek, plec);
			setLiczba_publikacji(liczba_publikacji);
			Pracownik_uczelni.mozliwe_stanowiska=Pracownik_Badawczo_Dydaktyczny.mozliwe_stanowiska;
			setStanowisko(stanowisko);
			setStaz_pracy(staz_pracy);
			setPensja(pensja);
	}
	
	public int getLiczba_publikacji() {
		return liczba_publikacji;
	}
	public void setLiczba_publikacji(int liczba_publikacji) {
		if(liczba_publikacji>=0)
			this.liczba_publikacji = liczba_publikacji;
	}
	
	public String toString()
	{
		return "Pracownik badawczo-dydaktyczny:  "+super.toString()+liczba_publikacji+" publikacji naukowych";
	}

	
}
