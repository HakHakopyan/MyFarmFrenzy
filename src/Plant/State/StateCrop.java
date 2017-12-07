package Plant.State;

import Command.Command;
import Crop.*;
import Plant.*;
import Season.Season;

/**
 * Состояние урождай созрел
 */
public class StateCrop extends AbstrPlantState {

    public StateCrop(Plant myPlant) {
        super(myPlant);

        myPlant.newCrop();
    }

    @Override
    public String getRepresentation(String name) {
        if (myPlant.isCropReady())
            return name + " Ready!";
        return name + "Ripen...";
    }

    @Override
    public Cropable GetCrop() {
        if (myPlant.isCropReady()) {
            Cropable bufCrop = myPlant.myCrop;
            myPlant.newCrop();

            return bufCrop;
        }
        return null;
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

    @Override
    public boolean isDie() {
        return false;
    }

    @Override
    public boolean isCropReady() {
        if (this.myPlant.myCrop == null)
            return false;
        return this.myPlant.myCrop.isRipe();
    }

    public void doComand(Command com) {
        if (this.myPlant.myCrop.isRipen()) {
            com.Execute(this.myPlant.myCrop);
        }
    }
}
