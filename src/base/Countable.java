package base;

/**
 * Имеющий количество
 */
public interface Countable {
    /**
     * возвращает количество у объекта, у которого метод был вызван
     * @return the quantity stored in the object or object count
     */
    public int getCount();

    /**
     * устанавливает количество у объекта у которого был вызван
     * @param count содержит устанавливаемое количество
     */
    public void setCount(int count);
}
