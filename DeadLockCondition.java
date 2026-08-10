class Pen {
    public synchronized void writeWithPenAndPaper(Paper paper) {
        System.out.println(Thread.currentThread().getName() + " : is using pen" + this + " and trying to use paper");
        paper.finishWriting();
    }
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " : Finished Writing");
    }
}


class Paper {
    public synchronized void writeWithPenAndPaper(Pen pen) {
        System.out.println(Thread.currentThread().getName() + " : is using paper" + this + " and trying to use pen");
        pen.finishWriting();
    }
    public synchronized void finishWriting() {
        System.out.println(Thread.currentThread().getName() + " : finished using paper " + this);
    }
}
class Task1 implements Runnable {
    private Pen pen;
    private Paper paper;
    public Task1(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}
class Task2 implements Runnable {
    private Pen pen;
    private Paper paper;
    public Task2(Pen pen, Paper paper) {
        this.pen = pen;
        this.paper = paper;
    }
    @Override
    public void run() {
        paper.writeWithPenAndPaper(pen);
        // synchronized (pen){      => This way the deadlock can be avaoided as it will force the thread2 which works with Task2 to acquire pen object before initializing so the thread2 will wait for the thread1 to finish first and release the object pen to initialize
        //     paper.writeWithPenAndPaper(pen);
        // }
    }
}
public class DeadLockCondition {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();
        Thread thread1 = new Thread(new Task1(pen, paper), "Thread1");
        Thread thread2 = new Thread(new Task2(pen, paper), "Thread2");
        thread1.start();
        thread2.start();
    }
}
