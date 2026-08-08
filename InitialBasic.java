class A extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Unsynchronized and unprioritized Thread 1");
        }
    }
}
class B extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Unsynchronized and unprioritized Thread 2");
        }
    }
}
class C extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Unsynchronized and prioritized Thread 3");
        }
    }
}
class D extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Unsynchronized and prioritized Thread 4");
        }
    }
}
class E extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Synchronized and prioritized Thread 5");
            try{
                Thread.sleep(300);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
class F extends Thread{
    public void run(){
        for(int i = 0; i<100; i++){
            System.out.println("Synchronized and prioritized Thread 6");
            try{
                Thread.sleep(300);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}
public class InitialBasic {
    public static void main(String[] args) {
        A obj1 = new A();
        B obj2 = new B();
        E obj5 = new E();
        F obj6 = new F();
        C obj3 = new C();
        D obj4 = new D();
        obj3.setPriority(Thread.MAX_PRIORITY);
        obj4.setPriority(Thread.MIN_PRIORITY);
        System.out.println("Priority value at default of thread 1: "+ obj1.getPriority());
        System.out.println("Priority value at default of thread 2: "+ obj2.getPriority());
        System.out.println("Priority value after changing priority of thread 3: "+ obj3.getPriority());
        System.out.println("Priority value after changing priority of thread 4: "+ obj4.getPriority());
        obj1.start();
        obj2.start();
        obj3.start();
        obj4.start();        
        obj5.start();
        try{
            Thread.sleep(5);
        }catch(Exception e){
            System.out.println(e);
        }
        obj6.start();

        Runnable obj9 = () ->   //lambda expression way
        {
                for(int i = 0; i<10; i++){
                    System.out.println("Synchronized and prioritized Thread 1");
                    try{
                        Thread.sleep(10);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            };
            Runnable obj10 = () ->
        {
                for(int i = 0; i<10; i++){
                    System.out.println("Synchronized and prioritized Thread 2");
                    try{
                        Thread.sleep(10);
                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            };
        Thread t9 = new Thread(obj9);
        Thread t10 = new Thread(obj10);
        t9.start();
        t10.start();
    }
}