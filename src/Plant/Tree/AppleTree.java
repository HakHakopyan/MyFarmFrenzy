package Plant.Tree;

import Command.Command;
import Crop.Fruit.*;
import Plant.*;
import Season.Season;

import java.util.List;


public class AppleTree extends Plant {

    public AppleTree(int lifeTimem, double cost) {
        super(lifeTimem, cost, Season.WINTER);
    }

    @Override
    public void newCrop() {
        this.myCrop = new Apple();
    }

    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + myState.getRepresentation();
    }

    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new AppleTree(this.myLifeTime, this.myCost);
    }

    @Override
    public void doCommand(List<Command> commandList) {
        myState.doCommand(commandList);
    }
}
