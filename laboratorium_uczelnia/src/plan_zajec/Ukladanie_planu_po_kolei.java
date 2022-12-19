package plan_zajec;

import java.util.ArrayList;
import java.util.List;

import main.Uczelnia;
import klasy_definiujace.*;

public class Ukladanie_planu_po_kolei implements Sposob_ukladania_planu {
	
	public Ukladanie_planu_po_kolei() {
		// TODO Auto-generated constructor stub
	}


	public Plan uloz_plan(List<Kurs> kursy,String nazwa) {
		
		Plan plan=new Plan(nazwa);
		int w=0,k=0;
		for(Kurs kurs:kursy)
		{
			if(w<plan.getGodziny().length && k<plan.getDnitygodnia().length)
				plan.add_to_plan(kurs,plan.getGodziny()[w], plan.getDnitygodnia()[k]);
			else 
				break;
			w++;
			if(w>=plan.getGodziny().length)
			{
				w=0;
				k++;
			}
		}
		
		return plan;
	}


	@Override
	public ArrayList<Plan> uloz_plany(Uczelnia u) {
		ArrayList<Kurs> wszystkie_kursy=u.getWszystkie_kursy();
		ArrayList<Plan> wynikowa=new ArrayList<Plan>();
		int liczba_kursow=u.getWszystkie_kursy().size(),liczba_sal=u.getLiczba_sal();
		for(int i=0;i<=liczba_kursow/35;i++)
		{
			if(i<liczba_sal)
				wynikowa.add(uloz_plan(wszystkie_kursy.subList(i*35,Math.min((i+1)*35,wszystkie_kursy.size())), "Sala nr "+(i+1)));
			
		}
		return wynikowa;
	}

}
