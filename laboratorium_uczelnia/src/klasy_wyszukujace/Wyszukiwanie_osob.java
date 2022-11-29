package klasy_wyszukujace;

import java.util.ArrayList;

import klasy_definiujace.*;

public class Wyszukiwanie_osob {
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		ArrayList<Osoba> lista_wynikowa=new ArrayList<Osoba>();
		for(Osoba o:osoby)
		{
			if(o.getImie().equals(imie))
				lista_wynikowa.add(o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Osoba> wyszukaj_osobe_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		ArrayList<Osoba> lista_wynikowa=new ArrayList<Osoba>();
		for(Osoba o:osoby)
		{
			if(o.getNazwisko().equals(nazwisko))
				lista_wynikowa.add(o);
		}
		return lista_wynikowa;
	}
	
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && o.getImie().equals(imie))
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && o.getNazwisko().equals(nazwisko))
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_stanowisku(String stanowisko,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && ((Pracownik_uczelni)o).getStanowisko().equals(stanowisko))
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_stażu(int staż_od,int staż_do ,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && ((Pracownik_uczelni)o).getStaz_pracy()>=staż_od && ((Pracownik_uczelni)o).getStaz_pracy()<=staż_do)
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_nadgodzinach(int nadgodziny_od,int nadgodziny_do ,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
	
			if(o instanceof Pracownik_administracyjny && ((Pracownik_administracyjny)o).getLiczba_nadgodzin()>=nadgodziny_od && ((Pracownik_administracyjny)o).getLiczba_nadgodzin()<=nadgodziny_do)
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Pracownik_uczelni> wyszukaj_pracownika_po_pensji(double pensja_od,double pensja_do ,ArrayList<Osoba> osoby)
	{
		ArrayList<Pracownik_uczelni> lista_wynikowa=new ArrayList<Pracownik_uczelni>();
		for(Osoba o:osoby)
		{
			if(o instanceof Pracownik_uczelni && ((Pracownik_uczelni)o).getPensja()>=pensja_od && ((Pracownik_uczelni)o).getPensja()<=pensja_do)
				lista_wynikowa.add((Pracownik_uczelni)o);
		}
		return lista_wynikowa;
	}

	
	public static ArrayList<Student> wyszukaj_studenta_po_imieniu(String imie,ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && o.getImie().equals(imie))
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Student> wyszukaj_studenta_po_nazwisku(String nazwisko,ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && o.getNazwisko().equals(nazwisko))
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}
	
	public static ArrayList<Student> wyszukaj_studenta_po_indeksie(String indeks,ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && o.getImie().equals(indeks))
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
	}

	public static ArrayList<Student> wyszukaj_studenta_po_roku(int rok, ArrayList<Osoba> osoby)
	{
		ArrayList<Student> lista_wynikowa=new ArrayList<Student>();
		for(Osoba o:osoby)
		{
			if(o instanceof Student && ((Student) o).getRok_studiow()==rok)
				lista_wynikowa.add((Student)o);
		}
		return lista_wynikowa;
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
	

}
