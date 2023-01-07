package sortowanie;

import java.io.Serializable;
import java.util.Comparator;
import klasy_definiujace.Kurs;
public class Sort_by_lecturer implements Comparator<Kurs>,Serializable {

	private static final long serialVersionUID = -3820444394992021647L;

	public Sort_by_lecturer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Kurs o1, Kurs o2) {
		return o1.getNazwisko_prowadzacego().compareTo(o2.getNazwisko_prowadzacego());
	}
	
	public String toString()
	{
		return "Sortowanie po nazwisku prowadzacego";
	}
	public boolean equals(Object o)
	{
		if(o instanceof Sort_by_lecturer)
			return true;
		return false;
	}
}	
