import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {
    public static void main(String[] args)  {
        ExecutorService executor =  Executors.newFixedThreadPool(5);
        // types of ThreadPools: newFixedThreadPool, newCachedThreadPool, newSingleThreadPool,newScheduledThreadPool
        for (int i = 0; i<10; i++){
            int Taskid = i;
            executor.execute(()->{
                System.out.println(("Task " + Taskid + " is performed by " + Thread.currentThread().getName()));
            });
        }
        Future <Integer> f1 = executor.submit(()-> 10);
        try {
            System.out.println(f1.get());
        } catch (ExecutionException e) {
            
        } catch(  InterruptedException e){
            Thread.currentThread().interrupt();
        }

        // why we use submit in place of execute
        Future <Integer> f2 = executor.submit(() -> {return 10/0;});
        try {
            System.out.println(f2.get());
        } catch (Exception e) {
            System.out.println("Cached Exception from submit");
        }
        // that's the reason as the exceptions can't be cached in execute method
        // try {
        //     executor.execute(() -> {
        //         int x = 10 / 0;
        //         // System.out.println(x);
        //     });
        // } catch (Exception e) {
        //     System.out.println("Cached exception from execute");
        // }
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        scheduler.schedule(()-> {System.out.println("Hello " + Thread.currentThread().getName());}, 2, TimeUnit.MILLISECONDS);
        ScheduledFuture<?> fixedRateHandle = scheduler.scheduleWithFixedDelay(()-> {System.out.println("Hi : " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },0, 2,TimeUnit.MILLISECONDS);
        ScheduledFuture<?> fixedDelayHandle = scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Fixed-delay task started...");
            try {
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Fixed-delay task finished.");
        }, 1, 2, TimeUnit.MILLISECONDS);

        // Stop the scheduler after 10 MILLISECONDS so the program can exit
        scheduler.schedule(() -> {
            System.out.println("Shutting down scheduler...");
            fixedRateHandle.cancel(true);
            fixedDelayHandle.cancel(true);
            scheduler.shutdown();
        }, 10, TimeUnit.MILLISECONDS);
        // executor.shutdown();
        executor.close();
        
    }
}
