package mine.test.springtest;

public class AppAppRunnable implements Runnable {
    private String name;

    public AppAppRunnable(String name) {
        // Thread();
        this.name = name;
    }

    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            // System.out.println("Runnable run!");
            for (long k = 0; k < 100000000; k++) {
            }
            System.out.println(name + ": " + i);
        }
    }
}
