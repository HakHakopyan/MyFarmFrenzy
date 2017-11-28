package Crop;
import Cost.Cost;

/**
 * crop [cra:p] - урожай
 */
public abstract class Crop extends Cost {
    int SHELF_LIFE;

    protected Crop(int sLife, int cost) {
        super(cost);
        this.SHELF_LIFE = sLife;
    }

    public void addTime() {
        if (this.time < this.SHELF_LIFE) {
            this.time += 1;
        }
    }

    public Boolean spoiled() {
        if (this.time <= this.SHELF_LIFE) {
            return true;
        }
        return false;
    }
}
