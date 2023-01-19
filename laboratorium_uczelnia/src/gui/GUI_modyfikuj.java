package gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import gui.GUI_wyszukaj.*;
import klasy_definiujace.*;
import main.Uczelnia;

public class GUI_modyfikuj {

	Uczelnia uczelnia;
	GUI_menu menu;
	GUI_wyszukaj wyszukiwanie;
	ArrayList<?> wyszukana_ArrayList;
	
	public GUI_modyfikuj(GUI_menu menu) {
		this.menu=menu;
		this.uczelnia=menu.uczelnia;
		wyszukiwanie=new GUI_wyszukaj(menu);
		
	}

	public JPanel tworz_opcje_modyfikacji()
	{
		JPanel panel_opcji_wyszukiwania=new JPanel();
		panel_opcji_wyszukiwania.setLayout(new BoxLayout(panel_opcji_wyszukiwania, BoxLayout.Y_AXIS));
			
		JButton przycisk_wyszukaj_osobe=new JButton("Usun osobe");
		przycisk_wyszukaj_osobe.addActionListener(new przycisk_usun_osobe_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_osobe);
			
		JButton przycisk_wyszukaj_kurs=new JButton("Usun kurs");
		przycisk_wyszukaj_kurs.addActionListener(new przycisk_usun_kurs_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_kurs);
			
		JButton przycisk_wyszukaj_studenta=new JButton("Usun studenta");
		przycisk_wyszukaj_studenta.addActionListener(new przycisk_usun_studenta_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_studenta);
			
		JButton przycisk_wyszukaj_pracownika=new JButton("Usun pracownika");
		przycisk_wyszukaj_pracownika.addActionListener(new przycisk_usun_pracownika_listener());
		panel_opcji_wyszukiwania.add(przycisk_wyszukaj_pracownika);
			
		return panel_opcji_wyszukiwania;
			
	}
	
	public void usun(ArrayList<?> lista)
	{
		if(lista!=null) {
		if(!lista.isEmpty())
			{
			if(lista.get(0) instanceof Osoba)
				uczelnia.usun_osoby((ArrayList<Osoba>)lista);
			if(lista.get(0) instanceof Kurs)
				uczelnia.usun_kursy((ArrayList<Kurs>) lista);
			}
		}
	}
	
