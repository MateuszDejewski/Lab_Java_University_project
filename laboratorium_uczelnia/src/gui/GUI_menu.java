package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import klasy_definiujace.*;
import main.*;
import obserwator.*;
import plan_zajec.*;
import sortowanie.*;


public class GUI_menu implements Serializable {

	private static final long serialVersionUID = -2388157453572561083L;
	Uczelnia uczelnia;
	transient JFrame ramka_glowna;
	transient JFrame ramka_zapisodczyt;
	transient JPanel opcje_podstawowe;
	transient JPanel opcje_szczegolowe;
	transient JTextField komunikaTextField;
	Ukladanie_planu ukladanie_planu;
	Comparator<Osoba> sposob_wypisywania_osob;
	Comparator<Kurs> sposob_wypisywania_kursow;
	transient GUI_menu menu;
	
	public GUI_menu(Uczelnia uczelnia) {
		this.uczelnia=uczelnia;
		stan_domyslny();
	}
	
	public GUI_menu(Uczelnia uczelnia,Ukladanie_planu ukladanie_planu,Comparator<Osoba> sposob_wypisywania_osob,Comparator<Kurs> sposob_wypisywania_kursow)
	{
		this.uczelnia=uczelnia;
		this.ukladanie_planu=ukladanie_planu;
		this.sposob_wypisywania_osob=sposob_wypisywania_osob;
		this.sposob_wypisywania_kursow=sposob_wypisywania_kursow;
	}
	
	public void stan_domyslny()
	{
		ukladanie_planu=new Ukladanie_planu(uczelnia);
		uczelnia.addObserver(new obserwator_zmian(this));
		sposob_wypisywania_osob=new Sort_by_surname();
		sposob_wypisywania_kursow=new Sort_by_ECTS();
		menu=this;
	}
	
	public void utworz()
	{
		menu=this;
		ramka_glowna=new JFrame("System zarządzania uczelnia");
		opcje_podstawowe=utworz_opcje_podstawowe();
		opcje_szczegolowe=new JPanel();
		
		komunikaTextField=new JTextField("System gotowy do dzialania");
		komunikaTextField.setEditable(false);
		
		ramka_glowna.add(BorderLayout.WEST,opcje_podstawowe);
		ramka_glowna.add(BorderLayout.SOUTH,komunikaTextField);
		ramka_glowna.add(opcje_szczegolowe);
		ramka_glowna.pack();
		ramka_glowna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ramka_glowna.setLocation(200, 100);
		ramka_glowna.setVisible(true);
	}
	
	public JPanel utworz_opcje_podstawowe()
	{
		JMenu zapis_odczyt = new JMenu("Uczelnia");
		
		JMenuItem zapisz=new JMenuItem("Zapisz stan");
		zapisz.addActionListener(new zapisz_stan_Listener());
		zapis_odczyt.add(zapisz);
		
		JMenuItem odtworz=new JMenuItem("Odtworz stan");
		odtworz.addActionListener(new odtworz_stan_Listener());
		zapis_odczyt.add(odtworz);
		JMenuBar zapis_odczytBar =new JMenuBar();
		zapis_odczytBar.add(zapis_odczyt);
		ramka_glowna.setJMenuBar(zapis_odczytBar);
		
		JPanel panel_opcji_podstawowych=new JPanel();
		panel_opcji_podstawowych.setLayout(new BoxLayout(panel_opcji_podstawowych, BoxLayout.Y_AXIS));
		
		JButton przycisk_dodaj_dane=new JButton("Dodaj dane");
		przycisk_dodaj_dane.addActionListener(new przycisk_dodaj_dane_Listener());
		panel_opcji_podstawowych.add(przycisk_dodaj_dane);
		
		JButton przycisk_modyfikuj=new JButton("Modyfikuj dane");
		przycisk_modyfikuj.addActionListener(new przycisk_modyfikuj_dane_Listener());
		panel_opcji_podstawowych.add(przycisk_modyfikuj);
		
		
		
		JButton przycisk_wyszukaj=new JButton("Wyszukaj");
		przycisk_wyszukaj.addActionListener(new przycisk_wyszukaj_Listener());
		panel_opcji_podstawowych.add(przycisk_wyszukaj);
		
		JButton przycisk_plan=new JButton("Zarzadzaj planem zajec");
		przycisk_plan.addActionListener(new przycisk_plan_zajec_Listener());
		panel_opcji_podstawowych.add(przycisk_plan);
		
		JButton przycisk_ustawienia=new JButton("Ustawienia");
		przycisk_ustawienia.addActionListener(new przycisk_ustawienia_Listener());
		panel_opcji_podstawowych.add(przycisk_ustawienia);
		
		return panel_opcji_podstawowych;
		
		
	}
	
