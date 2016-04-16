package mine.test.springtest;

import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("-----main start!");

        AppAppRunnable appAppRunnable1 = new AppAppRunnable("myRunnable1");
        AppAppRunnable appAppRunnable2 = new AppAppRunnable("myRunnable2");

        Thread thread1 = new Thread(appAppRunnable1);
        Thread thread2 = new Thread(appAppRunnable2);

        // thread1.start();
        // thread2.start();

        AppAppThread appAppThread1 = new AppAppThread("myThread1");
        AppAppThread appAppThread2 = new AppAppThread("myThread2");

        appAppThread1.start();
        // appAppThread2.start();

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("a", "aa");
        hashMap.put("b", "bb");
        hashMap.put("c", "33");
        hashMap.put("o", "oo");
        hashMap.put("p", "pp");
        hashMap.put("q", "qq");
        hashMap.put("a", "aaa");

        String str = hashMap.get("2");

    }
}
