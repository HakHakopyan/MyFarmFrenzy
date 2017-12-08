package Crop.Vegetables;

import Const.CropConst;
import Crop.*;

public class Tomato extends Crop {
    public Tomato() {
        super(CropConst.TOMATO_RT, CropConst.TOMATO_SL, CropConst.TOMATO_COUNT, CropConst.TOMATO_COST);
    }

    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + this.myState.getRepresentation();
    }
}
