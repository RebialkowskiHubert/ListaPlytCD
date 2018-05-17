package projekt;

public class Cecha {
	String gatunek;
	int ocena;
	public Cecha(String gatunek, int ocena) {
		this.gatunek=gatunek;
		this.ocena=ocena;
	}
	
	public String toString() {
		return(gatunek+" ocena: "+ocena);
	}
	
	String getGatunek() {
		return gatunek;
	}
	
	int getOcena() {
		return ocena;
	}
}