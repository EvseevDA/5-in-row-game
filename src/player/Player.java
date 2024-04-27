package player;

/**
 * Интерфейс, представляющий действия для сущностей игроков.
 * @see Bot
 * @see User
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public interface Player {

    /**
     * @return метку, соответствующую игроку.
     */
    char getMark();

    /**
     * Ход игрока в некоторую позицию.
     * @param i первая координата.
     * @param j вторая координата.
     * @return результат хода.
     */
    boolean makeAMoveTo(int i, int j);
}
