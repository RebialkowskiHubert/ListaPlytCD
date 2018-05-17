package projekt;

import java.util.*;

/**
 * @author Hubert Rebialkowski
 */

public class Plyty {
	/**
	 * Odpowiada za numeracje ArrayListy
	 */
	Integer lp;
	/**
	 * Nazwa plyty CD
	 */
	String nazwa;
	/**
	 * Cena plyty CD
	 */
	double cena;
	/**
	 * Data wydania plyty CD
	 */
	Date data;
	/**
	 * Pole obiektowe okreslajaca cechy plyty CD
	 */
	Cecha c;
	/**
	 * ArrayLista przechowywujaca dane o plytach CD
	 */
	static List<Plyty> lista=new ArrayList<Plyty>();
	/**
	 * Konstruktor klasy Plyty
	 * @param lp licznik ArrayListy
	 * @param nazwa nazwa plyty CD
	 * @param cena cena plyty CD
	 * @param data data wydania plyty CD
	 * @param c okresla cechy plyty(gatunek, kod)
	 */
	public Plyty() {}
	public Plyty(int lp, String nazwa, double cena, Date data, Cecha c) {
		this.lp=lp;
		this.nazwa=nazwa;
		this.cena=cena;
		this.data=data;
		this.c=c;
	}
	/**
	 * @return jak ma wyświetlać ArrayLista (uzycie w zapisie XML). 
	 */
	public String toString() {
		return("\n"+lp+". "+nazwa+" cena: "+cena+" zł data wydania: "+data+" gatunek: "+c);
	}
	
	Integer getLp() {
		return lp;
	}
	
	String getNazwa() {
		return nazwa;
	}
	
	double getCena() {
		return cena;
	}
	
	Date getData() {
		return data;
	}
	
	Cecha getCecha() {
		return c;
	}
}