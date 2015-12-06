package assign4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A program that contains method to see if two strings are anagrams, returns
 * the largest group of anagrams in an array of strings, and returns the largest
 * group of anagrams in a file.
 * 
 * @author Jordan Hensley, Romney Doria jHensley Doria CS_2420 fall 2015 Assign4
 *         9/21/2015
 *
 */
public class AnagramUtil {

	/**
	 * A helper method used when sorting a word
	 * 
	 * @param myString
	 * @return an array of chars containing all of the characters of the input
	 *         string
	 */
	private static char[] toArray(String myString) {

		char[] charList = new char[myString.length()];
		// Saves each letter in the word in the char array
		for (int i = 0; i < myString.length(); i++) {
			charList[i] = myString.charAt(i);
		}
		return charList;

	}

	/**
	 * Sorts each character of a string parameter using insertion sort
	 * 
	 * @param myString
	 * @return the sorted version of the input string as a string
	 */
	public static String sort(String myString) {

		if (myString.length() == 0) {
			return "";
		} else {
			// Sort should not be case sensitive
			myString = myString.toLowerCase();
			// Calls the helper method to get an array of chars
			char[] myWord = toArray(myString);

			// Do the insertion sort
			for (int i = 1; i < myWord.length; i++) {
				int j;
				for (j = i - 1; j >= 0 && (myWord[j] >= myString.charAt(i)); j--) {
					myWord[j + 1] = myWord[j];
				}
				myWord[j + 1] = myString.charAt(i);
			}
			String finalWord = "";
			// Read the sorted characters of myWord into finalWord
			for (int i = 0; i < myWord.length; i++) {
				finalWord += myWord[i];
			}
			return finalWord;
		}
	}

	/**
	 * A generic method that performs on insertion sort on the input array, by
	 * order of the input comparator
	 * 
	 * @param item
	 * @param comp
	 */
	public static <T> void insertionSort(T[] item, Comparator<? super T> comp) {

		for (int i = 1; i < item.length; i++) {
			T element = item[i];
			int j;
			// Run insertion sort and compare using the input comparator.
			for (j = i - 1; j >= 0 && (comp.compare(item[j], element) > 0); j--) {
				item[j + 1] = item[j];
			}
			item[j + 1] = element;
		}
	}

	/**
	 * A method to determine if two string are anagrams, will return true if the
	 * strings are the same
	 * 
	 * @param s1
	 * @param s2
	 * @return true if the two input words are anagrams and false if otherwise
	 */
	public static boolean areAnagrams(String s1, String s2) {
		// Compare the sorted strings
		return sort(s1).equals(sort(s2));
	}

	/**
	 * @param list
	 * @return an array of strings containing all the words that belong to the
	 *         largest group of anagrams present in the input array of strings.
	 *         Will return an empty string if no anagrams exist in the array.
	 */
	public static String[] getLargestAnagramGroup(String[] list) {

		// Sort using the comparator, anagrams will now be next to each other
		AnagramComparator comp = new AnagramComparator();
		insertionSort(list, comp);
		//Arrays.sort(list, comp);
		
		// Temporary arraylists to keep track of the anagrams
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> bestSoFar = new ArrayList<String>();

		for (int i = 0; i < list.length - 1; i++) {
			// Add first element
			if (temp.isEmpty()) {
				temp.add(list[i]);
			}

			// If next element is an angram, add it and start again
			if (areAnagrams(list[i], list[i + 1])) {
				temp.add(list[i + 1]);
				continue;
			}
			// Save the biggest group of anagrams
			if (temp.size() > bestSoFar.size()) {
				bestSoFar = new ArrayList<String>(temp);
			}
			// Must now clear the temp anagram group
			temp.clear();
		}
		// If no anagrams are found, return an empty string.
		if (bestSoFar.size() == 1) {
			return new String[0];
		} else {
			// Read in the bestSoFar arrayList into a string array
			String[] finalList = new String[bestSoFar.size()];
			for (int i = 0; i < finalList.length; i++) {
				finalList[i] = bestSoFar.get(i);
			}
			return finalList;
		}

	}

	/**
	 * 
	 * @param filename
	 * @return an array containing all of the strings that belong to the largest
	 *         group of anagrams in the input file. If no anagrams exist, will
	 *         return an empty array. If the file cannot be found an error
	 *         message will be printed and an empty array of strings will be
	 *         returned.
	 */
	public static String[] getLargestAnagramGroup(String filename) {

		ArrayList<String> tempList = new ArrayList<String>();
		// Try and read in all the lines in the file into tempList
		try {
			Scanner scan = new Scanner(new File(filename));
			while (scan.hasNextLine()) {
				tempList.add(scan.nextLine());
			}
			scan.close();
			// What to do if file cannot be found
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + "Empty list was passed.");
			return new String[0];
		}
		String finalList[] = new String[tempList.size()];

		// Read the arrayList tempList into an array
		for (int i = 0; i < tempList.size(); i++) {
			finalList[i] = tempList.get(i);
		}

		// Now call getLargestAnagram on the array of Strings
		return getLargestAnagramGroup(finalList);
	}
}
