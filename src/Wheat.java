import Crop.Crop;

/**
 * wheat [wi:t] пшеница
 */
public class Wheat extends Herb {
    Wheat() {
        super(ProductionTimes.WHEAT, Season.SUMMER);
    }

    @Override
    public Crop getDelivery() {
        if (deliveryIsPossible()) {
            return new Millet();
        }
        return null;
    }
}
