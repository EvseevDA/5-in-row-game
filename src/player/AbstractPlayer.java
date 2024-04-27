/*
 * All rights reserved.
 */

package player;

import constants.Constants;
import field.Cell;
import field.Field;

import java.util.Objects;

/**
 *  Класс-скелет для классов, которые реализуют сущности игроков.<br>
 *  Классы игроков не должны содержать внутри себя игровой логики. Они просто представляют собой сущности игроков,
 *  характеризуемых полем, на котором они играют и меткой, которой они ходят.<br>
 *  Классы игроков должны быть основаны на значении.
 *  @see Player
 *  @see Bot
 *  @see User
 *  @since 19.0.1
 *  @author Dmitry Evseev
 */
public abstract sealed class AbstractPlayer implements Player permits Bot, User {

    protected final char mark;
    protected final Field field;

    protected AbstractPlayer(char mark, Field field) {
        this.mark = mark;
        this.field = Objects.requireNonNull(field);
    }

    /**
     * @return метку, соответствующую игроку.
     */
    @Override
    public final char getMark() {
        return mark;
    }

    /**
     * Пытается поставить метку игрока на поле по переданным координатам.
     * @param i первая координата.
     * @param j вторая координата.
     * @return {@code true}, если метку удалось поставить (ячейка по переданным координатам была свободна);
     *         {@code false}, если метку поставить не удалось (ячейка по переданным координатам была занята).
     * @throws RuntimeException если хотя бы одна из координат выходит за пределы поля.
     */
    @Override
    public final boolean makeAMoveTo(int i, int j) {
        try {
            return field.setCellStateAt(i, j, getCellStateMatchedWithPlayer());
        } catch (RuntimeException ex) {
            throw new RuntimeException("Wrong coordinates. Coordinates must be in range [0, " + (Constants.FIELD_SIZE - 1) + "].");
        }
    }

    /**
     * В классах, реализующих сущность игрока должен возвращать состояние ячейки, соответствующее игроку.
     * @return состояние ячейки в соответствии с объектом игрока, на котором вызывался метод.
     */
    protected abstract Cell.CellState getCellStateMatchedWithPlayer();
}
