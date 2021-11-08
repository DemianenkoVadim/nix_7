package ua.com.alevel.reversethreads;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReverseThread extends Thread implements Runnable {

    int counter;
    public static final int FIFTY_THREADS = 50;
    public static boolean exit = false;

    public ReverseThread(int counter) {
        this.counter = counter;
    }

    public void run() {
        if (counter < FIFTY_THREADS) {
            createThread(counter + 1);
        }
        System.out.println("Hello from: " + Thread.currentThread().getName());
    }

    public void createThread(int counter) {
        ReverseThread thread = new ReverseThread(counter);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error("Problem: thread has been interrupted " + e.getMessage());
        }
    }

    public static void starter() {
        ReverseThread thread = new ReverseThread(1);
        thread.start();
        try {
            ReverseThread.sleep(50);
            thread.stopped();
            ReverseThread.sleep(50);
        } catch (InterruptedException e) {
            log.error("Caught is" + e.getMessage());
        }
    }

    private void stopped() {
        exit = true;
    }
}
