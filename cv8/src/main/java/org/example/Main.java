package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String vstupniText = "HELLO JAVA! TEST STRATEGY 123";

        HistogramStrategy strategy = HistogramStrategyFactory.getStrategy(false);

        Map<Character, Integer> absolutniHistogram = strategy.calculate(vstupniText);

        System.out.println("Absolutní histogram:");
        absolutniHistogram.forEach((k, v) -> System.out.println(k + ": " + v));

        HistogramCalculator calculator = new HistogramCalculator();
        Map<Character, Double> relativniHistogram = calculator.calculateRelativeHistogram(absolutniHistogram);

        System.out.println("\nRelativní histogram:");
        relativniHistogram.forEach((k, v) -> System.out.println(k + ": " + String.format("%.2f", v)));
    }
}
