package Factory;

import Crop.Cropable;
import Plant.Plantable;

public interface Factoriable {

    public Plantable createPlant(String name);
    public Cropable createCrop(String name);

    /**
     *
     * @param cl class containing methods that create objects
     */
    public void registre(Class cl);
}
