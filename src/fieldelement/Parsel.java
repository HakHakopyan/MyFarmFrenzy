package fieldelement;

import command.Command;
import crop.Cropable;
import factory.Factoriable;
import plant.Plantable;
import season.Season;
import observer.*;

import java.util.List;

/**
 * determines the class for the element of the field
 *  parcel |ˈpɑːrsl|  — участок земли
 */
public class Parsel implements Arable {
    /**
     * Ссылка на фабрику, с помощью которой создаются новые растения
     */
    Factoriable myFactory;
    /**
     * Растение, которое живет внутри/на нашего участка земли
     */
    Plantable myPlant;
    /**
     * ссылка на объект текущего состояния участка земли - есть растение / нет растения
     */
    ArableState myState;
    /**
     * State - Plant exist
     */
    ArableState myPlantExistState = new PlantExsistState(this);
    /**
     * State - without Plant
     */
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
