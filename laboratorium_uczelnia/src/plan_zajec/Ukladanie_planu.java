package plan_zajec;

import main.*;
import obserwator.Observer;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.ArrayList;


public class Ukladanie_planu implements Observer{

	private Sposob_ukladania_planu sposob;
	Uczelnia uczelnia;
	
	public Ukladanie_planu(Uczelnia uczelnia) {
		this.uczelnia=uczelnia;
		wybor_sposobu();
	}
	
	public Sposob_ukladania_planu getSposob() {
		return sposob;
	}

	public void setSposob(Sposob_ukladania_planu sposob) {
		this.sposob = sposob;
	}

	public void wybor_sposobu()
	{

		int liczba_kursow=uczelnia.getWszystkie_kursy().size(),liczba_sal=uczelnia.getLiczba_sal();
		if(liczba_kursow>25 || liczba_sal==1)
			setSposob(new Ukladanie_planu_po_kolei());
		if((liczba_kursow/liczba_sal)<=30)
			setSposob(new Ukladanie_planu_rownomiernie());
		if((liczba_kursow/liczba_sal)<=15)
			setSposob(new Ukladanie_planu_4dni(Random_objects.randomInt(5)+1,new Ukladanie_planu_rownomiernie() ));
		
	}
	
	public Plan uloz_plan(String nazwa)
	{
		return getSposob().uloz_plan(uczelnia.getWszystkie_kursy(),nazwa);
	}
	
	public ArrayList<Plan> uloz_plany()
	{
		return getSposob().uloz_plany(uczelnia);
	}
	
	public void wypisz_plany(ArrayList<Plan> plany)
	{
		plany.forEach((n)->n.wypisz_plan());
	}
	
	public void eksportuj_plany(ArrayList<Plan> plany)
	{
		plany.forEach((n)-> n.eksportuj_plan(n.getNazwa()));
	}
	/*
	public static void main(String[] args) {
		
		Uczelnia test= new Uczelnia(5);
		for(int i=0;i<3;i++)
		{
			test.dodaj_osobe(Random_objects.pracownik_administracyjny());
			test.dodaj_osobe(Random_objects.pracownik_badawczo_dydaktyczny());
			test.dodaj_osobe(Random_objects.student());
			for(int j=0;j<46;j++) 
			{
				Kurs kurs=Random_objects.kurs();
				((Student)test.getLudzie().get(test.getLudzie().size()-1)).add_kurs(kurs);
				test.getWszystkie_kursy().add(kurs);
			}
		}
		Ukladanie_planu testPlanu=new Ukladanie_planu(test);
		testPlanu.setSposob(new Ukladanie_planu_4dni(1,new Ukladanie_planu_rownomiernie()));
		testPlanu.eksportuj_plany(testPlanu.uloz_plany());
		testPlanu.wypisz_plany(testPlanu.uloz_plany());
		}
	*/

	@Override
	public void update() {
		System.out.println("*\nNastąpiła aktualizacja stanu uczelni i zmiana planu zajęć\nNowy paln znajduje się w plikach tekstowych\n*");
		eksportuj_plany(uloz_plany());
		
	}
}
