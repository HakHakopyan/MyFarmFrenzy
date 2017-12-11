package fieldelement;

import plant.*;

/**
 * defines interface for {@link Arable Arable} states
 */
public interface ArableState extends Plantable {

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

//    public void changeState(ArableState newState);

}
