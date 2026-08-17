class Student{
    public void method(String name){}
}
public class LambdaExpression{
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello");
        Thread t1 = new Thread(runnable);
        t1.start();
        Thread t2 = new Thread(() -> {
            for(int i = 0; i<10;i++){
                System.out.println("HI");
            }
        });
        t2.start();
        Student stud = new Student(){
            public void method(String name){
                for(int i =0;i<10;i++){
                    System.out.println(name + " " + i);
                }
            }
        } ;
        stud.method("Developer");
        Runnable runn = () ->{
            System.out.println("Namaste");
        };
        Thread t3 = new Thread(runn);
    }
}