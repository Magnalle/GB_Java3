package task_5_1;

import java.util.concurrent.*;

public class Car implements Runnable {
    private static int CARS_COUNT;
    //public static CountDownLatch cdl = null;
    public static CyclicBarrier cbStart = null, cbEnd = null;
    public static Semaphore sem = null;
    static {
        CARS_COUNT = 0;
    }
    private static volatile boolean startSent = false;
    private static Object startLock = new Object();
    private static volatile boolean endSent = false;
    private static Object endLock = new Object();;

    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cbStart.await();
            synchronized (startLock){
                if(!startSent){
                    System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                    startSent = true;
                }
            }
//            cdl.countDown();
//            try {
//                cdl.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        try {
            cbStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        synchronized (endLock) {
            if (!endSent) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
                endSent = true;
            }
        }
    }
}