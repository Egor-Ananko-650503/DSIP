package by.bsuir.dsip.action;

public class Hadamard {
    public static int[][] generate(int dim) {
        if (dim % 2 != 0) {
            throw new IllegalArgumentException("dim is not a power of 2");
        }

        int[][] hadamard = new int[dim][dim];

        // initialize Hadamard matrix of order n
        hadamard[0][0] = 1;
        for (int k = 1; k < dim; k += k) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    hadamard[i + k][j] = hadamard[i][j];
                    hadamard[i][j + k] = hadamard[i][j];
                    hadamard[i + k][j + k] = hadamard[i][j] == 1 ? -1 : 1;
                }
            }
        }

        return hadamard;
    }

    public static int[][] toWalsh(int[][] hadamard) {
        int n = hadamard.length;

        if (n % 2 != 0) {
            throw new IllegalArgumentException("dim is not a power of 2");
        }

        int[][] walsh = new int[n][n];
        for (int i = 0; i < n; i++) {
            int newIndex = 0;
            for (int j = 1; j < n; j++) {
                if (hadamard[i][j] != hadamard[i][j - 1]) newIndex++;
            }
            for (int j = 0; j < n; j++) {
                walsh[newIndex][j] = hadamard[i][j];
            }
        }

        return walsh;
    }
}
