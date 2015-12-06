package assign4;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Test;

/**
 * A class to test the functionality and quality of AnagramUtil
 * 
 * @author Jordan Hensley, Romney Doria jHensley Doria CS_2420 fall 2015 Assign4
 *         9/21/2015
 *
 */
public class AnagramUtilTester {

	@Test
	public void testSort() {
		assertEquals("aelrt", AnagramUtil.sort("later"));
		assertEquals("aelrt", AnagramUtil.sort("alert"));
		assertEquals("", AnagramUtil.sort(""));
		assertEquals("b", AnagramUtil.sort("b"));
		assertEquals("abcdefghijklmnopqrstuvwxyz", AnagramUtil.sort("zyxwvutsrqponmlkjihgfedcba"));
		assertEquals("aabbbc", AnagramUtil.sort("babbac"));
	}

	@Test
	public void testAreAnagrams() {
		assertTrue(AnagramUtil.areAnagrams("", ""));
		assertTrue(AnagramUtil.areAnagrams("and", "dan"));
		assertTrue(AnagramUtil.areAnagrams("a", "a"));
		assertTrue(AnagramUtil.areAnagrams("start", "start"));
		assertTrue(AnagramUtil.areAnagrams("later", "alert"));
		assertTrue(AnagramUtil.areAnagrams("CaReT", "rEAct"));
		assertTrue(AnagramUtil.areAnagrams("a", "A"));
		assertFalse(AnagramUtil.areAnagrams("a", "b"));
		assertFalse(AnagramUtil.areAnagrams("and", "dann"));
		assertFalse(AnagramUtil.areAnagrams("true", "false"));
		assertFalse(AnagramUtil.areAnagrams("kdslf", "sdlfj"));
		assertFalse(AnagramUtil.areAnagrams("", "a"));
	}

	@Test
	public void testAnagramGroup() {
		String[] test = { "and", "dan", "man", "nad" };
		String[] largestGroup = AnagramUtil.getLargestAnagramGroup(test);
		assertEquals(3, largestGroup.length);
		assertEquals("and", largestGroup[0]);
		assertEquals("dan", largestGroup[1]);
		assertEquals("nad", largestGroup[2]);

		String[] testEmpty = new String[0];
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(testEmpty).length);

		String[] noAnagram = { "hi", "and", "but", "yes", "no", "what", "take" };
		assertEquals(0, AnagramUtil.getLargestAnagramGroup(noAnagram).length);

		String[] testMostAnagrams = { "and", "Caret", "react", "dan", "nad", "crate", "ratec" };
		String[] result = AnagramUtil.getLargestAnagramGroup(testMostAnagrams);
		assertEquals(4, result.length);
		assertEquals("Caret", result[0]);
		assertEquals("react", result[1]);
		assertEquals("crate", result[2]);
		assertEquals("ratec", result[3]);
	}
	
	@Test
	public void testGetLargestWithFile() {
		String[] testFile = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		assertEquals(7, testFile.length);
		assertEquals("carets", testFile[0]);
		assertEquals("Caters", testFile[1]);
		assertEquals("caster", testFile[2]);
		assertEquals("crates", testFile[3]);
		assertEquals("Reacts", testFile[4]);
		assertEquals("recast", testFile[5]);
		assertEquals("traces", testFile[6]);
		
		String[] testNoSuchFile = AnagramUtil.getLargestAnagramGroup("ldjf.tx");
		assertEquals(0, testNoSuchFile.length);
	}
	

	@Test
	public void testInsertionSort() {

		AnagramComparator comp = new AnagramComparator();
		String[] testString = { "hi", "and", "cat", "dog", "for", "tac" };
		AnagramUtil.insertionSort(testString, comp);
		assertEquals(6, testString.length);
		assertEquals("cat", testString[0]);
		assertEquals("tac", testString[1]);
		assertEquals("and", testString[2]);
		assertEquals("dog", testString[3]);
		assertEquals("for", testString[4]);
		assertEquals("hi", testString[5]);
		
		String[] testEmpty = new String[0];
		AnagramUtil.insertionSort(testEmpty, comp);
		assertEquals(0, testEmpty.length);

		BackwardsDouble compDouble = new BackwardsDouble();
		Double[] testDouble = { 1.337,9.99, 5.55, 11.9, .01, 15.0, 4.5, -9.999	};
		AnagramUtil.insertionSort(testDouble, compDouble);
		assertEquals(8, testDouble.length);
		assertEquals(-9.999, testDouble[0], 0);
		assertEquals(.01, testDouble[1], 0);
		assertEquals(1.337, testDouble[2], 0);
		assertEquals(4.5, testDouble[3], 0);
		assertEquals(5.55, testDouble[4], 0);
		assertEquals(9.99, testDouble[5], 0);
		assertEquals(11.9, testDouble[6], 0);
		assertEquals(15.0, testDouble[7], 0);
		
	}	

	public class BackwardsDouble implements Comparator<Double> {

		@Override
		public int compare(Double o1, Double o2) {
			if (o1 < o2)
				return -1;
			else if (o2 < o1) {
				return 1;
			} else
				return 0;

		}
	}

}
