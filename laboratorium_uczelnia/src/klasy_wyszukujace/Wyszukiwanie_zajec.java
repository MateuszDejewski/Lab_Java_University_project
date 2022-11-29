package klasy_wyszukujace;

import java.util.ArrayList;

import klasy_definiujace.*;

public class Wyszukiwanie_zajec {

	public static ArrayList<Kurs> wyszukaj_kursy_po_nazwie(String nazwa, ArrayList<Kurs> wszystkie_kursy)
	{
		ArrayList<Kurs> wynikowa=new ArrayList<Kurs>();
		for(Kurs k:wszystkie_kursy)
		{
			if(k.getNazwa().equals(nazwa))
				wynikowa.add(k);
		}
		return wynikowa;
	}
	
	public static ArrayList<Kurs> wyszukaj_kursy_po_prowadzacym(String imie_prowadzacego,String nazwisko_prowadzacego, ArrayList<Kurs> wszystkie_kursy)
	{
		ArrayList<Kurs> wynikowa=new ArrayList<Kurs>();
		for(Kurs k:wszystkie_kursy)
		{
			if(k.getImie_prowadzacego().equals(imie_prowadzacego)&&k.getNazwisko_prowadzacego().equals(nazwisko_prowadzacego))
				wynikowa.add(k);
		}
		return wynikowa;
	}
	
	public static ArrayList<Kurs> wyszukaj_kursy_po_ECTS(int ects_od,int ects_do ,ArrayList<Kurs> kursy)
	{
		ArrayList<Kurs> lista_wynikowa=new ArrayList<Kurs>();
		for(Kurs k:kursy)
		{
			if(k.getEcts()>=ects_od && k.getEcts()<=ects_do)
				lista_wynikowa.add(k);
		}
		return lista_wynikowa;
	}
	
}
