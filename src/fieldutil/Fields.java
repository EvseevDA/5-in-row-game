package fieldutil;

import constants.Constants;
import field.Field;


/**
 * Утилита для поля.<p>
 * Содержит в себе статические методы для получения требуемых частей поля.
 * @see Field
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class Fields {

    /**
     * @return главную диагональ поля ({@code field}).
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getMainDiagonal(Field field) {
        char[] result = new char[Constants.FIELD_SIZE];

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            result[i] = field.getMarkAt(i, i);
        }

        return result;
    }


    /**
     * @param numberOfDiagonal номер нужной диагонали. Отсчет ведется справа налево, начиная с 1.
     * @return диагональ выше главной с номером {@code numberOfDiagonal}.
     * @throws IndexOutOfBoundsException если номер диагонали выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getUpperMainDiagonalNumber(int numberOfDiagonal, Field field) {
        char[] result;

        try {
            result = new char[numberOfDiagonal];
        } catch (NegativeArraySizeException e) {
            throw new IndexOutOfBoundsException("Number of diagonal outs of range [1, " + Constants.FIELD_SIZE + "].");
        }

        for (int i = 0, j = Constants.FIELD_SIZE - numberOfDiagonal;
             i < numberOfDiagonal && j < Constants.FIELD_SIZE; ++i, ++j) {

            result[i] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @param numberOfDiagonal номер нужной диагонали. Отсчет ведется сверху вниз, начиная с 1.
     * @return диагональ ниже главной с номером {@code numberOfDiagonal}.
     * @throws IndexOutOfBoundsException если номер диагонали выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getUnderMainDiagonalNumber(int numberOfDiagonal, Field field) {
        char[] result;

        try {
            result = new char[Constants.FIELD_SIZE - numberOfDiagonal];
        } catch (NegativeArraySizeException e) {
            throw new IndexOutOfBoundsException("Number of diagonal outs of range [1, " + Constants.FIELD_SIZE + "].");
        }

        for (int i = numberOfDiagonal, j = 0; i < Constants.FIELD_SIZE; ++i, ++j) {

            result[j] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @return побочную диагональ поля ({@code field}).
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getSecondaryDiagonal(Field field) {
        char[] result = new char[Constants.FIELD_SIZE];

        for (int i = 0, j = Constants.FIELD_SIZE - 1; i < Constants.FIELD_SIZE && j >= 0; ++i, --j) {
            result[i] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @param numberOfDiagonal номер нужной диагонали. Отсчет ведется справа налево, начиная с 1.
     * @return диагональ выше побочной с номером {@code numberOfDiagonal}.
     * @throws IndexOutOfBoundsException если номер диагонали выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getUpperSecondaryDiagonalNumber(int numberOfDiagonal, Field field) {
        char[] result;

        try {
            result = new char[Constants.FIELD_SIZE - numberOfDiagonal];
        } catch (NegativeArraySizeException e) {
            throw new IndexOutOfBoundsException("Number of diagonal outs of range [1, " + Constants.FIELD_SIZE + "].");
        }

        for (int i = 0, j = Constants.FIELD_SIZE - numberOfDiagonal - 1; i < Constants.FIELD_SIZE - 1 && j >= 0; ++i, --j) {
            result[i] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     *
     * @param numberOfDiagonal номер нужной диагонали. Отсчет ведется снизу вверх, начиная с 1.
     * @return диагональ ниже главной с номером {@code numberOfDiagonal}.
     * @throws IndexOutOfBoundsException если номер диагонали выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getUnderSecondaryDiagonalNumber(int numberOfDiagonal, Field field) {
        char[] result;

        try {
            result = new char[numberOfDiagonal];
        } catch (NegativeArraySizeException e) {
            throw new IndexOutOfBoundsException("Number of diagonal outs of range [1, " + Constants.FIELD_SIZE + "].");
        }

        for (int i = Constants.FIELD_SIZE - numberOfDiagonal, j = Constants.FIELD_SIZE - 1;
             i < Constants.FIELD_SIZE && j >= Constants.FIELD_SIZE - numberOfDiagonal; ++i, --j) {

            result[Constants.FIELD_SIZE - j - 1] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @param numberOfRow номер нужной строки.
     * @return строку с нимером {@code numberOfRow} поля {@code field}.
     * @throws IndexOutOfBoundsException если номер строки выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getRowNumber(int numberOfRow, Field field) {
        char[] result = new char[Constants.FIELD_SIZE];

        for (int i = numberOfRow - 1, j = 0; j < Constants.FIELD_SIZE; ++j) {
            result[j] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @param numberOfColumn номер нужного столбца.
     * @return столбец с нимером {@code numberOfRow} поля {@code field}.
     * @throws IndexOutOfBoundsException если номер столбца выходит за пределы поля.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[] getColumnNumber(int numberOfColumn, Field field) {
        char[] result = new char[Constants.FIELD_SIZE];

        for (int i = 0, j = numberOfColumn - 1; i < Constants.FIELD_SIZE; ++i) {
            result[i] = field.getMarkAt(i, j);
        }

        return result;
    }


    /**
     * @param field поле, с которого нужно собрать все диагонали, строки и столбцы.
     * @return все диагонали, строки и столбцы поля {@code field} в порядке:<p>
     *     1) Главная диагональ.<p>
     *     2) Побочная диагональ.<p>
     *     3) Диагонали выше главной.<p>
     *     4) Диагонали ниже главной.<p>
     *     5) Диагонали выше побочной.<p>
     *     6) Диагонали ниже побочной<p>
     *     7) Строки.<p>
     *     8) Столбцы.
     * @throws NullPointerException если значение поля {@code null}.
     */
    public static char[][] getAllDiagonalsRowsColumns(Field field) {
        char[][] result = new char[Constants.COUNT_OF_DIAGONALS_IN_FIELD + Constants.COUNT_OF_ROWS_AND_COLUMNS_IN_FIELD][];
        int resultIndex = 0;

        result[resultIndex++] = Fields.getMainDiagonal(field);
        result[resultIndex++] = Fields.getSecondaryDiagonal(field);

        for (int i = 1; i < Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getUpperMainDiagonalNumber(i, field);
        }
        for (int i = 1; i < Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getUnderMainDiagonalNumber(i, field);
        }
        for (int i = 1; i < Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getUpperSecondaryDiagonalNumber(i, field);
        }
        for (int i = 1; i < Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getUnderSecondaryDiagonalNumber(i, field);
        }
        for (int i = 1; i <= Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getRowNumber(i, field);
        }
        for (int i = 1; i <= Constants.FIELD_SIZE; ++i) {
            result[resultIndex++] = Fields.getColumnNumber(i, field);
        }

        return result;
    }

}
