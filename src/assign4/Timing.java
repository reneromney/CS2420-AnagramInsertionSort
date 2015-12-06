package assign4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Timing {

	public static void main(String[] args) {
		// Do 10000 lookups and use the average running time.
		int timesToLoop = 1000;

		// For each problem size n . . .
		for (int n = 100; n <= 20000; n += 100) {

			// Setup of n size for testing
			long startTime, midpointTime, stopTime;

			// Used to generate random strings
			String sA = "";
			String sB = "";
			String letters = "abcdefghijklmnopqrstuvwxyz";
			Random rng = new Random();
			String[] testArray = new String[n];
			for (int i = 0; i < n; i++) {
				/**
				// Randomly inserts a number from 0 to the max integer value
				sA += letters.charAt(rng.nextInt(letters.length()-1));
				sB += letters.charAt(rng.nextInt(letters.length()-1));
				String
				*/
				for (int j = 0; j < 20; j++)
					sA += letters.charAt(rng.nextInt(25));
				testArray[i] = sA;
				sA = "";
			}

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();

			// Run the methods for testing
			for (int i = 0; i < timesToLoop; i++) {
				//AnagramUtil.areAnagrams(sA, sB);
				
				AnagramUtil.getLargestAnagramGroup(testArray);
			}

			midpointTime = System.nanoTime();

			// Time it takes to run loop
			for (int i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime + "\t" + (averageTime/(n*n)));
		}

	}

}
