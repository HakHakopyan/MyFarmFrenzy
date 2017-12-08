package Crop.State;

import Crop.*;
import Season.Season;

/**
 * Ripe |raɪp| созрел
 * состояние -> плод созрел
 */
public class StateRipe extends AbstrCropState {

    public StateRipe(Crop myCrop) {
        super(myCrop);
    }

    @Override
    public boolean isRipe() {
        return true;
    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public boolean isRipen() {
        return false;
    }

    @Override
    public void updateTime() {
        if (! this.myCrop.reduceShelfLife()) {
            this.myCrop.setCount(0);
            this.myCrop.changeState(new StateRotten(this.myCrop));
        }

    }

    @Override
    public void changeSeason(Season season) {
        if (season == Season.WINTER) {
            this.myCrop.changeState(new StateRotten(myCrop));
        }
    }

    @Override
    public String getRepresentation() {
        return "Ripe";
    }
}