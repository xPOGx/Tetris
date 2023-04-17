import java.util.stream.IntStream;

public class Field {
    private final int width;
    private final int height;
    private final int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field(int width, int height) {
        this.height = height;
        this.width = width;
        matrix = new int[height][width];
    }

    void print() {
        int[][] canvas = new int[height][width];

        for (int i = 0; i < height; i++) {
            System.arraycopy(matrix[i], 0, canvas[i], 0, width);
        }

        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }

        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();
    }

    void removeFullLines() {
        for (int i = height - 1; i >= 0; ) {
            if (IntStream.of(matrix[i]).sum() == width) {
                for (int j = i; j > 0; j--) {
                    matrix[j] = matrix[j-1];
                }
                matrix[0] = new int[width];
            } else i--;
        }
    }

    Integer getValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    void setValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = 1;
    }
}
