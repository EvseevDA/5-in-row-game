package algorithm;

import field.Field;
import player.Bot;
import player.User;
import util.Pair;


/**
 * Интерфейс, определяющих общую функциональность для всех алгоритмов, реализующих вычисление хода.
 * @see Field
 * @see Bot
 * @see User
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public interface Algorithm {

    /**
     * Вычисляет координаты для хода.
     * @param field поле, на котором играют бот и пользователь.
     * @param bot бот, который играет на поле.
     * @param user пользователь, который играет на поле.
     * @return координаты для хода.
     */
    Pair<Integer, Integer> getBestCoordinatesToMove(Field field, Bot bot, User user);

}
