package player;

import field.Field;

/**
 * Класс для созднания сущностей игроков.
 * Содержит фабричный метод, создающий конкертного игрока.
 * @see PlayerRole
 * @see Player
 * @see Bot
 * @see User
 * @see Field
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class PlayerFactory {

    /**
     * Создает игрока.
     * @param role константа, по которой определяется, какого игрока нужно создать.
     * @param field поле, к которому нужно привязать игрока.
     * @return экземпляр класса конкретного игрока.
     */
    public static Player createPlayer(PlayerRole role, Field field) {

        if (role == PlayerRole.BOT) {
            return new Bot(field);
        }

        if (role == PlayerRole.USER) {
            return new User(field);
        }

        throw new RuntimeException("No such role");
    }

}
