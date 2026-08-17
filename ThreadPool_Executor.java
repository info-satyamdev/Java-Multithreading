import java.util.concurrent.ArrayBlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool_Executor {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2));
        // types of blocking queues: ArrayBlockingQueue, LinkedinListBlockingQueue
        for(int i = 0 ; i<=10; i++){
            int taskID= i;
            executor.execute(()->{
                System.out.println("Task" + taskID + "Exceuted by: " + Thread.currentThread().getName());
            });
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                
            }
        }
        
    }
}
