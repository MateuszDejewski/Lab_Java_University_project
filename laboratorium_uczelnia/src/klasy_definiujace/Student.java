package klasy_definiujace;

import java.util.ArrayList;

public class Student extends Osoba{

	private static final long serialVersionUID = -5866679790393063287L;
	private String numer_indeksu;
	private int rok_studiow;
	private ArrayList<Kurs> kursy;
	private boolean erasmus;
	private boolean I_stopien;
	private boolean II_stopien;
	private boolean stacjonarne;
	private boolean niestacjonarne;
	
	public Student(String imie,String nazwisko,String pesel,int wiek,char plec,String numer_indeksu,int rok_studiow)
	{
		super(imie, nazwisko, pesel, wiek, plec);
		setNumer_indeksu(numer_indeksu);
		setRok_studiow(rok_studiow);
		setErasmus(false);
		setI_stopien(true);
		setII_stopien(false);
		setStacjonarne(true);
		setNiestacjonarne(false);
		kursy=new ArrayList<Kurs>();
		
		
	}
	
	public Student(String imie,String nazwisko,String pesel,int wiek,char plec,String numer_indeksu,int rok_studiow,boolean erasmus,boolean stopien,boolean stacjonarne) {
		super(imie, nazwisko, pesel, wiek, plec);
		setNumer_indeksu(numer_indeksu);
		setRok_studiow(rok_studiow);
		setErasmus(erasmus);
		setI_stopien(stopien);
		setII_stopien(!stopien);
		setStacjonarne(stacjonarne);
		setNiestacjonarne(!stacjonarne);
		kursy=new ArrayList<Kurs>();
	}

	public String getNumer_indeksu() {
		return numer_indeksu;
	}

	public void setNumer_indeksu(String numer_indeksu) throws IllegalArgumentException {
		if(numer_indeksu.length()==6)
			this.numer_indeksu = numer_indeksu;
		else {
			throw new IllegalArgumentException("numer_indeksu");
		}
	}

	public int getRok_studiow() {
		return rok_studiow;
	}

	public void setRok_studiow(int rok_studiow) {
		this.rok_studiow = rok_studiow;
	}

	public ArrayList<Kurs> getKursy() {
		return kursy;
	}

	public void setKursy(ArrayList<Kurs> kursy) {
		this.kursy = kursy;
	}
	
	public void add_kurs(Kurs k)
	{
		kursy.add(k);
	}
	
	public boolean isErasmus() {
		return erasmus;
	}

	public void setErasmus(boolean erasmus) {
		this.erasmus = erasmus;
	}

	public boolean isI_stopien() {
		return I_stopien;
	}

	public void setI_stopien(boolean i_stopien) {
		I_stopien = i_stopien;
	}

	public boolean isII_stopien() {
		return II_stopien;
	}

	public void setII_stopien(boolean iI_stopien) {
		II_stopien = iI_stopien;
	}

	public boolean isStacjonarne() {
		return stacjonarne;
	}

	public void setStacjonarne(boolean stacjonarne) {
		this.stacjonarne = stacjonarne;
	}

	public boolean isNiestacjonarne() {
		return niestacjonarne;
	}

	public void setNiestacjonarne(boolean niestacjonarne) {
		this.niestacjonarne = niestacjonarne;
	}
	
	public String toString()
	{
		String opis=new String("Student:  ");
		opis=opis+super.toString();
		opis=opis+" numer indeksu: "+numer_indeksu+" "+rok_studiow+" rok ";
		if(I_stopien)
			opis+="I stopień";
		else 
			opis+="II stopień";
		if(stacjonarne)
			opis+=" stacjonarne";
		else 
			opis+=" niestacjonatne";
		opis+="\n	Kursy: ";
		for(Kurs k:kursy)
		{
			opis+=k.toString()+", ";
		}
		return opis;
	}

}
