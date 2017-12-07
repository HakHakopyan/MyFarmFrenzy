package FieldElement;

import Command.Command;
import Crop.Cropable;
import Season.Season;

public class PlantExsistState implements ArableState{
    Parsel myParsel;
    boolean isPlantDie = false;

    public PlantExsistState(Parsel parsel) {
        this.myParsel = parsel;
    }

    @Override
    public void setPlant(String plantName) {
        this.myParsel.setPlant(plantName);
    }

    @Override
    public boolean plantExist() {
        return true;
    }

    @Override
    public String getRepresentation() {
        return this.myParsel.myPlant.getRepresentation();
    }

    @Override
    public Cropable getDelivery() {
        return this.myParsel.myPlant.getDelivery();
    }

    @Override
    public boolean isCropReady() {
        return this.myParsel.myPlant.isCropReady();
    }

    @Override
    public boolean isDie() {
        return this.myParsel.myPlant.isDie();
    }

    @Override
    public void doComand(Command com) {
        this.myParsel.myPlant.doComand(com);
    }

    @Override
    public void updateTime() {
        this.myParsel.myPlant.updateTime();
        if (isPlantDie) {
            this.myParsel.changeState(new WithoutPlantState(this.myParsel));
        }
        if (this.myParsel.isDie())
            this.isPlantDie = true;
    }

    @Override
    public void changeSeason(Season season) {
        this.myParsel.myPlant.changeSeason(season);
    }
}
