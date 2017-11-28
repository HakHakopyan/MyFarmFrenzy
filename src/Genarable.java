import Crop.Crop;

/**
 * getDelivery() -  Factory Method;
 */
public interface Genarable extends Timable {
    //public void addTime();
    Crop getDelivery();
    Boolean deliveryIsPossible();
}
