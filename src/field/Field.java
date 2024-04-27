package field;

import constants.Constants;
import fieldutil.FieldAnalysis;
import fieldutil.Fields;
import player.Player;

import java.util.Arrays;

/**
 * Класс {@code Field} представляет с собой игровое поле.<p>
 * По факту, представляет собой коллекцию объектов типа {@code Cell}.<p><p>
 * Этот класс содержит методы для взаимодействия игрока с полем:
 *<blockquote><pre>
 * {@code public boolean setMarkAt(int i, int j, char mark)}
 * </pre></blockquote>
 * Для получения информации одной из ячеек:
 * <blockquote><pre>
 * {@code public boolean isMarkedAt(int i, int j)}
 * {@code public char getMarkAt(int i, int j)}
 * </pre></blockquote>
 *
 * @see Cell
 * @see Fields
 * @see FieldAnalysis
 * @see Player
 * @see player.User
 * @see player.Bot
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public final class Field implements Cloneable {

    private final Cell[][] field;

    /**
     * Заполняет поле ячейками со значениями по умолчанию.
     */
    public Field() {

        field = new Cell[Constants.FIELD_SIZE][Constants.FIELD_SIZE];

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                field[i][j] = Cell.getCellByState(Cell.CellState.EMPTY);
            }
        }
    }


    /**
     * @return строковое представление поля с отступами между элементами в строке - <pre>{@code indent} и между строками - {@code '\n'}.
     */
    @Override
    public String toString() {

        StringBuilder fieldView = new StringBuilder();

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                fieldView.append(field[i][j].getMark()).append(Constants.INDENT);
            }
            fieldView.append('\n');
        }

        return fieldView.toString();
    }

    /**
     * Создает строковое представление поля с нумерованными строками и столбцами.<p>
     * Столбцы нумеруются сверху поля слева направо. Строки нумеруются слева от поля сверху вниз. Нумерация начинается с 1 и заканчивается размером поля.
     * @return представление текущего поля в виде строки с нумерацией строк и столбцов.<p>
     * Отступ между элементами строки - {@code indent}<p>
     * Между строками - {@code '\n'}
     */
    public String toStringWithNumberedRowsAndColumns() {

        StringBuilder fieldView = new StringBuilder();

        fieldView.append(Constants.INDENT);

        for (int i = 1; i <= Constants.FIELD_SIZE; ++i) {
            fieldView.append(i).append(Constants.INDENT);
        }

        fieldView.append('\n');

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            fieldView.append(i + 1).append(Constants.INDENT);
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                fieldView.append(field[i][j]).append(Constants.INDENT);
            }
            fieldView.append('\n');
        }

        return fieldView.toString();
    }

    /**
     * @param i координата по оси X, отсчет ведется слева направо, начиная с 0.
     * @param j координата по оси Y, отсчет ведется сверху вниз, начиная с 0.
     * @param state состояние, присваиваемое ячейке.
     * @return {@code true} в слуаче, если ячейка была закрашена в результате выполнения метода<p>
     *         {@code false} в противном случае.
     * @throws IndexOutOfBoundsException в случае, когда координаты выходят за пределы поля.
     */
    public boolean setCellStateAt(int i, int j, Cell.CellState state) {
        checkForBounds(i, j);

        if (field[i][j].isMarked()) {
            return false;
        }

        field[i][j] = Cell.getCellByState(state);

        return true;
    }


    /**
     * @param i координата по оси X, отсчет ведется слева направо, начиная с 0.
     * @param j координата по оси Y, отсчет ведется сверху вниз, начиная с 0.
     * @return метку ячейки с индексами i, j.
     * @throws IndexOutOfBoundsException в случае, когда координаты выходят за пределы поля.
     */
    public char getMarkAt(int i, int j) {
        checkForBounds(i, j);

        return field[i][j].getMark();
    }


    /**
     * @param i координата по оси X, отсчет ведется слева направо, начиная с 0.
     * @param j координата по оси Y, отсчет ведется сверху вниз, начиная с 0.
     * @return {@code true} в случае, если ячейка содержит в себе символ по умолчанию,
     *         {@code false} - в противном случае.
     * @throws IndexOutOfBoundsException в случае, когда координаты выходят за пределы поля.
     */
    public boolean isMarkedAt(int i, int j) {
        checkForBounds(i, j);

        return field[i][j].isMarked();
    }

    /**
     * @param i координата по оси X, отсчет ведется слева направо, начиная с 0.
     * @param j координата по оси Y, отсчет ведется сверху вниз, начиная с 0.
     * @return {@code true} в случае, если ячейка содержит в себе символ {@code mark},
     *         {@code false} - в противном случае.
     * @throws IndexOutOfBoundsException в случае, когда координаты выходят за пределы поля.
     */
    public boolean isMarkedByAt(int i, int j, char mark) {
        checkForBounds(i, j);

        return field[i][j].isMarkedBy(mark);
    }

    private void checkForBounds(int pos) {
        if (pos < 0 || pos >= Constants.FIELD_SIZE) {
            throw new IndexOutOfBoundsException("Wrong coordinates. Coordinates must be in range [0, "
                    + (Constants.FIELD_SIZE - 1) + "].");
        }
    }

    private void checkForBounds(int i, int j) {
        checkForBounds(i);
        checkForBounds(j);
    }

    /**
     * @return прототип поля, состоящий из прототипов всех его ячеек.
     */
    @Override
    public Object clone() {
        Field temp = new Field();

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                temp.field[i][j] = (Cell) field[i][j].clone();
            }
        }

        return temp;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Field field1 = (Field) object;
        return Arrays.deepEquals(field, field1.field);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(field);
    }
}
