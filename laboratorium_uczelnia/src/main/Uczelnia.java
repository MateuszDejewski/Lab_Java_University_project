package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import gui.GUI_menu;
import klasy_definiujace.*;
import obserwator.Observable;
import obserwator.Observer;

public class Uczelnia implements Serializable, Observable  {
	
	private static final long serialVersionUID = -5635913568749821695L;

	transient private ArrayList<Observer> obserwatorzy;
	
	private ArrayList<Osoba> ludzie;
	private ArrayList<Kurs> wszystkie_kursy;
	private int liczba_sal=1;
	
	public Uczelnia() {
		setLudzie(new ArrayList<Osoba>());
		setWszystkie_kursy(new ArrayList<Kurs>());
		setObserwatorzy(new ArrayList<Observer>());
	}
	
	public Uczelnia(int liczba_sal)
	{
		setLudzie(new ArrayList<Osoba>());
		setWszystkie_kursy(new ArrayList<Kurs>());
		setLiczba_sal(liczba_sal);
		setObserwatorzy(new ArrayList<Observer>());
	}
	
	public int getLiczba_sal() {
		return liczba_sal;
	}

	public void setLiczba_sal(int liczba_sal) {
		if(liczba_sal>0)
			{
			this.liczba_sal = liczba_sal;
			notifyObservers();
			}
		
	}

	public ArrayList<Observer> getObserwatorzy() {
		return obserwatorzy;
	}

	public void setObserwatorzy(ArrayList<Observer> obserwatorzy) {
		this.obserwatorzy = obserwatorzy;
	}

	public ArrayList<Osoba> getLudzie() {
		return ludzie;
	}

	public void setLudzie(ArrayList<Osoba> ludzie) {
		if(ludzie!=null) {
		this.ludzie = ludzie;
		notifyObservers();
		}
	}
	
	public ArrayList<Kurs> getWszystkie_kursy() {
		return wszystkie_kursy;
	}

	public void setWszystkie_kursy(ArrayList<Kurs> wszystkie_kursy) {
		if(wszystkie_kursy!=null) {
		this.wszystkie_kursy = wszystkie_kursy;
		notifyObservers();
		}
	}
	
	public void dodaj_osobe(Osoba o)
	{
		if(o!=null) {
		ludzie.add(o);
		notifyObservers();
		}
	}
	
	public void usun_osobe(Osoba o)
	{
		if(o!=null) {
		ludzie.remove(o);
		notifyObservers();
		}
	}
	
	public void usun_osoby(ArrayList<? extends Osoba> o)
	{
		if(o!=null)	{
		ludzie.removeAll(o);
		notifyObservers();
		}
	}
	
	public void dodaj_kurs(Kurs k)
	{
		if(k!=null) {
		wszystkie_kursy.add(k);
		notifyObservers();
		}
	}
		
	public void usun_kurs(Kurs k)
	{
		if(k!=null) {
		wszystkie_kursy.remove(k);
		notifyObservers();
		}
	}
	
	public void usun_kursy(ArrayList<Kurs> k)
	{
		if(k!=null) {
		wszystkie_kursy.removeAll(k);
		notifyObservers();
		}
	}
	
	public <E extends Osoba> ArrayList<E> bez_duplikatow(ArrayList<E> lista)
	{
		HashSet<E> zbior_elementow=new HashSet<E>(lista);
		return new ArrayList<E>(zbior_elementow);
	}
	
	public static void main(String[] args) {
		
		Uczelnia test= new Uczelnia(3);
		GUI_menu gui=new GUI_menu(test);
		gui.utworz();
		
		/*
		
		
		for(int i=0;i<7;i++)
		{
			test.dodaj_osobe(Random_objects.pracownik_administracyjny());
			test.dodaj_osobe(Random_objects.pracownik_badawczo_dydaktyczny());
			test.dodaj_osobe(Random_objects.student());
			for(int j=0;j<6;j++) 
			{
				Kurs kurs=Random_objects.kurs();
				((Student)test.getLudzie().get(test.getLudzie().size()-1)).add_kurs(kurs);
				test.getWszystkie_kursy().add(kurs);
			}
		}
		Komunikacja_z_uzytkownikiem komunikator=new Komunikacja_z_uzytkownikiem(test);
		komunikator.menu();
		
		
		//Test funkcjonalności zaliczenie listy 5
		
		
	
		*/

	}


	@Override
	public void notifyObservers() {
		if (getObserwatorzy()!=null && !getObserwatorzy().isEmpty()) {
			getObserwatorzy().forEach((n)-> n.update());
		}
		
	}

	@Override
	public void addObserver(Observer o) {
		getObserwatorzy().add(o);
		
	}

	@Override
	public void removeObserver(Observer o) {
		getObserwatorzy().remove(o);
		
	}
}
