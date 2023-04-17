import java.awt.event.KeyEvent;

/**
 * Class Tetris - main functionality of Game.
 */
public class Tetris {
    private final Field field;                  //Field with cells
    private Figure figure;                      //Figure
    private KeyboardObserver keyboardObserver; //Read keyboard
    private boolean isGameOver;                 //Game Over?

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = null;
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
     * Main program cycle
     */
    public void run() throws Exception {
        //Create object "keyboard observer" and run it
        keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Game not over :)
        isGameOver = false;
        // Create first figure in top middle width: x - half of width, y - 0
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);

        //while not Game Over
        while (!isGameOver) {
            //"keyboard observer" key pressed?
            if (keyboardObserver.hasKeyEvents()) {
                makeAction(keyboardObserver);
            }

            step();             //one more step
            field.print();      //print field status
            //noinspection BusyWait
            Thread.sleep(1000);
        }

        //Prints "Game Over"
        System.out.println("Game Over");
    }

    private void makeAction(KeyboardObserver keyboardObserver) {
        while (keyboardObserver.hasKeyEvents()) {
            KeyEvent event = keyboardObserver.getEventFromTop();
            //q - ends game
            if (event.getKeyChar() == 'q') {
                isGameOver = true;
                return;
            }
            //<- move figure left
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                figure.left();
                //-> move figure right
            else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                figure.right();
                //code 53 ("number 5") - rotate figure
            else if (event.getKeyCode() == 53)
                figure.rotate();
                //space - fall to the END
            else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                figure.downMaximum();
        }
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
            isGameOver = figure.getY() <= 1; //if landed on TOP - GAME OVER
            field.removeFullLines(); //clean full lines
            figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0); //create new figure
            keyboardObserver.cleatKeyEvents();
        }
    }

    public static Tetris game;

    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20);
        game.run();
    }
}
