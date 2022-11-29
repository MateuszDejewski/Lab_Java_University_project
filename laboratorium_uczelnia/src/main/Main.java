package main;

import java.util.ArrayList;

import klasy_definiujace.*;
import klasy_wyszukujace.*;

public class Main {


	public static void main(String[] args) {
		
		ArrayList<Osoba> ludzie=new ArrayList<Osoba>();
		ArrayList<Kurs> wszystkie_kursy=new ArrayList<Kurs>();
		Random_objects generator_danych=new Random_objects();
		for(int i=0;i<3;i++)
		{
			ludzie.add(generator_danych.pracownik_administracyjny());
			ludzie.add(generator_danych.pracownik_badawczo_dydaktyczny());
			ludzie.add(generator_danych.student());
			for(int j=0;j<3;j++) 
			{
				Kurs kurs=generator_danych.kurs();
				((Student)ludzie.get(ludzie.size()-1)).add_kurs(kurs);
				wszystkie_kursy.add(kurs);
			}
		}
		System.out.println("Wszystkie osoby: ");
		ludzie.forEach((n) -> System.out.printf("\n%s\n",n.toString()));
		
		System.out.println("\n\nWyniki wyszukiwania dla nazwiska Żelwetro: ");
		Wyszukiwanie_osob.wyszukaj_osobe_po_nazwisku("Żelwetro", ludzie).forEach((n)-> System.out.printf("\n%s\n",n.toString()));
		
		System.out.println("\n\nWyniki wyszukiwania procowników z conajmniej 10 letnim stażem: ");
		Wyszukiwanie_osob.wyszukaj_pracownika_po_stażu(10, 100, ludzie).forEach((n) -> System.out.println(n.toString()));
		
		System.out.println("\n\nWyniki wyszukiwania procowników z pensją z przedziału 5000 - 8000zł : ");
		Wyszukiwanie_osob.wyszukaj_pracownika_po_pensji(5000, 8000, ludzie).forEach((n) -> System.out.println(n.toString()));
		
		System.out.println("\n\nWyniki wyszukiwania studentów 1 roku: ");
		Wyszukiwanie_osob.wyszukaj_studenta_po_roku(1, ludzie).forEach((n) ->System.out.println(n.toString()));
		
		System.out.printf("\n\nWyniki wyszukiwania studentów zapisanych na %s: \n",wszystkie_kursy.get(0).getNazwa());
		Wyszukiwanie_osob.wyszukaj_studenta_po_kursie(wszystkie_kursy.get(0), ludzie).forEach((n) ->System.out.println(n.toString()));
		
		System.out.println("\n\nWyniki wyszukiwania kursów za 4-6 ECTS: ");
		Wyszukiwanie_zajec.wyszukaj_kursy_po_ECTS(4, 6, wszystkie_kursy).forEach((n) ->System.out.println(n.toString()));
		
	}
}
