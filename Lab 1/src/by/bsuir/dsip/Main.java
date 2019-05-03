package by.bsuir.dsip;

import by.bsuir.dsip.action.Action;
import by.bsuir.dsip.action.FileAction;
import by.bsuir.dsip.action.Hadamard;
import by.bsuir.dsip.bean.Complex;

//y = cos(3x) + sin(2x) N = 8
public class Main {
    public static void main(String[] args) {
        int N = 32;
        Complex[] y = new Complex[N];
        Complex[] z = new Complex[N];
        FileAction fileAction = new FileAction();

        for (int i = 0; i < N; i++) {
            y[i] = new Complex(Math.cos(3 * i), 0.0);
            z[i] = new Complex(Math.sin(2 * i), 0.0);
        }

        Complex[] resultCyclic = Action.convolutionCyclic(y, z);
        fileAction.writeInAmpl(resultCyclic, 1, 1, "reports/conv-classic.txt");
        Complex[] resultFFT = Action.convolutionCyclicFFT(y, z);
        fileAction.writeInAmpl(resultFFT, 1, 1, "reports/conv-fft.txt");
        Complex[] resultCoefcorr = Action.coefcorr(y, z);
        fileAction.writeInAmpl(resultCoefcorr, 1, 1, "reports/coefcorr-classic.txt");
        Complex[] resultCoefcorrFFT = Action.coefcorrFFT(y, z);
        fileAction.writeInAmpl(resultCoefcorrFFT, 1, 1, "reports/coefcorr-fft.txt");

        if (args.length > 0) {
            int tSize = (int) Math.pow(2.0, 13.0);
            Complex[] testX = new Complex[tSize];
            Complex[] testY = new Complex[tSize];
            for (int i = 0; i < tSize; i++) {
                var re = Math.random() * 1.0 - .14;
                var im = Math.random() * 1.0 - .26;
                testX[i] = new Complex(re, im);
                re = Math.random() * 1.0 - .23;
                im = Math.random() * 1.0 - .11;
                testY[i] = new Complex(re, im);
            }

            long convClassicStart = System.currentTimeMillis();
            Complex[] convClassic = Action.convolutionCyclic(testX, testY);
            System.out.println("Conv. classic time: " + (System.currentTimeMillis() - convClassicStart) + " ms");

            long convFftStart = System.currentTimeMillis();
            Complex[] convFft = Action.convolutionCyclicFFT(testX, testY);
            System.out.println("Conv. FFT time: " + (System.currentTimeMillis() - convFftStart) + " ms");

            long coefcorrClassicStart = System.currentTimeMillis();
            Complex[] coefcorrClassic = Action.coefcorr(testX, testY);
            System.out.println("Coefcorr. classic time: " + (System.currentTimeMillis() - coefcorrClassicStart) + " ms");

            long coefcorrFftStart = System.currentTimeMillis();
            Complex[] coefcorrFft = Action.coefcorrFFT(testX, testY);
            System.out.println("Coefcorr. FFT time: " + (System.currentTimeMillis() - coefcorrFftStart) + " ms");

            System.out.println(convClassic[0]);
            System.out.println(convFft[0]);
            System.out.println(coefcorrClassic[0]);
            System.out.println(coefcorrFft[0]);
        }
    }
}
