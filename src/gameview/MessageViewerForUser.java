package gameview;

import constants.Constants;

/**
 * Класс, реализующий отображение в консоли сообщений для пользователя.
 * @see Constants
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public final class MessageViewerForUser {

    /**
     * Выводит запрос на ввод координат.
     */
    public static void requestToEnterCoordinates() {
        System.out.print(Constants.REQUEST_FOR_ENTER_COORDINATES);
    }


    /**
     * Выодит сообщение о победе пользователя.
     */
    public static void youWinMessage() {
        System.out.println(Constants.YOU_WON_MESSAGE);
    }


    /**
     * Выводит сообщение о победе бота.
     */
    public static void botWinMessage() {
        System.out.println(Constants.BOT_WON_MESSAGE);
    }


    /**
     * Выводит сообщение о ничьей.
     */
    public static void tieMessage() {
        System.out.println(Constants.TIE_MESSAGE);
    }


    /**
     * Выводит сообщение о том, что введены нечисловые координаты.
     */
    public static void notNumericCoordinatesMessage() {
        System.out.println(Constants.NOT_NUMERIC_COORDINATES_MESSAGE);
    }


    /**
     * Выводит сообщение из параметра.
     * @param message сообщение, которое нужно вывести.
     */
    public static void showMessage(String message) {
        System.out.println(message + "\n\n");
    }


    /**
     * Выводит сообщение с предложением новой игры или выхода из игры.
     */
    public static void continueOrStopMessage() {
        System.out.println(Constants.CONTINUE_OR_STOP);
    }


    /**
     * Выводит сообщение с предложением сделать выбор.
     */
    public static void requestToEnterChoose() {
        System.out.print(Constants.CHOOSE_MESSAGE);
    }

}
