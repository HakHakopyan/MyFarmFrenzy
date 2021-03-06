package fieldelement;

import command.Command;
import crop.Cropable;
import observer.MyObservable;
import season.Season;

import java.util.List;

public class GreenHouse implements Arable {
    Arable myArable;

    public GreenHouse(Arable arable) {
        this.myArable = arable;
        this.changeSeason(Season.SUMMER);
    }

    @Override
    public boolean plantExist() {
        return myArable.plantExist();
    }

    @Override
    public void setPlant(String plantName) {
        this.myArable.setPlant(plantName);
    }

    @Override
    public void watchFor(MyObservable ob) {
        ob.deleteObserver(this.myArable);
        ob.addObserver(this);
    }

    @Override
    public String getRepresentation() {
        return this.myArable.getRepresentation();
    }

    @Override
    public Cropable getCrop() {
        return this.myArable.getCrop();
    }

    @Override
    public boolean isCropReady() {
        return this.myArable.isCropReady();
    }

    @Override
    public boolean isDie() {
        return this.myArable.isDie();
    }

    @Override
    public void doCommand(List<Command> commandList) {
        this.myArable.doCommand(commandList);
    }

    @Override
    public void updateTime() {
        this.myArable.updateTime();
    }

    @Override
    public void changeSeason(Season season) {
        this.myArable.changeSeason(Season.SUMMER);
    }
}
