package by.bsuir.dsip.action;

public class Action {

    public double[] convolution(double[] x, double[] y){

        int N = x.length;
        int M = y.length;
        double[] z = new double[M + N - 1];
        for(int i = 0; i < N + M - 1; i++){
            int k = i;
            for(int j = 0; j <= i & j < M; j++, k--){

                if(k >= N) {

                    continue;
                }
                z[i] += y[j] * x[k];
            }
            z[i] /= (M + N - 1);
        }
        return z;
    }
}

