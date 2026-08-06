public class ThreadMethods extends Thread{
    public ThreadMethods(String name){
        super(name);
    }
    public void run(){
        for(int i = 0; i<10; i++){
            try {
                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println(e);
            }
            // System.out.println(i);
            System.out.println(Thread.currentThread().getName() +   " | Priority: " + Thread.currentThread().getPriority() + " | Count: " + i);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadMethods t1 = new ThreadMethods("Developer");
        ThreadMethods t2 = new ThreadMethods("Sam");
        ThreadMethods t3 = new ThreadMethods("Reveloper");
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
    }
}
