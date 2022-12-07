package klasy_wyszukujace;

import java.util.ArrayList;

import klasy_definiujace.*;

public class Wyszukiwanie_osob {
	
	public static boolean warunki_osoba(String imie,String nazwisko,String pesel,int wiek_od,int wiek_do,char Plec, Osoba osoba )
	{
		if(imie!=null && !osoba.getImie().equals(imie))
			return false;
		if(nazwisko!=null && !osoba.getNazwisko().equals(nazwisko))
			return false;
		if(pesel!=null && !osoba.getPesel().equals(pesel))
			return false;
		if( (wiek_od!=-1 && osoba.getWiek()<wiek_od) || (wiek_do!=-1 && osoba.getWiek()>wiek_do) )
			return false;
		if(Plec!='-' && !(osoba.getPlec()==Plec))
			return false;
		return true;
	}
	
	public static  ArrayList<Osoba> wyszukaj_osobe_po_parametrach(String imie,String nazwisko,String pesel,int wiek_od,int wiek_do,char Plec,ArrayList<Osoba> osoby)
	{
		ArrayList<Osoba> lista_wynikowa=new ArrayList<Osoba>();
		for(Osoba o:osoby)
		{
			if(warunki_osoba(imie, nazwisko, pesel, wiek_od,wiek_do, Plec, o))
				lista_wynikowa.add(o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		return wyszukaj_osobe_po_parametrach(imie, null, null,-1, -1, '-', osoby);
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		return wyszukaj_osobe_po_parametrach(null, nazwisko, null, -1,-1, '-', osoby);
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_pesel(String pesel,ArrayList<Osoba> osoby)
	{
		return wyszukaj_osobe_po_parametrach(null, null, pesel, -1,-1, '-', osoby);
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_wieku(int wiek_od,int wiek_do,ArrayList<Osoba> osoby)
	{
		return wyszukaj_osobe_po_parametrach(null, null, null, wiek_od,wiek_do, '-', osoby);
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_plci(char plec,ArrayList<Osoba> osoby)
	{
		return wyszukaj_osobe_po_parametrach(null, null, null, -1,-1, plec, osoby);
	}
	
	
	public static boolean warunki_pracownik_uczelni(String imie,String nazwisko,String stanowsiko,int staż_od,int staż_do ,int nadgodziny_od,int nadgodziny_do,double pensja_od,double pensja_do,Pracownik_uczelni osoba )
	{
		if(imie!=null && !osoba.getImie().equals(imie))
			return false;
		if(nazwisko!=null && !osoba.getNazwisko().equals(nazwisko))
			return false;
		if(stanowsiko!=null && !osoba.getStanowisko().equals(stanowsiko))
			return false;
		if( (staż_od!=-1 && osoba.getStaz_pracy()<staż_od) || (staż_do!=-1 && osoba.getStaz_pracy()>staż_do) )
			return false;
		if(osoba instanceof Pracownik_administracyjny && ( (nadgodziny_od!=-1 && ((Pracownik_administracyjny)osoba).getLiczba_nadgodzin()<nadgodziny_od) || (nadgodziny_do!=-1 && ((Pracownik_administracyjny)osoba).getLiczba_nadgodzin()>nadgodziny_do) ) )
			return false;
		if((nadgodziny_do!=-1 || nadgodziny_od!=-1) && !(osoba instanceof Pracownik_administracyjny))
			return false;
		if( (pensja_od!=-1 && osoba.getPensja()<pensja_od) || (pensja_do!=-1 && osoba.getPensja()>pensja_do) )
			return false;
		return true;
	}
	
	public static  ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_parametrach(String imie,String nazwisko,String stanowsiko,int staż_od,int staż_do ,int nadgodziny_od,int nadgodziny_do,double pensja_od,double pensja_do ,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && warunki_pracownik_uczelni(imie, nazwisko, stanowsiko, staż_od, staż_do, nadgodziny_od, nadgodziny_do, pensja_od, pensja_do, ((Pracownik_uczelni)o)))
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(imie, null, null, -1, -1, -1, -1, -1, -1, osoby);
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(null, nazwisko, null, -1, -1, -1, -1, -1, -1, osoby);
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_stanowisku(String stanowisko,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(null, null, stanowisko, -1, -1, -1, -1, -1, -1, osoby);
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_stażu(int staż_od,int staż_do ,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(null, null, null, staż_od, staż_do, -1, -1, -1, -1, osoby);
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_nadgodzinach(int nadgodziny_od,int nadgodziny_do ,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(null, null, null, -1, -1, nadgodziny_od, nadgodziny_do, -1, -1, osoby);
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_pensji(double pensja_od,double pensja_do ,ArrayList<Osoba> osoby)
	{
		return wyszukaj_pracownika_po_parametrach(null, null, null, -1, -1, -1, -1, pensja_od, pensja_do, osoby);
	}


	public static boolean warunki_student(String imie,String nazwisko,String nr_indeksu,int rok, Student osoba )
	{
		if(imie!=null && !osoba.getImie().equals(imie))
			return false;
		if(nazwisko!=null && !osoba.getNazwisko().equals(nazwisko))
			return false;
		if(nr_indeksu!=null && !osoba.getNumer_indeksu().equals(nr_indeksu))
			return false;
		if(rok!=-1 && osoba.getRok_studiow()!=rok)
			return false;
		
		return true;
	}
	
	public static  ArrayList<Student> wyszukaj_studenta_po_parametrach(String imie,String nazwisko,String nr_indeksu,int rok,ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && warunki_student(imie, nazwisko, nr_indeksu, rok, ((Student)o)))
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Student> wyszukaj_studenta_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		return wyszukaj_studenta_po_parametrach(imie, null, null, -1, osoby);
	}
	
	public static ArrayList<Student> wyszukaj_studenta_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		return wyszukaj_studenta_po_parametrach(null, nazwisko, null, -1, osoby);
	}
	
	public static ArrayList<Student> wyszukaj_studenta_po_indeksie(String indeks,ArrayList<Osoba> osoby)
	{
		return wyszukaj_studenta_po_parametrach(null, null, indeks, -1, osoby);
	}

	public static ArrayList<Student> wyszukaj_studenta_po_roku(int rok, ArrayList<Osoba> osoby)
	{
		return wyszukaj_studenta_po_parametrach(null, null, null, rok, osoby);
	}

	public static ArrayList<Student> wyszukaj_studenta_po_kursie(Kurs kurs,ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && ((Student) o).getKursy().contains(kurs))
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}
	
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_wszystkich_pracownikow(ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni)
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_Badawczo_Dydaktyczny> wyszukaj_wszystkich_pracownikow_badawczo_dydaktycznych(ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_Badawczo_Dydaktyczny> lista_wynikowa=new ArrayList<Pracownik_Badawczo_Dydaktyczny>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_Badawczo_Dydaktyczny)
				lista_wynikowa.add((Pracownik_Badawczo_Dydaktyczny)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_administracyjny> wyszukaj_wszystkich_pracownikow_administacyjnych(ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_administracyjny> lista_wynikowa=new ArrayList<Pracownik_administracyjny>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_administracyjny)
				lista_wynikowa.add((Pracownik_administracyjny)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Student> wyszukaj_wszystkich_studentow(ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student)
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}
	
	
	public static void wypisz_osoby(ArrayList<Osoba> osoby)
	{
		osoby.forEach((n)->System.out.println(n.toString()));
		if(osoby.isEmpty())
			System.out.println("Brak rekordów spełniających kryteria");
	}
	
	public static void wypisz_pracownikow(ArrayList<Pracownik_uczelni> osoby)
	{
		osoby.forEach((n)->System.out.println(n.toString()));
		if(osoby.isEmpty())
			System.out.println("Brak rekordów spełniających kryteria");
	}
	
	public static void wypisz_pracownikow_administracyjnych(ArrayList<Pracownik_administracyjny> osoby)
	{
		osoby.forEach((n)->System.out.println(n.toString()));
		if(osoby.isEmpty())
			System.out.println("Brak rekordów spełniających kryteria");
	}
	
	public static void wypisz_pracownikow_badawczo_dydaktycznych(ArrayList<Pracownik_Badawczo_Dydaktyczny> osoby)
	{
		osoby.forEach((n)->System.out.println(n.toString()));
		if(osoby.isEmpty())
			System.out.println("Brak rekordów spełniających kryteria");
	}
	
	public static void wypisz_studentow(ArrayList<Student> osoby)
	{
		osoby.forEach((n)->System.out.println(n.toString()));
		if(osoby.isEmpty())
			System.out.println("Brak rekordów spełniających kryteria");
	}
	
	
}
