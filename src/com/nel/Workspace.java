package com.nel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Workspace {

    public static String getText(String fileName){
        StringBuilder sb = new StringBuilder(); //неперезаписываемый String
        File file = new File(fileName);

        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print("Строка: " + sb.toString());
        return sb.toString();
    }

    public static String getPattern(){
        System.out.print("Введите паттерн: ");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
