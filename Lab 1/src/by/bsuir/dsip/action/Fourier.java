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

        Complex[] y = fftImpl(x, true);
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

        double angle = 2 * Math.PI / n;
        Complex wn = new Complex(Math.cos(angle), Math.sin(angle));
        Complex w = new Complex(1.0, 0.0);

        if (opposite) wn = wn.conjugate();
        Complex[] y = new Complex[n];
        for (int k = 0; k < n / 2; ++k) {
            y[k] = evenCalc[k].plus(w.times(oddCalc[k]));
            y[k + n / 2] = evenCalc[k].minus(w.times(oddCalc[k]));
            w = w.times(wn);
        }

        return y;
    }

    public static Complex[] dft(Complex[] x) {
        return dftImpl(x, false);
    }

    public static Complex[] idft(Complex[] x) {
        int n = x.length;

        Complex[] y = dftImpl(x, true);
        for (int i = 0; i < n; i++) {
            y[i] = y[i].scale(1.0 / n);
        }

        return y;
    }

    private static Complex[] dftImpl(Complex[] x, boolean opposite) {
        int n = x.length;

        if (n == 1) {
            return new Complex[]{x[0]};
        }

        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        Complex[] y = new Complex[n];
        for (int i = 0; i < n; ++i) {
            y[i] = new Complex(0.0, 0.0);

            double angle = 2 * Math.PI * i / n;
            Complex wi = new Complex(Math.cos(angle), -1.0 * Math.sin(angle));
            Complex wj = new Complex(1.0, 0);

            if (opposite) wi = wi.conjugate();
            for (int j = 0; j < n; ++j) {
                y[i] = y[i].plus(x[j].times(wj));
                wj = wj.times(wi);
            }
        }

        return y;
    }

    public static Complex[] dft2(Complex[] in){
        int n = in.length;
        Complex[] out = new Complex[n];
        for (int i = 0; i<n; i++){
            double sumreal = 0;
            double sumimag = 0;
            for(int j = 0; j<n; j++){
                double angle = 2 * Math.PI * j * i / n;
                sumreal+= in[j].re()* Math.cos(angle) + in[j].im() * Math.sin(angle);
                sumimag+= -in[j].re()* Math.sin(angle) + in[j].im() * Math.cos(angle);
            }
            out[i] = new Complex(sumreal, sumimag);
        }
        return out;
    }
}
