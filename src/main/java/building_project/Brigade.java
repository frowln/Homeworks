package building_project;

import java.util.List;

class Brigade extends Thread {
    private final String nameOfBrigade;
    public List<Integer> workIds;
    private long idleTime;

    public List<Integer> getWorkIds() {
        return workIds;
    }

    private long gamePlayTime;

    public Brigade(String nameOfBrigade, List<Integer> workIds) {
        this.nameOfBrigade = nameOfBrigade;
        this.workIds = workIds;
        this.idleTime = 0;
        this.gamePlayTime = 0;
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();

            Main.thWork.get(1).join();

            for (int workId : workIds) {
                Main.thWork.get(workId).join();
            }

            long endTime = System.currentTimeMillis();
            idleTime += endTime - startTime;

            System.out.println("Бригада " + nameOfBrigade + " завершила свои работы");

            addGameTime(idleTime);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addGameTime(long time) {
        this.gamePlayTime += time;
    }

    public long getGamePlayTime() {
        return gamePlayTime;
    }

    public String getnameOfBrigade() {
        return nameOfBrigade;
    }
}
