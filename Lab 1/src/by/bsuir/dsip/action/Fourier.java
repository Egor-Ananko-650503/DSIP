package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

public class Fourier {

    private Fourier() {
    }

    public static Complex[] fft(Complex[] x) {
        return fftImpl(x, false);
    }

    public static Complex[] ifft(Complex[] x) {
        int n = x.length;
        Complex[] y = new Complex[n];

        y = fftImpl(x, true);

        for (int i = 0; i < n; ++i) {
            y[i] = y[i].scale(1.0 / n);
        }

        return y;
    }

    private static Complex[] fftImpl(Complex[] x, boolean opposite) {
        int n = x.length;

        if (n == 1) {
            return new Complex[]{x[0]};
        }

        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        Complex[] even = new Complex[n / 2];
        for (int k = 0; k < n / 2; ++k) {
            even[k] = x[2 * k];
        }
        Complex[] evenCalc = fftImpl(even, opposite);

        Complex[] odd = even;
        for (int k = 0; k < n / 2; ++k) {
            odd[k] = x[2 * k + 1];
        }
        Complex[] oddCalc = fftImpl(odd, opposite);

        Complex wn = new Complex(Math.cos(2 * Math.PI / n), Math.sin(2 * Math.PI / n));
        if (opposite) wn = wn.conjugate();
        Complex w = new Complex(1.0, 0.0);
        Complex[] y = new Complex[n];
        for (int k = 0; k < n / 2; ++k) {
            y[k] = evenCalc[k].plus(w.times(oddCalc[k]));
            y[k + n / 2] = evenCalc[k].minus(w.times(oddCalc[k]));
            w = w.times(wn);
        }
        return y;
    }
}
