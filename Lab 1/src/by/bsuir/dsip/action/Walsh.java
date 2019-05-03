package by.bsuir.dsip.action;

public class Walsh {

    public double[] DWT(double[][] walsh, double[] x){

        int N = walsh.length;
        double[] result = new double[N];
        for(int i = 0; i < N; i++){
            int value = 0;
            for(int j = 0; j < N; j++){
                value += walsh[i][j] * x[j];
            }
            result[i] = value;
        }
        return result;
    }
}
