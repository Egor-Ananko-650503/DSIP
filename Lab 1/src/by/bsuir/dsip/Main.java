package by.bsuir.dsip;

import by.bsuir.dsip.action.Fourier;
import by.bsuir.dsip.bean.Complex;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//y = cos(3x) + sin(2x) N = 8
public class Main {
    public static void main(String[] args) {
        final int N = 32;
        final double T = 1;
        final double fd = N / (2 * T);
        Complex[] x = new Complex[N];

        try {
            FileWriter fileWriter = new FileWriter("source.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < 128; i++) {
                printWriter.printf("%s, %s\n", i, Math.cos(3 * i * 0.125) + Math.sin(2 * i * 0.125));
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter("before.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < N; i++) {
                x[i] = new Complex(Math.cos(3 * i) + Math.sin(2 * i), 0.0);
                printWriter.printf("%s\n", x[i].re());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Complex[] dx = Fourier.fft(x);
//        Complex[] rdx = dx;
        Complex[] rdx = new Complex[dx.length];
        for (int i = 0; i < dx.length / 2; i++) {
            rdx[dx.length / 2 - 1 - i] = dx[i];
            rdx[dx.length - 1 - i] = dx[dx.length / 2 + i];
        }
        try {
            FileWriter fileWriter = new FileWriter("ampl.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            FileWriter fileWriterP = new FileWriter("phase.txt");
            PrintWriter printWriterP = new PrintWriter(fileWriterP);
            for (int i = 0; i < rdx.length; i++) {
                printWriter.printf("%s, %s\n", fd * ((double) i / N), rdx[i].abs());
                printWriterP.printf("%s, %s\n", fd * ((double) i / N), rdx[i].phase());
            }
            printWriter.close();
            printWriterP.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Complex[] idx = Fourier.ifft(dx);
        try {
            FileWriter fileWriter = new FileWriter("after.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (int i = 0; i < idx.length; i++) {
                printWriter.printf("%s, %s\n", i, idx[i].re());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done!");
    }
}
