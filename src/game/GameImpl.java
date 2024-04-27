package game;

import algorithm.Algorithm;
import algorithm.AlphaBeta;
import algorithm.Minimax;
import constants.Constants;
import field.Field;
import fieldutil.FieldAnalysis;
import gameview.FieldViewer;
import gameview.MessageViewerForUser;
import player.*;
import userinputreader.UserInputReader;
import util.Pair;

import java.util.Objects;

/**
 * Класс представляет собой реализацию хода игры.
 * @see Minimax
 * @see AlphaBeta
 * @see Field
 * @see Bot
 * @see User
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public class GameImpl implements Game {

    private Field field;
    private Player bot;
    private Player user;
    private Algorithm algorithmForBotMoves;
    private UserInputReader userInputReader;

    /**
     * Инициализирует алгоритм для вычисления ходов бота алгоритмом по умолчанию.
     */
    public GameImpl() {
        algorithmForBotMoves = new Minimax();
    }


    /**
     * Инициализирует объекты, необходимые для игры.
     */
    @Override
    public void init() {
        field = new Field();
        bot = PlayerFactory.createPlayer(PlayerRole.BOT, field);
        user = PlayerFactory.createPlayer(PlayerRole.USER, field);
        userInputReader = new UserInputReader();
    }


    /**
     * Реализует ход игры.<p>
     * Является точкой входа в игру.
     */
    @Override
    public void start() {

        boolean userMoveResult = true;

        gameStart: while (true) {

            if (isEnd()) {

                MessageViewerForUser.continueOrStopMessage();

                choosing: while (true) {

                    MessageViewerForUser.requestToEnterChoose();

                    try {
                        readAnswer();
                    } catch (RuntimeException e) {
                        MessageViewerForUser.showMessage(e.getMessage());
                        continue choosing;
                    }

                    break gameStart;
                }
            }

            if (userMoveResult) {
                FieldViewer.showField(field);
            }

            MessageViewerForUser.requestToEnterCoordinates();

            Pair<Integer, Integer> userMove = new Pair<>(-1, -1);
            try {
                userMove = userInputReader.readCoordinates();
            } catch (RuntimeException ignore) {

            }

            try {
                if (!user.makeAMoveTo(userMove.getFirstValue() - 1, userMove.getSecondValue() - 1)) {
                    userMoveResult = false;
                    continue gameStart;
                } else {
                    userMoveResult = true;
                }
            } catch (RuntimeException ex) {
                MessageViewerForUser.showMessage("Coordinates out of range [1, " + Constants.FIELD_SIZE + "].");
                userMoveResult = false;
                continue gameStart;
            }

            if (isEnd()) {

                MessageViewerForUser.continueOrStopMessage();

                choosing: while (true) {

                    MessageViewerForUser.requestToEnterChoose();

                    try {
                        readAnswer();
                    } catch (RuntimeException e) {
                        MessageViewerForUser.showMessage(e.getMessage());
                        continue choosing;
                    }

                    break gameStart;
                }
            }

            Pair<Integer, Integer> botMove = algorithmForBotMoves.getBestCoordinatesToMove(field, (Bot) bot, (User) user);
            bot.makeAMoveTo(botMove.getFirstValue(), botMove.getSecondValue());
        }
    }


    /**
     * Устанавливает алгоритм для вычисления ходов бота.
     * @param algorithm новый алгоритм для ходов бота.
     */
    public void setAlgorithm(Algorithm algorithm) {
        algorithmForBotMoves = Objects.requireNonNull(algorithm);
    }


    /**
     * Перезапускает игру.
     */
    public void reset() {
        init();
        start();
    }


    /**
     * Читает ответ пользователя на предложение начать игру снова или выйти из игры.
     */
    public void readAnswer() {
        int choose;

        choose = userInputReader.readChoose();
        userInputReader.close();

        if (choose == 1) {
            reset();
            return;
        } else if (choose == 2) {
            return;
        }

        throw new RuntimeException("Choose must be number in range [1, 2].");
    }


    /**
     * Проверка на то, что игра закончена.
     * @return {@code true}, если игра закончена, {@code false} - если не закончена.
     */
    private boolean isEnd() {
        if (FieldAnalysis.isWinner(bot, field)) {
            FieldViewer.showField(field);
            MessageViewerForUser.botWinMessage();
            return true;
        } else if (FieldAnalysis.isWinner(user, field)) {
            FieldViewer.showField(field);
            MessageViewerForUser.youWinMessage();
            return true;
        } else if (FieldAnalysis.isFilled(field)) {
            FieldViewer.showField(field);
            MessageViewerForUser.tieMessage();
            return true;
        }

        return false;
    }
}
