package plant.tree;

import command.Command;
import crop.fruit.*;
import plant.*;
import season.Season;

import java.util.List;

/**
 * class for Apple Tree
 */
public class AppleTree extends Plant {

    /**
     * Constructor for Aplle Tree
     * @param lifeTimem Apple Tree life time
     * @param cost Apple Tree price
     */
    public AppleTree(int lifeTimem, double cost) {
        super(lifeTimem, cost, Season.WINTER);
    }

    /**
     * say apple trees create new apples
     */
    @Override
    public void newCrop() {
        this.myCrop = new Apple();
    }

    /**
     * Возвращает представление для Яблони - имя и информация ос состоянии
     * @return строка, содержащая имя и информацию о состоянии
     */
    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + myState.getRepresentation();
    }

    /**
     * the method returns a reference to a new instance of the Apple object
     * with the same internal state as the object whose method is called
     * @return reference to the Apple object
     * @throws CloneNotSupportedException
     */
    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new AppleTree(this.myLifeTime, this.myCost);
    }

    @Override
    public void doCommand(List<Command> commandList) {
        myState.doCommand(commandList);
    }
}
