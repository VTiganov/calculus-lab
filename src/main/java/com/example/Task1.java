package com.example;

public class Task1 {
    public static float func(float x){
        return x;
    }

    public static float dichotomyMethod(float a, float b, float tolerance){
        float c;
        while ((b - a) / 2.0 > tolerance) {
            c = (a + b) / 2;
            
            if(func(c) == 0){
                return c;
            }

            else if(func(c) * func(a) < 0){
                b = c;
            }

            else{
                a = c;
            }
        }
        return (a + b) / 2;
    }

    
}
