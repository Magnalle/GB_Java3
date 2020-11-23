package Task_3_1_2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class main {
    static final String src = "src/task_3_1_2/";

    public static void main(String[] args) {
        task_4_1();
        task_4_2();
    }

    public static final void task_4_1(){
        try (FileInputStream in = new FileInputStream(src + "3_1.txt")) {
            byte[] arr = new byte[50];
            int x;
            while ((x = in.read(arr)) > 0) {
                System.out.print(new String(arr, 0, x, "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final void task_4_2(){
        ArrayList<FileInputStream> files = new ArrayList<>();
        SequenceInputStream in = null;
        FileOutputStream out = null;
        final int N = 5;
        byte[] arr = new byte[100];
        try {
            for (int i = 0; i < N; i++) {
                files.add(new FileInputStream(src + "3_2_" + (i + 1) +".txt"));
            }
            in = new SequenceInputStream(Collections.enumeration(files));
            out = new FileOutputStream(src + "3_2_result.txt");

            int x;
            while ((x = in.read(arr)) != -1) {
                out.write(arr, 0, x);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            for(FileInputStream file: files) {
                try {
                    file.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(in != null)
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            if(out != null)
                try {
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }
}
