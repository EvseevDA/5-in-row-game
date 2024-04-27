package userinputreader;

import constants.Constants;
import util.Pair;

import java.io.Closeable;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс для чтения пользовательского ввода, связанного с игрой.
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class UserInputReader implements Closeable {

    private final Scanner reader = new Scanner(System.in);

    /**
     * Читает координаты, вводимые пользователем
     * @return пару из координат, введенных пользователем
     */
    public Pair<Integer, Integer> readCoordinates() {
        int firstCoordinate;
        int secondCoordinate;

        try {
            firstCoordinate = reader.nextInt();
            secondCoordinate = reader.nextInt();
        } catch (InputMismatchException ex) {
            reader.nextLine();
            throw new RuntimeException(Constants.NOT_NUMERIC_COORDINATES_MESSAGE);
        }

        return new Pair<>(firstCoordinate, secondCoordinate);
    }


    /**
     * Читает ответы пользователя на предложения внутри игры
     * @return введенный пользователем ответ
     */
    public int readChoose() {
        int choose;
        try {
            choose = reader.nextInt();
        } catch (InputMismatchException ex) {
            reader.nextLine();
            throw new RuntimeException("Choose must be a number");
        }

        return choose;
    }

    /**
     * Закрывает reader.
     */
    @Override
    public void close() {
        reader.close();
    }

}
