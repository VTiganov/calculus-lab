package com.example;

public class Task2 {

    public static void main(String[] args) {
        double a = 0;
        double b = 2;
        double epsilon = 1e-6;

        double root = findRoot(a, b, epsilon);

        if (Double.isNaN(root)) {
            System.out.println("Root not found.");
        } else {
            System.out.println("Root: " + root);

            double trueRoot = Math.sqrt(2);
            double rms = calculateRMS(root, trueRoot);
            System.out.println("Root Mean Square Deviation: " + rms);
        }
    }

    public static double findRoot(double a, double b, double epsilon) {
        if (f(a) * f(b) >= 0) {
            return Double.NaN;
        }

        double c = a;
        int iterations = 0;

        while ((b - a) >= epsilon) {
            c = (a + b) / 2;
            if (f(c) == 0.0) {
                break;
            } else if (f(c) * f(a) < 0) {
                b = c;
            } else {
                a = c;
            }
            iterations++;
        }

        System.out.println("Number of iterations: " + iterations);
        return c;
    }

    public static double f(double x) {
        return x * x - 2;
    }

    public static double calculateRMS(double foundRoot, double trueRoot) {
        return Math.sqrt((foundRoot - trueRoot) * (foundRoot - trueRoot));
    }
}
