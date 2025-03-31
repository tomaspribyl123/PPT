package org.example;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileHistogramStrategy implements HistogramStrategy {
    @Override
    public Map<Character, Integer> calculate(String filePath) {
        HistogramCalculator calc = new HistogramCalculator();
        try {
            return calc.calculateFromFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}