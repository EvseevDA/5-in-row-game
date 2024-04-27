import algorithm.AlphaBeta;
import game.GameImpl;

/**
 * Класс {@code Main} является точкой входа в приложение.
 * @see GameImpl
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class Main {

    /**
     * Метод, с которого начинается выполнение проекта.
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) {
        GameImpl XO = new GameImpl();
        XO.setAlgorithm(new AlphaBeta());
        XO.init();
        XO.start();
    }

}