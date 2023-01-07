package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import klasy_wyszukujace.*;
import klasy_definiujace.*;
import plan_zajec.*;
import sortowanie.*;
import obserwator.*;

public class Komunikacja_z_uzytkownikiem {
	
	Uczelnia uczelnia;
	Scanner wczytaj=new Scanner(System.in);
	Ukladanie_planu ukladanie_planu;
	Comparator<Osoba> sposob_wypisywania_osob;
	Comparator<Kurs> sposob_wypisywania_kursow;
	
	public Komunikacja_z_uzytkownikiem(Uczelnia uczelnia) {
		przygotoj_do_dzialania(uczelnia);
		System.out.println("Witaj w systemie zarządzania uczelnią");
	}
	
	private void przygotoj_do_dzialania(Uczelnia uczelnia)
	{
		this.uczelnia=uczelnia;
		this.ukladanie_planu=new Ukladanie_planu(this.uczelnia);
		this.uczelnia.setObserwatorzy(new ArrayList<Observer>());
		this.uczelnia.addObserver(ukladanie_planu);
		this.uczelnia.addObserver(new Korektor_danych(uczelnia));
		this.sposob_wypisywania_osob =new Sort_by_surname();
		this.sposob_wypisywania_kursow=new Sort_by_ECTS_and_surname();
	}
	
	public void menu()
	{
		System.out.println("*****MENU*****\nWybierz akcje:");
		System.out.println(" 0: Zamknij program\n 1: Dodaj dane\n 2: Modyfikuj dane\n 3: Zapisz stan\n 4: Odtwórz stan\n 5: Wyszukaj\n 6: Zarzadzaj planem zajec ");
		
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0:
		{
			System.out.println("Dziękujemy za skorzystanie z systemu. Do zobaczenia!");
			wczytaj.close();
			return;
		}
		case 1: {
			dodaj_dane();
			break;
		}
		case 2: {
			modyfikuj_dane();
			break;
		}
		case 3: {
			serializuj();
			break;
		}
		case 4: {
			deserializuj();
			break;
		}
		case 5:{
			wyszukaj();
			break;
		}
		case 6:{
			plan_zajec();
			break;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			}
		}
		
