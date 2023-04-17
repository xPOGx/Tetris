/**
 * Class FigureFactory generate figures.
 */
public class FigureFactory {
    /**
     * Six figure templates
     */
    public static final int[][][] BRICKS = {
            {
                {0, 1, 0, 0},   // X
                {0, 1, 1, 0},   // X X
                {0, 0, 1, 0},   //   X
                {0, 0, 0, 0}    //
            },
            {
                {0, 0, 1, 0},   //   X
                {0, 1, 1, 0},   // X X
                {0, 1, 0, 0},   // X
                {0, 0, 0, 0}    //
            },
            {
                {0, 0, 0, 0},   //
                {0, 1, 1, 0},   // X X
                {0, 1, 1, 0},   // X X
                {0, 0, 0, 0}    //
            },
            {
                {0, 1, 0, 0},   // X
                {0, 1, 0, 0},   // X
                {0, 1, 0, 0},   // X
                {0, 1, 0, 0}    // X
            },
            {
                {0, 1, 0, 0},   // X
                {0, 1, 0, 0},   // X
                {0, 1, 1, 0},   // X X
                {0, 0, 0, 0}    //
            },
            {
                {0, 0, 1, 0},   //   X
                {0, 0, 1, 0},   //   X
                {0, 1, 1, 0},   // X X
                {0, 0, 0, 0}    //
            },
            {
                {0, 1, 0, 0},   //   X
                {0, 1, 1, 0},   //   X
                {0, 1, 0, 0},   // X X
                {0, 0, 0, 0}    //
            }
    };

    /**
     * Methode get random figure.
     */
    public static Figure createRandomFigure(int x, int y) {
        int index = (int) (Math.random() * 6);
        return new Figure(x, y, BRICKS[index]);
    }
}
