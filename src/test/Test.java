package test;

public class Test {
    static int[][] matrix = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
    };
    public static void main(String[] args) {
        rotate();
        rotate();
        rotate();
        rotate();
    }

    static void rotate() {
        int[][] matrix2 = new int[4][4];

        for (int width = 0, m = 0; width < 4; width++, m++) {
            for (int height = 3, n = 0; height >= 0; height--, n++) {
                matrix2[m][n] = matrix[height][width];
            }
        }

        matrix = matrix2;

        print();
    }

    static void print() {
        for (int i = 0; i < 4; i++) {
            for (int i1 = 0; i1 < 4; i1++) {
                System.out.print(matrix[i][i1]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
