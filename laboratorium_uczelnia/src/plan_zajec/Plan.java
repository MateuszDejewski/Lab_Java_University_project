package plan_zajec;


import java.io.*;
import klasy_definiujace.Kurs;


public class Plan {
	
	private Kurs[][] plan;
	private String[] godziny= {"7:00 - 9:00","9:00 -  11:00","11:00 - 13:00","13:00 - 15:00","15:00 - 17:00","17:00 - 19:00","19:00 - 21:00"};
	private static String[] dnitygodnia = {"poniedziałek", "wtorek","środa","czwartek","piątek","sobota","niedziela"};
	private String nazwa;
	
	public Plan(String nazwa) {
		plan=new Kurs[7][7];
		this.setNazwa(nazwa);
	}
	
	public Plan(String nazwa,String[] godziny)
	{
		this.godziny=godziny;
		plan=new Kurs[godziny.length][7];
		this.setNazwa(nazwa);
	}
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String[] getGodziny()
	{
		return godziny;
	}
	
	public Kurs[][] getPlan()
	{
		return plan;
	}
	
	public String[] getDnitygodnia()
	{
		return dnitygodnia;
	}
	
	
	private int znajdz_wiersz(String czas)
	{
		for(int i=0;i<godziny.length;i++)
		{
			if(czas==godziny[i])
				return i;
		}
		return -1;
	}
	
	private int znajdz_kolumne(String dzientygodnia)
	{
		for(int i=0;i<dnitygodnia.length;i++)
		{
			if(dzientygodnia==dnitygodnia[i])
				return i;
		}
		return -1;
	}
	
	public boolean add_to_plan(Kurs kurs, String czas, String dzientygodnia)
	{
		int k=znajdz_kolumne(dzientygodnia),w=znajdz_wiersz(czas);
		
		if(k!=-1 && w!=-1 && plan[w][k]==null)
		{
			plan[w][k]=kurs;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean add_to_plan(Kurs k, int nr_wiersza, int nr_kolumny)
	{
		if(plan[nr_wiersza][nr_kolumny]==null)
		{
			plan[nr_wiersza][nr_kolumny]=k;
			return true;
		}
		return false;
	}
	
	public boolean is_empty(String czas, String dzientygodnia)
	{
		int k=znajdz_kolumne(dzientygodnia),w=znajdz_wiersz(czas);
		
		if(k!=-1 && w!=-1 && plan[w][k]==null)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean is_empty(int nr_wiersza, int nr_kolumny)
	{
		if(nr_kolumny>=0 &&nr_kolumny<plan[nr_wiersza].length && nr_wiersza>=0 &&nr_wiersza<plan.length && plan[nr_wiersza][nr_kolumny]==null)
		{
			return true;
		}
		return false;
	}
	
	public boolean contains_kurs(Kurs k)
	{
		for(int i=0;i<plan.length;i++)
		{
			for(int j=0;j<plan[i].length;j++)
			{
				if(plan[i][j]!=null && plan[i][j].equals(k))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void remove_from_plan(Kurs k)
	{
		for(int i=0;i<plan.length;i++)
		{
			for(int j=0;j<plan[i].length;j++)
			{
				if(plan[i][j].equals(k))
				{
					plan[i][j]=null;
					
				}
			}
		}
	}
	
	public void remove_from_plan(String dzientygodnia,String czas)
	{
		int k=znajdz_kolumne(dzientygodnia),w=znajdz_wiersz(czas);
		
		if(k!=-1 && w!=-1)
		{
			plan[w][k]=null;
		}
	}
	
	public void remove_from_plan(int nr_wiersza,int nr_kolumny)
	{		
			plan[nr_wiersza][nr_kolumny]=null;
	}
	
	public boolean eksportuj_plan(String nazwa) 
	{
		try(PrintWriter wypisz=new PrintWriter(new FileWriter(new File("C:/Users/mateu/git/Lab_Java_University_project/laboratorium_uczelnia/Plany_zajec/"+nazwa+".txt"))))
		{
		wypisz.println(nazwa);
		wypisz.print("                  ");
		for(int i=0;i<dnitygodnia.length;i++)
			wypisz.printf("%-60s",dnitygodnia[i]);
		
		for(int i=0;i<plan.length;i++)
		{
			wypisz.printf("\n%-17s ", godziny[i]);
			for(int j=0;j<plan[i].length;j++)
			{
				if(plan[i][j]!=null)
					wypisz.printf("%-60s",plan[i][j].toString().substring(0, plan[i][j].toString().length()-6));
				else {
					wypisz.printf("%-60s", " ");
				}
			}
		}
		wypisz.println("\n");
		
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void wypisz_plan()
	{
		System.out.println(nazwa);
		System.out.print("                  ");
		for(int i=0;i<dnitygodnia.length;i++)
			System.out.printf("%-60s",dnitygodnia[i]);
		
		for(int i=0;i<plan.length;i++)
		{
			System.out.printf("\n%-17s ", godziny[i]);
			for(int j=0;j<plan[i].length;j++)
			{
				if(plan[i][j]!=null)
					System.out.printf("%-60s",plan[i][j].toString().substring(0, plan[i][j].toString().length()-6));
				else {
					System.out.printf("%-60s", " ");
				}
			}
		}
		System.out.println("\n");
	}
}
