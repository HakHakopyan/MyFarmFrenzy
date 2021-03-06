package fieldelement;

import command.Command;
import crop.Cropable;
import plant.Plantable;
import plant.state.PlantState;
import season.Season;

import java.util.List;

public class WithoutPlantState implements ArableState{
    Parsel myParsel;

    public WithoutPlantState(Parsel parsel) {
        this.myParsel = parsel;
        this.myParsel.myPlant = null;
    }

    @Override
    public void setPlant(String plantName) {
        Plantable newPlant = myParsel.myFactory.createPlant(plantName);

        if (newPlant == null)
            throw new IllegalArgumentException("plant with name" + plantName + " is not Exsist");

        this.myParsel.myPlant = newPlant;
        this.myParsel.changeState(myParsel.myPlantExistState);
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
    public Cropable getCrop() {
        throw  new NullPointerException("plant not exist!");
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
    public void doCommand(List<Command> commandList) {
        throw new IllegalArgumentException("Can not execute a command as there is no plant");
    }

    @Override
    public void updateTime() {

    }

    @Override
    public void changeSeason(Season season) {

    }
}
