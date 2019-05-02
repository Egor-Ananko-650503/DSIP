package by.bsuir.dsip.action;

public class Hadamard {
    public static int[][] generate(int dim) {
        if(dim % 2 != 0) {
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
}
