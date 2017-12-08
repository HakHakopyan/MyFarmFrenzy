package Plant.Tree;

import Crop.Fruit.*;
import Plant.*;
import Season.Season;


public class AppleTree extends Plant {

    public AppleTree(int lifeTimem, double cost) {
        super(lifeTimem, cost, Season.WINTER);
    }

    @Override
    public void newCrop() {
        this.myCrop = new Apple();
    }

    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + myState.getRepresentation();
    }

    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new AppleTree(this.myLifeTime, this.myCost);
    }
}
