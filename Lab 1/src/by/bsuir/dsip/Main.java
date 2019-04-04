package by.bsuir.dsip;

import by.bsuir.dsip.action.FileAction;
import by.bsuir.dsip.action.Fourier;
import by.bsuir.dsip.bean.Complex;

//y = cos(3x) + sin(2x) N = 8
public class Main {
    public static void main(String[] args) {
        final int N = 32;
        final double T = 1;
        final double fd = N / (2 * T);
        Complex[] x = new Complex[N];
        FileAction fileAction = new FileAction();
        final int NN = 128;

        Complex[] source = new Complex[NN];
        for (int i = 0; i < source.length; i++) {
            source[i] = new Complex(Math.cos(3 * i * 0.125) + Math.sin(2 * i * 0.125), 0.0);
        }
        fileAction.writeInRe(source, "source.txt");

        for (int i = 0; i < N; i++) {
            x[i] = new Complex(Math.cos(3 * i) + Math.sin(2 * i), 0.0);
        }
        fileAction.writeInRe(x, "before.txt");

        Complex[] dx = Fourier.fft(x);
//        Complex[] rdx = dx;

        Complex[] rdx = new Complex[dx.length];
        for (int i = 0; i < dx.length / 2; i++) {
            rdx[dx.length / 2 - 1 - i] = dx[i];
            rdx[dx.length - 1 - i] = dx[dx.length / 2 + i];
        }
        fileAction.writeInAmpl(rdx, N, fd, "ampl.txt");
        fileAction.writeInPhase(rdx, N, fd, "phase.txt");

        Complex[] idx = Fourier.ifft(dx);
        fileAction.writeInRe(idx, "after.txt");

        System.out.println("Done!");
    }
}
