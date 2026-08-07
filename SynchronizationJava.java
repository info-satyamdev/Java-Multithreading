class Counter {
    private int count = 0;
    // critical section
    
    public void increment() {
        // synchronized() -> synchronizes the threads to run in parallel and one after another
        synchronized (this) {
            count++;
        }
    }
    public void getCount() {
        System.out.println(count);
    }
}
class MyThread extends Thread {
    private Counter counter;

    MyThread(Counter counter) {
        this.counter = counter;
    }
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }
}
public class SynchronizationJava {
    public static void main(String[] args) {
        Counter counting = new Counter();
        MyThread t1 = new MyThread(counting);
        MyThread t2 = new MyThread(counting);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println(e);
        }
        counting.getCount();
    }
}