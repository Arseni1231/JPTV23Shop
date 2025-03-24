package org.example.shop.Input;

import java.util.Scanner;

public interface Input {
    Scanner scanner = new Scanner(System.in);

    default String nextLine() {
        return scanner.nextLine();
    }

    default int nextInt() {
        while (true) {
            try {
                return Integer.parseInt(nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число: ");
            }
        }
    }

    default double nextDouble() {
        while (true) {
            try {
                return Double.parseDouble(nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число с плавающей запятой: ");
            }
        }
    }

    default long nextLong() {
        while (true) {
            try {
                return Long.parseLong(nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите длинное целое число: ");
            }
        }
    }
}

