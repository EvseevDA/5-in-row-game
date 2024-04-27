package constants;

/**
 * Константы проекта.<p>
 * Например, метка ячейки по умолчанию и размер поля соотстветственно:
 * <blockquote><pre>
 *     {@code public static final char defaultEmptyMark = '-';}
 *     {@code public static final int fieldSize = 10;}
 * </pre></blockquote>
 */
public class Constants {

    /**
     * метка по умолчнию для пользователя
     */
    public static final char DEFAULT_USER_MARK = 'X';


    /**
     * метка по умолчнию для бота
     */
    public static final char DEFAULT_BOT_MARK = 'O';


    /**
     * метка по умолчнию для пустой ячейки
     */
    public static final char DEFAULT_EMPTY_MARK = '-';


    /**
     * размер поля
     */
    public static final int FIELD_SIZE = 10;


    /**
     * отступ между элементами поля при представлении поля как строки
     */
    public static final char INDENT = '\t';


    /**
     * число диагоналей в поле
     */
    public static final int COUNT_OF_DIAGONALS_IN_FIELD = (FIELD_SIZE * 2 - 1) * 2;


    /**
     * число строк и столбцов в поле
     */
    public static final int COUNT_OF_ROWS_AND_COLUMNS_IN_FIELD = FIELD_SIZE * 2;


    /**
     * длина комбинации для победы в игре
     */
    public static final int LENGTH_OF_ROW_TO_WIN = 5;


    /**
     * запрос на ввод координат
     */
    public static final String REQUEST_FOR_ENTER_COORDINATES = "Enter the coordinates (row number first, then column number): ";


    /**
     * уведомление о текущей ситуации на поле
     */
    public static final String CURRENT_FIELD_MESSAGE = "\n\nCURRENT SITUATION:\n\n";


    /**
     * сообщение о победе пользователя
     */
    public static final String YOU_WON_MESSAGE = "YOU WIN!\n\n";


    /**
     * сообщение о победе бота
     */
    public static final String BOT_WON_MESSAGE = "BOT WIN((((((\nIf u see this message I passed project :)\n\n";


    /**
     * сообщение о ничьей
     */
    public static final String TIE_MESSAGE = "TIE\nIf u see this message it's not bad for me ;)\n\n";


    /**
     * сообщение о вводе нечисловых координат
     */
    public static final String NOT_NUMERIC_COORDINATES_MESSAGE = "Coordinates must be numbers.\n\nTry again\n\n";


    /**
     * сообщение об ошибке ввода
     */
    public static final String INPUT_ERROR_MESSAGE = "Input error! Try again.\n\n";


    /**
     * сообщение с предложением новой игры или выхода из игры
     */
    public static final String CONTINUE_OR_STOP = "\n\n1. Play again\n2. Exit\n\n";


    /**
     * запрос на ввод выбора между новой игрой и выходом из игры
     */
    public static final String CHOOSE_MESSAGE = "Enter your choose: ";


    /**
     * приз за выигрыш
     */
    public static final int WIN_PRIZE = 10000;


    /**
     * приз за комбинацию из 4х в ряд
     */
    public static final int FOUR_IN_A_ROW_PRIZE = 250;


    /**
     * приз за комбинацию из 3х в ряд
     */
    public static final int THREE_IN_A_ROW_PRIZE = 150;


    /**
     * приз за комбинацию из 2х в ряд
     */
    public static final int TWO_IN_A_ROW_PRIZE = 100;


    /**
     * максимальный шаг рекурсии при рассматривании ходов в алгоритме
     */
    public static final int MAX_RECURSION_STEP = 4;


    /**
     * штраф за один шаг рекурсии
     */
    public static final int TAX_PER_RECURSION_STEP = 300;


    /**
     * предложение выхода из игры досрочно
     */
    public static final String WORD_TO_EXIT = "Exit";

}
