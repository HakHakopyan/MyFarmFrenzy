package fieldelement;

import plant.Plantable;

import observer.*;
import plant.*;

/**
 * Defines the interface for a piece of land on which the plant is grow
 */
public interface Arable extends Plantable {

    /**
     * Checks if our element has a living plant
     * @return true if plant exist and a living
     */
    public boolean plantExist();

    /**
     * set new {@link Plant Plant} ancestor with name plantName
     * @param plantName contains name of plant
     */
    public void setPlant(String plantName);

    /**
     * method in which the parameter object to watch for
     * @param ob Observable - object to watch for
     */
    public void watchFor(MyObservable<Arable> ob);
}
