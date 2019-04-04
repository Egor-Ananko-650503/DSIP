package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileAction {

    private FileWriter fileWriter;
    private PrintWriter printWriter;

    private void initialize(String name){
        try {
            fileWriter = new FileWriter(name);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInRe(Complex[] x, String name){

        initialize(name);
        for(int i = 0; i < x.length; i++){
            printWriter.printf("%s, %s\n", i, x[i].re());
        }
        printWriter.close();
    }

    public void writeInAmpl(Complex[] x, int N, double fd, String name){

        initialize(name);
        for (int i = 0; i < x.length; i++){
            printWriter.printf("%s, %s\n", fd * ((double) i / N), x[i].abs());
        }
        printWriter.close();
    }

    public void writeInPhase(Complex[] x, int N, double fd, String name){

        initialize(name);
        for(int i = 0; i< x.length; i++){
            printWriter.printf("%s, %s\n", fd * ((double) i / N), x[i].phase());
        }
        printWriter.close();
    }
}
