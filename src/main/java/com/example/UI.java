package com.example;

import java.util.List;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Select a task:");
                System.out.println("1. Find the root of a function using the dichotomy method");
                System.out.println("2. Find the roots of the equation f(x)=0 for a given function and interval");
                System.out.println("3. Exit");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter the task number.");
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
                        findRootsAndCalculateRMS(scanner);
                        break;
                    case 3:
                        System.out.println("Exiting the program.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }

    private static void findRootsAndCalculateRMS(Scanner scanner) {
        System.out.println("Enter the function f(x) (x*x*x - 4*x - 9 works perfectly):");
        String function = scanner.nextLine();

        double a = getDoubleInput(scanner, "Enter the starting point a:");
        double b = getDoubleInput(scanner, "Enter the ending point b:");
        double epsilon = getDoubleInput(scanner, "Enter the precision (e.g., 0.001):");
        double trueRoot = getDoubleInput(scanner, "Enter the true root:");

        if (a >= b) {
            System.out.println("Error: the starting point a must be less than the ending point b.");
            return;
        }

        try {
            List<Double> roots = Task2.findRoots(function, a, b, epsilon);
            if (roots.isEmpty()) {
                System.out.println("No roots found in the specified interval.");
            } else {
                System.out.println("Approximate roots of the function:");
                for (double root : roots) {
                    System.out.println(root);
                    double rms = Task2.calculateRMS(root, trueRoot);
                    System.out.println("RMS: " + rms);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void findRootUsingDichotomy(Scanner scanner) {
        System.out.println("Enter the function f(x) (x*x*x - 4*x - 9 works perfectly):");
        String function = scanner.nextLine();

        float a = getFloatInput(scanner, "Enter the starting point a:");
        float b = getFloatInput(scanner, "Enter the ending point b:");
        float tolerance = getFloatInput(scanner, "Enter the precision (e.g., 0.001 or 0,001):");

        if (a >= b) {
            System.out.println("Error: the starting point a must be less than the ending point b.");
            return;
        }

        try {
            float root = Task1.dichotomyMethod(function, a, b, tolerance);
            System.out.println("Approximate root of the function: " + root);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static float getFloatInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextFloat()) {
                float value = scanner.nextFloat();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Please enter a real number.");
                scanner.next();
            }
        }
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } else {
                System.out.println("Please enter a real number.");
                scanner.next();
            }
        }
    }
}
