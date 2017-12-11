package observer;

/**
 * Интерфейс обновления для объектов, наблюдающих за ходом времени (Растения, Урожай...)
 */
public interface ObserverTime {
    /**
     * Method for update time/(internal state) of the object
     */
    public void updateTime();
}
