package fieldelement;

import command.Command;
import crop.Cropable;
import season.Season;

import java.util.List;

public class PlantExsistState implements ArableState{
    Parsel myParsel;
    boolean isPlantDie = false;

    public PlantExsistState(Parsel parsel) {
        this.myParsel = parsel;
    }

    @Override
    public void setPlant(String plantName) {
        this.myParsel.changeState(this.myParsel.myWithoutPlantState);
        this.myParsel.setPlant(plantName);
    }

    @Override
    public boolean plantExist() {
        if (this.myParsel.myPlant.isDie())
            return false;
        return true;
    }

    @Override
    public String getRepresentation() {
        return this.myParsel.myPlant.getRepresentation();
    }

    @Override
    public Cropable getCrop() {
        return this.myParsel.myPlant.getCrop();
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
    public void updateTime() {
        this.myParsel.myPlant.updateTime();
    }

    @Override
    public void changeSeason(Season season) {
        this.myParsel.myPlant.changeSeason(season);
    }

    @Override
    public void doCommand(List<Command> commandList) {
        this.myParsel.myPlant.doCommand(commandList);
    }
}
