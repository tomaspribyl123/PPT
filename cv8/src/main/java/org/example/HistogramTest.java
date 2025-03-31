package org.example;

import java.util.Map;

public class HistogramTest {
    public static void main(String[] args) {
        HistogramCalculator calc = new HistogramCalculator();

        String testText = "HELLO JAVA! 123";
        Map<Character, Integer> abs = calc.calculateAbsoluteHistogram(testText);
        System.out.println("Absolutní histogram: " + abs);

        Map<Character, Double> rel = calc.calculateRelativeHistogram(abs);
        System.out.println("Relativní histogram: " + rel);
    }
}
