package plant.state;

import command.Command;
import crop.*;
import plant.*;
import season.Season;

import java.util.List;

/**
 * Состояние урождай созрел
 */
public class StateCrop extends AbstrPlantState {

    public StateCrop(Plant myPlant) {
        super(myPlant);

        myPlant.newCrop();
    }

    @Override
    public String getRepresentation() {
        if (myPlant.isCropReady())
            return " Ready!";
        return "Ripen...";
    }

    @Override
    public Cropable getCrop() {
        Cropable retCrop = myPlant.myCrop;
        myPlant.newCrop();
        if (retCrop.isRipen()) {
            retCrop.setCost(0);
            retCrop.setCount(0);
        }
            return retCrop;

    }

    @Override
    public void updateTime() {
        if (!this.myPlant.reduceLifeTime())
            this.myPlant.changeState(new StateDie(this.myPlant));
        else {
        this.myPlant.myCrop.updateTime();
        if (this.myPlant.isCropRotten()) {
            this.myPlant.newCrop();
        }
        }
    }

    @Override
    public void changeSeason(Season season) {
        if (season == Season.WINTER){
            if (this.myPlant.season == Season.WINTER) {
                this.myPlant.changeState(new StateIdle(this.myPlant));
            } else
                this.myPlant.changeState(new StateDie((this.myPlant)));
        }
    }


    public boolean isDie() {
        return false;
    }


    public boolean isCropReady() {
        return this.myPlant.myCrop.isRipe();
    }

    public void doComand(Command com) {
        if (this.myPlant.myCrop.isRipen()) {
            com.Execute(this.myPlant.myCrop);
        }
    }

    @Override
    public void doCommand(List<Command> commandList) {
        myPlant.myCrop.doCommand(commandList);
    }
}
