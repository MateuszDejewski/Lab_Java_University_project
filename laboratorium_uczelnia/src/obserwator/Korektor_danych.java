package obserwator;

import java.util.ArrayList;

import klasy_wyszukujace.*;
import klasy_definiujace.*;
import main.*;

public class Korektor_danych implements Observer{

	private Uczelnia uczelnia;
	
	public Korektor_danych(Uczelnia uczelnia) {
		this.uczelnia=uczelnia;
	}

	public Uczelnia getUczelnia() {
		return uczelnia;
	}

	public void setUczelnia(Uczelnia uczelnia) {
		this.uczelnia = uczelnia;
	}

	public void sprawdz_kursy_u_studentow()
	{
		ArrayList<Kurs> wszystkie_kursy=uczelnia.getWszystkie_kursy();
		ArrayList<Student> studenci =Wyszukiwanie_osob.wyszukaj_wszystkich_studentow(uczelnia.getLudzie());
		
		for(Student s:studenci)
		{
			ArrayList<Kurs> kursy=(ArrayList<Kurs>) s.getKursy().clone();
			for(Kurs k: kursy)
			{
				if(!wszystkie_kursy.contains(k))
					s.remove_kurs(k);
			}
		}
	}
	
	
	
	@Override
	public void update() {
		sprawdz_kursy_u_studentow();
		//System.out.print("*\nDokonano weryfikacji danych\n*\n");
	}

}
