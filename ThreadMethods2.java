public class ThreadMethods2 extends Thread{
    public void run(){
        while (true) {
            System.out.println("Hello my friend");
        }
    }
    public static void main(String[] args) {
        ThreadMethods2 t1 = new ThreadMethods2();
        // setDaemon() ->marks a thread as a daemon thread. Unlike normal user threads, daemon threads automatically terminate when all active user threads finish execution.
// Example: If a user thread counts from 1 to 10 (with a 1-second delay) and a daemon thread is set to print "hello" in an infinite loop, the daemon thread will automatically stop running the moment the counting thread completes, regardless of its infinite loop.}
        t1.setDaemon(true);
        t1.start();
        System.out.println("Daemon thread terminated");
    }
}
