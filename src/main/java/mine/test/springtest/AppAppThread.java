package mine.test.springtest;

public class AppAppThread extends Thread {

    public AppAppThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        // System.out.println("Thread run!");
        // for (int i = 0; i < 10; i++) {
        // for (long k = 0; k < 100000000; k++) {
        // }
        // System.out.println(this.getName() + " :" + i);
        // }

        for (int i = 0; i < 100; i++) {
            if ((i) % 10 == 0) {
                System.out.println("-------" + i);
            }
            System.out.print(i);
            try {
                Thread.sleep(2);
                System.out.print("    线程睡眠1毫秒！\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
