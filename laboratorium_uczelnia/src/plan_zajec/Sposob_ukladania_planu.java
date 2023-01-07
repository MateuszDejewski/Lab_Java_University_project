package plan_zajec;

import java.util.ArrayList;
import java.util.List;

import klasy_definiujace.Kurs;
import main.Uczelnia;

public interface Sposob_ukladania_planu {
	
	public ArrayList<Plan> uloz_plany(Uczelnia u);
	public Plan uloz_plan(List<Kurs> kursy,String nazwa);
	
	
}
