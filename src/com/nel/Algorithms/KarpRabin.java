package com.nel.Algorithms;


import java.util.ArrayList;

public class KarpRabin {

    public static ArrayList<Integer> indexer = new ArrayList<>();

    public static void karpRabin(String OriginalText, String SearchingText, int primeNumber)
    {
        // инициализация
        int OriginalTextLength = OriginalText.length();
        int SearchingTextLength = SearchingText.length();
        int p2m = 1;
        int counter = 0;

        //коэффициенты
        for (int i = 0; i < SearchingTextLength - 1; i++)
            p2m = (p2m * 2) % primeNumber;

        // вычисление хэша от подстроки и куска строки по формуле 1
        int HashSearchingText = gorner2mod(SearchingText, SearchingTextLength, primeNumber);
        int HashOriginalText = gorner2mod(OriginalText, SearchingTextLength, primeNumber);


        // поиск вхождений
        for (int j = 0; j <= OriginalTextLength - SearchingTextLength; j++) {
            if (HashOriginalText == HashSearchingText) { // проверка действительно ли совпали строки (вдруг коллизия)
                int k = 0;
                // наивное сравнение строк
                while (k < SearchingTextLength && SearchingText.charAt(k) == OriginalText.charAt(j + k)){
                    k++;
                    counter++;
                }

                if (k == SearchingTextLength)
                    indexer.add(j);
            }

            // чтобы не выйти за границы строки
            if (j < OriginalTextLength - SearchingTextLength) {
                // хэш = хэш - первый символ + последний символ по формуле 2
                HashOriginalText = ((HashOriginalText - p2m * OriginalText.charAt(j)) * 2 + OriginalText.charAt(j + SearchingTextLength)) % primeNumber;
                if (HashOriginalText < 0) // конвертация в положительное если вышло < 0
                    HashOriginalText += primeNumber;
            }
        }
        System.out.println("Индексы совпадений: " + indexer.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);
    }

    // вычисляет по схеме Горнера значение многочлена степени m с коэффициентами S[0..m-1] по модулю q
    private static int gorner2mod(String str, int m, int q) {
        int res = 0;
        for (int i = 0; i < m; i++)
            // степень двойки возрастает с каждой итерациией
            res = (res * 2 + str.charAt(i)) % q;
        return res;
    }

}
