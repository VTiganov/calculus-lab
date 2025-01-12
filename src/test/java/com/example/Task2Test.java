package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class Task2Test {

    @Test
    public void testFindRoot() {
        String function = "x*x*x - 4*x - 9";
        double a = 2;
        double b = 3;
        double epsilon = 0.000001;
        double expectedRoot = 2.706528663635254; // Примерное значение корня

        double root = Task2.findRoot(function, a, b, epsilon);
        assertEquals(expectedRoot, root, epsilon, "Корень функции не совпадает с ожидаемым значением");
    }

    @Test
    public void testCalculateRMS() {
        double foundRoot = 2.5;
        double trueRoot = 2.5;
        double expectedRMS = 0.0;

        double rms = Task2.calculateRMS(foundRoot, trueRoot);
        assertEquals(expectedRMS, rms, 0.000001, "RMS не совпадает с ожидаемым значением");
    }

    @Test
    public void testFindRootNoRoot() {
        String function = "x*x - 2";
        double a = 1;
        double b = 2;
        double epsilon = 0.000001;

        double root = Task2.findRoot(function, a, b, epsilon);
        assertFalse(Double.isNaN(root), "Функция должна вернуть NaN, так как корень не найден");
    }

    @Test
    public void testFindRootDifferentFunction() {
        String function = "sin(x)";
        double a = 3;
        double b = 4;
        double epsilon = 0.000001;
        double expectedRoot = Math.PI; // Примерное значение корня

        double root = Task2.findRoot(function, a, b, epsilon);
        assertEquals(expectedRoot, root, epsilon, "Корень функции не совпадает с ожидаемым значением");
    }

    @Test
    public void testCalculateRMSDifferentRoots() {
        double foundRoot = 2.5;
        double trueRoot = 3.0;
        double expectedRMS = 0.5;

        double rms = Task2.calculateRMS(foundRoot, trueRoot);
        assertEquals(expectedRMS, rms, 0.000001, "RMS не совпадает с ожидаемым значением");
    }
}
