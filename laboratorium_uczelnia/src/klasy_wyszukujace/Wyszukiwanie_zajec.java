package klasy_wyszukujace;

import java.util.ArrayList;

import klasy_definiujace.*;

public class Wyszukiwanie_zajec {

	public static boolean warunki_kurs(String nazwa,String imie_prowadzącego,String nazwisko_prowadzacego,int ects_od,int ects_do, Kurs kurs )
	{
		if(nazwa!=null && !kurs.getNazwa().equals(nazwa))
			return false;
		if(imie_prowadzącego!=null && !kurs.getImie_prowadzacego().equals(imie_prowadzącego))
			return false;
		if(nazwisko_prowadzacego!=null && !kurs.getNazwisko_prowadzacego().equals(nazwisko_prowadzacego))
			return false;
		if( (ects_od!=-1 && kurs.getEcts()<ects_od) || (ects_do!=-1 && kurs.getEcts()>ects_do) )
			return false;
	
		return true;
	}
	
	public static ArrayList<Kurs> wyszukaj_kurs_po_parametrach (String nazwa,String imie_prowadzącego,String nazwisko_prowadzacego,int ects_od,int ects_do,ArrayList<Kurs> kursy)
	{
		ArrayList<Kurs> lista_wynikowa=new ArrayList<Kurs>();
		for(Kurs k:kursy)
		{
			if(warunki_kurs(nazwa, imie_prowadzącego, nazwisko_prowadzacego, ects_od, ects_do, k))
				lista_wynikowa.add(k);
		}
		return lista_wynikowa;
	}
	
	
	public static ArrayList<Kurs> wyszukaj_kursy_po_nazwie(String nazwa, ArrayList<Kurs> wszystkie_kursy)
	{
		return wyszukaj_kurs_po_parametrach(nazwa, null, null, -1, -1, wszystkie_kursy);
	}
	
	public static ArrayList<Kurs> wyszukaj_kursy_po_prowadzacym(String imie_prowadzacego,String nazwisko_prowadzacego, ArrayList<Kurs> wszystkie_kursy)
	{
		return wyszukaj_kurs_po_parametrach(null, imie_prowadzacego, nazwisko_prowadzacego, -1, -1, wszystkie_kursy);
	}
	
	public static ArrayList<Kurs> wyszukaj_kursy_po_ECTS(int ects_od,int ects_do ,ArrayList<Kurs> wszystkie_kursy)
	{
		return wyszukaj_kurs_po_parametrach(null, null, null, ects_od, ects_do, wszystkie_kursy);
	}

	public static void wypisz_kursy(ArrayList<Kurs> kursy)
	{
			kursy.forEach((n)->System.out.println(n.toString()));	
	}
}
