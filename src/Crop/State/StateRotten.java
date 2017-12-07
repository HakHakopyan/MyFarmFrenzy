package Crop.State;

import Crop.*;

/**
 * Состояние Урожай испорчен
 */
public class StateRotten extends AbstrCropState {

    public StateRotten(Crop myCrop) {
        super(myCrop);
    }

    @Override
    public boolean isRipe() {
        return false;
    }

    @Override
    public boolean isRotten() {
        return true;
    }

    @Override
    public boolean isRipen() {
        return false;
    }

    @Override
    public void updateTime() {

    }
}
