package algorithm;

import field.Cell;
import fieldutil.FieldAnalysis;
import constants.Constants;
import player.Bot;
import player.User;
import util.Pair;
import field.Field;

/**
 * Класс представляющий собой реализацию алгоритма минимакс.<p>
 * Минимакс рекурсивно рассматривает возможные ходы как пользователя, так и бота и на основе оценки, полученной на последнем шаге рекурсии, возвращает лучшие координаты для хода.
 * @see Algorithm
 * @see Field
 * @see Bot
 * @see User
 * @see Constants
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class Minimax implements Algorithm {

    /**
     * Оболочка над методом minimax.
     * @param field поле, на котором играют бот и пользователь.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @return координаты для хода.
     */
    @Override
    public Pair<Integer, Integer>
    getBestCoordinatesToMove(Field field, Bot bot, User user) {
        return minimax(field, bot, user, 0).getFirstValue();
    }


    /**
     * Реализация алгоритма минимакс.
     * @param field поле, на котором играют бот и пользователь.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @param recursionStep шаг рекурсии.
     * @return пару из пары лучшей оценки из возможных и лучших координат для хода.
     */
    private Pair<Pair<Integer, Integer>, Integer>
    minimax(Field field, Bot bot, User user, int recursionStep) {

        // =============================================================================================================

        // если выиграл бот или игрок - вернуть приз с учетом шага рекурсии
        if (FieldAnalysis.isWinner(bot, field)) {
            return new Pair<>(new Pair<>(-1, -1), Constants.WIN_PRIZE - recursionStep * Constants.TAX_PER_RECURSION_STEP);
        } else if (FieldAnalysis.isWinner(user, field)) {
            return new Pair<>(new Pair<>(-1, -1), -Constants.WIN_PRIZE - recursionStep * Constants.TAX_PER_RECURSION_STEP);
        }

        // если поле закончилось или дошли до максимального шага рекурсии - вернуть оценку с учетом шага рекурсии
        if (FieldAnalysis.isFilled(field) || recursionStep == Constants.MAX_RECURSION_STEP) {
            return new Pair<>(new Pair<>(-1, -1),
                    Evaluation.eval(field, bot, user) - recursionStep * Constants.TAX_PER_RECURSION_STEP);
        }

        // =============================================================================================================

        Pair<Integer, Integer>[] freeCoordinates = FieldAnalysis.getCoordinatesOfFreeCells(field);
        Pair<Pair<Integer, Integer>, Integer> result = null;

        // ход бота
        if (recursionStep % 2 == 0) {

            Field tempField = (Field) field.clone();
            tempField.setCellStateAt(freeCoordinates[0].getFirstValue(), freeCoordinates[0].getSecondValue(), Cell.CellState.BOT_MARKED);

            Pair<Pair<Integer, Integer>, Integer> temp = minimax(tempField, bot, user, recursionStep + 1);

            int max = temp.getSecondValue();
            result = temp;

            result.getFirstValue().setFirstValue(freeCoordinates[0].getFirstValue());
            result.getFirstValue().setSecondValue(freeCoordinates[0].getSecondValue());

            for (int i = 1; i < freeCoordinates.length; ++i) {

                tempField = (Field) field.clone();
                tempField.setCellStateAt(freeCoordinates[i].getFirstValue(), freeCoordinates[i].getSecondValue(), Cell.CellState.BOT_MARKED);

                temp = minimax(tempField, bot, user, recursionStep + 1);

                if (temp.getSecondValue() > max) {
                    max = temp.getSecondValue();
                    result = temp;
                    result.getFirstValue().setFirstValue(freeCoordinates[i].getFirstValue());
                    result.getFirstValue().setSecondValue(freeCoordinates[i].getSecondValue());
                }
            }
        }

        // ход пользователя
        if (recursionStep % 2 == 1) {

            Field tempField = (Field) field.clone();
            tempField.setCellStateAt(freeCoordinates[0].getFirstValue(), freeCoordinates[0].getSecondValue(), Cell.CellState.USER_MARKED);

            Pair<Pair<Integer, Integer>, Integer> temp = minimax(tempField, bot, user, recursionStep + 1);

            int min = temp.getSecondValue();
            result = temp;

            result.getFirstValue().setFirstValue(freeCoordinates[0].getFirstValue());
            result.getFirstValue().setSecondValue(freeCoordinates[0].getSecondValue());

            for (int i = 1; i < freeCoordinates.length; ++i) {

                tempField = (Field) field.clone();
                tempField.setCellStateAt(freeCoordinates[i].getFirstValue(), freeCoordinates[i].getSecondValue(), Cell.CellState.USER_MARKED);

                temp = minimax(tempField, bot, user, recursionStep + 1);

                if (temp.getSecondValue() < min) {
                    min = temp.getSecondValue();
                    result = temp;
                    result.getFirstValue().setFirstValue(freeCoordinates[i].getFirstValue());
                    result.getFirstValue().setSecondValue(freeCoordinates[i].getSecondValue());
                }
            }
        }

        return result;
    }
}
