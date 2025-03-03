public class ElDvojbran {
    private double A, B, C, D; // Parametry dvojbranu

    public ElDvojbran(double A, double B, double C, double D) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
    }

    public double[] calculateOutput(double inputVoltage, double inputCurrent, double loadResistance) {
        // Mezivýpočty
        double unloadedVoltage = A * inputVoltage + B * inputCurrent;
        double unloadedCurrent = C * inputVoltage + D * inputCurrent;

        System.out.println("Mezivýpočty:");
        System.out.printf("Nezatížené napětí: %.2f V\n", unloadedVoltage);
        System.out.printf("Nezatížený proud: %.2f A\n", unloadedCurrent);

        // Výstupní napětí a proud
        double outputVoltage = unloadedVoltage * (loadResistance / (B + loadResistance));
        double outputCurrent = outputVoltage / loadResistance;

        System.out.println("Výsledky:");
        System.out.printf("Výstupní napětí: %.2f V\n", outputVoltage);
        System.out.printf("Výstupní proud: %.2f A\n", outputCurrent);

        return new double[]{outputVoltage, outputCurrent};
    }
}
