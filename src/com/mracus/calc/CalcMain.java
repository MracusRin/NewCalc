package com.mracus.calc;


import java.util.Arrays;
import java.util.Scanner;

/**
 * NewCalc
 * Программа выполняет простые арифметические операции +, -, /, *
 * На вход принимаются одновременно только арабские или только римские цифры.
 * Цифры должны быть мерьше или равны 10.
 * Цифры и оператор должны быть разделены пробелом.
 *
 * @author MracusRin
 * @version 0.1a
 */

public class CalcMain {
    public static void main(String[] args) {

        //TODO: Оптимизировать логику работы программы и вызов исключений.
        //TODO: Переписать все с использованием классов.
        //TODO: Исправить агоритм перевода чисел toRoman(), IIII != 4

        Scanner scanner = new Scanner(System.in);
        int num1;
        int num2;

        welcome();
        System.out.print("Введите вырежение: ");
        String inputStr = scanner.nextLine();
        String[] arrayStr = inputStr.split(" ");

        if (arrayStr.length <= 2) {
            System.out.println("Ошибка. Строка не является математической операцией.");
        } else if (arrayStr.length > 3) {
            System.out.println("Формат математической операции не удовлетворяет заданию\n" +
                    "> Два операнда и один оператор (+, -, /, *)");
        } else {
            String num1Str = arrayStr[0];
            String num2Str = arrayStr[2];
            String arg = arrayStr[1];
            if (isNumber(num1Str) && isNumber(num2Str)) {
                num1 = Integer.parseInt(num1Str);
                num2 = Integer.parseInt(num2Str);
                if (num1 > 10 || num2 > 10) {
                    System.out.println("Ошибка. Введите числа меньше или равные 10.");
                } else {
                    System.out.println("Ответ: " + calcResult(num1, num2, arg));
                }
            } else if (isRoman(num1Str) && isRoman(num2Str)) {
                num1 = toArabic(num1Str);
                num2 = toArabic(num2Str);
                if (num1 > 10 || num2 > 10) {
                    System.out.println("Ошибка. Введите числа меньше или равные X.");
                } else {
                    System.out.println("Ответ: " + toRoman(calcResult(num1, num2, arg)));
                }
            } else {
                System.out.println("Ошибка. Ипользуюйте одновременно только арабские или только римские цифры.");
            }
        }
    }


    public static boolean isNumber(String str) {
        return str.replaceAll("[0-9]", "").length() == 0;
    }


    public static boolean isRoman(String str) {
        return str.replaceAll("[XLIVECD]", "").length() == 0;
    }


    public static int calcResult(int num1, int num2, String arg) {
        int result = 0;
        switch (arg) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "/" -> result = num1 / num2;
            case "*" -> result = num1 * num2;
            default -> System.out.println("Ошибка. Операция " + arg + " не поддерживается.");
        }
        return result;
    }


    public static String toRoman(int num) {
        if (num < 1) {
            return "Недопустимая операция. В римской системе нет отрицательных чисел.";
        }
        String result = "";
        int[] romanValues = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanChars = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < romanValues.length; i++) {
            while (num >= romanValues[i]) {
                num -= romanValues[i];
                result += romanChars[i];
            }
        }
        return result;
    }


    public static int toArabic(String romanStr) {
        String[] romanChars = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return Arrays.asList(romanChars).indexOf(romanStr) + 1;
    }


    public static void welcome() {
        String welcome = """
                NewCalc 0.1a
                > Формат ввода: два арабских или два римских числа
                  разделенные пробелами и знаком арифметической операции.
                > Доступны простые арефметческие операции (+, -, /, *)
                """;
        System.out.println(welcome);
    }

}
