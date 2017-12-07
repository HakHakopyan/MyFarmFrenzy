package Crop.State;

import Crop.*;

public abstract class AbstrCropState implements CropState{
    Crop myCrop;

    public AbstrCropState(Crop myCrop) {
        this.myCrop = myCrop;
    }
}
