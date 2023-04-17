public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    void left() {
        x--;
        if (!isCurrentPositionAvailable()) {
            x++;
        }
    }

    void right() {
        x++;
        if (!isCurrentPositionAvailable()) {
            x--;
        }
    }

    void down() {
        y++;
    }

    void up() {
        y--;
    }

    void downMaximum() {
        while (isCurrentPositionAvailable()) {
            y++;
        }

        y--;
    }

    void rotate() {
        int[][] matrix2 = new int[4][4];
        int[][] original = new int[4][4];

        for (int i = 0; i < 4; i++) {
            System.arraycopy(matrix[i], 0, original[i], 0, matrix[i].length);
        }


        for (int width = 0, m = 0; width < 4; width++, m++) {
            for (int height = 3, n = 0; height >= 0; height--, n++) {
                matrix2[m][n] = matrix[height][width];
            }
        }

        matrix = matrix2;
        if (!isCurrentPositionAvailable()) {
            matrix = original;
        }
    }

    boolean isCurrentPositionAvailable() {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] == 1) {
                    if (y + i >= field.getHeight())
                        return false;
                    if (x + j < 0 || x + j >= field.getWidth())
                        return false;

                    Integer value = field.getValue(x + j, y + i);
                    if (value == null || value == 1)
                        return false;
                }
            }
        }

        return true;
    }

    void landed() {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrix[i][j] == 1)
                    field.setValue(x + j, y + i, 1);
            }
        }
    }
}
