package projekt;

import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.text.*;
import java.util.*;

public class Logika {
	Plyty pl=new Plyty();
	 static Baza b=new Baza();
	/**
	 * ArrayLista przechowywujaca tymczasowe dane.
	 */
	 List<Plyty> temp=new ArrayList<Plyty>();
	/**
	 * Zmienna przechowywujaca format daty.
	 */
	 static DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
	 Integer lp=new Integer(1);
	/**
	 * indeks odpowiada za wprowadzanie numeru indeksu przez uzytkownika,
	 * n to licznik tabeli.
	 */
	 int indeks=0;
	static int n=0;
	/**
	 * wymaga posortowania zgodnie z lp, zabezpiecza przed modyfikacja danych.
	 */
	 static boolean blokadadane=false;
	public static void main(String[] args) throws ParseException, BladCena, BladOcena {		
		InterfejsGraf ig=new InterfejsGraf();
		
		if(args.length==0)
			ig.setVisible(true);
		
		if(args.length>0) {
			ig.setVisible(false);
			if(args[0].equals("wczytaj")) {
				InterfejsText.wyswietl();
			}

			if(args.length>1) {
				if(args[1].equals("lpsort")) {
					lpsort();
					InterfejsText.wyswietl();
				}
		
				if(args[1].equals("tytulsort")) {
					tytulsort();
					InterfejsText.wyswietl();
				}
					
				if(args[1].equals("cenasort")) {
					cenasort();
					InterfejsText.wyswietl();
				}
					
				if(args[1].equals("datasort")) {
					
					InterfejsText.wyswietl();
				}
			}
			System.exit(0);
		}
	}
		/**
		 * Umozliwia dodanie elementu do ArrayListy.
		 * @param snazwa nazwa pl podana przez uzytkownika.
		 * @param scena cena pl podana przez uzytkownika.
		 * @param data data wydania pl podana przez uzytkownika.
		 * @param sgatunek gatunek pl podany przez uzytkownika.
		 * @param socena ocena pl podana przez uzytkownika.
		 * @throws ParseException
		 * @throws BladCena wyjatek blednego formatu ceny pl.
		 * @throws BladOcena wyjatek blednego formatu oceny pl.
		 */
	public void dodaj(String snazwa, String scena, Date data, String sgatunek, String socena) throws ParseException, BladCena, BladOcena {
		int ocena;
		double cena;
		if(scena.matches("^[0-9]+.+[0-9]+$")) {
			cena=Double.parseDouble(scena);
			if(socena.matches("[1-6]")) {
				ocena=Integer.parseInt(socena);
			}
			else
				throw new BladOcena();
		}
		else
			throw new BladCena();
				
		Cecha c=new Cecha(sgatunek, ocena);
		pl.lista.add(new Plyty(lp, snazwa, cena, data, c));
		/**
		 * Wektor przechowywujacy dane wyswietlone w tabeli.
		 */
		Vector<Object> v=new Vector<Object>();
				
		v.add(lp);
		v.add(snazwa);
		v.add(cena);
		v.add(df.format(data));
		v.add(sgatunek);
		v.add(ocena);
		InterfejsGraf.dm.addRow(v);
		lp++;
		n++;
	}
	
	/**
	 * Umozliwia usuniecie elementu z listy.
	 * @param sindex numer indeksu podawany przez uzytkownika w celu usuniecia elementu.
	 * @throws BladIndeks wyjatek blednego formatu lub niepoprawnej liczby indeksu.
	 */
	public   void usun(String sindex) throws BladIndeks {
		if(sindex.matches("^[0-9]+$")) {
			indeks=Integer.parseInt(sindex);
			if(indeks>0 && indeks<=pl.lista.size()) {
				pl.lista.remove(indeks-1);
				InterfejsGraf.dm.removeRow(indeks-1);
				if(pl.lista.size()>=indeks-1) {
					lp--;
					for(int i=0; i<pl.lista.size(); i++) {
						Plyty p=pl.lista.get(i);
						int lp=p.getLp();
						if(i>=indeks-1)
							lp--;
						String nazwa=p.getNazwa();
						double cena=p.getCena();
						Date data=p.getData();
						Cecha c=p.getCecha();
						String gatunek=c.getGatunek();
						int ocena=c.getOcena();
						Cecha cech=new Cecha(gatunek, ocena);
						temp.add(new Plyty(lp, nazwa, cena, data, cech));
						InterfejsGraf.dm.setValueAt(lp, i, 0);
					}	
					pl.lista.clear();
					pl.lista.addAll(temp);
					temp.clear();
					if(n!=0)
						n--;
				}
			}
			else
				throw new BladIndeks();
		}
		else
			throw new BladIndeks();
	}
	
