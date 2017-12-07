package Crop.State;
import Crop.*;
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
}
