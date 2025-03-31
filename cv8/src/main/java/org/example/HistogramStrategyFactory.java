package org.example;

public class HistogramStrategyFactory {
    public static HistogramStrategy getStrategy(boolean isFileInput) {
        if (isFileInput) {
            return new FileHistogramStrategy();
        } else {
            return new TextHistogramStrategy();
        }
    }
}
