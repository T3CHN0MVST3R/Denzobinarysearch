package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    // Вспомогательный метод для вывода результата теста
    private static void printTestResult(String testName, String arrayOrListDesc, Object key, Object expected, Object actual) {
        System.out.println("--------------------------------------------------------");
        System.out.println("Тест: " + testName);
        System.out.println("Массив/Список: " + arrayOrListDesc);
        System.out.println("Ключ: " + key);
        System.out.println("Ожидается: " + expected);
        System.out.println("Получено: " + actual);

        boolean pass = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        System.out.println("Результат: " + (pass ? "PASS" : "FAIL"));
        System.out.println("--------------------------------------------------------");
        System.out.println();
    }

    public static void main(String[] args) {
        // ТЕСТЫ ДЛЯ MyArrays
        byte[] bArr = {1, 3, 5, 7, 9};
        printTestResult("Поиск существующего byte",
                "[1,3,5,7,9]",
                (byte)5, 2, MyArrays.binarySearch(bArr, (byte)5));

        printTestResult("Поиск несуществующего byte",
                "[1,3,5,7,9]",
                (byte)2, -2, MyArrays.binarySearch(bArr, (byte)2));

        printTestResult("Поиск существующего byte в диапазоне",
                "[1,3,5,7,9], fromIndex=1,toIndex=4",
                (byte)5, 2, MyArrays.binarySearch(bArr, 1, 4, (byte)5));

        printTestResult("Поиск несуществующего byte в диапазоне",
                "[1,3,5,7,9], fromIndex=1,toIndex=4",
                (byte)9, -5, MyArrays.binarySearch(bArr, 1, 4, (byte)9));

        short[] sArr = {10, 20, 30, 40, 50};
        printTestResult("Поиск существующего short",
                "[10,20,30,40,50]",
                (short)30, 2, MyArrays.binarySearch(sArr, (short)30));

        printTestResult("Поиск несуществующего short",
                "[10,20,30,40,50]",
                (short)25, -3, MyArrays.binarySearch(sArr, (short)25));

        printTestResult("Поиск short в диапазоне",
                "[10,20,30,40,50], fromIndex=1,toIndex=4",
                (short)30, 2, MyArrays.binarySearch(sArr, 1, 4, (short)30));

        int[] iArr = {10, 20, 30, 40, 50};
        printTestResult("Поиск существующего int",
                "[10,20,30,40,50]",
                30, 2, MyArrays.binarySearch(iArr, 30));

        printTestResult("Поиск несуществующего int",
                "[10,20,30,40,50]",
                25, -3, MyArrays.binarySearch(iArr, 25));

        printTestResult("Поиск минимального int",
                "[10,20,30,40,50]",
                10, 0, MyArrays.binarySearch(iArr, 10));

        printTestResult("Поиск максимального int",
                "[10,20,30,40,50]",
                50, 4, MyArrays.binarySearch(iArr, 50));

        printTestResult("Поиск int в диапазоне",
                "[10,20,30,40,50], fromIndex=1,toIndex=4",
                30, 2, MyArrays.binarySearch(iArr, 1, 4, 30));

        printTestResult("Поиск несуществующего int в диапазоне",
                "[10,20,30,40,50], fromIndex=1,toIndex=4",
                25, -3, MyArrays.binarySearch(iArr, 1, 4, 25));

        // Проверка некорректных диапазонов для int[]
        try {
            MyArrays.binarySearch(iArr, 3, 2, 20);
            printTestResult("Некорректный диапазон (fromIndex > toIndex)",
                    "[10,20,30,40,50], fromIndex=3,toIndex=2",
                    20, "IllegalArgumentException", "Нет исключения");
        } catch (IllegalArgumentException e) {
            printTestResult("Некорректный диапазон (fromIndex > toIndex)",
                    "[10,20,30,40,50], fromIndex=3,toIndex=2",
                    20, "IllegalArgumentException", "IllegalArgumentException");
        }

        try {
            MyArrays.binarySearch(iArr, -1, 2, 20);
            printTestResult("Некорректный диапазон (fromIndex < 0)",
                    "[10,20,30,40,50], fromIndex=-1,toIndex=2",
                    20, "ArrayIndexOutOfBoundsException", "Нет исключения");
        } catch (ArrayIndexOutOfBoundsException e) {
            printTestResult("Некорректный диапазон (fromIndex < 0)",
                    "[10,20,30,40,50], fromIndex=-1,toIndex=2",
                    20, "ArrayIndexOutOfBoundsException", "ArrayIndexOutOfBoundsException");
        }

        try {
            MyArrays.binarySearch(iArr, 0, 6, 20);
            printTestResult("Некорректный диапазон (toIndex > length)",
                    "[10,20,30,40,50], fromIndex=0,toIndex=6",
                    20, "ArrayIndexOutOfBoundsException", "Нет исключения");
        } catch (ArrayIndexOutOfBoundsException e) {
            printTestResult("Некорректный диапазон (toIndex > length)",
                    "[10,20,30,40,50], fromIndex=0,toIndex=6",
                    20, "ArrayIndexOutOfBoundsException", "ArrayIndexOutOfBoundsException");
        }

        long[] lArr = {100L, 200L, 300L, 400L, 500L};
        printTestResult("Поиск существующего long",
                "[100,200,300,400,500]",
                300L, 2, MyArrays.binarySearch(lArr, 300L));

        printTestResult("Поиск несуществующего long",
                "[100,200,300,400,500]",
                250L, -3, MyArrays.binarySearch(lArr, 250L));

        float[] fArr = {1.0f, 2.0f, 3.0f, 4.0f};
        printTestResult("Поиск существующего float",
                "[1.0,2.0,3.0,4.0]",
                3.0f, 2, MyArrays.binarySearch(fArr, 3.0f));

        printTestResult("Поиск несуществующего float",
                "[1.0,2.0,3.0,4.0]",
                2.5f, -3, MyArrays.binarySearch(fArr, 2.5f));

        double[] dArr = {0.5, 1.5, 2.5, 3.5, 4.5};
        printTestResult("Поиск существующего double",
                "[0.5,1.5,2.5,3.5,4.5]",
                3.5, 3, MyArrays.binarySearch(dArr, 3.5));

        printTestResult("Поиск несуществующего double",
                "[0.5,1.5,2.5,3.5,4.5]",
                2.0, -3, MyArrays.binarySearch(dArr, 2.0));

        char[] cArr = {'a','c','e','g'};
        printTestResult("Поиск существующего char",
                "[a,c,e,g]",
                'e', 2, MyArrays.binarySearch(cArr, 'e'));

        printTestResult("Поиск несуществующего char",
                "[a,c,e,g]",
                'b', -2, MyArrays.binarySearch(cArr, 'b'));

        // ТЕСТ ДЛЯ ОБОБЩЁННОГО МЕТОДА С COMPARATOR в MyArrays
        String[] strArr = {"apple", "banana", "cherry", "date"};
        printTestResult("Поиск существующего String через Comparator",
                "[apple, banana, cherry, date]",
                "cherry", 2, MyArrays.binarySearch(strArr, "cherry", Comparator.naturalOrder()));

        printTestResult("Поиск несуществующего String через Comparator",
                "[apple, banana, cherry, date]",
                "blueberry", -3, MyArrays.binarySearch(strArr, "blueberry", Comparator.naturalOrder()));

        printTestResult("Поиск существующего String в диапазоне через Comparator",
                "[apple, banana, cherry, date], fromIndex=1,toIndex=3",
                "banana", 1, MyArrays.binarySearch(strArr, 1, 3, "banana", Comparator.naturalOrder()));

        try {
            MyArrays.binarySearch(strArr, "date", null);
            printTestResult("Null comparator для массива",
                    "[apple, banana, cherry, date]",
                    "date", "NullPointerException", "Нет исключения");
        } catch (NullPointerException e) {
            printTestResult("Null comparator для массива",
                    "[apple, banana, cherry, date]",
                    "date", "NullPointerException", "NullPointerException");
        }

        // ТЕСТЫ ДЛЯ MyCollections
        // Используем списки
        List<Integer> intList = Arrays.asList(10, 20, 30, 40, 50);
        printTestResult("MyCollections: поиск существующего int (Comparable)",
                "[10,20,30,40,50]",
                30, 2, MyCollections.binarySearch(intList, 30));

        // Несуществующий 25 должен быть вставлен на позицию 2 => -3
        printTestResult("MyCollections: поиск несуществующего int (Comparable)",
                "[10,20,30,40,50]",
                25, -3, MyCollections.binarySearch(intList, 25));

        // Тест для списка строк
        List<String> stringList = Arrays.asList("apple", "banana", "cherry", "date");
        printTestResult("MyCollections: поиск существующей строки (Comparable)",
                "[apple, banana, cherry, date]",
                "cherry", 2, MyCollections.binarySearch(stringList, "cherry"));

        // Несуществующая "blueberry" между "banana"(инд1) и "cherry"(инд2) => insertion point=2 => результат=-3
        printTestResult("MyCollections: поиск несуществующей строки (Comparable)",
                "[apple, banana, cherry, date]",
                "blueberry", -3, MyCollections.binarySearch(stringList, "blueberry"));

        // Поиск с Comparator
        Comparator<String> cmp = Comparator.naturalOrder();
        printTestResult("MyCollections: поиск строки с Comparator",
                "[apple, banana, cherry, date]",
                "banana", 1, MyCollections.binarySearch(stringList, "banana", cmp));

        // Нулевой компаратор
        try {
            MyCollections.binarySearch(stringList, "date", null);
            printTestResult("MyCollections: null comparator",
                    "[apple, banana, cherry, date]",
                    "date", "NullPointerException", "Нет исключения");
        } catch (NullPointerException e) {
            printTestResult("MyCollections: null comparator",
                    "[apple, banana, cherry, date]",
                    "date", "NullPointerException", "NullPointerException");
        }

        // Несуществующий элемент с Comparator
        // "blueberry" опять между "banana"(1) и "cherry"(2) => insertion point=2 => -3
        printTestResult("MyCollections: несуществующий элемент с Comparator",
                "[apple, banana, cherry, date]",
                "blueberry", -3, MyCollections.binarySearch(stringList, "blueberry", cmp));
    }
}