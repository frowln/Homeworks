package building_project;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Work> thWork = new ArrayList<>();
    public static List<Brigade> brigades = new ArrayList<>();

    public static void main(String[] args) {
        thWork.add(new Work(0, "работ", 0, new int[]{}));
        thWork.add(new Work(1,"Проекта", 7, new int[]{0}));
        thWork.add(new Work(2, "Изготовления окон", 7, new int[]{1}));
        thWork.add(new Work(3, "Изготовления дверей", 7, new int[]{1}));
        thWork.add(new Work(4, "Возведения фундамента", 14, new int[]{1}));
        thWork.add(new Work(5,"Прокладки коммуникаций", 4, new int[]{4}));
        thWork.add(new Work(6, "Возведения стен", 14, new int[]{4}));
        thWork.add(new Work(7, "Возведения крыши", 3, new int[]{6}));
        thWork.add(new Work(8, "Установки окон", 1, new int[]{2, 7}));
        thWork.add(new Work(9,"Установки дверей", 1, new int[]{3, 7}));
        thWork.add(new Work(10, "Установки батарей", 3, new int[]{5, 8, 9}));
        thWork.add(new Work(11, "Прокладки электропроводки", 3, new int[]{5, 7}));
        thWork.add(new Work(12, "Отделки стен и потолка", 7, new int[]{8, 9, 11}));
        thWork.add(new Work(13,"Отделки пола", 7, new int[]{12}));
        thWork.add(new Work(14, "Установки осветительных приборов", 1, new int[]{12}));
        thWork.add(new Work(15, "Установка сантехники", 2, new int[]{5, 11, 12}));
        thWork.add(new Work(16, "строительства", 0, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 ,14, 15}));

        brigades.add(new Brigade("Каменщики", List.of(4, 6)));
        brigades.add(new Brigade("Плотники", List.of(2, 3, 7, 8, 9)));
        brigades.add(new Brigade("Сантехники", List.of(5, 10, 15)));
        brigades.add(new Brigade("Штукатуры", List.of(12, 13)));
        brigades.add(new Brigade("Электрики", List.of(11, 14)));

        long startTime = System.currentTimeMillis();

        for (Work work : thWork) {
            work.start();
        }

        for (Brigade brigade : brigades) {
            brigade.start();
        }

        try {
            for (Work work : thWork) {
                work.join();
            }

            for (Brigade brigade : brigades) {
                brigade.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long finistTime = System.currentTimeMillis();
        long resultDays = (finistTime - startTime) / 1000;
        System.out.println("Работа завершена за " + resultDays + " дней");


        for (Brigade brigade : brigades) {
            List<Integer> list = brigade.getWorkIds();
            int days = list.stream().mapToInt(v -> thWork.get(v).getDays()).sum();
            System.out.println("Бригада " + brigade.getnameOfBrigade() + " играла в домино " + (int) Math.ceil((double) (brigade.getGamePlayTime() - (days * 1000L + 7000)) / 1000) + " дней");
        }
    }
}

