package com.nel.Algorithms;

import java.util.ArrayList;

public class Naive {

    public static ArrayList<Integer> indexer = new ArrayList<>();

    public static void naive(String text, String pattern){
        int N = text.length();
        int M = pattern.length();
        int counter = 0;

        for(int  i = 0; i <= (N - M); i++){
            int j;
            // на каждой позиции сравниваем посимвольно до первого расхождения с подстрокой
            for(j = 0; j < M; j++) {
                counter++;
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;
            }
            if(j == M)
                indexer.add(i);
        }
        System.out.println("Индексы совпадений: " + indexer.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);
    }
}
