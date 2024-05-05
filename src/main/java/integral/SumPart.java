package integral;

public class SumPart extends Thread {
    private final double a;
    private final double b;
    private double sum;
    private static final int N = 100;

    public SumPart(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getPartSum() {
        return sum;
    }

    private Double func(Double x) {
        return Math.sin(x * x);
    }

    @Override
    public void run() {
        double h = (b - a) / N;
        for (int i = 0; i < N; i++) {
            sum += h * func(a + i * h + h / 2);
        }
        Main.addToTotalSum(sum);
    }
}