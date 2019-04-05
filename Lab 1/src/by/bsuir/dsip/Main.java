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
        fileAction.writeInRe(source, "reports/source.txt");

        for (int i = 0; i < N; i++) {
            x[i] = new Complex(Math.cos(3 * i) + Math.sin(2 * i), 0.0);
        }
        fileAction.writeInRe(x, "reports/before.txt");

        // FFT

        Complex[] fx = Fourier.fft(x);
        for (int i = 0; i < fx.length; i++) {
            fx[i] = fx[i].scale(1.0 / fx.length);
        }
//        Complex[] rfx = fx;
        Complex[] rfx = halfRevers(fx);
        fileAction.writeInAmpl(rfx, N, fd, "reports/fft-ampl.txt");
        fileAction.writeInPhase(rfx, N, fd, "reports/fft-phase.txt");

        Complex[] ifx = Fourier.ifft(fx);
        fileAction.writeInRe(ifx, "reports/fft-after.txt");

        // DFT

        Complex[] dx = Fourier.dft2(x);
        for (int i = 0; i < dx.length; i++) {
            dx[i] = dx[i].scale(1.0 / dx.length);
        }
//        Complex[] rdx = dx;
        Complex[] rdx = halfRevers(dx);
        fileAction.writeInAmpl(rdx, N, fd, "reports/dft-ampl.txt");
        fileAction.writeInPhase(rdx, N, fd, "reports/dft-phase.txt");

        Complex[] idx = Fourier.idft2(dx);
        fileAction.writeInRe(idx, "reports/dft-after.txt");

        System.out.println("Done!");

        if (args.length > 0) {
            int tSize = (int) Math.pow(2.0, 14.0);
            Complex[] testSrc = new Complex[tSize];
            for (int i = 0; i < tSize; i++) {
                var re = Math.random() * 10.0 - 5.0;
                var im = Math.random() * 10.0 - 5.0;
                testSrc[i] = new Complex(re, im);
            }
            long dftStart = System.currentTimeMillis();
            Complex[] testDft = Fourier.dft2(testSrc);
            System.out.println("Dft time: " + (System.currentTimeMillis() - dftStart) + " ms");

            long idftStart = System.currentTimeMillis();
            Complex[] testIdft = Fourier.idft2(testDft);
            System.out.println("Idft time: " + (System.currentTimeMillis() - idftStart) + " ms");

            long fftStart = System.currentTimeMillis();
            Complex[] testFft = Fourier.dft(testSrc);
            System.out.println("Fft time: " + (System.currentTimeMillis() - fftStart) + " ms");

            long ifftStart = System.currentTimeMillis();
            Complex[] testIfft = Fourier.idft(testFft);
            System.out.println("Ifft time: " + (System.currentTimeMillis() - ifftStart) + " ms");
        }

        /*for (int i = 0; i < tSize; i++) {
            System.out.println(testSrc[i] + " || " + testIdft[i] + " || " + testIfft[i]);
//            System.out.println(testDft[i] + " || " + testFft[i]);
        }*/
    }

    public static Complex[] halfRevers(Complex[] in) {
        Complex[] out = new Complex[in.length];
        for (int i = 0; i < in.length / 2; i++) {
            out[in.length / 2 - 1 - i] = in[i];
            out[in.length - 1 - i] = in[in.length / 2 + i];
        }
        return out;
    }
}
