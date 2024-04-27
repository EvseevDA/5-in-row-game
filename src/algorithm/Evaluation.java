package algorithm;

import constants.Constants;
import field.Field;
import fieldutil.Fields;
import player.Bot;
import player.Player;
import player.User;

/**
 * Класс, содержащий методы для оценки выгодности ситуации на поле для игроков.
 * @see Field
 * @see Player
 * @see Bot
 * @see User
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class Evaluation {

    /**
     * Дает оценку на поле относительно бота.
     * @param field поле, на котором производится оценка.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @return целочисленную оценку положения бота.
     */
    public static int eval(Field field, Bot bot, User user) {

        // инициализируем результат
        int result = 0;

        // собираем все диагонали, строки и столбцы поля в виде двумерного массива типа char
        char[][] rowsToCheck = Fields.getAllDiagonalsRowsColumns(field);

        // учитываем оценку бота
        result += getEvalFor(bot, rowsToCheck);
        // пользователя
        result -= getEvalFor(user, rowsToCheck);

        // возвращаем результат
        return result;
    }


    /**
     * Подсчитывает число очков, заработанное игроком на поле без учета очков второго игрока.
     * @param player игрок, для которого производится оценка.
     * @param rowsToCheck диагонали, строки, столбцы поля, на котором производится оценка.
     * @return целочисленное число очков, заработанных игроком.
     */
    private static int getEvalFor(Player player, char[][] rowsToCheck) {

        // инициализируем результат
        int result = 0;
        // сохраням марку, соответствующую игроку
        char markToCheck = player.getMark();

        // переменная, в которую записывается сколько марок стоит в ряд
        int nowInARow = 0;

        // переменная на случай, если собрано 4 в ряд
        // показывает, свободна ли четверка
        boolean fourInARowIsFree = false;

        for (int i = 0; i < rowsToCheck.length; ++i) {
            // рассматривать строку длиной меньше 5 бессмысленно
            if (rowsToCheck[i].length > 4) {
                for (int j = 0; j < rowsToCheck[i].length; ++j) {
                    // если наткнулись на метку игрока - считаем сколько его меток стоит в ряд
                    if (rowsToCheck[i][j] == markToCheck) {
                        nowInARow = 1;
                        int k = j + 1;
                        // цикл с подсчетом числа марок игрока в ряд
                        while (k < rowsToCheck[i].length && rowsToCheck[i][k] == markToCheck) {
                            nowInARow++;
                            k++;
                        }

                        // если в ряд 2 или 3
                        if (nowInARow == 2 || nowInARow == 3) {
                            try {
                                // пытаемся проверить, свободны ли ячейки слева и справа от комбинации
                                if (rowsToCheck[i][j - 1] == Constants.DEFAULT_EMPTY_MARK
                                     && rowsToCheck[i][k] == Constants.DEFAULT_EMPTY_MARK) {

                                    // если свободны - прибавляем приз за 2 или 3 в ряд в результат
                                    if (nowInARow == 2) {
                                        result += Constants.TWO_IN_A_ROW_PRIZE;
                                    } else {
                                        result += Constants.THREE_IN_A_ROW_PRIZE;
                                    }
                                }
                            // игнорируем случай, если при проверке вышли за границы поля
                            } catch (IndexOutOfBoundsException ignored) { }
                        }

                        // если в ряд 4
                        if (nowInARow == 4) {
                            try {
                                // пытаемся проверить, свободна ли ячейка слева от четверки
                                if (rowsToCheck[i][j - 1] == Constants.DEFAULT_EMPTY_MARK) {
                                    // если свободна - обозначаем, что четверка свободна
                                    fourInARowIsFree = true;
                                }
                            // игнорируем случай, если при проверке вышли за границы поля
                            } catch (IndexOutOfBoundsException ignored) { }

                            try {
                                // пытаемся проверить, свободна ли ячейка справа от четверки
                                if (rowsToCheck[i][k] == Constants.DEFAULT_EMPTY_MARK) {
                                    // если свободна - обозначаем, что четверка свободна
                                    fourInARowIsFree = true;
                                }
                            // игнорируем случай, если при проверке вышли за границы поля
                            } catch (IndexOutOfBoundsException ignored) { }

                            // если четверка свободна - прибавляем приз за 4 в ряд в результат
                            if (fourInARowIsFree) {
                                result += Constants.FOUR_IN_A_ROW_PRIZE;
                            }

                            // переменную, которая показывает состояние четверки (свободна, несвободна)
                            // сбрасываем в дефолтное состояние
                            fourInARowIsFree = false;
                        }

                        // если 5 в ряд
                        if (nowInARow == 5) {
                            // прибавляем к результату приз за выигрыш
                            result += Constants.WIN_PRIZE;
                        }

                        // дальнейшее рассмотрение поля продолжаем с ячеки, следующей после рассмотренной комбинации
                        j = k;
                    }
                }
            }
        }

        // возвращаем результат
        return result;
    }

}
