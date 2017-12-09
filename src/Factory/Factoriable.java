package Factory;

import Crop.Cropable;
import Plant.Plantable;

/**
 * Able to produce plants or crops or both
 */
public interface Factoriable {

    /**
     *  instance |ˈɪnstəns|  — экземпляр объекта
     * This method creates an instance of the plant class and returns
     * @param name The Name of the Plant whose instance you want to create
     * @return instance of the plant
     */
    public Plantable createPlant(String name);

    /**
     *  instance |ˈɪnstəns|  — экземпляр объекта
     * This method creates an instance of the Crop class and returns
     * @param name The Name of the Crop whose instance you want to create
     * @return instance of the Crop
     */
    public Cropable createCrop(String name);

    /**
     *  The method gets objects from the class methods, which then can "create"
     *  The Names of the Plants are taken fropm the annoraion "@What(<PlantName>)"
     * @param cl class containing methods that create objects
     */
    public void registre(Class cl);

    /**
     * get Names of the Plants that the factory can create
     * @return Plant Names
     */
    public String[] getPlantNames();
}
