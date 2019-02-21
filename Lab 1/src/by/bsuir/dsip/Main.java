package by.bsuir.dsip;

import by.bsuir.dsip.action.Fourier;
import by.bsuir.dsip.bean.Complex;

public class Main {
    public static void main(String[] args) {
        int n = (int)Math.pow(2, 7);
        Complex[] x = new Complex[n];

        x[0] = new Complex(-0.03480425839330703, 0);
        x[1] = new Complex(0.07910192950176387, 0);
        x[2] = new Complex(0.7233322451735928, 0);
        x[3] = new Complex(0.1659819820667019, 0);

        for (int i = 4; i < n; i += 4) {
            x[i] = new Complex(x[0]);
            x[i + 1] = new Complex(x[1]);
            x[i + 2] = new Complex(x[2]);
            x[i + 3] = new Complex(x[3]);
        }
//        show(x, "x");

        Complex[] y = Fourier.fft(x);
        show(y, "y = fft(x)");

        Complex[] z = Fourier.ifft(y);
//        show(z, "z = ifft(y)");

        System.out.println("Done!");
    }

    private static void show(Complex[] x, String title) {
        System.out.println(title);
        System.out.println("-------------------");
        for (Complex elem : x) {
            System.out.println(elem);
        }
        System.out.println();
    }
}
