import Crop.Crop;

/**
 * well [wel] олодец
 * gives water to plants
 * if the water ends the the well itself slowly fills, or we fill water for money
 */
public class Well extends Generator {
    /**
     * how many liters of water can be stored
     */
    int capacity;
    /**
     * residue [rezidu:] остаток
     */
    int waterResidue;

    Well(int capacity) {
        super(ProductionTimes.WELL);

        this.capacity = capacity;
    }

    @Override
    public void addTime() {
        if (this.timer < this.READY_TYME) {
            this.timer += 1;
        }

        this.timer = 0;
        this.waterResidue+=1;
    }

    @Override
    public Crop getDelivery() {
        this.waterResidue -=1;

        return null;
    }

    @Override
    public Boolean deliveryIsPossible() {
        if (waterResidue > 0) {
            return true;
        }

        return false;
    }

    /*
    public Boolean  full() {
        if (waterResidue < capacity) {
            return false;
        }

        return true;
    }
    */
}
