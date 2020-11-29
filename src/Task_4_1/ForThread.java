package Task_4_1;

class ForThread implements Runnable{
    static Object lock = new Object();
    public String letter;
    volatile static int counter = 0;
    public static int max_counter;
    int currentThreadNum;
    public ForThread(String l){
        letter = l;
        currentThreadNum = max_counter++;
        //System.out.println(currentThreadNum);
    }
    @Override
    public void run() {
        for(int i = 0; i < 20; i++)
            synchronized (lock) {
                try {
                    while (counter != currentThreadNum){
                    //if(counter != currentThreadNum) {
                        //System.out.println("Thread " + currentThreadNum + " waiting");
                        lock.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.print(letter);
                counter = (counter + 1) % max_counter;
                //System.out.println("Thread " + currentThreadNum + " notifying");
                lock.notifyAll();
            }
    }
}