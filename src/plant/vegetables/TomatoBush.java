package plant.vegetables;

import command.Command;
import crop.vegetables.Tomato;
import plant.Plant;
import plant.Plantable;
import season.Season;

import java.util.List;

/**
 * Куст томата
 */
public class TomatoBush extends Plant {
    /**
     * Constructor for Tomato brush
     * @param lifeTime Tomato brush life time
     * @param cost Tomato brush price
     */
    public TomatoBush(int lifeTime, double cost) {
        super(lifeTime, cost, Season.SUMMER);
    }

    @Override
    public void newCrop() {
        this.myCrop = new Tomato();
    }

    /**
     * the method returns a reference to a new instance of the Tomato object
     * with the same internal state as the object whose method is called
     * @return reference to the Apple object
     * @throws CloneNotSupportedException
     */
    @Override
    public Plantable clone() throws CloneNotSupportedException {
        return new TomatoBush(this.myLifeTime, this.myCost);
    }

    /**
     * Возвращает представление для куста Томата - имя и информация ос состоянии
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
