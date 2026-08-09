import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteExample {
    private int count = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    public void increment() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }
    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }
    public static void main(String[] args) {
        ReadWriteExample counter = new ReadWriteExample();
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " read : " + counter.getCount());
                }
            }
        };
        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<10;i++){
                    counter.increment();
                    System.out.println(Thread.currentThread().getName() + " : Incremented by 1");
                }
            }
        };
        Thread writeThread1 = new Thread(writeTask, "Thread1");
        Thread readThread1 = new Thread(readTask, "Thread2");
        Thread readThread2 = new Thread(readTask, "Thread3");
        writeThread1.start();
        readThread1.start();
        readThread2.start();
        try {
            writeThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        try {
            readThread1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        try {
            readThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        System.out.println("Final Count : " + counter.getCount());
    }
}