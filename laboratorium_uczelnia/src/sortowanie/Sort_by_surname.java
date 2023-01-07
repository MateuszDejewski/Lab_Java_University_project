package sortowanie;

import java.io.Serializable;
import java.util.Comparator;
import klasy_definiujace.*;

	
	
public class Sort_by_surname implements Comparator<Osoba>,Serializable{

	private static final long serialVersionUID = -136589639592328100L;
		@Override
		public int compare(Osoba o1, Osoba o2) {
			return o1.getNazwisko().compareTo(o2.getNazwisko());
			
		}
		public String toString()
		{
			return "Sortowanie po nazwisku (domyslne)";
		}
		public boolean equals(Object o)
		{
			if(o instanceof Sort_by_surname)
				return true;
			return false;
		}
	}
	
	
	
	

