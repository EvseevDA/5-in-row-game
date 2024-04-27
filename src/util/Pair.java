package util;

import java.util.Objects;

/**
 * Класс представляет собой структуру данных из пары значений.
 * @param <T> тип певрого значения.
 * @param <Y> тип второго значения.
 */
public class Pair<T, Y> {

    private T firstValue;
    private Y secondValue;

    /**
     * @param firstValue первое значение в паре.
     * @param secondValue второе значение в паре.
     */
    public Pair(T firstValue, Y secondValue) {
        this.firstValue = Objects.requireNonNull(firstValue);
        this.secondValue = Objects.requireNonNull(secondValue);
    }


    /**
     * @return первое значение из пары.
     */
    public T getFirstValue() {
        return this.firstValue;
    }


    /**
     * @return второе значение из пары.
     */
    public Y getSecondValue() {
        return this.secondValue;
    }


    /**
     * Устанавливает первое значение в паре.
     * @param firstValue значение, присваиваемое перовму значению в паре.
     */
    public void setFirstValue(T firstValue) {
        this.firstValue = Objects.requireNonNull(firstValue);
    }


    /**
     * Устанавливает второе значение в паре.
     * @param secondValue значение, присваиваемое второму значению в паре.
     */
    public void setSecondValue(Y secondValue) {
        this.secondValue = Objects.requireNonNull(secondValue);
    }


    /**
     * @return копию текущей пары.
     */
    @Override
    public Object clone() {
        return new Pair<>(this.getFirstValue(), this.getSecondValue());
    }


    /**
     * @return строкове представление текущей пары.
     */
    @Override
    public String toString() {
        return "[" + firstValue + ", " + secondValue + "]";
    }
}
