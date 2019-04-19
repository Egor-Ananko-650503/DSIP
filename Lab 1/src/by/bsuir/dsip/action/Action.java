package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

public class Action {

    public static Complex[] convolution(Complex[] x, Complex[] y) {
        int N = x.length;
        int M = y.length;
        Complex[] z = new Complex[M + N - 1];
        for (int i = 0; i < N + M - 1; i++) {
            z[i] = new Complex(0.0, 0.0);
            int k = i;
            for (int j = 0; j <= i & j < M; j++, k--) {
                if (k >= N) {
                    continue;
                }
                z[i] = z[i].plus(y[j].times(x[k]));
            }
            z[i] = z[i].scale(1.0 / (M + N - 1));
        }
        return z;
    }

    public static Complex[] convolutionCyclic(Complex x[], Complex y[]) {
        int N = x.length;

        if (N != y.length) {
            throw new IllegalArgumentException("x, y must have the same length");
        }

        if (N % 2 != 0) {
            throw new IllegalArgumentException("data length is not a power of 2");
        }

        Complex out[] = new Complex[N];
        for (int n = 0; n < N; n++) {
            out[n] = new Complex(0.0, 0.0);
            for (int m = 0; m < N; m++) {
                int yIdx = (n - m < 0) ? N - m + n : n - m;
                out[n] = out[n].plus(x[m].times(y[yIdx]));
            }
        }

        return out;
    }

    public static Complex[] convolutionCyclicFFT(Complex x[], Complex y[]) {
        int N = x.length;

        if (N != y.length) {
            throw new IllegalArgumentException("x, y must have the same length");
        }

        if (N % 2 != 0) {
            throw new IllegalArgumentException("data length is not a power of 2");
        }

        Complex[] xf = Fourier.fft(x);
        Complex[] yf = Fourier.fft(y);
        Complex[] xyMul = new Complex[N];
        for (int i = 0; i < N; i++) {
            xyMul[i] = xf[i].times(yf[i]);
        }

        return Fourier.ifft(xyMul);
    }
}
