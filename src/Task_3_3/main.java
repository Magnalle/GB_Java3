package Task_3_3;

import java.io.*;
import java.util.Random;

public class main {
    static final String src = "src/task_3_3/";
    static final int N = 1800; // размер страницы
    static final String fileName = "3_3.txt";

    public static void main(String[] args) {
        fileGeneration();
        fileReading();
    }

    public static final void fileGeneration(){
        long t = System.currentTimeMillis();
        int fileSize = 10 * 1024 * 512; // char в UTF занимает 2 байта
        try(PrintStream out = new PrintStream(src + fileName)){
            Random rnd = new Random();
            for(int i = 0; i < fileSize; i++){
                out.print((char)(rnd.nextInt(32) +  'А')) ;
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Время генерации файла (мс): " + (System.currentTimeMillis() - t));
    }

    public static final void fileReading(){
        long t = System.currentTimeMillis();
        try (FileInputStream in = new FileInputStream(src + fileName)) {
            byte[] arr = new byte[N * 2]; // char в UTF занимает 2 байта
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x, "UTF-8"));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Затраченное время (мс): " + (System.currentTimeMillis() - t));
    }
}
