package Plant.State;

import Command.Command;
import Plant.*;
import Crop.*;
import Season.Season;

import java.util.List;

public class StateDie extends AbstrPlantState {

    public StateDie(Plant myPlant) {
        super(myPlant);

        this.myPlant.destroyCrop();
    }

    @Override
    public String getRepresentation() {
        return " Die";
    }

    @Override
    public void updateTime() {
    }

    @Override
    public void changeSeason(Season season) {
    }

    @Override
    public boolean isDie() {
        return true;
    }

    @Override
    public Cropable getCrop() {
        throw new NullPointerException("Plant " + this.getRepresentation() + "cann't give Crop!");
        /*
        Cropable retCrop = this.myPlant.myCrop;
        this.myPlant.newCrop();
        this.myPlant.myCrop.changeSeason(Season.WINTER);
        return retCrop;
        */
    }

    public boolean isCropReady() {
        return false;
    }

    @Override
    public void doCommand(List<Command> commandList) {
        throw new IllegalArgumentException("Die, cann't do coammand");
    }
}
