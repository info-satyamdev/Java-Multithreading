import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {
    public final Lock lock = new ReentrantLock();
    public void outerMethod() {
        try {
            // Acquires the lock unless the current thread is interrupted.
            // Unlike lock.lock(), this immediately throws InterruptedException if
            // interrupted while waiting.
            lock.lockInterruptibly();
            try {
                System.out.println("Outer Method");
                // Calling innerMethod which also requests the exact same lock
                innerMethod();
            } finally {
                // Decrements hold count (Hold Count becomes 0).
                // The lock is officially released to other threads only when hold count reaches
                // 0.
                lock.unlock();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void innerMethod() {
        // REENTRANCY IN ACTION:
        // Since the current thread already holds 'lock', calling lock() here succeeds
        // immediately.
        // It simply increments the internal hold count instead of blocking.
        lock.lock(); // Hold count = 2
        try {
            System.out.println("Inner Method");
        } finally {
            // Releases one level of lock retention (Hold Count becomes 1).
            // Lock is NOT yet released to other threads because outerMethod still holds it.
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantExample obj = new ReentrantExample();
        obj.outerMethod();
    }
}
