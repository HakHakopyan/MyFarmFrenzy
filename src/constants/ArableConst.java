package constants;

/**
 * Whole constants for field element - Arable
 */
public interface ArableConst {
    /**
     * Стоимость кусочка земли
     */
    double PARSEL_PRICE = 25;
    /**
     * Стоимость Теплицы
     */
    double GREENHOUSE_PRICE = 40;
    /**
     * Количество элементов поля, выводимых на консоль в одной строке
     */
    int ARABLE_COUNT_IN_LINE_WHEN_REPRESENT = 2;
    /**
     * Количество символов которое должна занимать информацию об растении посаженном на участке земли
     * если информация меньше, дополняется пробелами
     */
    int AMOUNT_OF_SPACE_FOR_DISPLAY = 30;
}
