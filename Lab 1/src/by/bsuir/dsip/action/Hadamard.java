package by.bsuir.dsip.action;

public class Hadamard {
    public static double[][] generate(int dim) {
        if (dim % 2 != 0) {
            throw new IllegalArgumentException("dim is not a power of 2");
        }

        double[][] hadamard = new double[dim][dim];

        // initialize Hadamard matrix of order n
        hadamard[0][0] = 1.0;
        for (int k = 1; k < dim; k += k) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    hadamard[i + k][j] = hadamard[i][j];
                    hadamard[i][j + k] = hadamard[i][j];
                    hadamard[i + k][j + k] = hadamard[i][j] == 1.0 ? -1.0 : 1.0;
                }
            }
        }

        return hadamard;
    }

    public static double[][] toWalsh(double[][] hadamard) {
        int n = hadamard.length;
        int m = hadamard[0].length;

        if (n % 2 != 0 || m % 2 != 0) {
            throw new IllegalArgumentException("dim is not a power of 2");
        }

        double[][] walsh = new double[n][n];
        for (double[] line : hadamard) {
            int newIndex = 0;
            for (int j = 1; j < n; j++) {
                if (line[j] != line[j - 1]) newIndex++;
            }
            System.arraycopy(line, 0, walsh[newIndex], 0, n);
        }

        return walsh;
    }
}
