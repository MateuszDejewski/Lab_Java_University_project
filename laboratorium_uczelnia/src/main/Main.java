package main;

import java.util.ArrayList;

import klasy_definiujace.*;
import klasy_wyszukujace.*;

public class Main {
	
	private ArrayList<Osoba> ludzie;
	private ArrayList<Kurs> wszystkie_kursy;
	
	public Main() {
		setLudzie(new ArrayList<Osoba>());
		setWszystkie_kursy(new ArrayList<Kurs>());
	}
	
	public ArrayList<Osoba> getLudzie() {
		return ludzie;
	}

	public void setLudzie(ArrayList<Osoba> ludzie) {
		this.ludzie = ludzie;
	}
	
	public ArrayList<Kurs> getWszystkie_kursy() {
		return wszystkie_kursy;
	}

	public void setWszystkie_kursy(ArrayList<Kurs> wszystkie_kursy) {
		this.wszystkie_kursy = wszystkie_kursy;
	}
	
	public void dodaj_osobe(Osoba o)
	{
		getLudzie().add(o);
	}
	
	public void dodaj_kurs(Kurs k)
	{
		getWszystkie_kursy().add(k);
	}
	
	public static void main(String[] args) {
		
		Main test= new Main();
		Random_objects generator_danych=new Random_objects();
		for(int i=0;i<3;i++)
		{
			test.dodaj_osobe(generator_danych.pracownik_administracyjny());
			test.dodaj_osobe(generator_danych.pracownik_badawczo_dydaktyczny());
			test.dodaj_osobe(generator_danych.student());
			for(int j=0;j<3;j++) 
			{
				Kurs kurs=generator_danych.kurs();
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
	
	}
}
