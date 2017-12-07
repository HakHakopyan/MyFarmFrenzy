package FieldElement;

import Command.Command;
import Crop.Cropable;
import Season.Season;

import java.util.Observable;

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
    public void watchFor(Observable ob) {
        ob.deleteObserver(this.myArable);
        ob.addObserver(this);
    }

    @Override
    public String getRepresentation() {
        return this.myArable.getRepresentation();
    }

    @Override
    public Cropable getDelivery() {
        return this.myArable.getDelivery();
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
    public void doComand(Command com) {
        this.myArable.doComand(com);
    }

    @Override
    public void updateTime() {
        this.myArable.updateTime();
    }

    @Override
    public void changeSeason(Season season) {
        this.myArable.changeSeason(Season.SUMMER);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null)
            this.updateTime();
        if (arg.getClass().equals(Season.class)) {
            this.changeSeason(Season.SUMMER);
        }
    }
}
