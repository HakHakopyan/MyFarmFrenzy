package crop.state;

import crop.*;

/**
 * Abstact class for crop states? that defines the parameter contains Crop and constructor for initialize this parameter
 */
public abstract class AbstrCropState implements CropState{
    /**
     * contains a crop for which the states are determined
     */
    Crop myCrop;

    /**
     * Construcror fo initialize parameter containing crop
     * @param myCrop reference to a crop instance
     */
    public AbstrCropState(Crop myCrop) {
        this.myCrop = myCrop;
    }
}
