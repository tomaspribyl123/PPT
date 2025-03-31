package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HistogramCalculator {

    public Map<Character, Integer> calculateAbsoluteHistogram(String text) {
        Map<Character, Integer> histogram = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                histogram.put(ch, histogram.getOrDefault(ch, 0) + 1);
            } else {
                histogram.put('#', histogram.getOrDefault('#', 0) + 1); // '#' jako ostatní znaky
            }
        }

        return histogram;
    }

    public Map<Character, Integer> calculateFromFile(String filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        return calculateAbsoluteHistogram(content);
    }

    public Map<Character, Double> calculateRelativeHistogram(Map<Character, Integer> absoluteHistogram) {
        Map<Character, Double> relativeHistogram = new HashMap<>();
        int total = absoluteHistogram.values().stream().mapToInt(i -> i).sum();

        for (Map.Entry<Character, Integer> entry : absoluteHistogram.entrySet()) {
            relativeHistogram.put(entry.getKey(), (double) entry.getValue() / total);
        }

        return relativeHistogram;
    }
}
