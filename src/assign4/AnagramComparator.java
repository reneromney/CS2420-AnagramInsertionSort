package assign4;

import java.util.Comparator;

/**
 * A class to implement the comparator used in AnagramUtil
 * 
 * @author Jordan Hensley, Romney Doria jHensley Doria CS_2420 fall 2015 Assign4
 *         9/21/2014
 *
 */
public class AnagramComparator implements Comparator<String> {

	/**
	 * Returns a positive number if o1's sorted self is larger than the sorted
	 * o2, negative if less than, and zero if equal to.
	 */
	@Override
	public int compare(String o1, String o2) {
		// Compares the sorted version of each string
		return AnagramUtil.sort(o1).compareTo(AnagramUtil.sort(o2));

	}

}
