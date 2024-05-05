package building_project;

class Work extends Thread {
    private int id;
    private String name;
    private int days;
    private int[] dependency;

    public Work(int id, String name, int days, int[] dependency) {
        this.id = id;
        this.name = name;
        this.days = days;
        this.dependency = dependency;
    }

    public int getDays() {
        return days;
    }

    @Override
    public void run() {
        try {
            for (int dep : dependency) {
                Main.thWork.get(dep).join();
            }
            if (!name.equals("строительства"))
                System.out.println("Начало " + name);
            Thread.sleep(1000L * days);
            if (!name.equals("работ"))
                System.out.println("Конец " + name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
