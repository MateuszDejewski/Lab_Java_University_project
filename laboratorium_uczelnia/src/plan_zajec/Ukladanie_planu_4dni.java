package plan_zajec;

import java.util.ArrayList;
import java.util.List;

import klasy_definiujace.Kurs;
import main.Uczelnia;

public class Ukladanie_planu_4dni implements Sposob_ukladania_planu{

	private int dzien_wolny;
	private Sposob_ukladania_planu sposob;
	public Ukladanie_planu_4dni(int dzien_wolny, Sposob_ukladania_planu sposob) {
		setDzien_wolny(dzien_wolny);
		setSposob(sposob);
	}
	public int getDzien_wolny() {
		return dzien_wolny;
	}
	public void setDzien_wolny(int dzien_wolny) {
		if(dzien_wolny>=1 &&dzien_wolny<=7)
			this.dzien_wolny = dzien_wolny;
	}
	public Sposob_ukladania_planu getSposob() {
		return sposob;
	}
	public void setSposob(Sposob_ukladania_planu sposob) {
		this.sposob = sposob;
	}
	
	@Override
	public ArrayList<Plan> uloz_plany(Uczelnia u) {
		ArrayList<Kurs> wszystkie_kursy=u.getWszystkie_kursy();
		ArrayList<Plan> wynikowa=new ArrayList<Plan>();
		int liczba_kursow=u.getWszystkie_kursy().size(),liczba_sal=u.getLiczba_sal();
		int kursy_na_sale=liczba_kursow/liczba_sal;
		int i;
		for(i=1;i<liczba_sal;i++)
		{
				
			wynikowa.add(uloz_plan(wszystkie_kursy.subList((i-1)*kursy_na_sale,i*kursy_na_sale), "Sala nr "+i));
			
		}
		wynikowa.add(uloz_plan(wszystkie_kursy.subList(i*kursy_na_sale,wszystkie_kursy.size()), "Sala nr "+i));
		return wynikowa;
	}
	
	@Override
	public Plan uloz_plan(List<Kurs> kursy, String nazwa) {
	Plan plan=sposob.uloz_plan(kursy, nazwa);
	ArrayList<Kurs> do_przelozenia =new ArrayList<Kurs>();
	for(int i=0;i<plan.getGodziny().length;i++)
	{
		if(!plan.is_empty(i, (dzien_wolny-1)))
		{
			do_przelozenia.add(plan.getPlan()[i][dzien_wolny-1]);
			plan.remove_from_plan(i, (dzien_wolny-1));
		}
	}
	int w=0,k=0;
	while(!do_przelozenia.isEmpty() && w<plan.getGodziny().length && k<(plan.getDnitygodnia().length-2))
	{
		if(plan.is_empty(w, k) && k!=(dzien_wolny-1))
			{
				plan.add_to_plan(do_przelozenia.get(0), w, k);
				do_przelozenia.remove(0);
			}
		w++;
		if(w>=plan.getGodziny().length)
		{
			w=0;
			k++;
		}
	}
	return plan;
	}
	
}
