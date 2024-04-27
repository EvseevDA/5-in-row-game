package field;

import constants.Constants;
import player.Player;

import java.util.Objects;

/**
 * Класс {@code Cell} представляет собой ячейку, из множества которых состоит игровое поле.<p>
 * Этот класс содержит методы для получения информации о ячейке:
 * <blockquote><pre>
 *     {@code public boolean isMarked();}
 *     {@code public char getMark();}
 * </pre></blockquote>
 * Для взаимодействия игрока и ячейки:
 * <blockquote><pre>
 *     {@code public boolean setMark(char mark);}
 * </pre></blockquote>
 *
 * @author Dmitriy Evseev
 * @see Field
 * @see Player
 * @see player.User
 * @see player.Bot
 * @since 19.0.1
 */
public final class Cell implements Cloneable {

    /**
     * Символ, представляющий ячейку.
     */
    private final char mark;


    /**
     * Присваивает ячейке символ по умолчанию.
     */
    private Cell() {
        mark = Constants.DEFAULT_EMPTY_MARK;
    }

    private Cell(char mark) {
        if (mark != Constants.DEFAULT_EMPTY_MARK
                && mark != Constants.DEFAULT_BOT_MARK
                && mark != Constants.DEFAULT_USER_MARK) {
            throw new IllegalArgumentException("Illegal mark value");
        }
        this.mark = mark;
    }

    private static final Cell EMPTY_CELL = new Cell();
    private static final Cell BOT_CELL = new Cell(Constants.DEFAULT_BOT_MARK);
    private static final Cell USER_CELL = new Cell(Constants.DEFAULT_USER_MARK);

    /**
     * @return ячейку, соответствующую переданному состоянию.
     */
    public static Cell getCellByState(CellState state) {
        if (state == CellState.EMPTY) {
            return EMPTY_CELL;
        }

        if (state == CellState.BOT_MARKED) {
            return BOT_CELL;
        }

        return USER_CELL;
    }


    /**
     * @return {@code true}, если ячейка содержит в себе символ не по умолчанию (т.е. была закрашена)<p>
     * в противном случае - {@code false}.
     */
    public boolean isMarked() {
        return mark != Constants.DEFAULT_EMPTY_MARK;
    }

    /**
     * Проверяет, закрашена ли ячейка меткой из параметра.
     *
     * @param mark метка, с которой сверяется значение метки в ячейке.
     * @return {@code true}, если ячейка закрашена меткой {@code mark}, {@code false} - в противном случае.
     */
    public boolean isMarkedBy(char mark) {
        return this.mark == mark;
    }

    /**
     * @return символ, хранящийся в ячейке.
     */
    public char getMark() {
        return mark;
    }


    /**
     * @return прототип ячейки со скопированным значением {@code mark}.
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cell cell = (Cell) object;
        return mark == cell.mark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark);
    }

    /**
     * @return строковое представление ячейки.
     */
    @Override
    public String toString() {
        return String.valueOf(mark);
    }

    /**
     * Перечисление, содержащее в себе возможные состояния ячейки.<br>
     * Ячейка может находиться в одном из 3х состояний: пустая ({@code EMPTY}),
     *                                                  помечена ботом ({@code BOT_MARKED}),
     *                                                  помечена пользователем ({@code USER_MARKED}).
     */
    public enum CellState {

        EMPTY, BOT_MARKED, USER_MARKED

    }
}
