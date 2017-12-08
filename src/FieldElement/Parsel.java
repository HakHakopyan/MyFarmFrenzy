package FieldElement;

import Command.Command;
import Crop.Cropable;
import Factory.Factoriable;
import Plant.Plantable;
import Season.Season;
import Observer.*;

import java.util.Observable;

public class Parsel implements Arable {

    Factoriable myFactory;
    Plantable myPlant;
    ArableState myState;

    public Parsel(Factoriable factory) {
        this.myFactory = factory;
        this.changeState(new WithoutPlantState(this));
    }

    public void setPlant(String plantName) {
        this.myState.setPlant(plantName);
    }

    @Override
    public void watchFor(MyObservable ob) {
        ob.addObserver(this);
    }

    public void changeState(ArableState newState) {
        this.myState = newState;
    }

    @Override
    public String getRepresentation() {
        return myState.getRepresentation();
    }

    @Override
    public Cropable getCrop() {
        return this.myState.getCrop();
    }

    @Override
    public boolean isCropReady() {
        return this.myState.isCropReady();
    }

    @Override
    public boolean isDie() {
        return myState.isDie();
    }

    @Override
    public void doComand(Command com) {
        if (this.myPlant != null)
            this.myPlant.doComand(com);
    }

    @Override
    public void updateTime() {
       myState.updateTime();
    }

    @Override
    public void changeSeason(Season season) {
        myState.changeSeason(season);
    }

    public boolean plantExist() {
        return myState.plantExist();
    }
}
