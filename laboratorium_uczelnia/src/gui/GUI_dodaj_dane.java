package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import klasy_definiujace.*;
import main.Uczelnia;

public class GUI_dodaj_dane {

	JFrame ramka_dodawania;
	Uczelnia uczelnia;
	
	public GUI_dodaj_dane(Uczelnia uczelnia) {
		this.uczelnia=uczelnia;
	}

	public JPanel tworz_opcje_dodawania()
	{
		JPanel panel_opcji_dodawania=new JPanel();
		panel_opcji_dodawania.setLayout(new BoxLayout(panel_opcji_dodawania, BoxLayout.Y_AXIS));
		
		JButton przycisk_dodaj_kurs=new JButton("Dodaj kurs");
		przycisk_dodaj_kurs.addActionListener(new przycisk_dodaj_kurs_Listener());
		panel_opcji_dodawania.add(przycisk_dodaj_kurs);
		
		JButton przycisk_dodaj_studenta=new JButton("Dodaj studenta");
		przycisk_dodaj_studenta.addActionListener(new przycisk_dodaj_studenta_Listener());
		panel_opcji_dodawania.add(przycisk_dodaj_studenta);
		
		JButton przycisk_dodaj_pracownika_administracyjnego=new JButton("Dodaj pracownika administracyjnego");
		przycisk_dodaj_pracownika_administracyjnego.addActionListener(new przycisk_dodaj_pracownika_administracyjnego_Listener());
		panel_opcji_dodawania.add(przycisk_dodaj_pracownika_administracyjnego);
		
		JButton przycisk_dodaj_pracownika_badawczo_dydaktycznego=new JButton("Dodaj pracownika badawczo_dydaktycznego");
		przycisk_dodaj_pracownika_badawczo_dydaktycznego.addActionListener(new przycisk_dodaj_pracownika_badawczo_dydaktycznego_Listener());
		panel_opcji_dodawania.add(przycisk_dodaj_pracownika_badawczo_dydaktycznego);
		
		JButton przycisk_plan=new JButton("Przypisz kurs studentowi");
		przycisk_plan.addActionListener(new przycisk_przypisz_kurs_studentowi_Listener());
		panel_opcji_dodawania.add(przycisk_plan);
		
		return panel_opcji_dodawania;
		
	}
	
	public ArrayList<JTextField> tworz_okno_dodawania(String nazwa,String komunikat, String[] tablica_pol, JButton przycisk_dodaj)
	{ //zwraca arrayliste pol z ktorych bede pobieral dane oraz pole polecenia na miejscu 0
	
		ArrayList<JTextField> pola_danych =new ArrayList<JTextField>();
		ramka_dodawania=new JFrame(nazwa);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(tablica_pol.length,2,2,2));
		
		JTextField field=new JTextField(komunikat);
		pola_danych.add(field);
		ramka_dodawania.add(BorderLayout.NORTH,field);
		ramka_dodawania.add(BorderLayout.SOUTH,przycisk_dodaj);
		
		for(int i=0;i<tablica_pol.length;i++)
		{
			field=new JTextField(tablica_pol[i]);
			field.setEditable(false);
			panel.add(field);
			field=new JTextField("");
			panel.add(field);
			pola_danych.add(field);
		}
		
		ramka_dodawania.add(BorderLayout.CENTER,panel);
		ramka_dodawania.pack();
		ramka_dodawania.setLocation(200, 150);
		ramka_dodawania.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ramka_dodawania.setVisible(true);
		
