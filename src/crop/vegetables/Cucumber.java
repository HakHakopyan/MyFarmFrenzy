package crop.vegetables;

import constants.*;
import crop.*;

public class Cucumber extends Crop{

    /**
     * Конструктор - инициализирует объект Огурец с помощь констант содержащихся в CropConst
     */
    public Cucumber() {
        super(CropConst.CUCAMBER_RT, CropConst.CUCAMBER_SL, CropConst.CUCAMBER_COUNT, CropConst.CUCAMBER_COST);
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
