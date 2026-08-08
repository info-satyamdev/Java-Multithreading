import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class UnfairLockExample {
    private final Lock unfairLock = new ReentrantLock(true);
    public void accessResource(){
        unfairLock.lock();
        try {
            System.out.println(Thread.  currentThread().getName() + " : Acquired the lock");
            Thread.sleep(1000);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        finally{
            System.out.println(Thread.currentThread().getName() + " : released the lock");
            unfairLock.unlock();
        }
    }
    public static void main(String[] args) {
        UnfairLockExample obj = new UnfairLockExample();
        Runnable task = new Runnable() {
            public void run(){
                obj.accessResource();
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }

}
