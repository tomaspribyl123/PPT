package org.example;

import java.util.Map;

public class TextHistogramStrategy implements HistogramStrategy {
    @Override
    public Map<Character, Integer> calculate(String input) {
        HistogramCalculator calc = new HistogramCalculator();
        return calc.calculateAbsoluteHistogram(input);
    }
}

