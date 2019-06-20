package com.moron.project.plf;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
	String fileName = "BTVN-5.txt";

	long startTime = System.nanoTime();

	printMap(Selector.readToMapAndGetSorted(fileName, false));
//	printMap(Selector.readToListAndGetSorted(fileName));

	long endTime = System.nanoTime();
	System.out.println("Total time: " + TimeUnit.NANOSECONDS.toMillis(endTime - startTime));
    }

    public static void printMap(Map<String, Integer> sorted) {

	for (String word : sorted.keySet()) {
	    System.out.println(word + ": " + sorted.get(word));
	}
    }
}
