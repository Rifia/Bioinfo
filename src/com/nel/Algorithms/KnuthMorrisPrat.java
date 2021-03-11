package com.nel.Algorithms;

import java.util.ArrayList;

public class KnuthMorrisPrat {

    public static ArrayList<Integer> indexer = new ArrayList<>();

    public static int[] prefixFunction(String pattern) {
        int [] values = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            int j = 0;
            while (i + j < pattern.length() && pattern.charAt(j) == pattern.charAt(i + j)) {
                values[i + j] = Math.max(values[i + j], j + 1);
                j++;
            }
        }
        return values;
    }

    public static void KMPSearch(String text, String pattern) {

        int[] prefixFunc = prefixFunction(pattern);
        int counter = 0;

        int i = 0;
        int j = 0;

        while (i < text.length()) {
            counter++;
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            if (j == pattern.length()) {
                indexer.add(i - j);
                j = prefixFunc[j - 1];
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = prefixFunc[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        System.out.println("Индексы совпадений: " + indexer.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);
    }
}
