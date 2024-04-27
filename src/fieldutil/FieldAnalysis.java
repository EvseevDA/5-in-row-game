/*
 * All rights reserved.
 */

package fieldutil;

import constants.Constants;
import field.Field;
import player.Player;
import util.Pair;

import java.util.Objects;

/**
 * Класс для анализа поля<p>
 * Содержит методы для получения информации о ситуации на поле
 * @see Field
 * @see Fields
 * @see Player
 * @see player.Bot
 * @see player.User
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class FieldAnalysis {

    /**
     * Определяет победил ли игрок.
     * @param player игрок, для которого производится оценка.
     * @param field поле, на котором производится оценка.
     * @return {@code true}, если игрок победил, иначе - {@code false}.
     */
    public static boolean isWinner(Player player, Field field) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(field);

        char[][] rowsToCheck = Fields.getAllDiagonalsRowsColumns(field);

        for (char[] chars : rowsToCheck) {
            if (hasFiveInARow(String.valueOf(chars), player.getMark())) {
                return true;
            }
        }

        return false;
    }


    /**
     * Определяет, заполнено ли поле.
     * @param field поле, для которого производится оценка.
     * @return {@code true}, если поле заполнено, иначе - {@code false}.
     */
    public static boolean isFilled(Field field) {
        Objects.requireNonNull(field);

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                if (field.isMarkedAt(i, j) == false) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Определяет число свободных ячеек на поле.
     * @param field поле, для которого производится оценка.
     * @return число свободных ячеек.
     */
    public static int getCountOfFreeCells(Field field) {
        Objects.requireNonNull(field);

        int result = 0;

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                result += field.isMarkedAt(i, j) ? 0 : 1;
            }
        }

        return result;
    }


    /**
     * Определяет свободные координаты на поле.
     * @param field поле, для которого производится оценка.
     * @return массив из пар координат свободных ячеек.
     */
    public static Pair<Integer, Integer>[] getCoordinatesOfFreeCells(Field field) {
        Objects.requireNonNull(field);

        @SuppressWarnings("unchecked")
        Pair<Integer, Integer>[] result = (Pair<Integer, Integer>[]) new Pair[FieldAnalysis.getCountOfFreeCells(field)];

        int resIndex = 0;

        for (int i = 0; i < Constants.FIELD_SIZE; ++i) {
            for (int j = 0; j < Constants.FIELD_SIZE; ++j) {
                if (field.isMarkedAt(i, j) == false) {
                    result[resIndex++] = new Pair<>(i, j);
                }
            }
        }

        return result;

    }


    /**
     * Определяет, есть ли в строке комбинация из 5 одинаковых символов подряд.
     * @param rowToEval строка, в которой ищется комбинация.
     * @param mark символ, из которого состоит комбинация.
     * @return {@code true}, если комбинация найдена, {@code false} - если не найдена.
     */
    private static boolean hasFiveInARow(String rowToEval, char mark) {
        Objects.requireNonNull(rowToEval);

        int index = rowToEval.indexOf(String.valueOf(mark).repeat(5));
        return index != -1;
    }

}
