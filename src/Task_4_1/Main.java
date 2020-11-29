package Task_4_1;

public class Main {

    public static void main(String[] args) {
        ForThread.max_counter = 0;
        Thread t1 = new Thread(new ForThread("A"));
        t1.start();
        Thread t2 = new Thread(new ForThread("B"));
        t2.start();
        Thread t3 = new Thread(new ForThread("C"));
        t3.start();
    }
}
