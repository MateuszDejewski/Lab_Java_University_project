package plan_zajec;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import klasy_definiujace.Kurs;
import main.Uczelnia;

public class Ukladanie_planu_rownomiernie implements Sposob_ukladania_planu,Serializable{

	private static final long serialVersionUID = -513303522623402682L;

	public Ukladanie_planu_rownomiernie() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Plan uloz_plan(List<Kurs> kursy,String nazwa) {
		
		Plan plan=new Plan(nazwa);
		int w=plan.getGodziny().length/2,k=0,i=1;
		for(Kurs kurs:kursy)
		{
			if(w<plan.getGodziny().length && w>=0 && k<plan.getDnitygodnia().length)
				plan.add_to_plan(kurs,w,k);
			else 
				break;
			k++;
			if(k>=plan.getDnitygodnia().length-2)
			{
				w+=i;
				k=0;
				if(i>0)
					i++;
				else 
					i--;
				i=i*(-1);
				
			}
		}
		
		return plan;
	
	}
	
	@Override
	public ArrayList<Plan> uloz_plany(Uczelnia u) {
		ArrayList<Kurs> wszystkie_kursy=u.getWszystkie_kursy();
		ArrayList<Plan> wynikowa=new ArrayList<Plan>();
		int liczba_kursow=u.getWszystkie_kursy().size(),liczba_sal=u.getLiczba_sal();
		int kursy_na_sale=liczba_kursow/liczba_sal;
		int i;
		for(i=1;i<=liczba_sal;i++)
		{
				wynikowa.add(uloz_plan(wszystkie_kursy.subList((i-1)*kursy_na_sale,Math.min(i*kursy_na_sale,wszystkie_kursy.size())), "Sala nr "+i));
			
		}
		return wynikowa;
	}
	
	public String toString()
	{
		return "Rownomiernie - zajecia sa ukladane od poniedzialku do piatku zaczynajac od srodkowych godzin";
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Ukladanie_planu_rownomiernie)
			return true;
		else 
			return false;	
	}
	
}
