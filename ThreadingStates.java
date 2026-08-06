public class ThreadingStates extends Thread {
    @Override
    public void run() {
        System.out.println("Running State");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadingStates thread = new ThreadingStates();
        System.out.println(thread.getState());
        thread.start();
        Thread.sleep(150);
        System.out.println(thread.getState());
        System.out.println(Thread.currentThread().getState());
        thread.join();
        System.out.println(thread.getState());
    }
}