	public boolean wypisz_JTextArea(ArrayList<?> lista,JTextArea area)
	{//zwraca prawde gdy wypisal jakies rekordy
		if(lista==null || lista.isEmpty())
		{
			area.append("Brak rekordow spelniajacych kryteria");
			return false;
		}
		
		if(lista.get(0) instanceof Kurs)
		{
			ArrayList<Kurs> lista2=(ArrayList<Kurs>) lista;
			Collections.sort(lista2, sposob_wypisywania_kursow);
			for(int i=0;i<lista2.size();i++)
				area.append(lista2.get(i).toString()+"\n");	
			return true;
		}
		if(lista.get(0) instanceof Osoba)
		{
			ArrayList<Osoba> lista2=(ArrayList<Osoba>) lista;
			Collections.sort(lista2, sposob_wypisywania_osob);
			for(int i=0;i<lista2.size();i++)
				area.append(lista2.get(i).toString()+"\n");	
			return true;
		}
		return false;
	}
	
	class przycisk_dodaj_dane_Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			komunikaTextField.setText("Jestes w sekcji dodawania danych");
			ramka_glowna.remove(opcje_szczegolowe);
			GUI_dodaj_dane dodaj_dane=new GUI_dodaj_dane(uczelnia);
			opcje_szczegolowe=dodaj_dane.tworz_opcje_dodawania();
			ramka_glowna.add(BorderLayout.CENTER,opcje_szczegolowe);
			ramka_glowna.pack();
		}
	}
	
	class przycisk_modyfikuj_dane_Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			komunikaTextField.setText("Jestes w sekcji modyfikacji danych");
			ramka_glowna.remove(opcje_szczegolowe);	
			GUI_modyfikuj modyfikuj=new GUI_modyfikuj(menu);
			opcje_szczegolowe=modyfikuj.tworz_opcje_modyfikacji();
			ramka_glowna.add(BorderLayout.CENTER,opcje_szczegolowe);
			ramka_glowna.pack();
		}
	}
	
	public JTextField tworz_okno_zapis_odczyt(String nazwa,ActionListener listener)
	{
		JFrame ramka_ZO=new JFrame(nazwa);
		
		
		JTextArea podaj=new JTextArea("Podaj nazwe pliku");
		podaj.setEditable(false);
		ramka_ZO.add(BorderLayout.NORTH,podaj);
		
		JTextField wczytaj=new JTextField("");;
		ramka_ZO.add(BorderLayout.CENTER,wczytaj);
		
		JButton wykonaj=new JButton(nazwa);
		wykonaj.addActionListener(listener);
		ramka_ZO.add(BorderLayout.SOUTH,wykonaj);
		ramka_ZO.pack();
		ramka_ZO.setVisible(true);
		ramka_zapisodczyt=ramka_ZO;
		return wczytaj;
	}
	
	class zapisz_stan_Listener implements ActionListener
	{
		
		JTextField wczytajField;
		@Override
		public void actionPerformed(ActionEvent e) {		
			wczytajField=tworz_okno_zapis_odczyt("Zapisz stan uczelni",new zapisz_listener());
			wczytajField.requestFocus();
		}
		
		class zapisz_listener implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String nazwa=wczytajField.getText();
			try (ObjectOutputStream zapiszObjectOutputStream=new ObjectOutputStream(new FileOutputStream(new File(nazwa)))){
				zapiszObjectOutputStream.writeObject(menu);
				wczytajField.setText("Zapisano");
				wczytajField.setBackground(Color.green);
				komunikaTextField.setText("Zapisano dane do pliku "+nazwa);
				}catch (Exception e2) {
					wczytajField.setText("Zapis nie powiódł się, spróbuj ponownie");
					wczytajField.setBackground(Color.red);			
					e2.printStackTrace();
				}
				
			}
			
		}
	}
	
	class odtworz_stan_Listener implements ActionListener
	{
		JTextField wczytajField;
		@Override
		public void actionPerformed(ActionEvent e) {	
			wczytajField=tworz_okno_zapis_odczyt("Wczytaj stan uczelni",new wczytaj_listener());
			wczytajField.requestFocus();
			}
			
		
		class wczytaj_listener implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				String nazwa=wczytajField.getText();
				try(ObjectInputStream wczytajInputStream=new ObjectInputStream(new FileInputStream(new File(nazwa)))){
					Object object =wczytajInputStream.readObject();
					ramka_zapisodczyt.dispose();
					ramka_glowna.dispose();
					GUI_menu nowe_menu=(GUI_menu)object;
					nowe_menu.uczelnia.setObserwatorzy(new ArrayList<Observer>());
					nowe_menu.uczelnia.addObserver(new obserwator_zmian(nowe_menu));
					nowe_menu.utworz();
					nowe_menu.komunikaTextField.setText("Sysetm wczytany z pliku "+nazwa);
					nowe_menu.ramka_glowna.pack();
					
					}catch (Exception e3) {
						wczytajField.setText("Odczyt nie powiódł się,sprawdz nazwę pliku i spróbuj ponownie");
						wczytajField.setBackground(Color.red);			
						e3.printStackTrace();
					}
					
			}
				
			}
	}
	
	class przycisk_wyszukaj_Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			komunikaTextField.setText("Jestes w sekcji wyszukiwania");
			ramka_glowna.remove(opcje_szczegolowe);	
			GUI_wyszukaj wyszukaj=new GUI_wyszukaj(menu);
			opcje_szczegolowe=wyszukaj.tworz_opcje_wyszukiwania();
			ramka_glowna.add(BorderLayout.CENTER,opcje_szczegolowe);
			ramka_glowna.pack();
		}
	}
	
	class przycisk_plan_zajec_Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			komunikaTextField.setText("Jestes w sekcji zarzadzania planem zajec");
			ramka_glowna.remove(opcje_szczegolowe);
			GUI_plan_zajec plan=new GUI_plan_zajec(menu);
			opcje_szczegolowe=plan.tworz_opcje_planu();
			ramka_glowna.add(BorderLayout.CENTER,opcje_szczegolowe);
			ramka_glowna.pack();
			
		}
	}
	
	class przycisk_ustawienia_Listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			komunikaTextField.setText("Jestes w sekcji ustawien");
			GUI_ustawienia ustawienia=new GUI_ustawienia(menu);
			ustawienia.utworz_okienko_ustawien();
		}
	}
	
	class obserwator_zmian implements obserwator.Observer
	{
		GUI_menu menu;
		public obserwator_zmian(GUI_menu menu) {
			this.menu=menu;
		}
		
		@Override
		public void update() {
			Korektor_danych korektor_danych=new Korektor_danych(menu.uczelnia);
			korektor_danych.update();
			menu.ukladanie_planu.update();
			menu.komunikaTextField.setText("Nastąpila zmiana danych, nowy plan zostal wyeksportowany do plikow");
			menu.ramka_glowna.pack();
		}
		
	}
	
}
