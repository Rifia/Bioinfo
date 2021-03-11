package com.nel;


import com.nel.Algorithms.KnuthMorrisPrat;
import com.nel.Algorithms.Naive;

public class Main {

    public static void main(String[] args) {
        String fileName = "src/com/nel/res/text.txt";
	    String text = Workspace.getText(fileName);
	    String pattern = Workspace.getPattern();

        Naive.naive(text, pattern);
        //KnuthMorrisPrat.KMPSearch(text, pattern);

    }
}
