package task_4_2;

import java.util.ArrayList;

public class Main {
    static Object scaner = new Object();
    static Object printer = new Object();
    static Object network = new Object();
    static int counter = 0;

    static void printerBusy(int procNum){
        System.out.println("Процесс " + procNum + " печатает.");
    }
    static void scanerBusy(int procNum){
        System.out.println("Процесс " + procNum + " сканирует.");
    }
    static void networkBusy(int procNum){
        System.out.println("Процесс " + procNum + " передает по сети.");
    }

    static class printProcess implements Runnable{
        private int procNum;
        public printProcess(){
            procNum = counter++;
        }
        @Override
        public void run() {
            synchronized (network){
                networkBusy(procNum);
            }
            synchronized (printer){
                printerBusy(procNum);
            }
        }
    }

    static class scanProcess implements Runnable{
        int procNum;
        public scanProcess(){
            procNum = counter++;
        }
        @Override
        public void run() {
            synchronized (scaner){
                scanerBusy(procNum);
            }
            synchronized (network){
                networkBusy(procNum);
            }
        }
    }

    static class copyProcess implements Runnable{
        int procNum;
        public copyProcess(){
            procNum = counter++;
        }
        @Override
        public void run() {
            synchronized (scaner){
                synchronized (printer){
                    scanerBusy(procNum);
                    printerBusy(procNum);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 10; i ++)
            threads.add(new Thread(new printProcess()));
        for(int i = 0; i < 10; i ++)
            threads.add(new Thread(new scanProcess()));
        for(int i = 0; i < 10; i ++)
            threads.add(new Thread(new copyProcess()));
        for(int i = 0; i < threads.size(); i++)
            threads.get(i).start();
    }
}

