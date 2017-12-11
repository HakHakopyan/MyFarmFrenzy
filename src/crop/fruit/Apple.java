package crop.fruit;

import constants.*;
import crop.*;

/**
 * Класс для урожая - Яблоко
 */
public class Apple extends Crop {
    /**
     * Конструктор - инициализирует объект Яблоко с помощь констант содержащихся в CropConst
     */
    public Apple() {
        super(CropConst.APPLE_RT,CropConst.APPLE_SL, CropConst.APPLE_COUNT, CropConst.APPLE_COST);
    }

    /**
     * Возвращает представление для Яблока - имя и информация о состоянии
     * @return строка, содержащая имя и информацию о состоянии
     */
    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + this.myState.getRepresentation();
    }
}
