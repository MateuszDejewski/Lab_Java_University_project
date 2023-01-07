package sortowanie;

import java.io.Serializable;
import java.util.Comparator;

import klasy_definiujace.Osoba;



public class Sort_by_surname_and_name implements Comparator<Osoba>,Serializable
{
	private static final long serialVersionUID = 1437865993762542708L;

	@Override
	public int compare(Osoba o1, Osoba o2) {
		int wynik=o1.getNazwisko().compareTo(o2.getNazwisko());
		if(wynik!=0)
			return wynik;
		else {
			return o1.getImie().compareTo(o2.getImie());
		}
	}
	
	public String toString()
	{
		return "Sortowanie po nazwisku i imieniu";
	}
	public boolean equals(Object o)
	{
		if(o instanceof Sort_by_surname_and_name)
			return true;
		return false;
	}	
}


