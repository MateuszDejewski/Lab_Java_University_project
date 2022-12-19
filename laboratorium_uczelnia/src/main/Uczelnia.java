package main;

import java.io.Serializable;
import java.util.ArrayList;


import klasy_definiujace.*;
import obserwator.Observable;
import obserwator.Observer;

public class Uczelnia implements Serializable, Observable  {
	
	private static final long serialVersionUID = -5635913568749821695L;

	transient private ArrayList<Observer> obserwatorzy;
	
	private ArrayList<Osoba> ludzie;
	private ArrayList<Kurs> wszystkie_kursy;
	private int liczba_sal=1;
	
	public Uczelnia() {
		setLudzie(new ArrayList<Osoba>());
		setWszystkie_kursy(new ArrayList<Kurs>());
		setObserwatorzy(new ArrayList<Observer>());
	}
	
	public Uczelnia(int liczba_sal)
	{
		setLudzie(new ArrayList<Osoba>());
		setWszystkie_kursy(new ArrayList<Kurs>());
		setLiczba_sal(liczba_sal);
		setObserwatorzy(new ArrayList<Observer>());
	}
	
	public int getLiczba_sal() {
		return liczba_sal;
	}

	public void setLiczba_sal(int liczba_sal) {
		if(liczba_sal>0)
			{
			this.liczba_sal = liczba_sal;
			notifyObservers();
			}
		
	}

	public ArrayList<Observer> getObserwatorzy() {
		return obserwatorzy;
	}

	public void setObserwatorzy(ArrayList<Observer> obserwatorzy) {
		this.obserwatorzy = obserwatorzy;
	}

	public ArrayList<Osoba> getLudzie() {
		return ludzie;
	}

	public void setLudzie(ArrayList<Osoba> ludzie) {
		this.ludzie = ludzie;
		notifyObservers();
	}
	
	public ArrayList<Kurs> getWszystkie_kursy() {
		return wszystkie_kursy;
	}

	public void setWszystkie_kursy(ArrayList<Kurs> wszystkie_kursy) {
		this.wszystkie_kursy = wszystkie_kursy;
		notifyObservers();
	}
	
	public void dodaj_osobe(Osoba o)
	{
		ludzie.add(o);
		notifyObservers();
	}
	
	public void usun_osobe(Osoba o)
	{
		ludzie.remove(o);
		notifyObservers();
	}
	
	public void usun_osoby(ArrayList<?> o)
	{
			ludzie.removeAll(o);
			notifyObservers();
	}
	
	public void dodaj_kurs(Kurs k)
	{
		wszystkie_kursy.add(k);
		notifyObservers();
	}
	
	public void usun_kurs(Kurs k)
	{
		wszystkie_kursy.remove(k);
		notifyObservers();
	}
	
	public void usun_kursy(ArrayList<Kurs> k)
	{
		wszystkie_kursy.removeAll(k);
		notifyObservers();
	}
	
	public static void main(String[] args) {
		
		Uczelnia test= new Uczelnia(3);
		Komunikacja_z_uzytkownikiem komunikator=new Komunikacja_z_uzytkownikiem(test);
		komunikator.menu();
		
		/*
		//Test funkcjonalności zaliczenie listy 5
		
		
		for(int i=0;i<7;i++)
		{
			test.dodaj_osobe(Random_objects.pracownik_administracyjny());
			test.dodaj_osobe(Random_objects.pracownik_badawczo_dydaktyczny());
			test.dodaj_osobe(Random_objects.student());
			for(int j=0;j<6;j++) 
			{
				Kurs kurs=Random_objects.kurs();
				((Student)test.getLudzie().get(test.getLudzie().size()-1)).add_kurs(kurs);
				test.getWszystkie_kursy().add(kurs);
			}
		}
		
		
		
		
		
		System.out.println("Wszystkie osoby: ");
		Wyszukiwanie_osob.wypisz_osoby(test.getLudzie());
		
		System.out.println("\n\nWyniki wyszukiwania dla nazwiska Żelwetro: ");
		Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_nazwisku("Żelwetro", test.getLudzie()));
		
		System.out.println("\n\nWyniki wyszukiwania procowników z conajmniej 10 letnim stażem: ");
		Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_stażu(10, 100, test.getLudzie()));
		
		System.out.println("\n\nWyniki wyszukiwania procowników z pensją z przedziału 5000 - 8000zł : ");
		Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_pensji(5000, 8000, test.getLudzie()));
		
		System.out.println("\n\nWyniki wyszukiwania studentów 1 roku: ");
		Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_roku(1, test.getLudzie()));
		
		System.out.printf("\n\nWyniki wyszukiwania studentów zapisanych na %s: \n",test.getWszystkie_kursy().get(0).toString());
		Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_kursie(test.getWszystkie_kursy().get(0), test.getLudzie()));
	
		System.out.println("\n\nWyniki wyszukiwania kursów za 4-6 ECTS: ");
		Wyszukiwanie_zajec.wypisz_kursy(Wyszukiwanie_zajec.wyszukaj_kursy_po_ECTS(4, 6, test.getWszystkie_kursy()));
		*/
	}


	@Override
	public void notifyObservers() {
		if (getObserwatorzy()!=null && !getObserwatorzy().isEmpty()) {
			getObserwatorzy().forEach((n)-> n.update());
		}
		
	}

	@Override
	public void addObserver(Observer o) {
		getObserwatorzy().add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		getObserwatorzy().remove(o);
		
	}
}
