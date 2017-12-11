package constants;

/**
 * Constants for Crops initialization
 */
public interface CropConst {

    /**
     * RT - Rippen time - срок созревания урожая
     */
    int APPLE_RT = 3;
    /**
     * SL - shelf life - срок хранения урожая, введен для возможности сбора урожая самостоятельно с поля
     *                   если не уложился в срок хранения, то урожай пропадает
     */
    int APPLE_SL = 2;
    /**
     * количество урожая даваемого растением за один цикл созревания урожая
     */
    int APPLE_COUNT = 10;
    /**
     * стоимость единицы урожая
     */
    double APPLE_COST = 0.5;

    int TOMATO_RT = 1;
    int TOMATO_SL = 2;
    int TOMATO_COUNT = 2;
    double TOMATO_COST = 2;

    int CUCAMBER_RT = 1;
    int CUCAMBER_SL = 1;
    int CUCAMBER_COUNT = 3;
    double CUCAMBER_COST = 1;
}
