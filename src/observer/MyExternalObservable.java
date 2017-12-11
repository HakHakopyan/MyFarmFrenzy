package observer;

import java.util.Observable;

/**
 * Определяем наследника класса {@link Observable Observable}
 * Нужен для наблюдения внешними объектами внутренних процессов класс тип поля которого явл данный класс
 */
public class MyExternalObservable extends Observable {
    public MyExternalObservable() {
        super();
        this.setChanged();
    }

    /**
     * делаем public для возможности оповещения
     */
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

}
