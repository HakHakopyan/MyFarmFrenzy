package Base.Plant.Herb;

import Crop.Crop;
import Base.Plant.Herb.Herb;
import Crop.Cereal.Millet;
import Season.Season;
import main.ProductionTimes;

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
