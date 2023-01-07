package sortowanie;

import java.io.Serializable;
import java.util.Comparator;

import klasy_definiujace.Osoba;

public class Sort_by_surname_and_age implements Comparator<Osoba>,Serializable{

	private static final long serialVersionUID = -6464256220330884708L;

	public Sort_by_surname_and_age() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Osoba o1, Osoba o2) {
		int wynik=o1.getNazwisko().compareTo(o2.getNazwisko());
		if(wynik!=0)
			return wynik;
		else {
			return o1.getWiek()-o2.getWiek();
		}
	}
	
	public String toString()
	{
		return "Sortowanie po nazwisku i wieku";
	}
	public boolean equals(Object o)
	{
		if(o instanceof Sort_by_surname_and_age)
			return true;
		return false;
	}
}
