package projekt;

import java.util.Comparator;
public class KomparatorData implements Comparator<Plyty> {
	public int compare(Plyty s1, Plyty s2) {
		if(s1.getData().compareTo(s2.getData())<0)
			return -1;
		else
			return 0;
	}
}