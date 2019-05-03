package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

public class Walsh {

    public static Complex[] DWT(double[][] walsh, Complex[] x) {

        int N = x.length;
        Complex[] result = new Complex[N];
        for(int i = 0; i < N; i++){
            Complex value = new Complex(0, 0);
            for(int j = 0; j < N; j++){
                value.plus(x[j].times(new Complex(walsh[i][j], 0)));
            }
            result[i] = value;
        }

        return result;
    }

    public Complex[] IDWT(double[][] walsh, Complex[] x){

        Complex[] result = DWT(walsh, x);
        int N = result.length;
        for(int i = 0; i < N; i++){
            result[i].scale(1/N);
        }

        return result;
    }
}
