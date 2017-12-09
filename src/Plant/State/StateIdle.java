package Plant.State;

import Command.Command;
import Crop.*;
import Plant.*;
import Season.Season;

import java.util.List;

/**
 * Состояние простоя, когда зима растение ничего не делает
 */
public class StateIdle extends AbstrPlantState{

    public StateIdle(Plant myPlant) {
        super(myPlant);
        this.myPlant.destroyCrop();
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
    public Cropable getCrop() {
        throw new NullPointerException("Plant " + this.getRepresentation() + " cann't give Crop!");
        /*
        Cropable retCrop = this.myPlant.myCrop;
        this.myPlant.newCrop();
        this.myPlant.myCrop.changeSeason(Season.WINTER);
        return retCrop;
        */
    }

    @Override
    public boolean isCropReady() {
        return false;
    }

    @Override
    public String getRepresentation() {
        return "Idle";
    }

    @Override
    public void doCommand(List<Command> commandList) {
        throw new IllegalArgumentException("Idle, cann't do coammand");
    }
}