	class usun_listener implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				usun(wyszukana_ArrayList);
				wyszukiwanie.wyniki_wyszukiwania.append("\n\n*USUNIETO*\n");
				wyszukiwanie.wyniki_wyszukiwania.setBackground(Color.red);
				wyszukiwanie.ramka_wyszukiwania.pack();
				((JButton)e.getSource()).setEnabled(false);
			}
			
		}
	
	class przycisk_usun_osobe_listener implements ActionListener, Runnable {

		przycisk_wyszukaj_osobe_listener przycisk_wyszukaj;
		@Override
		public void actionPerformed(ActionEvent e) {
			
		przycisk_wyszukaj=wyszukiwanie.new przycisk_wyszukaj_osobe_listener();
		przycisk_wyszukaj.actionPerformed(e);
		Thread wyszukanieThread = new Thread(this);
		wyszukanieThread.start();
			
		}

		@Override
		public void run() {
		wyszukana_ArrayList=przycisk_wyszukaj.wynik;
		while(wyszukana_ArrayList==null || wyszukana_ArrayList.isEmpty())
			{
			wyszukana_ArrayList=przycisk_wyszukaj.wynik;
			}
		
				wyszukiwanie.ramka_wyszukiwania.dispose();
				wyszukiwanie.ramka_wyszukiwania=new JFrame("Potwierdz usuniecie");
				JButton usun=new JButton("Usun rekordy");
				usun.addActionListener(new usun_listener());
				wyszukiwanie.ramka_wyszukiwania.add(BorderLayout.SOUTH,usun);
				wyszukiwanie.ramka_wyszukiwania.add(wyszukiwanie.wyniki_wyszukiwania);
				wyszukiwanie.ramka_wyszukiwania.pack();
				wyszukiwanie.ramka_wyszukiwania.setVisible(true);
				return;
			
		}
		
		
		
	}
	
	class przycisk_usun_kurs_listener implements ActionListener,Runnable
	{

		przycisk_wyszukaj_kurs_listener przycisk_wyszukaj;
		@Override
		public void actionPerformed(ActionEvent e) {
			
		przycisk_wyszukaj=wyszukiwanie.new przycisk_wyszukaj_kurs_listener();
		przycisk_wyszukaj.actionPerformed(e);
		Thread wyszukanieThread = new Thread(this);
		wyszukanieThread.start();

		}

		@Override
		public void run() {
		wyszukana_ArrayList=przycisk_wyszukaj.wynik;
		while(wyszukana_ArrayList==null || wyszukana_ArrayList.isEmpty())
			{
			wyszukana_ArrayList=przycisk_wyszukaj.wynik;
			}
		
				wyszukiwanie.ramka_wyszukiwania.dispose();
				wyszukiwanie.ramka_wyszukiwania=new JFrame("Potwierdz usuniecie");
				JButton usun=new JButton("Usun rekordy");
				usun.addActionListener(new usun_listener());
				wyszukiwanie.ramka_wyszukiwania.add(BorderLayout.SOUTH,usun);
				wyszukiwanie.ramka_wyszukiwania.add(wyszukiwanie.wyniki_wyszukiwania);
				wyszukiwanie.ramka_wyszukiwania.pack();
				wyszukiwanie.ramka_wyszukiwania.setVisible(true);
		
			
		}
		
	}
	
	class przycisk_usun_studenta_listener implements ActionListener,Runnable
	{
		przycisk_wyszukaj_studenta_listener przycisk_wyszukaj;
		@Override
		public void actionPerformed(ActionEvent e) {
			
		przycisk_wyszukaj=wyszukiwanie.new przycisk_wyszukaj_studenta_listener();
		przycisk_wyszukaj.actionPerformed(e);
		Thread wyszukanieThread = new Thread(this);
		wyszukanieThread.start();
			
		}

		@Override
		public void run() {
		wyszukana_ArrayList=przycisk_wyszukaj.wynik;
		while(wyszukana_ArrayList==null || wyszukana_ArrayList.isEmpty())
			{
			wyszukana_ArrayList=przycisk_wyszukaj.wynik;
			}
		
				wyszukiwanie.ramka_wyszukiwania.dispose();
				wyszukiwanie.ramka_wyszukiwania=new JFrame("Potwierdz usuniecie");
				JButton usun=new JButton("Usun rekordy");
				usun.addActionListener(new usun_listener());
				wyszukiwanie.ramka_wyszukiwania.add(BorderLayout.SOUTH,usun);
				wyszukiwanie.ramka_wyszukiwania.add(wyszukiwanie.wyniki_wyszukiwania);
				wyszukiwanie.ramka_wyszukiwania.pack();
				wyszukiwanie.ramka_wyszukiwania.setVisible(true);
		
			
		}
		
	}
	
	class przycisk_usun_pracownika_listener implements ActionListener,Runnable
	{

		przycisk_wyszukaj_pracownika_listener przycisk_wyszukaj;
		@Override
		public void actionPerformed(ActionEvent e) {
			
		przycisk_wyszukaj=wyszukiwanie.new przycisk_wyszukaj_pracownika_listener();
		przycisk_wyszukaj.actionPerformed(e);
		Thread wyszukanieThread = new Thread(this);
		wyszukanieThread.start();
			
		}

		@Override
		public void run() {
		wyszukana_ArrayList=przycisk_wyszukaj.wynik;
		while(wyszukana_ArrayList==null || wyszukana_ArrayList.isEmpty())
			{
			wyszukana_ArrayList=przycisk_wyszukaj.wynik;
			}
		
				wyszukiwanie.ramka_wyszukiwania.dispose();
				wyszukiwanie.ramka_wyszukiwania=new JFrame("Potwierdz usuniecie");
				JButton usun=new JButton("Usun rekordy");
				usun.addActionListener(new usun_listener());
				wyszukiwanie.ramka_wyszukiwania.add(BorderLayout.SOUTH,usun);
				wyszukiwanie.ramka_wyszukiwania.add(wyszukiwanie.wyniki_wyszukiwania);
				wyszukiwanie.ramka_wyszukiwania.pack();
				wyszukiwanie.ramka_wyszukiwania.setVisible(true);
		
			
		}
		
	}
	
	
}
