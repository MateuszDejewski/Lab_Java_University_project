package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import javax.swing.*;

import klasy_definiujace.*;
import plan_zajec.*;
import sortowanie.*;

public class GUI_ustawienia{
	
	GUI_menu menu;
	JFrame ramka_ustawien;
	JList<Integer> lista_sal;
	JList<Sposob_ukladania_planu> lista_sposobow_planu;
	JList<Object> lista_sort_osob;
	JList<Object> lista_sort_kursy;	
	
	
	public GUI_ustawienia(GUI_menu menu) {
		this.menu=menu;
	}
	
	public void utworz_okienko_ustawien()
	{
		ramka_ustawien=new JFrame("Ustawienia");
		JPanel panel_ustawien=new JPanel();
		panel_ustawien.setLayout(new GridLayout(4,2,2,2));
		
		Integer[] sale=new Integer[99];
		for(int i=1;i<=99;i++)
			sale[i-1]=i;
		lista_sal=new JList<Integer>(sale);
		lista_sal.setDropMode(DropMode.INSERT);
		JScrollPane przewijanie_lista_sal=new JScrollPane(lista_sal);
		lista_sal.setSelectedValue(menu.uczelnia.getLiczba_sal(), true);
		lista_sal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		przewijanie_lista_sal.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie_lista_sal.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTextArea saleArea=new JTextArea("Liczba sal");
		saleArea.setEditable(false);
		panel_ustawien.add(saleArea);
		panel_ustawien.add(przewijanie_lista_sal);
		
		JTextArea planArea = new JTextArea("Spasob ukladania planu");
		planArea.setEditable(false);
		panel_ustawien.add(planArea);
		
		Sposob_ukladania_planu[] sposob_planu= new Sposob_ukladania_planu[12];
		sposob_planu[0]=new Ukladanie_planu_rownomiernie();
		sposob_planu[1]=new Ukladanie_planu_po_kolei();
		int m=2;
		for(int i=1;i<=5;i++)
		{
			sposob_planu[m++]=new Ukladanie_planu_4dni(i, new Ukladanie_planu_rownomiernie());
			sposob_planu[m++]=new Ukladanie_planu_4dni(i, new Ukladanie_planu_po_kolei());
		}
		lista_sposobow_planu=new JList<Sposob_ukladania_planu>(sposob_planu);
		lista_sposobow_planu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for(Sposob_ukladania_planu s:sposob_planu)
		{
			if(s.equals(menu.ukladanie_planu.getSposob()))
					lista_sposobow_planu.setSelectedValue(s, true);
		}
		JScrollPane przewijanie_plan=new JScrollPane(lista_sposobow_planu);
		przewijanie_plan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie_plan.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel_ustawien.add(przewijanie_plan);
		
		JTextArea ludzieArea=new JTextArea("Sposob wyswietlania osob");
		ludzieArea.setEditable(false);
		panel_ustawien.add(ludzieArea);
		
		Object[] sort_osob={(Object)new Sort_by_surname(),(Object)new Sort_by_surname_and_name(),(Object)new Sort_by_surname_and_age()};
		lista_sort_osob =new JList<Object>(sort_osob);
		for(Object o:sort_osob)
		{
			if(((Comparator<Osoba>)o).equals(menu.sposob_wypisywania_osob))
					lista_sort_osob.setSelectedValue(o, true);
		}
		lista_sort_osob.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane przewijanie_osoby=new JScrollPane(lista_sort_osob);
		przewijanie_osoby.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie_osoby.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_ustawien.add(przewijanie_osoby);
		
		JTextArea kursyArea=new JTextArea("Sposob wyswietlania kursow");
		kursyArea.setEditable(false);
		panel_ustawien.add(kursyArea);
		
		Object[] sort_kursy={(Object)new Sort_by_ECTS(),(Object)new Sort_by_ECTS_and_surname(),(Object)new Sort_by_lecturer()};
		lista_sort_kursy =new JList<Object>(sort_kursy);
		for(Object o:sort_kursy)
		{
			if(((Comparator<Kurs>)o).equals(menu.sposob_wypisywania_kursow))
					lista_sort_kursy.setSelectedValue(o, true);
		}
		lista_sort_osob.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane przewijanie_kursy=new JScrollPane(lista_sort_kursy);
		przewijanie_kursy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		przewijanie_kursy.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel_ustawien.add(przewijanie_kursy);
		
		JButton zapisz_zmiany=new JButton("Zapisz zmiany");
		zapisz_zmiany.addActionListener(new zapisz_zmiany_listener());
		ramka_ustawien.add(BorderLayout.SOUTH,zapisz_zmiany);
		
		ramka_ustawien.add(panel_ustawien);
		ramka_ustawien.pack();
		ramka_ustawien.setVisible(true);
	}

	class zapisz_zmiany_listener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!lista_sal.isSelectionEmpty())
				menu.uczelnia.setLiczba_sal(lista_sal.getSelectedValue());
			if(!lista_sposobow_planu.isSelectionEmpty())
				menu.ukladanie_planu.setSposob(lista_sposobow_planu.getSelectedValue());
			if(!lista_sort_osob.isSelectionEmpty())
				menu.sposob_wypisywania_osob=(Comparator<Osoba>)lista_sort_osob.getSelectedValue();
			if(!lista_sort_kursy.isSelectionEmpty())
				menu.sposob_wypisywania_kursow=(Comparator<Kurs>) lista_sort_kursy.getSelectedValue();
			ramka_ustawien.dispose();
		}
		
	}
}
