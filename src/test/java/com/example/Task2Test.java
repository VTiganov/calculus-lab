package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.List;

public class Task2Test {

    @Test
    public void testFindRoots() {
    String function = "x*x*x - 4*x - 9";
    double a = 2;
    double b = 3;
    double epsilon = 0.000001;
    double expectedRoot = 2.706528663635254; // Approximate value of the root

    List<Double> roots = Task2.findRoots(function, a, b, epsilon);
    assertTrue(roots.size() > 0, "The root list should not be empty");
    assertTrue(Math.abs(roots.get(0) - expectedRoot) < 2 * epsilon, "The root list does not contain the expected root");
}


    @Test
    public void testCalculateRMS() {
        double foundRoot = 2.5;
        double trueRoot = 2.5;
        double expectedRMS = 0.0;

        double rms = Task2.calculateRMS(foundRoot, trueRoot);
        assertEquals(expectedRMS, rms, 0.000001, "RMS does not match the expected value");
    }

    @Test
    public void testFindRootsNoRoot() {
        String function = "x*x";
        double a = 1;
        double b = 2;
        double epsilon = 0.000001;

        List<Double> roots = Task2.findRoots(function, a, b, epsilon);
        assertTrue(roots.isEmpty(), "The root list should be empty as no root is expected");
    }

    @Test
    public void testFindRootsDifferentFunction() {
        String function = "x";
        double a = -2;
        double b = 3;
        double epsilon = 0.000001;
        double expectedRoot = 0; // Approximate value of the root

        List<Double> roots = Task2.findRoots(function, a, b, epsilon);
        assertTrue(roots.size() > 0, "The root list should not be empty");
        assertTrue(Math.abs(roots.get(0) - expectedRoot) < epsilon, "The root list does not contain the expected root");
    }

    @Test
    public void testCalculateRMSDifferentRoots() {
        double foundRoot = 2.5;
        double trueRoot = 3.0;
        double expectedRMS = 0.5;

        double rms = Task2.calculateRMS(foundRoot, trueRoot);
        assertEquals(expectedRMS, rms, 0.000001, "RMS does not match the expected value");
    }
}
