package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import klasy_definiujace.Kurs;
import plan_zajec.Plan;

public class GUI_plan_zajec {

	GUI_menu menu;
	ArrayList<Plan> plany;
	JFrame ramka_planow;
	JPanel wyswietlany_plan;
	JTextField nazwa_planu;
	JButton przycisk_nastepny;
	JButton przycisk_poprzedni;
	int numer_plan=0;
	
	public GUI_plan_zajec(GUI_menu menu)
	{
		this.menu=menu;
		plany=menu.ukladanie_planu.uloz_plany();
	}
	
	public JPanel tworz_opcje_planu()
	{
		JPanel panel_plan=new JPanel();
		panel_plan.setLayout(new BoxLayout(panel_plan, BoxLayout.Y_AXIS));
			
		JButton przycisk_wyswietl=new JButton("Wyswietl plan");
		przycisk_wyswietl.addActionListener(new przycisk_wyswietl_listener());
		panel_plan.add(przycisk_wyswietl);
			
		JButton przycisk_eksport=new JButton("Eksportuj plan");
		przycisk_eksport.addActionListener(new przycisk_eksportuj_listener());
		panel_plan.add(przycisk_eksport);
					
		return panel_plan;
			
	}
	
	public void tworz_okienko_wyswietlania_planu()
	{
		ramka_planow=new JFrame("Plan zajec");
		wyswietlany_plan=wyswietl_plan(plany.get(0));
		nazwa_planu=new JTextField(plany.get(0).getNazwa());
		nazwa_planu.setEditable(false);
		
		przycisk_nastepny=new JButton("Nastepny");
		przycisk_nastepny.addActionListener(new nastepny_listener());
		
		przycisk_poprzedni=new JButton("Poprzedni");
		przycisk_poprzedni.addActionListener(new poprzedni_listener());
		przycisk_poprzedni.setEnabled(false);
		
		JPanel panel_przyciskow = new JPanel();
		panel_przyciskow.add(przycisk_poprzedni);
		panel_przyciskow.add(przycisk_nastepny);
		
		ramka_planow.add(BorderLayout.NORTH,nazwa_planu);
		ramka_planow.add(BorderLayout.SOUTH,panel_przyciskow);
		ramka_planow.add(wyswietlany_plan);
		ramka_planow.pack();
		ramka_planow.setVisible(true);
		
	}
	
	public JPanel wyswietl_plan(Plan p)
	{
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(p.getGodziny().length+1,p.getDnitygodnia().length+1,2,2)); 
		
		JTextArea pole=new JTextArea("\n");
		pole.setEditable(false);
		panel.add(pole);
		for(int i=0;i<p.getDnitygodnia().length;i++)
		{
			pole=new JTextArea(p.getDnitygodnia()[i]);
			pole.setEditable(false);
			panel.add(pole);
		}
		
		for(int i=0;i<p.getGodziny().length;i++)
		{
			pole=new JTextArea(p.getGodziny()[i]);
			pole.setEditable(false);
			panel.add(pole);
			
			for(int j=0;j<p.getDnitygodnia().length;j++)
			{
				if(p.getPlan()[i][j]==null)
					pole=new JTextArea("\n");
				else {
					Kurs kurs=p.getPlan()[i][j];
					String napis=String.format(" %s \n   %s %s",kurs.getNazwa(),kurs.getImie_prowadzacego(),kurs.getNazwisko_prowadzacego() );
					pole=new JTextArea(napis);
				}
				
				pole.setEditable(false);
				panel.add(pole);
			}
		}
		
		return panel;
	}
	
	public void zmien_wyswietlany_plan(Plan p)
	{
		nazwa_planu.setText(p.getNazwa());
		ramka_planow.remove(wyswietlany_plan);
		wyswietlany_plan=wyswietl_plan(p);
		ramka_planow.add(wyswietlany_plan);
		ramka_planow.pack();
		
	}
	
	class nastepny_listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
		numer_plan++;
		zmien_wyswietlany_plan(plany.get(numer_plan));
		przycisk_poprzedni.setEnabled(true);
		if(numer_plan==plany.size()-1)
			przycisk_nastepny.setEnabled(false);
		}
		
	}
	
	class poprzedni_listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			numer_plan--;
			zmien_wyswietlany_plan(plany.get(numer_plan));
			przycisk_nastepny.setEnabled(true);
			if(numer_plan==0)
				przycisk_poprzedni.setEnabled(false);
			}
		
	}
	
	class przycisk_wyswietl_listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			plany=menu.ukladanie_planu.uloz_plany();
			tworz_okienko_wyswietlania_planu();
			
		}
		
	}
	
	class przycisk_eksportuj_listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			plany=menu.ukladanie_planu.uloz_plany();
			menu.ukladanie_planu.eksportuj_plany(plany);
			menu.komunikaTextField.setText("Wyeksportowano plany do plikow txt");
			
		}
		
	}
}
