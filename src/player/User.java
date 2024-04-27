package player;

import constants.Constants;
import field.Cell;
import field.Field;


/**
 * Класс, представляющий пользователя. Класс является основанным на значении.
 * @see Player
 * @see Bot
 * @see Field
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public final class User extends AbstractPlayer implements Cloneable {
    /**
     * Инициализирует поле для пользователя, инициализирует метку для пользователя меткой по умолчанию.
     * @param field поле, на котором играет пользователь.
     */
    public User(Field field) {
        super(Constants.DEFAULT_USER_MARK, field);
    }

    /**
     * @return состояние ячейки, соответствующее пользователю.
     */
    @Override
    protected Cell.CellState getCellStateMatchedWithPlayer() {
        return Cell.CellState.USER_MARKED;
    }

    /**
     * @return копию текущего пользователя.
     */
    @Override
    public Object clone() {
        return new User((Field) this.field.clone());
    }
}
