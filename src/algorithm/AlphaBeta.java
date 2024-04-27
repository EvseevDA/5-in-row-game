package algorithm;

import constants.Constants;
import field.Cell;
import field.Field;
import player.Bot;
import player.User;
import util.Pair;
import fieldutil.FieldAnalysis;


/**
 * Класс представляет собой реализацию алгоритма альфа-бета, который является оптимизаций минимакса.<p>
 * Альфа-бета позволяет досрочно прекратить рассмотрение некоторых ходов на основе уже рассмотренных.
 * @see Algorithm
 * @see Minimax
 * @see Field
 * @see Bot
 * @see User
 * @see Constants
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class AlphaBeta implements Algorithm {
    /**
     * Оболочка над методом альфа-бета.
     * @param field поле, на котором играют бот и пользователь.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @return лучшие координаты для хода.
     */
    @Override
    public Pair<Integer, Integer>
    getBestCoordinatesToMove(Field field, Bot bot, User user) {
        return alphaBeta(field, bot, user, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0).getFirstValue();
    }


    /**
     * Реализация алгоритма альфа-бета.
     * @param field поле, на котором играют бот и пользователь.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @param alpha значение числа альфа (по умолчанию -бесконечность).
     * @param beta значение числа бета (по умолчанию +бесконечность).
     * @param recursionStep шаг рекурсии.
     * @return пару из пары лучшей оценки из возможных и лучших координат для хода.
     */
    private Pair<Pair<Integer, Integer>, Integer>
    alphaBeta(Field field, Bot bot, User user, Double alpha, Double beta, int recursionStep) {

        // =============================================================================================================

        // если выиграл бот или игрок - вернуть приз с учетом шага рекурсии
        if (FieldAnalysis.isWinner(bot, field)) {
            return new Pair<>(new Pair<>(-1, -1), Constants.WIN_PRIZE - recursionStep * Constants.TAX_PER_RECURSION_STEP);
        } else if (FieldAnalysis.isWinner(user, field)) {
            return new Pair<>(new Pair<>(-1, -1), -Constants.WIN_PRIZE - recursionStep * Constants.TAX_PER_RECURSION_STEP);
        }

        // если поле закончилось или дошли до максимального шага рекурсии - вернуть оценку с учетом шага рекурсии
        if (FieldAnalysis.isFilled(field) || recursionStep == Constants.MAX_RECURSION_STEP) {
            return new Pair<>(
                    new Pair<>(-1, -1),
                    Evaluation.eval(field, bot, user) - recursionStep * Constants.TAX_PER_RECURSION_STEP
            );
        }

        // =============================================================================================================

        Pair<Integer, Integer>[] freeCoordinates = FieldAnalysis.getCoordinatesOfFreeCells(field);
        Pair<Pair<Integer, Integer>, Integer> result = null;

        if (recursionStep % 2 == 0) {

            double g = Double.NEGATIVE_INFINITY;

            for (int i = 0; i < freeCoordinates.length && g < beta; ++i) {

                Field tempField = (Field) field.clone();
                tempField.setCellStateAt(freeCoordinates[i].getFirstValue(), freeCoordinates[i].getSecondValue(), Cell.CellState.BOT_MARKED);

                Pair<Pair<Integer, Integer>, Integer> temp = alphaBeta(tempField, bot, user, alpha, beta,
                                                            recursionStep + 1);

                if (temp.getSecondValue().doubleValue() > g) {
                    g = temp.getSecondValue().doubleValue();

                    result = temp;
                    result.getFirstValue().setFirstValue(freeCoordinates[i].getFirstValue());
                    result.getFirstValue().setSecondValue(freeCoordinates[i].getSecondValue());
                }

                alpha = max(g, alpha);
            }
        }

        if (recursionStep % 2 == 1) {

            double g = Double.POSITIVE_INFINITY;

            for (int i = 0; i < freeCoordinates.length && g > alpha; ++i) {

                Field tempField = (Field) field.clone();
                tempField.setCellStateAt(freeCoordinates[i].getFirstValue(), freeCoordinates[i].getSecondValue(), Cell.CellState.USER_MARKED);

                Pair<Pair<Integer, Integer>, Integer> temp = alphaBeta(tempField, bot, user, alpha, beta, recursionStep + 1);

                if (temp.getSecondValue().doubleValue() < g) {
                    g = temp.getSecondValue().doubleValue();

                    result = temp;
                    result.getFirstValue().setFirstValue(freeCoordinates[i].getFirstValue());
                    result.getFirstValue().setSecondValue(freeCoordinates[i].getSecondValue());
                }

                beta = min(g, beta);
            }
        }
        return result;
    }

    private double max(Double a, Double b) {
        return a > b ? a : b;
    }

    private double min(Double a, Double b) {
        return a < b ? a : b;
    }


}
