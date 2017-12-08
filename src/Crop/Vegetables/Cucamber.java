package Crop.Vegetables;

import Const.CropConst;
import Crop.*;

public class Cucamber extends Crop{

    public Cucamber() {
        super(CropConst.CUCAMBER_RT, CropConst.CUCAMBER_SL, CropConst.CUCAMBER_COUNT, CropConst.CUCAMBER_COST);
    }

    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + this.myState.getRepresentation();
    }
}
