package FieldElement;

import Command.Command;
import Crop.Cropable;
import Plant.Plantable;
import Season.Season;

public class WithoutPlantState implements ArableState {
    Parsel myParsel;

    public WithoutPlantState(Parsel parsel) {
        this.myParsel = parsel;
        this.myParsel.myPlant = null;
    }

    @Override
    public void setPlant(String plantName) {
        Plantable newPlant = myParsel.myFactory.createPlant(plantName);

        if (newPlant == null)
            throw new IllegalArgumentException("Plant with name" + plantName + " is not Exsist");

        this.myParsel.myPlant = newPlant;
        this.myParsel.changeState(new PlantExsistState(this.myParsel));
    }

    @Override
    public boolean plantExist() {
        return false;
    }

    @Override
    public String getRepresentation() {
        return "Empty";
    }

    @Override
    public Cropable getDelivery() {
        return null;
    }

    @Override
    public boolean isCropReady() {
        return false;
    }

    @Override
    public boolean isDie() {
        return true;
    }

    @Override
    public void doComand(Command com) {

    }

    @Override
    public void updateTime() {

    }

    @Override
    public void changeSeason(Season season) {

    }
}
