package com.nel.Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoyerMoore {

    private static int ALPHABET_LEN = 255;
    private static int[] delta1;
    private static int[] delta2;

    //заполнение первого массива, который хранит в себе позиции самых равых вхождений символов паттерна(обеспечивание минимального сдвига)
    private static void FillDelta1(String pattern){
        // заполняем массив значениями, равным длине паттерна
        for (int i = 0; i < ALPHABET_LEN; i++) {
            delta1[i] = pattern.length();
        }
        // формируем список самых правых вхождений символов в паттерн
        for (int i = 0; i < pattern.length() - 1; i++) {
            delta1[pattern.charAt(i)] = pattern.length() - 1 - i;
        }
    }

    // check if str[p .. str.Length - 1] is prefix of the string str
    // то есть: берем суффикс строки с определенной позиции
    private static boolean IsPrefix(String text, int pos)
    {
        // длина суффикса
        int suffixLen = text.length() - pos;

        // проходимся по той части строки, где предположительно может быть префикс
        // эта часть той же длины, что и суффикс
        for (int i = 0; i < suffixLen; i++) {
            // если на какой-то итерации расхождение символов
            if (text.charAt(i) != text.charAt(pos + i)) {
                // то суффикс не является и префиксом
                // по сути эта функция помогает определить грань
                return false;
            }
        }
        // если полное совпадение, то суффикс является и префиксом
        return true;
    }

    // returns the length of the longest common suffix of str and str[1 .. p]
    // возвращает длину самого длинного общего суффикса строки и строки до определенной позиции
    private static int GetSuffixLength(String text, int pos)
    {
        int i = 0;
        // идем от начала строки и сравниваем посимвольно с конца суффикс строки и суффикс префиксной подстроки
        // пока есть совпадения и префиксная подстрока не закончилась, прибавляем длину
        // после первого несовпадения возвращаем длину этого суффикса
        while (i < pos && text.charAt(pos - i) == text.charAt(text.length() - 1 - i))
            i++;

        return i;
    }

    // заполнение массива дельта 2
    private static void FillDelta2(String pattern) {
        delta2 = new int[pattern.length()];
        // последний индекс префикса положим равным длине паттерна
        int lastPrefixIndex = pattern.length();
        // проходимся с конца по паттерну(i всегда уменьшается)
        for (int i = pattern.length() - 1; i >= 0; i--) {
            // на первой итерации i - это позиция последней буквы паттерна, длина суффикса будет равна нулю и цикл не выполнится
            // передаем i и паттерн в функцию isPrefix, и там длина суффикса сразу равна единице, то есть сравнение идет начиная с одной буквы
            // допустим совпало, получили грань, и тогда
            if (IsPrefix(pattern, i + 1)) {
                // последний индекс префикса будет равен длине самого паттерна, т.е. нет паттерна - нет и префикса. т.е. первый элемент delta2 всегда равен длине самого паттерна
                lastPrefixIndex = i + 1;
            }
            //заносим сюда последний индекс префикса, если таковых не будет, то все элементы этого массива будут равны длине строки
            // для паттерна длины 5 delta2 примет вид [5, 5, 5, 5, 5]
            // а если будет, то на паттерне aabaa delta2 = [5, 4, 3, 5, 5]
            delta2[pattern.length() - 1 - i] = lastPrefixIndex;
        }

        // не доходя до последнего символа паттерна
        for (int i = 0; i < pattern.length() - 1; i++) {
            // длина суффикса - это самое длинное совпадение концов подстроки
            // на первой итерации suffixLength = 0
            int suffixLength = GetSuffixLength(pattern, i);
            // если символы не совпадают, то обновляем значения в в соответствующей ячейке delta2
            if (pattern.charAt(i - suffixLength) != pattern.charAt(pattern.length() - 1 - suffixLength)) {
                delta2[pattern.length() - 1 - suffixLength] = pattern.length() - 1 - i;
            }
        }
    }
    // как и почему изменился массив
    // получили количество символов, на которые можно сдвинуть паттерн по строке с учетом и суффиксов внутри самого паттерна

    public static void Match(String text, String pattern) {

        // плохой символ
        delta1 = new int[ALPHABET_LEN];
        //хороший суффикс
        delta2 = new int[pattern.length()];

        ArrayList<Integer> indexer = new ArrayList<>();
        int counter = 0;

        FillDelta1(pattern);
        FillDelta2(pattern);
        System.out.println("delta2 = " + Arrays.toString(delta2));
        // индекс последнего элемента паттерна
        int n = text.length() - 1;
        int m = pattern.length() - 1;
        int i = m;

        // двигаем паттерн слева направо до тех пор, пока не совместим последние буквы строки и паттерна
        while (i <= n) {
            int j = m;
            // до тех пор пока не закончился паттерн, а с конца символы паттерна и строки совпадают, шагаем влево по паттерну и и по строке одновременно
            while (j != 0 && text.charAt(i) == pattern.charAt(j)) {
                counter++;
                i--;
                j--;
            }
            // если паттерн кончился, то добавляем позицию вхождения
            if (j == 0) {
                indexer.add(i);
            }
            // сдвигаем паттерн на максимальное значение из этих двух
            // первое - на позицию символа
            // второе - на позицию суффикса
            i += Math.max(delta1[text.charAt(i)], delta2[j]);
        }
        FreeMemory();
        System.out.println("Индексы совпадений: " + indexer.toString());
        System.out.println("Количество побуквенных сравнений: " + counter);
    }

    private static void FreeMemory()
    {
        delta1 = null;
        delta2 = null;
    }

}