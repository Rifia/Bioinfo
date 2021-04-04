package com.nel;


import com.nel.Algorithms.*;

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
        System.out.println("\nKarpRabin");
        KarpRabin.karpRabin(text, pattern, Integer.MAX_VALUE);
        System.out.println("\nBaezaYatesGonnet");
        BaezaYatesGonnet.BYG(text, pattern);
    }
}
