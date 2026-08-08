import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance = 200000;
    // Explicit ReentrantLock used to prevent race conditions during withdrawal
    public Lock lock = new ReentrantLock();
    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " - attempting to withdrawl : " + amount);
        try {
            // Attempt to acquire the lock within 1000ms (1 second).
            // Non-blocking alternative to lock.lock() to avoid indefinite
            // waiting/deadlocks.
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                // Critical Section Start: Safe to read/modify balance since lock is acquired
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " - Proceeding with withdrawal ");
                        // Simulate time taken by processing transaction (holding lock for 3s)
                        Thread.sleep(3000);
                        balance = balance - amount;
                        System.out.println(Thread.currentThread().getName()
                                + " Withdrawl complete! Available balance : " + (balance));
                    } catch (InterruptedException e) {
                        System.out.println("Inner sleep exception");
                        // Restore the thread's interrupted status so caller layers are aware of the
                        // interruption
                        // this informs other threads that there is a interruption in thread
                        Thread.currentThread().interrupt();
                    } finally {
                        // Crucial Best Practice: Always unlock in a finally block
                        // to guarantee lock release even if an exception occurs.
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " - Insufficient balance");
                    // Always release lock if condition fails after acquisition
                    lock.unlock();
                }
            } else {
                // Executed when lock acquisition times out (takes longer than 1000ms)
                System.out.println(
                        Thread.currentThread().getName() + " - Could Not accquire the lock, Try again later! ");
            }
        } catch (Exception e) {
            // Handles InterruptedException thrown specifically by tryLock()
            System.out.println("Outer lock execption");
            Thread.currentThread().interrupt();
        }
    }
}

public class JavaLocks {
    public static void main(String[] args) {
        // Shared resource instance across threads
        BankAccount bankUser = new BankAccount();
        // Define task each thread will execute
        Runnable task = new Runnable() {
            public void run() {
                bankUser.withdraw(4100);
            }
        };
        // Create two threads targeting the same BankAccount instance
        Thread t1 = new Thread(task, "Withdrawl 1");
        Thread t2 = new Thread(task, "Withdrawl 2");
        // Start execution concurrently
        // Note: Because withdrawal takes 3000ms and lock timeout is 1000ms,
        // the second thread will time out waiting for the lock.
        t1.start();
        t2.start();
    }
}