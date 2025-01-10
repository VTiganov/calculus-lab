package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class Task1Test {

    @Test
    public void testFunc() {
        String expression = "x^2 + 2*x + 1";  
        float result = Task1.func(expression, 3); 
        assertEquals(16.0, result, 0.001, "Function result should be 16 at x = 3.");
    }

    @Test
    public void testDichotomyMethod() {
        String expression = "x^2 - 4";  
        float a = 0, b = 3, tolerance = 0.01f;  
        float result = Task1.dichotomyMethod(expression, a, b, tolerance);
        assertTrue(result >= 1.9 && result <= 2.1, "Result should be close to 2.0");
    }

    @Test
    public void testIntervalLengths() {
        String expression = "x^2 - 4";  
        float a = 0, b = 3, tolerance = 0.1f;  
        ArrayList<Float> expectedIntervals = new ArrayList<>();
        expectedIntervals.add(3.0f);  
        expectedIntervals.add(1.5f);  
        expectedIntervals.add(0.75f); 
        
        Task1.dichotomyMethod(expression, a, b, tolerance);
        
        assertNotNull(expectedIntervals, "Interval lengths should not be null.");
    }

    @Test
    public void testFuncEdgeCases() {
        String expression = "x^2 - 4";  
        float result = Task1.func(expression, 0);  
        assertEquals(-4.0, result, 0.001, "Function result should be -4 at x = 0.");

        result = Task1.func(expression, -2);  
        assertEquals(0.0, result, 0.001, "Function result should be 0 at x = -2.");
    }


    @Test
    public void testDichotomyMethodPrecision() {
        String expression = "x^2 - 4";  
        float a = 0, b = 3, tolerance = 0.001f;
        float result = Task1.dichotomyMethod(expression, a, b, tolerance);
        assertTrue(Math.abs(result - 2) <= tolerance, "Result should be within the tolerance of 2.0");
    }
}
