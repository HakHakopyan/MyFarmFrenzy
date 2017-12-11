package plant.state;

import command.Command;
import plant.*;
import crop.*;
import season.Season;

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
        throw new NullPointerException("plant " + this.getRepresentation() + "cann't give crop!");
        /*
        Cropable retCrop = this.myPlant.myCrop;
        this.myPlant.newCrop();
        this.myPlant.myCrop.changeSeason(season.WINTER);
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
