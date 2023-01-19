package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import klasy_definiujace.*;
import klasy_wyszukujace.*;
import main.*;

public class GUI_wyszukaj {

	JFrame ramka_wyszukiwania;
	JTextArea wyniki_wyszukiwania;
	Uczelnia uczelnia;
	GUI_menu menu;
	
	public GUI_wyszukaj(GUI_menu menu) {
		this.uczelnia=menu.uczelnia;
		this.menu=menu;
	}

	public JPanel tworz_opcje_wyszukiwania()
	{
		JPanel panel_opcji_wyszukiwania=new JPanel();
		panel_opcji_wyszukiwania.setLayout(new BoxLayout(panel_opcji_wyszukiwania, BoxLayout.Y_AXIS));
			
		JButton przycisk_wyszukaj_osobe=new JButton("Wyszukaj osobe");
		przycisk_wyszukaj_osobe.addActionListener(new przycisk_wyszukaj_osobe_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_osobe);
			
		JButton przycisk_wyszukaj_kurs=new JButton("Wyszukaj kurs");
		przycisk_wyszukaj_kurs.addActionListener(new przycisk_wyszukaj_kurs_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_kurs);
			
		JButton przycisk_wyszukaj_studenta=new JButton("Wyszukaj studenta");
		przycisk_wyszukaj_studenta.addActionListener(new przycisk_wyszukaj_studenta_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_studenta);
		
		JButton przycisk_wyszukaj_studenta_kurs=new JButton("Wyszukaj studenta po kursie");
		przycisk_wyszukaj_studenta_kurs.addActionListener(new przycisk_wyszukaj_studentow_po_kursie_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_studenta_kurs);	
			
		JButton przycisk_wyszukaj_pracownika=new JButton("Wyszukaj pracownika");
		przycisk_wyszukaj_pracownika.addActionListener(new przycisk_wyszukaj_pracownika_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_pracownika);
		
		JButton przycisk_wyszukaj_grupe=new JButton("Wyswietl wiele danych");
		przycisk_wyszukaj_grupe.addActionListener(new przycisk_wyszukaj_grupe_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_grupe);
			
		return panel_opcji_wyszukiwania;
			
	}
	
	public ArrayList<Component> tworz_okno_wyszukiwania(String nazwa,String komunikat, String[] tablica_pol, JButton przycisk_wyszukaj)
	{ //zwraca arrayliste checbox,pole,checbox,pole, itd z ktorych bede pobieral dane oraz pole polecenia na miejscu 0
	
		ArrayList<Component> pola_danych =new ArrayList<Component>();
		ramka_wyszukiwania=new JFrame(nazwa);
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(tablica_pol.length,2,2,2));
		
		JTextField field=new JTextField();
		
		for(int i=0;i<tablica_pol.length;i++)
		{
			JCheckBox c=new JCheckBox(tablica_pol[i]);
			pola_danych.add(c);
			panel.add(c);
			field=new JTextField("");
			panel.add(field);
			pola_danych.add(field);
		}
		wyniki_wyszukiwania=new JTextArea(komunikat);
		wyniki_wyszukiwania.setEditable(false);
		JScrollPane przewijanie_wyniki=new JScrollPane(wyniki_wyszukiwania);
		przewijanie_wyniki.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		przewijanie_wyniki.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		ramka_wyszukiwania.add(BorderLayout.WEST,panel);
		ramka_wyszukiwania.add(BorderLayout.SOUTH,przycisk_wyszukaj);
		ramka_wyszukiwania.add(BorderLayout.CENTER,przewijanie_wyniki);
		ramka_wyszukiwania.setLocation(200, 150);
		ramka_wyszukiwania.pack();
		ramka_wyszukiwania.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ramka_wyszukiwania.setVisible(true);
		
