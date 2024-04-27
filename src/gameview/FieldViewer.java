package gameview;

import constants.Constants;
import field.Field;

/**
 * Класс, реализующий отображение поля в консоли.
 * @see Field
 * @since 19.0.1
 * @author Dmitry Evseev
 */
public final class FieldViewer {

    /**
     * Выводит поле.
     * @param field поле, которое нужно отобразить.
     */
    public static void showField(Field field) {
        System.out.println(Constants.CURRENT_FIELD_MESSAGE);
        System.out.println(field.toStringWithNumberedRowsAndColumns());
        System.out.println("\n\n");
    }

}
