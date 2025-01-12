package com.example;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Выберите задачу:");
                System.out.println("1. Найти корень функции методом дихотомии");
                System.out.println("2. Выйти");

                if (!scanner.hasNextInt()) {
                    System.out.println("Пожалуйста, введите номер задачи.");
                    scanner.next(); 
                    continue;
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        findRootUsingDichotomy(scanner);
                        break;
                    case 2:
                        System.out.println("Выход из программы.");
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                }
            }
        }
    }

    private static void findRootUsingDichotomy(Scanner scanner) {
        System.out.println("Введите функцию f(x) (x*x*x - 4*x - 9 точно работает):");
        String function = scanner.nextLine();

        float a = getFloatInput(scanner, "Введите начальную точку a:");
        float b = getFloatInput(scanner, "Введите конечную точку b:");
        float tolerance = getFloatInput(scanner, "Введите точность (например, 0.001 или 0,001):");

        if (a >= b) {
            System.out.println("Ошибка: начальная точка a должна быть меньше конечной точки b.");
            return;
        }

        try {
            float root = Task1.dichotomyMethod(function, a, b, tolerance);
            System.out.println("Корень функции приблизительно: " + root);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static float getFloatInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextFloat()) {
                float value = scanner.nextFloat();
                scanner.nextLine(); // Consume the newline character
                return value;
            } else {
                System.out.println("Пожалуйста, введите действительное число.");
                scanner.next(); // Consume the invalid input
            }
        }
    }
    
}
