package com.example;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите задачу:");
            // РАСПИСАТЬ ВХОД

            int choice = scanner.nextInt();
            
            switch (choice) {
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                }
            scanner.close();
        }
    }
}