package org.example;

import java.util.Map;

public interface HistogramStrategy {
    Map<Character, Integer> calculate(String input);
}