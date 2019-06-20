package com.moron.project.plf;

import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sorter {

    // sort and return a Map object Java 8 way
    public static Map<String, Integer> sortMapJava8(Map<String, Integer> sorted) {
	return sorted.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
		.collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    // sort and return a Map object custom quick-sort
    public static Map<String, Integer> sortMapQuickSort(Map<String, Integer> wordsFrequency) {

	
	List<Word> words = new ArrayList<Word>();
	
	for (String word : wordsFrequency.keySet())
	{
	    words.add(new Word(word, wordsFrequency.get(word)));
	}

	quickSort(words);

	Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
	for (int i = 0; i < words.size(); i++) {
	    sorted.put(words.get(i).value, words.get(i).frequency);
	}

	return sorted;
    }
    
    public static void quickSort(List<Word> words) {
	
	quickSort(words, 0, words.size() - 1);
    }

    private static void quickSort(List<Word> words, int left, int right) {
	int i = left;
	int j = right;
	Integer pivot = words.get((left + right) / 2).frequency;

	while (i <= j) {
	    while (pivot.compareTo(words.get(i).frequency) < 0) {
		i++;
	    }

	    while (pivot.compareTo(words.get(j).frequency) > 0) {
		j--;
	    }

	    if (i <= j) {
		Collections.swap(words, i, j);
		i++;
		j--;
	    }
	}

	if (left < j) {
	    quickSort(words, left, j);
	}
	if (i < right) {
	    quickSort(words, i, right);
	}
    }
}
