package battlefield;

import java.util.Comparator;

public class ComparatorePosizione implements Comparator<Robot> {

	@Override
	public int compare(Robot r1, Robot r2) {
		int cmp= r1.getPosizione().getX() - r2.getPosizione().getX();
			if (cmp==0)
				cmp= r1.getPosizione().getY() - r2.getPosizione().getY();
			return cmp;
	}

}