		menu();
	
	}
	
	
	public void dodaj_dane()
	{
		System.out.println("Wybierz typ danych który chcesz wprowadzić: ");
		System.out.println(" 1: Dodaj kurs\n 2: Dodaj studenta\n 3: Dodaj pracownika administracyjnego\n 4: Dodaj pracownika badawczo-dydaktycznego\n 5: Przypisz kurs studentowi\n 0: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			dodaj_kurs();
			break;
		}
		case 2: {
			dodaj_studenta();
			break;
		}
		case 3: {
			dodaj_pracownika_administracyjnego();
			break;
		}
		case 4: {
			dodaj_pracownika_badawczo_dydaktycznego();
			break;
		}
		case 5:{
			przypisz_kurs();
			break;
		}
		case 0: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
	}
	
	public void dodaj_kurs()
	{
		System.out.println("Dodajesz kurs, aby powrócić do meny wpisz -1");
		System.out.println("Podaj nazwę, imię prowadzącego, nazwisko prowadzącego i liczbę punktów ECTS oddzielone spacjami");
		
		if(wczytaj.hasNextInt())
		{
			if(wczytaj.nextInt()==-1)
				{
				return;
				}
		}
	String nazwa=wczytaj.next(), imie=wczytaj.next(),nazwisko=wczytaj.next();
	int ects=wczytaj.nextInt();
	
	try {
	uczelnia.dodaj_kurs(new Kurs(nazwa,imie,nazwisko,ects));
	System.out.println("Operacja zakończona, powrót do menu");
	}catch (IllegalArgumentException e)
	{
		switch (e.getMessage())	{	
		case "ects": {
			System.out.println("Podaj nieujemną liczbę punktow ECTS");
			ects=wczytaj.nextInt();
			break;
			}
		}		
		try {
		uczelnia.dodaj_kurs(new Kurs(nazwa,imie,nazwisko,ects));
		System.out.println("Operacja zakończona, powrót do menu");
		} catch (IllegalArgumentException e2) {
			System.out.print("Błędne dane, powrot do menu");
		}
		
	}
	}
	
	public void dodaj_studenta()
	{
		System.out.println("Dodajesz studenta, aby powrócić do meny wpisz -1");
		System.out.println("Podaj imie, nazwisko, pesel, wiek, plec , numer indeksu, rok studiow");
		
		if(wczytaj.hasNextInt())
		{
			if(wczytaj.nextInt()==-1)
				{
				return;
				}
		}
		
		String imie=wczytaj.next(),nazwisko=wczytaj.next(),pesel=wczytaj.next();
		int wiek=wczytaj.nextInt();
		char plec=wczytaj.next().charAt(0);
		String numer_indeksu=wczytaj.next();
		int rok=wczytaj.nextInt();
		
		try {
			uczelnia.dodaj_osobe(new Student(imie, nazwisko,pesel,wiek,plec, numer_indeksu,rok));
			System.out.println("Operacja zakończona, powrót do menu");
		}catch (IllegalArgumentException e) {
			
			switch (e.getMessage()) {
			case "pesel": {
				System.out.println("Podaj poprawny pesel");
				pesel=wczytaj.next();
				break;
			}
			case "numer_indeksu":{
				System.out.println("Podaj poprawny numer indeksu");
				numer_indeksu=wczytaj.next();
				break;
			}
			case "plec":{
				System.out.println("Podaj poprawną płeć");
				plec=wczytaj.next().charAt(0);
				break;
			}
			}
			
			try {
			uczelnia.dodaj_osobe(new Student(imie, nazwisko,pesel,wiek,plec, numer_indeksu,rok));
			System.out.println("Operacja zakończona, powrót do menu");
			}catch (IllegalArgumentException ex) {
				System.out.println("Błędne dane");
			}
		}
	}
	
	public void dodaj_pracownika_badawczo_dydaktycznego ()
	{
		System.out.println("Dodajesz pracownika badawczo-dydaktycznego, aby powrócić do meny wpisz -1");
		System.out.println("Podaj imie, nazwisko, pesel, wiek, plec , stanowisko, staż pracy, pensje, liczbe publikacji naukowych");
		
		if(wczytaj.hasNextInt())
		{
			if(wczytaj.nextInt()==-1)
				{
				return;
				}
		}
		
		String imie=wczytaj.next(),nazwisko=wczytaj.next(),pesel=wczytaj.next();
		int wiek=wczytaj.nextInt();
		char plec=wczytaj.next().charAt(0);
		String stanowisko=wczytaj.next();
		int staz=wczytaj.nextInt();
		double pensja=wczytaj.nextDouble();
		int liczba_publikacji=wczytaj.nextInt();
		
		try {
			uczelnia.dodaj_osobe(new Pracownik_Badawczo_Dydaktyczny(imie,nazwisko,pesel,wiek,plec, stanowisko, staz,pensja,liczba_publikacji));
			System.out.println("Operacja zakończona, powrót do menu");
		}catch (IllegalArgumentException e) {
			
			switch (e.getMessage()) {
			case "pesel": {
				System.out.println("Podaj poprawny pesel");
				pesel=wczytaj.next();
				break;
			}
			case "stanowisko":{
				System.out.println("Podaj poprawne stanowisko");
				stanowisko=wczytaj.next();
				break;
			}
			case "plec":{
				System.out.println("Podaj poprawną płeć");
				plec=wczytaj.next().charAt(0);
				break;
			}
			
			}
			try {
			uczelnia.dodaj_osobe(new Pracownik_Badawczo_Dydaktyczny(imie,nazwisko,pesel,wiek,plec, stanowisko, staz,pensja,liczba_publikacji));
			System.out.println("Operacja zakończona, powrót do menu");
			}catch (IllegalArgumentException ex) {
				System.out.println("Błędne dane");
			}
		}
	}
	
	public void dodaj_pracownika_administracyjnego()
	{
		System.out.println("Dodajesz pracownika administracyjnego, aby powrócić do meny wpisz -1");
		System.out.println("Podaj imie, nazwisko, pesel, wiek, plec , stanowisko, staż pracy, pensje, liczbe nadgodzin");
		
		if(wczytaj.hasNextInt())
		{
			if(wczytaj.nextInt()==-1)
				{
				return;
				}
		}	
		
		String imie=wczytaj.next(),nazwisko=wczytaj.next(),pesel=wczytaj.next();
		int wiek=wczytaj.nextInt();
		char plec=wczytaj.next().charAt(0);
		String stanowisko=wczytaj.next();
		int staz=wczytaj.nextInt();
		double pensja=wczytaj.nextDouble();
		int liczba_nadgodzin=wczytaj.nextInt();
		
		try {
		uczelnia.dodaj_osobe(new Pracownik_administracyjny(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, liczba_nadgodzin));
		System.out.println("Operacja zakończona, powrót do menu");
		}catch (IllegalArgumentException e) {
			
			switch (e.getMessage()) {
			case "pesel": {
				System.out.println("Podaj poprawny pesel");
				pesel=wczytaj.next();
				break;
			}
			case "stanowisko":{
				System.out.println("Podaj poprawne stanowisko");
				stanowisko=wczytaj.next();
				break;
			}
			case "plec":{
				System.out.println("Podaj poprawną płeć");
				plec=wczytaj.next().charAt(0);
				break;
			}
		
			}
			try {
			uczelnia.dodaj_osobe(new Pracownik_administracyjny(imie, nazwisko, pesel, wiek, plec, stanowisko, staz, pensja, liczba_nadgodzin));
			System.out.println("Operacja zakończona, powrót do menu");
			}catch (IllegalArgumentException ex) {
				System.out.println("Błędne dane");
				ex.printStackTrace();
			}
		}
	}
	
	public void przypisz_kurs()
	{
		System.out.println("Przypisujesz istniejący kurs istniejącemu studentowi. Aby powrócić do menu wpisz -1\nPodaj numer indeksu studenta oraz nazwę, imie i nazwisko prowadzącego, liczbę punktów ECTS kursu");
		String indexString="";
		int liczba;
		if(wczytaj.hasNextInt())
		{
			if((liczba=wczytaj.nextInt())==-1)
				{
				return;
				}
			else {
				indexString=""+liczba;
			}			
		}
		ArrayList<Student> osoba=Wyszukiwanie_osob.wyszukaj_studenta_po_indeksie(indexString, uczelnia.getLudzie());
		
		if(osoba.isEmpty())
		{
			System.out.println("Nie ma takiego studenta");
		}
		else {
			Student student=osoba.get(0);
			String nazwaString=wczytaj.next(),imieString=wczytaj.next(),nazwiskoString=wczytaj.next();
			int ects=wczytaj.nextInt();
			ArrayList<Kurs> kurs=Wyszukiwanie_zajec.wyszukaj_kurs_po_parametrach(nazwaString,imieString ,nazwiskoString ,ects , ects, uczelnia.getWszystkie_kursy());
			
			if(kurs.isEmpty())
				System.out.println("Nie ma takiego kursu");
			else {
				student.add_kurs(kurs.get(0));
				System.out.println("Przypisano studentowi nowy kurs");
			}
			
		}
	}
	
	

	public void wyszukaj()
	{
		System.out.println("Wybierz typ danych który chcesz wyszukać: ");
		System.out.println(" 1: Wyszukaj osobe\n 2: Wyszukaj kurs\n 3: Wyszukaj studenta\n 4: Wyszukaj pracownika\n 5: Wybierz sposob prezentacji danych\n 0: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			ArrayList<Osoba> wynik=wyszukaj_osobe();
			if(wynik!=null && sposob_wypisywania_osob!=null)	Collections.sort(wynik,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(wynik);
			break;
		}
		case 2: {
			ArrayList<Kurs> wynik=wyszukaj_kurs();
			if(wynik!=null && sposob_wypisywania_kursow!=null) Collections.sort(wynik,sposob_wypisywania_kursow);
			Wyszukiwanie_zajec.wypisz_kursy(wynik);
			break;
		}
		case 3: {
			ArrayList<Student> wynik=wyszukaj_studenta();
			if(wynik!=null && sposob_wypisywania_osob!=null) Collections.sort(wynik,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(wynik);
			break;
		}
		case 4: {
			ArrayList<Pracownik_uczelni> wynik=wyszukaj_pracownika();
			if(wynik!=null && sposob_wypisywania_osob!=null) Collections.sort(wynik,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(wynik);
			break;
		}
		case 5: {
			wybierz_sposob_prezentacji_danych();
		}
		case 0: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
		}
		}
	}
	
	public ArrayList<Osoba> wyszukaj_osobe()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkie osoby\n 1: imie\n 2: nazwisko\n 3: pesel\n 4: wiek (od do)\n 5: płeć\n 6: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszystkie osoby");
			return uczelnia.getLudzie();
			
		}
		case 1: {
			System.out.println("Podaj imię:");
			return Wyszukiwanie_osob.wyszukaj_osobe_po_imieniu(wczytaj.next(), uczelnia.getLudzie());
			
		}
		case 2: {
			System.out.println("Podaj nazwisko:");
			return Wyszukiwanie_osob.wyszukaj_osobe_po_nazwisku(wczytaj.next(), uczelnia.getLudzie());
			
		}
		case 3: {
			System.out.println("Podaj pesel:");
			return Wyszukiwanie_osob.wyszukaj_osobe_po_pesel(wczytaj.next(), uczelnia.getLudzie());
			
		}
		case 4: {
			System.out.println("Podaj zakres wieku w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			return Wyszukiwanie_osob.wyszukaj_osobe_po_wieku(x,y, uczelnia.getLudzie());
			
		}
		case 5: {
			System.out.println("Podaj płeć:");
			return Wyszukiwanie_osob.wyszukaj_osobe_po_plci(((wczytaj.next().equals("kobieta"))?'K':'M'), uczelnia.getLudzie());
			
		}
		case 6:{
			return osoba_parametry();
		}
		case -1: {
			return null;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
			
		}
		}	
	return null;
	}

	private ArrayList<Osoba> osoba_parametry()
	{
		System.out.println("Podaj kryteria (pisane małymi literami) wyszukiwania w formie kryterium/wartość\nnp. wiek_do/40  nazwisko/Kowalski ciąg instrukcji zakończ 0");
		String imieString=null,nazwiskoString=null,peselString=null;
			char plec='-';
			int wiek_od=-1,wiek_do=-1;
		
		while(!wczytaj.hasNextInt())
		{	
			
			String[] tab=wczytaj.next().split("/");
			switch (tab[0]) {
			case "imie": {
				imieString=tab[1];
				break;
			}
			case "nazwisko": {
				nazwiskoString=tab[1];
				break;
			}
			case "pesel": {
				peselString=tab[1];
				break;
			}
			case "płeć": {
				if(tab[1].equals("mężczyzna"))
					plec='M';
				if(tab[1].equals("kobieta"))
					plec='K';
				
				break;
			}
			case "wiek_od": {
				wiek_od=Integer.parseInt(tab[1]);
				break;
			}
			case "wiek_do": {
				wiek_do=Integer.parseInt(tab[1]);
				break;
			}
			default:{
				System.out.println("Błędne dane kryterium");
				break;
			}	
		}
	}
		wczytaj.nextInt();
		return Wyszukiwanie_osob.wyszukaj_osobe_po_parametrach(imieString, nazwiskoString, peselString, wiek_od, wiek_do, plec, uczelnia.getLudzie());
		
	}

	public ArrayList<Kurs> wyszukaj_kurs()
	{
		System.out.println("Wybierz kryterium wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkie kursy\n 1: nazwa\n 2: imie i nazwisko prowadzącego\n 3: punkty ECTS (od do)\n 4: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			return uczelnia.getWszystkie_kursy();
			
		}
		case 1:{
			System.out.println("Podaj nazwę");
			return Wyszukiwanie_zajec.wyszukaj_kursy_po_nazwie(wczytaj.next(), uczelnia.getWszystkie_kursy());
			
		}
		case 2:{
			System.out.println("Podaj imie i nazwisko prowdzącego");
			return Wyszukiwanie_zajec.wyszukaj_kursy_po_prowadzacym(wczytaj.next(),wczytaj.next(), uczelnia.getWszystkie_kursy());
		}
		case 3:{
			System.out.println("Podaj zakres punktów ECTS w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			return Wyszukiwanie_zajec.wyszukaj_kursy_po_ECTS(x, y, uczelnia.getWszystkie_kursy());
		}
		case 4:{
			return kurs_parametry();
		}
		case -1: {
			return null;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
		return null;
	}
	
	private ArrayList<Kurs> kurs_parametry()
	{
		System.out.println("Podaj kryteria (pisane małymi literami) wyszukiwania w formie kryterium/wartość\nnp. ects_do/8  nazwisko_prowadzącego/Kowalski ciąg instrukcji zakończ 0");
		
		String imieString=null,nazwiskoString=null,nazwaString=null;
		int ects_od=-1,ects_do=-1;
		
		while(!wczytaj.hasNextInt())
		{	
			
			String[] tab=wczytaj.next().split("/");
			switch (tab[0]) {
			case "imie_prowadzącego": {
				imieString=tab[1];
				break;
			}
			case "nazwisko_prowadzącego": {
				nazwiskoString=tab[1];
				break;
			}
			case "nazwa": {
				nazwaString=tab[1];
				break;
			}
			case "ects_od": {
				ects_od=Integer.parseInt(tab[1]);
				break;
			}
			case "ects_do": {
				ects_do=Integer.parseInt(tab[1]);
				break;
			}
			default:{
				System.out.println("Błędne dane kryterium");
				break;
			}	
		}
	}
		wczytaj.nextInt();
		return Wyszukiwanie_zajec.wyszukaj_kurs_po_parametrach(nazwaString, imieString, nazwiskoString, ects_od, ects_do, uczelnia.getWszystkie_kursy());
		
	}
	
	public ArrayList<Student> wyszukaj_studenta()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkich studentów\n 1: imie\n 2: nazwisko\n 3: numer indeksu\n 4: rok studiów\n 5: kurs\n 6: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszyscy studenci");
			return Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie());
		}
		case 1: {
			System.out.println("Podaj imię:");
			return Wyszukiwanie_osob.wyszukaj_studenta_po_imieniu(wczytaj.next(), uczelnia.getLudzie());
			
		}
		case 2: {
			System.out.println("Podaj nazwisko:");
			return Wyszukiwanie_osob.wyszukaj_studenta_po_nazwisku(wczytaj.next(), uczelnia.getLudzie());
		}
		case 3: {
			System.out.println("Podaj numer indeksu:");
			return Wyszukiwanie_osob.wyszukaj_studenta_po_indeksie(wczytaj.next(), uczelnia.getLudzie());
		}
		case 4: {
			System.out.println("Podaj rok studiów");
			return Wyszukiwanie_osob.wyszukaj_studenta_po_roku(wczytaj.nextInt(), uczelnia.getLudzie());
		
		}
		case 5: {	
			System.out.println("Podaj nazwę kursu, imię i nazwisko prowadzącego oraz liczbę punktow ects");
			Kurs kurs=new Kurs(wczytaj.next(),wczytaj.next(),wczytaj.next(), wczytaj.nextInt());
			return Wyszukiwanie_osob.wyszukaj_studenta_po_kursie(kurs, uczelnia.getLudzie());
		}
		case 6:{
			return student_parametry();
			
		}
		case -1: {
			return null;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
		return null;
	}
	
	private ArrayList<Student> student_parametry()
	{
		System.out.println("Podaj kryteria (pisane małymi literami) wyszukiwania w formie kryterium/wartość\nnp. rok/2  nazwisko/Kowalski ciąg instrukcji zakończ 0");
		String imieString=null,nazwiskoString=null;
		int rok=-1;
		
		while(!wczytaj.hasNextInt())
		{	
			
			String[] tab=wczytaj.next().split("/");
			switch (tab[0]) {
			case "imie": {
				imieString=tab[1];
				break;
			}
			case "nazwisko": {
				nazwiskoString=tab[1];
				break;
			}
			case "rok": {
				rok=Integer.parseInt(tab[1]);
				break;
			}
			default:{
				System.out.println("Błędne dane kryterium");
				break;
			}	
		}
	}
		wczytaj.nextInt();
		return Wyszukiwanie_osob.wyszukaj_studenta_po_parametrach(imieString, nazwiskoString, null,rok, uczelnia.getLudzie());
		
	}
	
	public ArrayList<Pracownik_uczelni> wyszukaj_pracownika()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkich pracownikow\n 1: znajdz wszystkich pracownikow badawczo-dydaktycznych\n 2: znajdz wszystkich pracownikow administracyjnych\n 3: imie\n 4: nazwisko\n 5: stanowisko\n 6: staż(od do)\n 7: nadgodziny(od do)\n 8: pensja(od do)\n 9: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszyscy pracownicy");
			return Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow(uczelnia.getLudzie());
			
		}
		case 1: {
			System.out.println("Wyszyscy pracownicy badawczo-dydaktyczni");
			ArrayList<?> wynik = (ArrayList<?>) Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_badawczo_dydaktycznych(uczelnia.getLudzie());
			ArrayList<Pracownik_uczelni> pracownicy = (ArrayList<Pracownik_uczelni>) wynik;
			return pracownicy;
		}
		case 2: {
			System.out.println("Wyszyscy pracownicy administracyjni");
			ArrayList<?> wynik = (ArrayList<?>) Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_administacyjnych(uczelnia.getLudzie());
			ArrayList<Pracownik_uczelni> pracownicy = (ArrayList<Pracownik_uczelni>) wynik;
			return pracownicy;
		}
		case 3: {
			System.out.println("Podaj imię:");
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_imieniu(wczytaj.next(), uczelnia.getLudzie());
		}
		case 4: {
			System.out.println("Podaj nazwisko:");
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_nazwisku(wczytaj.next(), uczelnia.getLudzie());
		}
		case 5: {
			System.out.println("Podaj stanowisko:");
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_stanowisku(wczytaj.next(), uczelnia.getLudzie());
		}
		case 6: {
			System.out.println("Podaj zakres stażu w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_stażu(x,y, uczelnia.getLudzie());
		}
		case 7: {
			System.out.println("Podaj zakres nadgodzin w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_nadgodzinach(x,y, uczelnia.getLudzie());
		}
		case 8: {
			System.out.println("Podaj zakres pensji w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			double x=wczytaj.nextDouble();
			wczytaj.next();
			double y=wczytaj.nextDouble();
			return Wyszukiwanie_osob.wyszukaj_pracownika_po_pensji(x,y, uczelnia.getLudzie());
		}
		case 9:{
			return pracownik_parametry();
		}
		case -1: {
			return null;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
		return null;
	}

	private ArrayList<Pracownik_uczelni> pracownik_parametry()
	{
		System.out.println("Podaj kryteria (pisane małymi literami) wyszukiwania w formie kryterium/wartość\nnp. pensja_do/4528,45  nazwisko/Kowalski ciąg instrukcji zakończ 0");
		String imieString=null,nazwiskoString=null,stanowiskoString=null;
		int staz_do=-1,staz_od=-1,nadgodziny_do=-1,nadgodziny_od=-1;
		double pensja_do=-1,pensja_od=-1;
		
		while(!wczytaj.hasNextInt())
		{	
			
			String[] tab=wczytaj.next().split("/");
			switch (tab[0]) {
			case "imie": {
				imieString=tab[1];
				break;
			}
			case "nazwisko": {
				nazwiskoString=tab[1];
				break;
			}
			case "stanowisko": {
				stanowiskoString=tab[1];
				break;
			}
			case "staż_od": {
				staz_od = Integer.parseInt(tab[1]);
				break;
			}
			case "staż_do": {
				staz_do=Integer.parseInt(tab[1]);
				break;
			}
			case "nadgodziny_od": {
				nadgodziny_od=Integer.parseInt(tab[1]);
				break;
			}
			case "nadgodziny_do": {
				nadgodziny_do=Integer.parseInt(tab[1]);
				break;
			}
			case "pensja_od": {
				pensja_od=Double.parseDouble(tab[1]);
				break;
			}
			case "pensja_do": {
				pensja_do=Double.parseDouble(tab[1]);
				break;
			}
			default:{
				System.out.println("Błędne dane kryterium");
				break;
			}	
		}
	}
		wczytaj.nextInt();
		return Wyszukiwanie_osob.wyszukaj_pracownika_po_parametrach(imieString, nazwiskoString, stanowiskoString, staz_od, staz_do, nadgodziny_od, nadgodziny_do, pensja_od, pensja_do, uczelnia.getLudzie());
		
	}
	
	public void wybierz_sposob_prezentacji_danych()
	{
		System.out.println("Wybierz sposob prezentacji danych:");
		System.out.println("1: Domyślny(Lista osob posortowana po nazwisko,a kursow po punktach ECTS i nazwisku prowadzącego)");
		System.out.println("2: Lista osob posortowana po nazwisku i imieniu");
		System.out.println("3: Lista osob posortowana po nazwisku i wieku");
		System.out.println("4: Lista kursow posortowana po punktach ECTS i nazwisku prowadzacego");
		System.out.println("5: Lista kursow posortowana po punktach ECTS");
		System.out.println("6: Lista kursow posortowana po nazwisku prowadzacego");
		System.out.println("7: Wyświetl liste osob bez sortowanie");
		System.out.println("8: Wyświetl liste kursow bez sortowania");
		System.out.println("0: Powrot do menu");
		
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			sposob_wypisywania_osob=new Sort_by_surname();
			sposob_wypisywania_kursow=new Sort_by_ECTS_and_surname();
			break;
		}
		case 2:{
			sposob_wypisywania_osob=new Sort_by_surname_and_name();
			break;
		}
		case 3:{
			sposob_wypisywania_osob=new Sort_by_surname_and_age();
			break;
		}
		case 4:{
			sposob_wypisywania_kursow=new Sort_by_ECTS_and_surname();
			break;
		}
		case 5:{
			sposob_wypisywania_kursow=new Sort_by_ECTS();
			break;
		}
		case 6:{
			sposob_wypisywania_kursow=new Sort_by_lecturer();
			break;
		}
		case 7:{
			sposob_wypisywania_osob=null;
			break;
		}
		case 8:{
			sposob_wypisywania_kursow=null;
			break;
		}
		case 0:{
			return;
		}
		default:
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
		}
	}
	
	
	public void serializuj()
	{
		System.out.println("Podaj nazwę pliku: ");
		String nazwa=wczytaj.next();
		nazwa+=".ser";
		try (ObjectOutputStream zapiszObjectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(nazwa)))){
		zapiszObjectOutputStream.writeObject(uczelnia);
		System.out.println("Zapisano");
		}catch (Exception e) {
			System.out.println("Zapis nie powiódł się, spróbuj ponownie");			
		}
	}
	
	public void deserializuj()
	{
		System.out.println("Podaj nazwę pliku: ");
		String nazwa=wczytaj.next();
		nazwa+=".ser";
		try(ObjectInputStream wczytajInputStream=new ObjectInputStream(new FileInputStream(new File(nazwa)))){
		Object object =wczytajInputStream.readObject();
		przygotoj_do_dzialania((Uczelnia) object);
		System.out.println("Wczytano");
		}catch (Exception e) {
			System.out.println("Odczyt nie powiódł się,sprawdz nazwę pliku i spróbuj ponownie");
		}
	}

	public void modyfikuj_dane()
	{
		System.out.println("Wybierz typ danych który chcesz wyszukać i usunąć: ");
		System.out.println(" 1: Wyszukaj osobe\n 2: Wyszukaj kurs\n 3: Wyszukaj studenta\n 4: Wyszukaj pracownika\n 5: Zmień liczbę dostępnych sal\n 0: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			ArrayList<Osoba> osoba=wyszukaj_osobe();
			uczelnia.usun_osoby(osoba);
			if(osoba!=null && sposob_wypisywania_osob!=null) Collections.sort(osoba,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(osoba);
			System.out.println("Usunięto powyższe rekordy");
			break;
		}
		case 2: {
			ArrayList <Kurs> kursy=wyszukaj_kurs();
			uczelnia.usun_kursy(kursy);
			if(kursy!=null && sposob_wypisywania_kursow!=null) Collections.sort(kursy,sposob_wypisywania_kursow);
			Wyszukiwanie_zajec.wypisz_kursy(kursy);
			System.out.println("Usunięto powyższe rekordy");
			break;
		}
		case 3: {
			ArrayList<Student> osoba=wyszukaj_studenta();
			uczelnia.usun_osoby(((ArrayList<Osoba>)((ArrayList<?>)osoba)));
			if(osoba!=null && sposob_wypisywania_osob!=null) Collections.sort(osoba,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(osoba);
			System.out.println("Usunięto powyższe rekordy");
			break;
		}
		case 4: {
			ArrayList<Pracownik_uczelni> osoba=wyszukaj_pracownika();
			uczelnia.usun_osoby(((ArrayList<Osoba>)((ArrayList<?>)osoba)));
			if(osoba!=null && sposob_wypisywania_osob!=null) Collections.sort(osoba,sposob_wypisywania_osob);
			Wyszukiwanie_osob.wypisz(osoba);
			System.out.println("Usunięto powyższe rekordy");
			break;
		}
		case 5:{
			System.out.println("Podaj nową liczbę sal");
			uczelnia.setLiczba_sal(wczytaj.nextInt());
			System.out.println("Aktualna liczba sal: "+uczelnia.getLiczba_sal());
		}
		case 0: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
		}
		}
	}
	
	public void plan_zajec()
	{
		System.out.println("Wybierz akcje");
		System.out.println(" 1: wypisz plany zajec\n 2: eksportuj plany zajec\n 3: wymus sposob ukladania planu zajec w czasie tej sesji\n 0: powroc do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			 ukladanie_planu.wypisz_plany(ukladanie_planu.uloz_plany());
			break;
		}
		case 2: {
			ukladanie_planu.eksportuj_plany(ukladanie_planu.uloz_plany());
			System.out.println("Wyeksportowano do plików tekstowych");
			break;
		}
		case 3: {
			ustaw_sposob_planu();
			break;
		}
		case 0:{
			return;
		}
		default:
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			plan_zajec();
		}
	}
	
	private void ustaw_sposob_planu()
	{
		System.out.println("Wybierz metode:");
		System.out.println(" 1: po_kolei (ustawia zajęcia wypełniając sale od poniedziałku od 7)");
		System.out.println(" 2: rownomiernie (ustawia zajęcia zaczynając od środka każdego dnia)");
		System.out.println(" 3: 4-dniowy tydzien pracy (ustawia plan po kolei lub rownomiernie zostoawiając wybrany dzień wolnym)");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			ukladanie_planu.setSposob(new Ukladanie_planu_po_kolei());
			break;
		}
		case 2: {
			ukladanie_planu.setSposob(new Ukladanie_planu_rownomiernie());
			break;
		}
		case 3:{
			System.out.println("Podaj sposob oraz numer dnia wolnego (licząc od poniedziałku 1)");
			String sposob=wczytaj.next();
			if(sposob.equals("po_kolei"))
				ukladanie_planu.setSposob(new Ukladanie_planu_4dni(wczytaj.nextInt(),new Ukladanie_planu_po_kolei()));
			if(sposob.equals("rownomiernie"))
				ukladanie_planu.setSposob(new Ukladanie_planu_4dni(wczytaj.nextInt(),new Ukladanie_planu_rownomiernie()));
			break;
		}
		default:
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			plan_zajec();
		}
		
	}
}