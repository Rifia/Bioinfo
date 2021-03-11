package com.nel.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class BoyerMoore {
    public static void boyerMoore(String text, String pattern) {
        int[] map = buildBoyerMooreCharTable(pattern);
        ArrayList<Integer> a = new ArrayList<>();

        if(pattern.length()>text.length()) System.out.println("Индексы совпадений: " + a.toString());

        int i = pattern.length() - 1;
        int j = pattern.length() - 1;
        int counter = 0;

        while(i<text.length()) {
            counter++;
            if(text.charAt(i)== pattern.charAt(j)) {
                if(j==0) {
                    a.add(i);
                    j = pattern.length() - 1;
                    i = i + pattern.length();
                }
                else {
                    i--;
                    j--;
                }
            }
            else {
                i = i + pattern.length() - j + map[text.charAt(i)]-1;
                j = pattern.length() - 1;
            }
        }
        System.out.println("Индексы совпадений: " + a.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);

    }
    //If the character is in the string: map[c] = length - last index of c - 1, Otherwise: map[c] = length
    public static int[] buildBoyerMooreCharTable(String pattern) {
        int[] map = new int[Character.MAX_VALUE + 1];
        Arrays.fill(map, pattern.length());
        for(char c : pattern.toCharArray()){
            map[c] = Math.max(1, pattern.length() - pattern.lastIndexOf(c) - 1);
        }
        //System.out.println("Таблица смещений: " + Arrays.toString(map));
        return map;
    }
}