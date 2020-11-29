package task_5_1;

import java.util.ArrayList;
import java.util.concurrent.*;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        //Car.cdl = new CountDownLatch(CARS_COUNT);
        Car.cbStart = new CyclicBarrier(CARS_COUNT);
        Car.cbEnd = new CyclicBarrier(CARS_COUNT);
        Car.sem = new Semaphore(CARS_COUNT / 2);
        for (int i = 0; i < cars.length; i++) {
            Thread t = new Thread(cars[i]);
            t.start();
        }
    }
}



