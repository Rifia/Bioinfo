package com.nel;


import com.nel.Algorithms.BoyerMoore;
import com.nel.Algorithms.KnuthMorrisPrat;
import com.nel.Algorithms.Naive;

public class Main {

    public static void main(String[] args) {
        String fileName = "src/com/nel/res/text.txt";
	    String text = Workspace.getText(fileName);
	    String pattern = Workspace.getPattern();

        System.out.println("\nNaive");
        Naive.naive(text, pattern);
        System.out.println("\nKnuthMorrisPrat");
        KnuthMorrisPrat.KMPSearch(text, pattern);
        System.out.println("\nBoyerMoore");
        BoyerMoore.Match(text, pattern);

    }
}
