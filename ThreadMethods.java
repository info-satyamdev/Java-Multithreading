public class ThreadMethods extends Thread{
    // constructor like these can be used to change the name of threads
    public ThreadMethods(String name){
        super(name);
    }
    public void run(){
        for(int i = 0; i<10; i++){
            try {
                // sleep(-> pauses or makes the thread sleep for the desired entered time i.e., forces the thread to wait for the specific value of time
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(e);
            }
            // System.out.println(i);
            // currentThread()-> selects the current running thread| getName() -> returns the name of the thread | getPriority -> returns the priority of the thread 
            System.out.println(Thread.currentThread().getName() +   " | Priority: " + Thread.currentThread().getPriority() + " | Count: " + i);
            // yield() -> gives a hint to the scheduler that you should give a chance to other threads also, but keep in mind it gives a hint not forces it to synchronize or give chance to other threads
            Thread.yield();
        }
    }
    public static void main(String[] args){
        // as we seen in constructor the value in class name becomes the name of thread
        ThreadMethods t1 = new ThreadMethods("Developer");
        ThreadMethods t2 = new ThreadMethods("Sam");
        ThreadMethods t3 = new ThreadMethods("Reveloper");
        // setPriority -> used to set the priority of the thread
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);
        // start()-> starts or intitiates the thread
        t1.start();
        // interrupt() -> forces or interrupts the thread and returns its state
        t1.interrupt();
        t2.start();
        t3.start();
    }
}
