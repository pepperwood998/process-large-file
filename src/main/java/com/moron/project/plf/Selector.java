package com.moron.project.plf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Selector {

    // read from file and extract data directly into a Map object, process on that
    // Map object
    // return a sorted Map
    public static Map<String, Integer> readToMapAndGetSorted(String fileName, boolean isJava8Style) throws IOException {
	FileReader file = null;
	BufferedReader bufReader = null;

	try {
	    file = new FileReader(fileName);
	    bufReader = new BufferedReader(file);

	    String line;
	    Map<String, Integer> wordsFrequency = new LinkedHashMap<String, Integer>();
	    while ((line = bufReader.readLine()) != null) {
		line = line.trim();
		if (line.length() > 0) {
		    String wordsPerLine[] = line.split("\\W+");
		    for (String word : wordsPerLine) {
			Integer count = wordsFrequency.get(word);
			if (count == null) {
			    wordsFrequency.put(word, 1);
			} else {
			    wordsFrequency.put(word, count.intValue() + 1);
			}
		    }
		}
	    }

	    // sorted by java 8 or custom quick-sort
	    if (isJava8Style) {
		Map<String, Integer> sorted = Sorter.sortMapJava8(wordsFrequency);
		return sorted;
	    }

	    Map<String, Integer> sorted = Sorter.sortMapQuickSort(wordsFrequency);
	    return sorted;

	} finally {
	    if (file != null) {
		file.close();
	    }
	    if (bufReader != null) {
		bufReader.close();
	    }
	}
    }

    // read from file and extract data into a List of Word objects, process on that list
    // extract the list into a Map object and return it
    public static Map<String, Integer> readToListAndGetSorted(String fileName) throws IOException {
	FileReader file = null;
	BufferedReader bufReader = null;

	try {
	    file = new FileReader(fileName);
	    bufReader = new BufferedReader(file);

	    String line;
	    List<Word> words = new ArrayList<Word>();
	    while ((line = bufReader.readLine()) != null) {
		line = line.trim();
		if (line.length() > 0) {
		    String wordsPerLine[] = line.split("\\W+");
		    for (String word : wordsPerLine) {
			int ind = words.indexOf(new Word(word, 0));
			if (ind != -1) {
			    words.get(ind).frequency++;
			} else {
			    words.add(new Word(word, 1));
			}
		    }
		}
	    }

	    Sorter.quickSort(words);

	    Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
	    for (int i = 0; i < words.size(); i++) {
		sorted.put(words.get(i).value, words.get(i).frequency);
	    }

	    return sorted;

	} finally {
	    if (file != null) {
		file.close();
	    }
	    if (bufReader != null) {
		bufReader.close();
	    }
	}
    }
}
