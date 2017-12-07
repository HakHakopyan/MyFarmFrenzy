package Plant.Tree;

import Crop.Fruit.*;
import Plant.*;
import Season.Season;


public class AppleTree extends Plant {

    public AppleTree(int lifeTimem, int cost) {
        super(lifeTimem, cost, Season.WINTER);
    }

    @Override
    public void newCrop() {
        this.myCrop = new Apple();
    }

    @Override
    public String getRepresentation() {
        return this.myState.getRepresentation(this.getClass().getSimpleName());
    }

    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new AppleTree(this.lifeTime, this.cost);
    }
}
