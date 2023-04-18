/**
 * Class Tetris - main functionality of Game.
 */
public class Tetris {
    private final Field field;                  //Field with cells
    private Figure figure;                      //Figure
    private boolean isGameOver;                 //Game Over?

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = FigureFactory.createRandomFigure(field.getWidth()/2 - 2, 0);
        isGameOver = false;
    }

    /**
     * Getter field
     */
    public Field getField() {
        return field;
    }

    /**
     * Getter figure.
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * One game step
     */
    public void step() {
        //move figure down
        figure.down();
        //if it can't:
        if (!figure.isCurrentPositionAvailable()) {
            figure.up(); //move up
            figure.landed(); //land
            isGameOver = figure.getY() <= 0; //if landed on TOP - GAME OVER
            field.removeFullLines(); //clean full lines
            if (!isGameOver) {
                figure = FigureFactory.createRandomFigure(field.getWidth() / 2 - 2, 0); //create new figure
            }
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
