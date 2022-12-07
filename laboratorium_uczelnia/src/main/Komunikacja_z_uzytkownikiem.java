package main;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import klasy_wyszukujace.*;
import klasy_definiujace.*;

public class Komunikacja_z_uzytkownikiem {
	
	Uczelnia uczelnia;
	Scanner wczytaj=new Scanner(System.in);
	
	public Komunikacja_z_uzytkownikiem(Uczelnia uczelnia) {
		this.uczelnia=uczelnia;
		System.out.println("Witaj w systemie zarządzania uczelnią");
	}
	
	public void menu()
	{
		System.out.println("*****MENU*****\nWybierz akcje:");
		System.out.println(" 1: Dodaj dane\n 2: Wyszukaj\n 3: Zapisz stan\n 4: Odtwórz stan\n 5: Zamknij program");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			dodaj_dane();
			break;
		}
		case 2: {
			wyszukaj();
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
		case 5:
		{
			System.out.println("Dziękujemy za skorzystanie z systemu. Do zobaczenia!");
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			}
		}
		menu();
		//wczytaj.close();
	
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
		
	
	uczelnia.dodaj_kurs(new Kurs(wczytaj.next(), wczytaj.next(),wczytaj.next(),wczytaj.nextInt()));
	System.out.println("Operacja zakończona, powrót do menu");
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
	
	uczelnia.dodaj_osobe(new Student(wczytaj.next(), wczytaj.next(),wczytaj.next(),wczytaj.nextInt(),((wczytaj.next().equals("kobieta"))?'K':'M'), wczytaj.next(), wczytaj.nextInt()));
	System.out.println("Operacja zakończona, powrót do menu");
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
	
	uczelnia.dodaj_osobe(new Pracownik_Badawczo_Dydaktyczny(wczytaj.next(), wczytaj.next(),wczytaj.next(),wczytaj.nextInt(),((wczytaj.next().equals("kobieta"))?'K':'M'), wczytaj.next(), wczytaj.nextInt(),wczytaj.nextDouble(),wczytaj.nextInt()));
	System.out.println("Operacja zakończona, powrót do menu");
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
	
