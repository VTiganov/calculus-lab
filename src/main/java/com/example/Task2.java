package com.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Task2 {

    public static double findRoot(String function, double a, double b, double epsilon) {
        Expression expression = new ExpressionBuilder(function)
                .variables("x")
                .build();

        if (f(expression, a) * f(expression, b) >= 0) {
            return Double.NaN;
        }

        double c = a;
        int iterations = 0;

        while ((b - a) >= epsilon) {
            c = (a + b) / 2;
            if (f(expression, c) == 0.0) {
                break;
            } else if (f(expression, c) * f(expression, a) < 0) {
                b = c;
            } else {
                a = c;
            }
            iterations++;
        }

        System.out.println("Number of iterations: " + iterations);
        return c;
    }

    private static double f(Expression expression, double x) {
        expression.setVariable("x", x);
        return expression.evaluate();
    }

    public static double calculateRMS(double foundRoot, double trueRoot) {
        return Math.sqrt((foundRoot - trueRoot) * (foundRoot - trueRoot));
    }
}
