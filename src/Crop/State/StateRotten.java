package Crop.State;

import Command.Command;
import Crop.*;
import Season.Season;

import java.util.List;

/**
 * Состояние Урожай испорчен
 */
public class StateRotten extends AbstrCropState {

    public StateRotten(Crop myCrop)
    {
        super(myCrop);
        this.myCrop.setCount(0);
        this.myCrop.setCost(0);
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
        // Nothing, because Crop is rotten ):
    }

    @Override
    public void changeSeason(Season season) {
        // Nothing, because Crop is already rotten ):
    }

    @Override
    public String getRepresentation() {
        return "Rotten";
    }

    @Override
    public void doCommand(List<Command> commandList) {
        //nothing because Crop is Rotten
    }
}
