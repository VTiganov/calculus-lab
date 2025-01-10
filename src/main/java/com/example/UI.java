package com.example;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите задачу:");
            System.out.println("1. Найти корень функции на отрезке методом дихотомии");
            System.out.println("2. Выйти");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    findRootUsingDichotomy(scanner);
                    break;
                case 2:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void findRootUsingDichotomy(Scanner scanner) {
        System.out.println("Введите начальную точку a:");
        float a = scanner.nextFloat();
        System.out.println("Введите конечную точку b:");
        float b = scanner.nextFloat();
        System.out.println("Введите точность в формате числа с плавающей точкой:");
        float tolerance = scanner.nextFloat();

        float root = Task1.dichotomyMethod(a, b, tolerance);
        System.out.println("Корень функции приблизительно: " + root);
    }
}
