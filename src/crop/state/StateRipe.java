package crop.state;

import command.Command;
import crop.*;
import season.Season;

import java.util.List;

/**
 * Ripe |raɪp| созрел
 * состояние - плод созрел
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

    @Override
    public void doCommand(List<Command> commandList) {
        // nothing since the fruit is already ripe
    }
}
