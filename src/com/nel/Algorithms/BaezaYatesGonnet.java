package com.nel.Algorithms;

import java.util.ArrayList;

public class BaezaYatesGonnet {
    public static ArrayList<Integer> indexer = new ArrayList<>();

    public static void BYG(String text, String pattern) {
        char[] alphabet = {'a', 'b', 'c'};
        int m = pattern.length();
        byte[] pattern_mask = new byte[Character.MAX_VALUE + 1];
        byte R = ~1; //не 1 = -2
        int counter = 0;

        // инициализация
        for (int i = 0; i <= 256; ++i)
            pattern_mask[i] = ~0;

        //составление матрицы t
        for (int i = 0; i < m; ++i)
            pattern_mask[pattern.charAt(i)] &= ~(1 << i);

        System.out.println("t =");
        for (int i = 0; i < m; ++i)
            System.out.println(toBinaryString(pattern_mask[alphabet[i]], m-1));


        System.out.println("-------------Run search:");

        for (int i = 0; i < text.length(); i++) {
            counter++;
            System.out.println("S(" + (i) +") = " + toBinaryString(R, m));
            R |= pattern_mask[text.charAt(i)];
            R <<= 1;
            if ((R & (1 << m)) == 0)
                indexer.add(i - m + 1);
        }
        System.out.println("Индексы совпадений: " + indexer.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);
    }

    public static String toBinaryString(int value, int len) {
        StringBuilder result = new StringBuilder(32);
        for(int i = 0; i <= len; ++i) {
            result.append(value & 1);
            value >>>= 1;
        }
        return result.toString();
    }
}