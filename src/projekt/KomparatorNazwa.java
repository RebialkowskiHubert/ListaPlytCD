package projekt;
import java.util.Comparator;
public class KomparatorNazwa implements Comparator<Plyty> {
	public int compare(Plyty s1, Plyty s2) {
		if(s1.getNazwa().compareTo(s2.getNazwa())<0)
			return -1;
		else
			return 0;
	}
}
