package sortowanie;

import java.io.Serializable;
import java.util.Comparator;

import klasy_definiujace.Kurs;

public class Sort_by_ECTS implements Comparator<Kurs>,Serializable {

	private static final long serialVersionUID = 4875262948332796087L;
	
	public Sort_by_ECTS() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compare(Kurs o1, Kurs o2) {
		return o1.getEcts()-o2.getEcts();
	
	}
	public String toString()
	{
		return "Sortowanie po liczbie punktow ECTS (domyslne)";
	}
	public boolean equals(Object o)
	{
		if(o instanceof Sort_by_ECTS)
			return true;
		return false;
	}
}
