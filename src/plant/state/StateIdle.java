package plant.state;

import command.Command;
import crop.*;
import plant.*;
import season.Season;

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
            this.myPlant.changeState(this.myPlant.myCropState);
    }

    @Override
    public boolean isDie() {
        return false;
    }

    @Override
    public Cropable getCrop() {
        throw new NullPointerException("plant " + this.getRepresentation() + " cann't give crop!");
        /*
        Cropable retCrop = this.myPlant.myCrop;
        this.myPlant.newCrop();
        this.myPlant.myCrop.changeSeason(season.WINTER);
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
