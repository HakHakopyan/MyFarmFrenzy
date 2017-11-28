import Crop.Crop;

/**
 * Facade - Фасад Делегирует два объекта
 *  arable |ˈærəbl|  — пашня, пахота
 * Element on which the plants grow
 */
public class Arable <T extends Plant> implements Genarable, Seasonable {

    private  T plant = null;
    private Crop crop = null;


    public void setPlant(T t) {
        if (this.crop != null)
            throw new IllegalArgumentException("Harvest before planting a new plant!");
        this.plant = t;
    }

    public void addTime() {
        if (plant != null) {
            plant.addTime();
            if (plant.deliveryIsPossible()) {
                this.crop = plant.getDelivery();
                this.plant = null;
            }
        }
        if (this.crop != null) {
            plant.addTime();
            if (this.crop.spoiled()) {
                this.crop = null;
            }
        }

    }

    @Override
    public Crop getDelivery() {
        if (crop != null) {
            Crop buf = this.crop;
            this.crop = null;
            return buf;
        }

        return null;
    }

    @Override
    public Boolean deliveryIsPossible() {
        return null;
    }

    private void checkPlantForCrop() {

        if (plant != null && plant.deliveryIsPossible()) {
            this.crop = plant.getDelivery();
            this.plant = null;
        }
    }

    public void destroyPlant() {
        checkPlantForCrop();
        if (this.plant != null)
            this.plant = null;
    }

    @Override
    public  void setSeason(Season season) {
        if (this.plant.getSeason().ordinal() <= season.ordinal()) {
            destroyPlant();
        }
    }
}
