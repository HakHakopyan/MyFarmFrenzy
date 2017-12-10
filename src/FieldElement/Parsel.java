package FieldElement;

import Command.Command;
import Crop.Cropable;
import Factory.Factoriable;
import Plant.Plantable;
import Season.Season;
import Observer.*;

import java.util.List;

public class Parsel implements Arable {

    Factoriable myFactory;
    Plantable myPlant;
    ArableState myState;

    ArableState myPlantExistState = new PlantExsistState(this);
    ArableState myWithoutPlantState = new WithoutPlantState(this);

    public Parsel(Factoriable factory) {
        this.myFactory = factory;
        this.changeState(myWithoutPlantState);
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
    public void doCommand(List<Command> commandList) {
           this.myState.doCommand(commandList);
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
