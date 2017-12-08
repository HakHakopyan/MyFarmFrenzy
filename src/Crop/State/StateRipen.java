package Crop.State;
import Crop.*;
import Season.Season;

/**
 * ripen |ˈraɪpən|  — созревать
 * состояние -> плод созревает
 */
public class StateRipen extends AbstrCropState {

    public StateRipen(Crop myCrop) {
        super(myCrop);
    }

    @Override
    public boolean isRipe() {
        return false;
    }

    @Override
    public boolean isRotten() {
        return false;
    }

    @Override
    public boolean isRipen() {
        return true;
    }

    @Override
    public void updateTime() {
        if(!this.myCrop.doRipen()) {
            this.myCrop.changeState(new StateRipe(this.myCrop));
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
        return "Rippen";
    }
}
