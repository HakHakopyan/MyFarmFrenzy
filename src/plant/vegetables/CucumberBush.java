package plant.vegetables;

import command.Command;
import crop.vegetables.Cucumber;
import crop.vegetables.Tomato;
import plant.Plant;
import plant.Plantable;
import season.Season;

import java.util.List;

/**
 * Куст огурца
 */
public class CucumberBush extends Plant{
    /**
     * Constructor for Cucumber brush
     * @param lifeTime Cucumber brush life time
     * @param cost Cucumber brush price
     */
    public CucumberBush(int lifeTime, double cost) {
        super(lifeTime, cost, Season.SUMMER);
    }

    @Override
    public void newCrop() {
        this.myCrop = new Cucumber();
    }

    /**
     * the method returns a reference to a new instance of the Cucumber object
     * with the same internal state as the object whose method is called
     * @return reference to the Apple object
     * @throws CloneNotSupportedException
     */
    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new CucumberBush(this.myLifeTime, this.myCost);
    }

    /**
     * Возвращает представление для куста Огурца - имя и информация ос состоянии
     * @return строка, содержащая имя и информацию о состоянии
     */
    @Override
    public String getRepresentation() {
        return this.getClass().getSimpleName() + " " + myState.getRepresentation();
    }

    @Override
    public void doCommand(List<Command> commandList) {
        myState.doCommand(commandList);
    }
}
