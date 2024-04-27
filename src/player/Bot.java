package player;

import constants.Constants;
import field.Cell;
import field.Field;

/**
 * Класс, представляющий бота.
 * Класс является основанным на значении.
 * @see Player
 * @see User
 * @see Field
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public final class Bot extends AbstractPlayer implements Cloneable {

    /**
     * Инициализирует поле для бота, инициализирует метку для бота меткой по умолчанию.
     * @param field поле, на котором играет бот.
     */
    public Bot(Field field) {
        super(Constants.DEFAULT_BOT_MARK, field);
    }

    /**
     * @return состояние ячейки, соответствующее боту.
     */
    @Override
    protected Cell.CellState getCellStateMatchedWithPlayer() {
        return Cell.CellState.BOT_MARKED;
    }

    /**
     * @return копию текущего бота.
     */
    @Override
    public Object clone() {
        return new Bot((Field) this.field.clone());
    }
}
