package by.bsuir.dsip.action;

import by.bsuir.dsip.bean.Complex;

public class Action {

    public Complex[] convolution(Complex[] x, Complex[] y){

        int N = x.length;
        int M = y.length;
        Complex[] z = new Complex[M + N - 1];
        for(int i = 0; i < N + M - 1; i++){
            int k = i;
            for(int j = 0; j <= i & j < M; j++, k--){

                if(k >= N) {

                    continue;
                }
                z[i] = z[i].plus(y[j].times(x[k]));
            }
            z[i] = z[i].scale(1.0 / (M + N - 1));
        }
        return z;
    }
}

