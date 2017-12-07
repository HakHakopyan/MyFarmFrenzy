package Plant.State;

import Command.Command;
import Crop.*;
import Plant.*;
import Season.Season;

/**
 * Состояние простоя, когда зима растение ничего не делает
 */
public class StateIdle extends AbstrPlantState{

    public StateIdle(Plant myPlant) {
        super(myPlant);
        this.myPlant.destroyCrop();
    }

    @Override
    public String getRepresentation(String name) {
        return name + " Idle";
    }

    @Override
    public Cropable GetCrop() {
        return null;
    }

    @Override
    public void updateTime() {
        if (!this.myPlant.reduceLifeTime())
            this.myPlant.changeState(new StateDie(this.myPlant));
    }

    @Override
    public void changeSeason(Season season) {
        if (season == Season.SUMMER)
            this.myPlant.changeState(new StateCrop(this.myPlant));
    }

    @Override
    public boolean isDie() {
        return false;
    }

    @Override
    public boolean isCropReady() {
        return false;
    }

    @Override
    public void doComand(Command com) {

    }
}
