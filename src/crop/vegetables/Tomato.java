package crop.vegetables;

import constants.*;
import crop.*;

public class Tomato extends Crop {
    /**
     * Конструктор - инициализирует объект Томат с помощь констант содержащихся в CropConst
     */
    public Tomato() {
        super(CropConst.TOMATO_RT, CropConst.TOMATO_SL, CropConst.TOMATO_COUNT, CropConst.TOMATO_COST);
    }

    /**
     * Возвращает представление для Огурца - имя и информация ос состоянии
     * @return строка, содержащая имя и информацию о состоянии
     */
    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + this.myState.getRepresentation();
    }
}
