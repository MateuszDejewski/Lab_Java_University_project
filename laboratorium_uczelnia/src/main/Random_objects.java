package main;

import java.util.Random;

import klasy_definiujace.*;

public class Random_objects {
	final Random g=new Random();
	private static String[] mozliwe_imiona={"Mateusz","Joanna","Krzysztof","Bartłomiej","Adam","Zofia"};
	private static String[] mozliwe_nazwiska= {"Król","Puterko","Kula","Borsuk","Żelwetro"};
	private static String[] mozliwe_nazwy_kursow= {"PSiO","Analiza_1","Analiza_2","Logika_dla_informatyków","Algebra","Bazy_danych","Matematyka_dyskretna","Fizyka_1","Fizyka_2"};
	
	public String imie()
	{
		return mozliwe_imiona[g.nextInt(mozliwe_imiona.length)];
	}
	
	public String nazwisko()
	{
		return mozliwe_nazwiska[g.nextInt(mozliwe_nazwiska.length)];
	}
	
	public String ciag_liczb(int n)
	{
		String napis=new String();
		for(int i=0;i<n;i++)
		{
			napis+=g.nextInt(10);
		}
		return napis;
	}
	
	public char plec(String imie)
	{
		if(imie.charAt(imie.length()-1)=='a')
			return 'K';
		else 
			return 'M';
		}
	
	public double pensja(int pensja_od,int pensja_do)
	{
		return g.nextInt(pensja_do-pensja_od)+g.nextDouble()+pensja_od;
	}
	
	public Student student()
	{	
		String imie=imie();
		return new Student(imie, nazwisko(), ciag_liczb(11), g.nextInt(20)+18, plec(imie), ciag_liczb(6), g.nextInt(5)+1, g.nextBoolean() , g.nextBoolean(), g.nextBoolean());
	}
	
	public Pracownik_administracyjny pracownik_administracyjny()
	{
		String stanowisko=Pracownik_administracyjny.mozliwe_stanowiska[g.nextInt(Pracownik_administracyjny.mozliwe_stanowiska.length)];
		String imie=imie();
		return new Pracownik_administracyjny(imie, nazwisko(), ciag_liczb(11), g.nextInt(40)+26, plec(imie), stanowisko, g.nextInt(20)+1, pensja(2400,12000), g.nextInt(25));
	}
	
	public Pracownik_Badawczo_Dydaktyczny pracownik_badawczo_dydaktyczny()
	{
		String stanowisko=Pracownik_Badawczo_Dydaktyczny.mozliwe_stanowiska[g.nextInt(Pracownik_Badawczo_Dydaktyczny.mozliwe_stanowiska.length)];
		String imie=imie();
		return new Pracownik_Badawczo_Dydaktyczny(imie,nazwisko(), ciag_liczb(11), g.nextInt(40)+26, plec(imie), stanowisko, g.nextInt(20)+1, pensja(2400,12000), g.nextInt(15));
	}

	
	public Kurs kurs()
	{
		return new Kurs(mozliwe_nazwy_kursow[g.nextInt(mozliwe_nazwy_kursow.length)], imie(), nazwisko(), g.nextInt(8)+1);
	}
}