		return pola_danych;
	}
	
	public String[] pobierz_dane(ArrayList<Component> pola)
	{//pobiera dane z pol tekstowych i zwraca w tablicy
		String[] dane=new String[pola.size()/2];
		for(int i=0;i<pola.size();i=i+2)
		{
			JCheckBox checkBox=((JCheckBox) pola.get(i));
			JTextField field=((JTextField) pola.get(i+1));
			if(checkBox.isSelected())
				dane[i/2]=field.getText();
			else 
				dane[i/2]=null;	
		}
		return dane;
	}
	
	public int int_do_wyszukiwania(String s)
	{
		if(s==null)
			return -1;
		else {
			return Integer.parseInt(s);
		}
	}
	
	public double double_do_wyszukiwania(String s)
	{
		if(s==null)
			return -1;
		else {
			return Double.parseDouble(s);
		}
	}
	
	public char plec_do_wyszukiwania(String s)
	{
		if(s==null)
			return '-';
		else {
			return Character.toUpperCase(s.charAt(0));
		}
	}
	
	class przycisk_wyszukaj_osobe_listener implements ActionListener
	{
		ArrayList<Component> lista_danych;
		String[] lista_pol= {"Imie:","Nazwisko:","Pesel:","Wiek od:","Wiek do:","Plec:"};
		ArrayList<Osoba> wynik;
		boolean czy_wyszukane=false;
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton przycisk_wyszukaj=new JButton("Wyszukaj");
			przycisk_wyszukaj.addActionListener(new wyszukaj_Listener());
			lista_danych=tworz_okno_wyszukiwania("Wyszukaj ", "Wybierz kryteria wyszukiwania", lista_pol, przycisk_wyszukaj);
		}
		
		class wyszukaj_Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] dane=pobierz_dane(lista_danych);
				wyniki_wyszukiwania.setText("Wyniki wyszukiwania dla ");
				for(int i=0;i<dane.length;i++)
				{
					if(dane[i]!=null)
						wyniki_wyszukiwania.append(lista_pol[i]+" "+dane[i]+", ");
				}
				wyniki_wyszukiwania.append("\n");
				wynik=Wyszukiwanie_osob.wyszukaj_osobe_po_parametrach(dane[0], dane[1], dane[2], int_do_wyszukiwania(dane[3]), int_do_wyszukiwania(dane[4]), plec_do_wyszukiwania(dane[5]), uczelnia.getLudzie());
				if(!wynik.isEmpty())
					czy_wyszukane=true;
				if(menu.wypisz_JTextArea(wynik,wyniki_wyszukiwania));
				{
					for(int i=0;i<lista_danych.size();i=i+2)
					{
						((JCheckBox) lista_danych.get(i)).setSelected(false);
						((JTextField) lista_danych.get(i+1)).setText("");
					}
				}
				ramka_wyszukiwania.pack();
				
			}
			
		}
	}
	
	class przycisk_wyszukaj_kurs_listener implements ActionListener
	{
		ArrayList<Component> lista_danych;
		String[] lista_pol= {"Nazwa:","Imie prowadzacego:","Nazwisko prowadzacego:","Minimalna liczba punktow ECTS:","Maksymalna liczba punktow ECTS:"};
		ArrayList<Kurs> wynik;
		boolean czy_wyszukane=false;
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton przycisk_wyszukaj=new JButton("Wyszukaj");
			przycisk_wyszukaj.addActionListener(new wyszukaj_Listener());
			lista_danych=tworz_okno_wyszukiwania("Wyszukaj ", "Wybierz kryteria wyszukiwania", lista_pol, przycisk_wyszukaj);
		}
		class wyszukaj_Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] dane=pobierz_dane(lista_danych);
				wyniki_wyszukiwania.setText("Wyniki wyszukiwania dla ");
				for(int i=0;i<dane.length;i++)
				{
					if(dane[i]!=null)
						wyniki_wyszukiwania.append(lista_pol[i]+" "+dane[i]+", ");
				}
				wyniki_wyszukiwania.append("\n");
				wynik=Wyszukiwanie_zajec.wyszukaj_kurs_po_parametrach(dane[0], dane[1], dane[2], int_do_wyszukiwania(dane[3]), int_do_wyszukiwania(dane[4]), uczelnia.getWszystkie_kursy());
				if(!wynik.isEmpty())
					czy_wyszukane=true;
				if(menu.wypisz_JTextArea(wynik,wyniki_wyszukiwania));
				{
					for(int i=0;i<lista_danych.size();i=i+2)
					{
						((JCheckBox) lista_danych.get(i)).setSelected(false);
						((JTextField) lista_danych.get(i+1)).setText("");
					}
				}
				ramka_wyszukiwania.pack();
				ramka_wyszukiwania.repaint();
				
			}
			
		}
	}
	
	class przycisk_wyszukaj_studenta_listener implements ActionListener
	{
		ArrayList<Component> lista_danych;
		String[] lista_pol= {"Imie:","Nazwisko:","Numer indeksu","Rok studiow:"};
		ArrayList<Student> wynik;
		boolean czy_wyszukane=false;
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton przycisk_wyszukaj=new JButton("Wyszukaj");
			przycisk_wyszukaj.addActionListener(new wyszukaj_Listener());
			lista_danych=tworz_okno_wyszukiwania("Wyszukaj ", "Wybierz kryteria wyszukiwania", lista_pol, przycisk_wyszukaj);
		}
		class wyszukaj_Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] dane=pobierz_dane(lista_danych);
				wyniki_wyszukiwania.setText("Wyniki wyszukiwania dla ");
				for(int i=0;i<dane.length;i++)
				{
					if(dane[i]!=null)
						wyniki_wyszukiwania.append(lista_pol[i]+" "+dane[i]+", ");
				}
				wyniki_wyszukiwania.append("\n");
				wynik=Wyszukiwanie_osob.wyszukaj_studenta_po_parametrach(dane[0], dane[1], dane[2], int_do_wyszukiwania(dane[3]),uczelnia.getLudzie());
				if(!wynik.isEmpty())
					czy_wyszukane=true;
				if(menu.wypisz_JTextArea(wynik,wyniki_wyszukiwania));
				{
					for(int i=0;i<lista_danych.size();i=i+2)
					{
						((JCheckBox) lista_danych.get(i)).setSelected(false);
						((JTextField) lista_danych.get(i+1)).setText("");
					}
				}
				ramka_wyszukiwania.pack();
				
			}
			
		}
	}
	
	class przycisk_wyszukaj_studentow_po_kursie_listener implements ActionListener
	{
		JList<Kurs> opcje_kursow;
		void utworz_okno_po_kursie()
		{
			ramka_wyszukiwania=new JFrame("Wyszukiwanie stuenta po kursie");
			
			ArrayList<Kurs> kursy=uczelnia.getWszystkie_kursy();
			Kurs[] lista_kursow=new Kurs[kursy.size()];
			for(int i=0;i<kursy.size();i++)
				lista_kursow[i]=kursy.get(i);
			opcje_kursow=new JList<Kurs>(lista_kursow);
			opcje_kursow.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			JScrollPane przewijanie_opcje_kursow=new JScrollPane(opcje_kursow);
			przewijanie_opcje_kursow.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			przewijanie_opcje_kursow.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			ramka_wyszukiwania.add(BorderLayout.WEST,przewijanie_opcje_kursow);
			
			JButton przycisk_wyszukaj_studentow_po_kursie=new JButton("Wyszukaj studentow po kursie");
			przycisk_wyszukaj_studentow_po_kursie.addActionListener(new wyszukaj_listener());
			ramka_wyszukiwania.add(BorderLayout.SOUTH,przycisk_wyszukaj_studentow_po_kursie);
			
			wyniki_wyszukiwania=new JTextArea("Wybierz kurs, aby zobaczyc przypisanych do niego studentow");
			if(kursy.isEmpty())
				wyniki_wyszukiwania.setText("W Uczelni nie ma jeszcze żadnego kursu");
			JScrollPane przewijanie_wyniki =new JScrollPane(wyniki_wyszukiwania);
			przewijanie_wyniki.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			przewijanie_wyniki.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			ramka_wyszukiwania.add(przewijanie_wyniki);
			ramka_wyszukiwania.pack();
			ramka_wyszukiwania.setVisible(true);
			
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			utworz_okno_po_kursie();
			
			
		}
		
		class wyszukaj_listener implements ActionListener
		{
			@Override
		public void actionPerformed(ActionEvent e) {
			Kurs kurs=(Kurs) opcje_kursow.getSelectedValue();
			wyniki_wyszukiwania.setText("Wszyscy studenci zapisani na "+kurs+"\n");
			menu.wypisz_JTextArea(Wyszukiwanie_osob.wyszukaj_studenta_po_kursie(kurs,uczelnia.getLudzie()), wyniki_wyszukiwania);
			ramka_wyszukiwania.pack();
		}
		}

		
	}
	
	class przycisk_wyszukaj_pracownika_listener implements ActionListener
	{
		ArrayList<Component> lista_danych;
		String[] lista_pol= {"Imie:","Nazwisko:","Stanowisko:","Staz od:","Staz do:","Minimalna liczba nadgodziny:","Maksymalna liczba nadgodzin:","Pensja od","Pensja do"};
		ArrayList<Pracownik_uczelni> wynik;
		boolean czy_wyszukane=false;
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton przycisk_wyszukaj=new JButton("Wyszukaj");
			przycisk_wyszukaj.addActionListener(new wyszukaj_Listener());
			lista_danych=tworz_okno_wyszukiwania("Wyszukaj ", "Wybierz kryteria wyszukiwania", lista_pol, przycisk_wyszukaj);
		}
		class wyszukaj_Listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] dane=pobierz_dane(lista_danych);
				wyniki_wyszukiwania.setText("Wyniki wyszukiwania dla ");
				for(int i=0;i<dane.length;i++)
				{
					if(dane[i]!=null)
						wyniki_wyszukiwania.append(lista_pol[i]+" "+dane[i]+", ");
				}
				wyniki_wyszukiwania.append("\n");
				wynik=Wyszukiwanie_osob.wyszukaj_pracownika_po_parametrach(dane[0], dane[1], dane[2], int_do_wyszukiwania(dane[3]), int_do_wyszukiwania(dane[4]), int_do_wyszukiwania(dane[5]), int_do_wyszukiwania(dane[6]),double_do_wyszukiwania(dane[7]) , double_do_wyszukiwania(dane[8]), uczelnia.getLudzie());
				if(!wynik.isEmpty())
					czy_wyszukane=true;
				if(menu.wypisz_JTextArea(wynik,wyniki_wyszukiwania));
				{
					for(int i=0;i<lista_danych.size();i=i+2)
					{
						((JCheckBox) lista_danych.get(i)).setSelected(false);
						((JTextField) lista_danych.get(i+1)).setText("");
					}
				}	
				ramka_wyszukiwania.pack();
			}
		}
	}
	
	class przycisk_wyszukaj_grupe_listener implements ActionListener
	{
		JCheckBox bez_duplikatow;
		ArrayList<?> do_wypisania;
		
		void utworz_okieno_wyszukiwania_wielu()
		{
			bez_duplikatow=new JCheckBox("Wyświetl wyniki bez duplikatow");
			
			ramka_wyszukiwania=new JFrame("Wyszukaj wiele osob");
			JPanel panel_guzikow=new JPanel();
			panel_guzikow.setLayout(new BoxLayout(panel_guzikow, BoxLayout.Y_AXIS));
			
			panel_guzikow.add(bez_duplikatow);
			
			JButton przycisk_wyszukaj_osobe=new JButton("Wyszukaj wszystkie osoby");
			przycisk_wyszukaj_osobe.addActionListener(new wyszukaj_wszystkie_osoby_listener());
			panel_guzikow.add(przycisk_wyszukaj_osobe);
				
			JButton przycisk_wyszukaj_kurs=new JButton("Wyszukaj wszystkie kursy");
			przycisk_wyszukaj_kurs.addActionListener(new wyszukaj_wszystkie_kursy_listener());
			panel_guzikow.add(przycisk_wyszukaj_kurs);
				
			JButton przycisk_wyszukaj_pracownika=new JButton("Wyszukaj wszystkich pracowniow");
			przycisk_wyszukaj_pracownika.addActionListener(new wyszukaj_wszystkich_pracownikow_listener());
			panel_guzikow.add(przycisk_wyszukaj_pracownika);
				
			JButton przycisk_wyszukaj_pracownika_adm=new JButton("Wyszukaj wszystkich pracownikow administracyjnych");
			przycisk_wyszukaj_pracownika_adm.addActionListener(new wyszukaj_wszystkich_pracownikow_administracyjnych_listener());
			panel_guzikow.add(przycisk_wyszukaj_pracownika_adm);
			
			JButton przycisk_wyszukaj_pracownika_BD=new JButton("Wyszukaj wszystkich pracownikow badawczo-dydaktycznych");
			przycisk_wyszukaj_pracownika_BD.addActionListener(new wyszukaj_wszystkich_pracownikow_BD_listener());
			panel_guzikow.add(przycisk_wyszukaj_pracownika_BD);
			
			JButton przycisk_wyszukaj_studentow=new JButton("Wyszukaj wszystkich studentow");
			przycisk_wyszukaj_studentow.addActionListener(new wyszukaj_wszystkich_studentow_listener());
			panel_guzikow.add(przycisk_wyszukaj_studentow);
			
			wyniki_wyszukiwania=new JTextArea("");
			JScrollPane przewijanie_wyniki =new JScrollPane(wyniki_wyszukiwania);
			przewijanie_wyniki.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			przewijanie_wyniki.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			ramka_wyszukiwania.add(BorderLayout.WEST,panel_guzikow);
			ramka_wyszukiwania.add(BorderLayout.CENTER,przewijanie_wyniki);
			ramka_wyszukiwania.pack();
			ramka_wyszukiwania.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
				utworz_okieno_wyszukiwania_wielu();
		}
		
		class wyszukaj_wszystkie_osoby_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszystkie osoby na uczelni\n");
				if(bez_duplikatow.isSelected())
					do_wypisania=uczelnia.bez_duplikatow(uczelnia.getLudzie());
				else 
					do_wypisania=(ArrayList<?>) uczelnia.getLudzie().clone();
				
				menu.wypisz_JTextArea( do_wypisania,wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
			}
		}
		
		class wyszukaj_wszystkie_kursy_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszystkie kursy na uczelni\n");
				menu.wypisz_JTextArea((ArrayList<?>) uczelnia.getWszystkie_kursy().clone(), wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
				
			}
		}
		
		class wyszukaj_wszystkich_pracownikow_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszyscy pracownicy na uczelni\n");
				if(bez_duplikatow.isSelected())
					do_wypisania=uczelnia.bez_duplikatow(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow(uczelnia.getLudzie()));
				else 
					do_wypisania=Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow(uczelnia.getLudzie());
				menu.wypisz_JTextArea(do_wypisania, wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
			}
		}
		
		class wyszukaj_wszystkich_pracownikow_administracyjnych_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszyscy pracownicy administracyjni na uczelni\n");
				if(bez_duplikatow.isSelected())
					do_wypisania=uczelnia.bez_duplikatow(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_administacyjnych(uczelnia.getLudzie()));
				else 
					do_wypisania=Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_administacyjnych(uczelnia.getLudzie());
				menu.wypisz_JTextArea(do_wypisania, wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
			}
		}
		
		class wyszukaj_wszystkich_pracownikow_BD_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszyscy pracownicy badawczo-dydaktyczni na uczelni\n");
				if(bez_duplikatow.isSelected())
					do_wypisania=uczelnia.bez_duplikatow(Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_badawczo_dydaktycznych(uczelnia.getLudzie()));
				else 
					do_wypisania=Wyszukiwanie_osob.wyszukaj_wszystkich_pracownikow_badawczo_dydaktycznych(uczelnia.getLudzie());
				menu.wypisz_JTextArea(do_wypisania, wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
			}
		}
		
		class wyszukaj_wszystkich_studentow_listener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				wyniki_wyszukiwania.setText("Wszyscy studenci na uczelni\n");
				if(bez_duplikatow.isSelected())
					do_wypisania=uczelnia.bez_duplikatow(Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie()));
				else 
					do_wypisania=Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie());
				menu.wypisz_JTextArea(do_wypisania, wyniki_wyszukiwania);
				ramka_wyszukiwania.pack();
			}
		}
		
	}
}