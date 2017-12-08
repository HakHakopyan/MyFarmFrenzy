package Crop.Fruit;

import Const.CropConst;
import Crop.*;

public class Apple extends Crop {
    public Apple() {
        super(CropConst.APPLE_RT,CropConst.APPLE_SL, CropConst.APPLE_COUNT, CropConst.APPLE_COST);
    }

    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + this.myState.getRepresentation();
    }
}