	/**
	 * Umozliwia zmodyfikowanie elementu listy parametry i wyjatki jak w metodach dodaj() i usun().
	 */
	public  void modyfikuj(String snazwa, String scena, Date data, String sgatunek, String socena, String sindex) throws ParseException, BladCena, BladOcena, BladIndeks {				
		int ocena;
		double cena;	
		if(scena.matches("^[0-9]+.+[0-9]+$")) {
			cena=Double.parseDouble(scena);
			if(socena.matches("[1-6]")) {
				ocena=Integer.parseInt(socena);
			}
			else
				throw new BladOcena();
		}
		else
			throw new BladCena();
				
		if(sindex.matches("^[0-9]+$")) {
			indeks=Integer.parseInt(sindex);
			if(indeks>0 && indeks<=pl.lista.size()) {
				Cecha c=new Cecha(sgatunek, ocena);
				Plyty pl=new Plyty(indeks, snazwa, cena, data, c);
				pl.lista.set(indeks-1, pl);	
				InterfejsGraf.dm.setValueAt(indeks, indeks-1, 0);
				InterfejsGraf.dm.setValueAt(snazwa, indeks-1, 1);
				InterfejsGraf.dm.setValueAt(cena, indeks-1, 2);
				InterfejsGraf.dm.setValueAt(df.format(data), indeks-1, 3);
				InterfejsGraf.dm.setValueAt(sgatunek, indeks-1, 4);
				InterfejsGraf.dm.setValueAt(ocena, indeks-1, 5);
			}
			else
				throw new BladIndeks();
		}
		else
			throw new BladIndeks();
	}
	
	/**
	 * Umozliwia wczytanie oraz wyswietlenie bazy danych.
	 */
	public void odczytaj() throws ParseException {
		b.select();
		tabela();
	}
	
	/**
	 * Umozliwia wyswietlenie elementow Arraylisty w tabeli.
	 */
	public static void tabela() {
		for(int i=0; i<Plyty.lista.size()+n; i++) {
			if(InterfejsGraf.dm.getRowCount()!=0)
				InterfejsGraf.dm.removeRow(0);
		}
		for(int i=0; i<Plyty.lista.size(); i++) {
			Vector<Object> v = new Vector<Object>();
			        
			Plyty p=Plyty.lista.get(i);
			int lp=p.getLp();
			String nazwa=p.getNazwa();
			double cena=p.getCena();
			Date data=p.getData();
			Cecha c=p.getCecha();
			String gatunek=c.getGatunek();
			int ocena=c.getOcena();      
			
			v.add(lp);
			v.add(nazwa);
			v.add(cena);
			v.add(df.format(data));
			v.add(gatunek);
			v.add(ocena);
			InterfejsGraf.dm.addRow(v);
		}
	}
	
	/**
	 * Umozliwia zapisanie listy w pliku XML.
	 * @param sciezkapliku odpowiada za przekazanie sciezki pliku zapisu.
	 */
	public   void zapisz(String sciezkapliku) {
		try {
			XStream xs=new XStream();
			String tekst=xs.toXML(pl.lista);
			File xml=new File(sciezkapliku);
			xml.createNewFile();
			FileWriter fw=new FileWriter(xml);
			fw.write(tekst);
			fw.close();
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Umozliwia otwarcie pliku XML i wyswietlenie go w tabeli.
	 * @param sciezkapliku sciezka pliku XML, ktory ma byc odczytany.
	 */
	@SuppressWarnings("unchecked")
	public  void otworz(String sciezkapliku) {
		XStream xs=new XStream();
		try {
			pl.lista.clear();
			pl.lista.addAll((Collection<Plyty>) xs.fromXML(new FileInputStream(sciezkapliku)));
			Plyty p=pl.lista.get(pl.lista.size()-1);
			lp=p.getLp()+1;
			tabela();
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Umozliwia zapis listy do bazy danych.
	 */
	public  void zapisbaza() {
		b.delete();
		for(int i=0; i<pl.lista.size(); i++) {
			Plyty p=pl.lista.get(i);
			int lp=p.getLp();
			String nazwa=p.getNazwa();
			double cena=p.getCena();
			Date data=p.getData();
			Cecha c=p.getCecha();
			String gatunek=c.getGatunek();
			int ocena=c.getOcena();
			b.insert(lp, nazwa, cena, data, gatunek, ocena);
		}
	}
	/**
	 * Sortuje elementy listy wedlug lp.
	 */
	public static  void lpsort() {
		KomparatorLp komp=new KomparatorLp();
		Collections.sort(Plyty.lista, komp);
		tabela();
		blokadadane=false;
	}
	
	/**
	 * Sortuje elementy listy wedlug nazwy.
	 */
	public static void tytulsort() {
		KomparatorNazwa komp=new KomparatorNazwa();
		Collections.sort(Plyty.lista, komp);
		tabela();
		blokadadane=true;
	}
	
	/**
	 * Sortuje elementy listy wedlug ceny.
	 */
	public static void cenasort() {
		KomparatorCena komp=new KomparatorCena();
		Collections.sort(Plyty.lista, komp);
		tabela();
		blokadadane=true;
	}
	
	/**
	 * Sortuje elementy listy wedlug daty
	 */
	public void datasort() {
		KomparatorData komp=new KomparatorData();
		Collections.sort(pl.lista, komp);
		tabela();
		blokadadane=true;
	}
}