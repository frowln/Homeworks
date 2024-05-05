package integral;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static double totalSum = 0.0;

    public static void main(String[] args) throws InterruptedException {
        int count = Runtime.getRuntime().availableProcessors();
        System.out.println("Количество потоков: " + count);
        double sp = Math.sqrt(Math.PI);
        double h = sp / count;

        // Запуск задачи без синхронизации
        runWithoutSynchronization(count, h);

        // Запуск задачи с синхронизацией
        runWithSynchronization(count, h);
    }

    // Запуск задачи без синхронизации
    private static void runWithoutSynchronization(int count, double h) throws InterruptedException {
        List<SumPart> thList = new ArrayList<>();
        double startTime = System.nanoTime();

        for (int i = 0; i < count; i++) {
            thList.add(new SumPart(i * h, (i + 1) * h));
        }

        thList.forEach(Thread::start);

        for (SumPart thread : thList) {
            thread.join();
        }

        double endTime = System.nanoTime();

        double result = thList.stream().mapToDouble(SumPart::getPartSum).sum();
        System.out.println("Результат без синхронизации: " + result + " : " + (endTime - startTime));
    }

    // Запуск задачи с синхронизацией
    private static void runWithSynchronization(int count, double h) throws InterruptedException {
        totalSum = 0.0; // обнуляем сумму перед новым вычислением
        List<SumPart> thList = new ArrayList<>();
        double startTime = System.nanoTime();

        for (int i = 0; i < count; i++) {
            thList.add(new SumPart(i * h, (i + 1) * h));
        }

        thList.forEach(Thread::start);

        for (SumPart thread : thList) {
            thread.join();
        }

        double endTime = System.nanoTime();

        System.out.println("Результат с синхронизацией: " + totalSum + " : " + (endTime - startTime));
    }

    // Метод для добавления частичной суммы к общей сумме с синхронизацией
    public static synchronized void addToTotalSum(double partSum) {
        totalSum += partSum;
    }
}
