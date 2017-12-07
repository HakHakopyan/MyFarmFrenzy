package Plant.State;

import Command.Command;
import Plant.*;
import Crop.*;
import Season.Season;

public class StateDie extends AbstrPlantState {

    public StateDie(Plant myPlant) {
        super(myPlant);

        this.myPlant.destroyCrop();
    }

    @Override
    public String getRepresentation(String name) {
        return name + " Die";
    }

    @Override
    public Cropable GetCrop() {
        return null;
    }

    @Override
    public void updateTime() {
    }

    @Override
    public void changeSeason(Season season){
    }

    @Override
    public boolean isDie() {
        return true;
    }

    @Override
    public boolean isCropReady() {
        return false;
    }

    @Override
    public void doComand(Command com) {

    }
}