	uczelnia.dodaj_osobe(new Pracownik_administracyjny(wczytaj.next(), wczytaj.next(),wczytaj.next(),wczytaj.nextInt(),((wczytaj.next().equals("kobieta"))?'K':'M'), wczytaj.next(), wczytaj.nextInt(),wczytaj.nextDouble(),wczytaj.nextInt()));
	System.out.println("Operacja zakończona, powrót do menu");
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
		System.out.println(" 1: Wyszukaj osobe\n 2: Wyszukaj kurs\n 3: Wyszukaj studenta\n 4: Wyszukaj pracownika\n 0: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 1: {
			wyszukaj_osobe();
			break;
		}
		case 2: {
			wyszukaj_kurs();
			break;
		}
		case 3: {
			wyszukaj_studenta();
			break;
		}
		case 4: {
			wyszukaj_pracownika();
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
	
	public void wyszukaj_osobe()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkie osoby\n 1: imie\n 2: nazwisko\n 3: pesel\n 4: wiek (od do)\n 5: płeć\n 6: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszystkie osoby");
			Wyszukiwanie_osob.wypisz_osoby(uczelnia.getLudzie());
			break;
		}
		case 1: {
			System.out.println("Podaj imię:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_imieniu(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 2: {
			System.out.println("Podaj nazwisko:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_nazwisku(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 3: {
			System.out.println("Podaj pesel:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_pesel(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 4: {
			System.out.println("Podaj zakres wieku w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_wieku(x,y, uczelnia.getLudzie()));;
			break;
		}
		case 5: {
			System.out.println("Podaj płeć:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_plci(((wczytaj.next().equals("kobieta"))?'K':'M'), uczelnia.getLudzie()));
			break;
		}
		case 6:{
			osoba_parametry();
			break;
		}
		case -1: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}	
	}

	private void osoba_parametry()
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
		Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_parametrach(imieString, nazwiskoString, peselString, wiek_od, wiek_do, plec, uczelnia.getLudzie()));
		
	}

	public void wyszukaj_kurs()
	{
		System.out.println("Wybierz kryterium wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkie kursy\n 1: nazwa\n 2: imie i nazwisko prowadzącego\n 3: punkty ECTS (od do)\n 4: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			Wyszukiwanie_zajec.wypisz_kursy(uczelnia.getWszystkie_kursy());
			break;
		}
		case 1:{
			System.out.println("Podaj nazwę");
			Wyszukiwanie_zajec.wypisz_kursy(Wyszukiwanie_zajec.wyszukaj_kursy_po_nazwie(wczytaj.next(), uczelnia.getWszystkie_kursy()));
			break;
		}
		case 2:{
			System.out.println("Podaj imie i nazwisko prowdzącego");
			Wyszukiwanie_zajec.wypisz_kursy(Wyszukiwanie_zajec.wyszukaj_kursy_po_prowadzacym(wczytaj.next(),wczytaj.next(), uczelnia.getWszystkie_kursy()));
			break;
		}
		case 3:{
			System.out.println("Podaj zakres punktów ECTS w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			Wyszukiwanie_zajec.wypisz_kursy(Wyszukiwanie_zajec.wyszukaj_kursy_po_ECTS(x, y, uczelnia.getWszystkie_kursy()));
			break;
		}
		case 4:{
			kurs_parametry();
			break;
		}
		case -1: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
	}
	
	private void kurs_parametry()
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
		Wyszukiwanie_zajec.wypisz_kursy(Wyszukiwanie_zajec.wyszukaj_kurs_po_parametrach(nazwaString, imieString, nazwiskoString, ects_od, ects_do, uczelnia.getWszystkie_kursy()));
		
	}
	
	public void wyszukaj_studenta()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkich studentów\n 1: imie\n 2: nazwisko\n 3: numer indeksu\n 4: rok studiów\n 5: kurs\n 6: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszyscy studenci");
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie()));
			
			break;
		}
		case 1: {
			System.out.println("Podaj imię:");
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_imieniu(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 2: {
			System.out.println("Podaj nazwisko:");
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_nazwisku(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 3: {
			System.out.println("Podaj numer indeksu:");
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_indeksie(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 4: {
			System.out.println("Podaj rok studiów");
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_roku(wczytaj.nextInt(), uczelnia.getLudzie()));
			break;
		}
		case 5: {
			
			System.out.println("Podaj nazwę kursu, imię i nazwisko prowadzącego oraz liczbę punktow ects");
			Kurs kurs=new Kurs(wczytaj.next(),wczytaj.next(),wczytaj.next(), wczytaj.nextInt());
			Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_kursie(kurs, uczelnia.getLudzie()));
			break;
		}
		case 6:{
			student_parametry();
			break;
		}
		case -1: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
	}
	
	private void student_parametry()
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
		Wyszukiwanie_osob.wypisz_studentow(Wyszukiwanie_osob.wyszukaj_studenta_po_parametrach(imieString, nazwiskoString, null,rok, uczelnia.getLudzie()));
		
	}
	
	public void wyszukaj_pracownika()
	{
		System.out.println("Wybierz kryterium do wyszukiwania: ");
		System.out.println(" 0: znajdz wszystkich pracownikow\n 1:znajdz wszystkich pracownikow badawczo-dydaktycznych\n 2:znajdz wszystkich pracownikow administracyjnych\n 3: imie\n 4: nazwisko\n 5: stanowisko\n 6: staż(od do)\n 7: nadgodziny(od do)\n 8: pensja(od do)\n 9: po wielu parametrach\n -1: Powróć do menu");
		int opcja=wczytaj.nextInt();
		switch (opcja) {
		case 0: {
			System.out.println("Wyszyscy pracownicy");
			Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow(uczelnia.getLudzie()));
			break;
		}
		case 1: {
			System.out.println("Wyszyscy pracownicy badawczo-dydaktyczni");
			Wyszukiwanie_osob.wypisz_pracownikow_badawczo_dydaktycznych(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_badawczo_dydaktycznych(uczelnia.getLudzie()));
			break;
		}
		case 2: {
			System.out.println("Wyszyscy pracownicy administracyjni");
			Wyszukiwanie_osob.wypisz_pracownikow_administracyjnych(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_administacyjnych(uczelnia.getLudzie()));
			break;
		}
		case 3: {
			System.out.println("Podaj imię:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_imieniu(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 4: {
			System.out.println("Podaj nazwisko:");
			Wyszukiwanie_osob.wypisz_osoby(Wyszukiwanie_osob.wyszukaj_osobe_po_nazwisku(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 5: {
			System.out.println("Podaj stanowisko:");
			Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_stanowisku(wczytaj.next(), uczelnia.getLudzie()));
			break;
		}
		case 6: {
			System.out.println("Podaj zakres stażu w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_stażu(x,y, uczelnia.getLudzie()));;
			break;
		}
		case 7: {
			System.out.println("Podaj zakres nadgodzin w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			int x=wczytaj.nextInt();
			wczytaj.next();
			int y=wczytaj.nextInt();
			Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_nadgodzinach(x,y, uczelnia.getLudzie()));;
			break;
		}
		case 8: {
			System.out.println("Podaj zakres pensji w formie ''x do y'' jeżeli chcesz pominąć kryterium wpisz -1");
			double x=wczytaj.nextDouble();
			wczytaj.next();
			double y=wczytaj.nextDouble();
			Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_pensji(x,y, uczelnia.getLudzie()));;
			break;
		}
		case 9:{
			pracownik_parametry();
			break;
		}
		case -1: {
			return;
		}
		default:{
			System.out.println("Brak poprawnego wyboru funkcjonalności, wybierz jeszcze raz");
			dodaj_dane();
		}
		}
	}

	private void pracownik_parametry()
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
		Wyszukiwanie_osob.wypisz_pracownikow(Wyszukiwanie_osob.wyszukaj_pracownika_po_parametrach(imieString, nazwiskoString, stanowiskoString, staz_od, staz_do, nadgodziny_od, nadgodziny_do, pensja_od, pensja_do, uczelnia.getLudzie()));
		
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
		uczelnia= (Uczelnia) object;
		System.out.println("Wczytano");
		}catch (Exception e) {
			System.out.println("Odczyt nie powiódł się,sprawdz nazwę pliku i spróbuj ponownie");
		}
	}
}