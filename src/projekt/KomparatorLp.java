package projekt;

import java.util.Comparator;
public class KomparatorLp implements Comparator<Plyty> {
	public int compare(Plyty s1, Plyty s2) {
		if(s2==null)
			return -1;
		if(s1.getLp()>s2.getLp())
			return 1;
		if(s1.getLp()<s2.getLp())
			return -1;
		else
			return 0;
	}
}