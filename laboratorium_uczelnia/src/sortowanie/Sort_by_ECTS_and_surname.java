package sortowanie;

import java.io.Serializable;
import java.util.Comparator;

import klasy_definiujace.Kurs;

public class Sort_by_ECTS_and_surname implements Comparator<Kurs>,Serializable {
	
	private static final long serialVersionUID = 4859724849265308717L;

	public Sort_by_ECTS_and_surname() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(Kurs o1, Kurs o2) {
		int wynik=o1.getEcts()-o2.getEcts();
		if(wynik!=0)
			return wynik;
		else {
			return o1.getNazwisko_prowadzacego().compareTo(o2.getNazwisko_prowadzacego());
		}
	}
	
	public String toString()
	{
		return "Sortowanie po liczbie punktow ECTS i nazwisku prowadzacego";
	}
	
	public boolean equals(Object o)
	{
		if(o instanceof Sort_by_ECTS_and_surname)
			return true;
		return false;
	}
}
