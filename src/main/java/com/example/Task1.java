package com.example;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Task1 {

    public static float func(String expression, float x) {
        Expression e = new ExpressionBuilder(expression)
                .variable("x")
                .build()
                .setVariable("x", x);
        return (float) e.evaluate();
    }

    public static float dichotomyMethod(String expression, float a, float b, float tolerance) {
        if (func(expression, a) * func(expression, b) > 0) {
            throw new IllegalArgumentException("Значения функции в точках a и b должны иметь противоположные знаки.");
        }

        float c;
        while ((b - a) / 2.0 > tolerance) {
            c = (a + b) / 2;

            if (func(expression, c) == 0) {
                return c;
            } else if (func(expression, c) * func(expression, a) < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return (a + b) / 2;
    }
}