		return pola_danych;
	}
	
	public String[] pobierz_dane(ArrayList<JTextField> pola)
	{//pobiera dane z pol tekstowych i zwraca w tablicy
		String[] dane=new String[pola.size()-1];
		for(int i=1;i<pola.size();i++)
		{
			dane[i-1]=pola.get(i).getText();
		}
		return dane;
	}
	
	class przycisk_dodaj_kurs_Listener implements ActionListener
	{
		
		ArrayList<JTextField> lista_danych;
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] lista_pol= {"Nazwa:","Imie prowadzacego:","Nazwisko prowadzacego:","Liczba punktow ECTS"};
			JButton przycisk_dodaj=new JButton("Dodaj");
			przycisk_dodaj.addActionListener(new dodaj_Listener());
			lista_danych=tworz_okno_dodawania("Dodaj kurs", "Wprowadz dane kursu", lista_pol, przycisk_dodaj);
		}
		
		class dodaj_Listener implements ActionListener
		{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String[] dane=pobierz_dane(lista_danych);
				Kurs nowyKurs=new Kurs(dane[0],dane[1],dane[2], Integer.parseInt(dane[3]));
				uczelnia.dodaj_kurs(nowyKurs);
				lista_danych.get(0).setText("Operacja zakonczona powodzeniem, mozesz dodac kolejny kurs");
				lista_danych.get(0).setBackground(Color.green);
				ramka_dodawania.pack();
				for(int i=1;i<lista_danych.size();i++)
					lista_danych.get(i).setText("");
				//klasy_wyszukujace.Wyszukiwanie_zajec.wypisz_kursy(uczelnia.getWszystkie_kursy());
			} 
			catch (IllegalArgumentException e1) {
				lista_danych.get(0).setText("Operacja zakonczona niepowodzeniem. Blad w "+e1.getMessage()+", sprobuj ponownie");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			catch (Exception e2) {
				lista_danych.get(0).setText("Nastapil nieoczekiwany blad, zamknij okno i sprobuj ponownie pozniej");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			
		}
		}
	}
	
	class przycisk_dodaj_studenta_Listener implements ActionListener
	{
		ArrayList<JTextField> lista_danych;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] lista_pol= {"Imie:","Nazwisko:","Pesel:","Wiek:","Plec:","Numer indeksu","Rok studiow:"};
			JButton przycisk_dodaj=new JButton("Dodaj");
			przycisk_dodaj.addActionListener(new dodaj_Listener());
			lista_danych=tworz_okno_dodawania("Dodaj studenta", "Wprowadz dane studenta", lista_pol, przycisk_dodaj);
			
		}
		
		class dodaj_Listener implements ActionListener
		{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String[] dane=pobierz_dane(lista_danych);
				Student nowyStudent=new Student(dane[0],dane[1],dane[2], Integer.parseInt(dane[3]),dane[4].charAt(0), dane[5], Integer.parseInt(dane[6]));
				uczelnia.dodaj_osobe(nowyStudent);
				lista_danych.get(0).setText("Operacja zakonczona powodzeniem, mozesz dodac kolejnego studenta");
				lista_danych.get(0).setBackground(Color.green);
				ramka_dodawania.pack();
				for(int i=1;i<lista_danych.size();i++)
					lista_danych.get(i).setText("");
				//klasy_wyszukujace.Wyszukiwanie_osob.wypisz(uczelnia.getLudzie());
			} 
			catch (IllegalArgumentException e1) {
				lista_danych.get(0).setText("Operacja zakonczona niepowodzeniem. Blad w "+e1.getMessage()+", sprobuj ponownie");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			catch (Exception e2) {
				lista_danych.get(0).setText("Nastapil nieoczekiwany blad, zamknij okno i sprobuj ponownie pozniej");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			
		}
		}
	}
	
	class przycisk_dodaj_pracownika_administracyjnego_Listener implements ActionListener
	{
		ArrayList<JTextField> lista_danych;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] lista_pol= {"Imie:","Nazwisko:","Pesel:","Wiek:","Plec:","Stanowisko:","Staz pracy:","Pensja","Liczba nadgodzin"};
			JButton przycisk_dodaj=new JButton("Dodaj");
			przycisk_dodaj.addActionListener(new dodaj_Listener());
			lista_danych=tworz_okno_dodawania("Dodaj pracownika administracyjnego", "Wprowadz dane pracownika administracyjnego", lista_pol, przycisk_dodaj);
			
		}
		
		class dodaj_Listener implements ActionListener
		{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String[] dane=pobierz_dane(lista_danych);
				Pracownik_administracyjny nowyPracownik=new Pracownik_administracyjny(dane[0],dane[1],dane[2], Integer.parseInt(dane[3]),dane[4].charAt(0), dane[5], Integer.parseInt(dane[6]), Double.parseDouble(dane[7]), Integer.parseInt(dane[8]));
				uczelnia.dodaj_osobe(nowyPracownik);
				lista_danych.get(0).setText("Operacja zakonczona powodzeniem, mozesz dodac kolejnego pracownika");
				lista_danych.get(0).setBackground(Color.green);
				ramka_dodawania.pack();
				for(int i=1;i<lista_danych.size();i++)
					lista_danych.get(i).setText("");
				//klasy_wyszukujace.Wyszukiwanie_osob.wypisz(uczelnia.getLudzie());
			} 
			catch (IllegalArgumentException e1) {
				lista_danych.get(0).setText("Operacja zakonczona niepowodzeniem. Blad w "+e1.getMessage()+", sprobuj ponownie");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			catch (Exception e2) {
				lista_danych.get(0).setText("Nastapil nieoczekiwany blad, zamknij okno i sprobuj ponownie pozniej");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			
		}
		}
	}
	
	class przycisk_dodaj_pracownika_badawczo_dydaktycznego_Listener implements ActionListener
	{
		ArrayList<JTextField> lista_danych;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] lista_pol= {"Imie:","Nazwisko:","Pesel:","Wiek:","Plec:","Stanowisko:","Staz pracy:","Pensja","Liczba publikacji"};
			JButton przycisk_dodaj=new JButton("Dodaj");
			przycisk_dodaj.addActionListener(new dodaj_Listener());
			lista_danych=tworz_okno_dodawania("Dodaj pracownika badawczo-dydaktycznego", "Wprowadz dane pracownika badawczo-dydaktycznego", lista_pol, przycisk_dodaj);
			
		}
		
		class dodaj_Listener implements ActionListener
		{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String[] dane=pobierz_dane(lista_danych);
				Pracownik_Badawczo_Dydaktyczny nowyPracownik=new Pracownik_Badawczo_Dydaktyczny(dane[0],dane[1],dane[2], Integer.parseInt(dane[3]),dane[4].charAt(0), dane[5], Integer.parseInt(dane[6]), Double.parseDouble(dane[7]), Integer.parseInt(dane[8]));
				uczelnia.dodaj_osobe(nowyPracownik);
				lista_danych.get(0).setText("Operacja zakonczona powodzeniem, mozesz dodac kolejnego pracownika");
				lista_danych.get(0).setBackground(Color.green);
				ramka_dodawania.pack();
				for(int i=1;i<lista_danych.size();i++)
					lista_danych.get(i).setText("");
				//klasy_wyszukujace.Wyszukiwanie_osob.wypisz(uczelnia.getLudzie());
			} 
			catch (IllegalArgumentException e1) {
				lista_danych.get(0).setText("Operacja zakonczona niepowodzeniem. Blad w "+e1.getMessage()+", sprobuj ponownie");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			catch (Exception e2) {
				lista_danych.get(0).setText("Nastapil nieoczekiwany blad, zamknij okno i sprobuj ponownie pozniej");
				lista_danych.get(0).setBackground(Color.red);
				ramka_dodawania.pack();
			}
			
		}
		}
	}
	
	class przycisk_przypisz_kurs_studentowi_Listener implements ActionListener
	{
		JTextField instrukcja;
		JList<Kurs> opcje_kursow;
		JList<Student> opcje_studentow;
		public void utworz_okienko_przypisania()
		{
			JFrame okienko_przypisania=new JFrame("Przypisz kurs studentkowi");
			instrukcja=new JTextField("Wybierz studenów i kursy,które chcesz im przypisać");
			instrukcja.setEditable(false);
			okienko_przypisania.add(BorderLayout.NORTH,instrukcja);
			
			JPanel panel_kursow=new JPanel();
			panel_kursow.setLayout(new BoxLayout(panel_kursow, BoxLayout.Y_AXIS));
			JTextField kursField=new JTextField("Wybierz kurs:");
			kursField.setEditable(false);
			panel_kursow.add(kursField);
			
			JPanel panel_studentow=new JPanel();
			panel_studentow.setLayout(new BoxLayout(panel_studentow, BoxLayout.Y_AXIS));
			JTextField studentField=new JTextField("Wybierz studenta");
			studentField.setEditable(false);
			panel_studentow.add(studentField);
			
			
			ArrayList<Kurs> kursy=uczelnia.getWszystkie_kursy();
			Kurs[] lista_kursow=new Kurs[kursy.size()];
			for(int i=0;i<kursy.size();i++)
				lista_kursow[i]=kursy.get(i);
			opcje_kursow=new JList<Kurs>(lista_kursow);
			JScrollPane przewijanie_opcje_kursow=new JScrollPane(opcje_kursow);
			przewijanie_opcje_kursow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			przewijanie_opcje_kursow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			panel_kursow.add(przewijanie_opcje_kursow);
			
			ArrayList<Student> studenci=klasy_wyszukujace.Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie());
			Student[] lista_studentow=new Student[studenci.size()];
			for(int i=0;i<studenci.size();i++)
				lista_studentow[i]=studenci.get(i);
			opcje_studentow=new JList<Student>(lista_studentow);
			JScrollPane przewijanie_opcje_studentow=new JScrollPane(opcje_studentow);
			przewijanie_opcje_studentow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			przewijanie_opcje_studentow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			panel_studentow.add(przewijanie_opcje_studentow);
			
			JButton przycisk_przypisz=new JButton("Przypisz kurs studentowi");
			przycisk_przypisz.addActionListener(new przycisk_przypisz_Listener());
			
			okienko_przypisania.add(BorderLayout.SOUTH,przycisk_przypisz);
			JPanel panel_wyboru=new JPanel(new GridLayout(1,2,2,0));
			panel_wyboru.add(panel_studentow);
			panel_wyboru.add(panel_kursow);
			okienko_przypisania.add(BorderLayout.CENTER,panel_wyboru);
			okienko_przypisania.setSize(1000,300);
			okienko_przypisania.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {	
			utworz_okienko_przypisania();
		}
		
		class przycisk_przypisz_Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!opcje_studentow.isSelectionEmpty()) {
					java.util.List<Student> studenci= opcje_studentow.getSelectedValuesList();
					java.util.List<Kurs> kursy=opcje_kursow.getSelectedValuesList();
					for(Student s:studenci)
					{
						for(Kurs k:kursy)
							s.add_kurs(k);
					}
					
					instrukcja.setText("Operacja zakonczona, mozesz wybrac kolejne rekordy do powiazania");
					instrukcja.setBackground(Color.green);
					opcje_kursow.clearSelection();
					opcje_studentow.clearSelection();
					//klasy_wyszukujace.Wyszukiwanie_osob.wypisz(klasy_wyszukujace.Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie()));
				}
				else {
					instrukcja.setText("Najpierw wybierz studenta i kurs");
					instrukcja.setBackground(Color.red);
				}
			}
			
		}
	}
}