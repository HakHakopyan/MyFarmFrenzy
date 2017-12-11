package factory;

import crop.*;
import plant.*;

import java.util.Map;

/**
 * Able to produce plants or crops or both
 */
public interface Factoriable {

    /**
     *  instance |ˈɪnstəns|  — экземпляр объекта
     * This method creates an instance of the plant class and returns
     * @param name The Name of the plant whose instance you want to create
     * @return instance of the plant
     */
    public Plantable createPlant(String name);

    /**
     *  instance |ˈɪnstəns|  — экземпляр объекта
     * This method creates an instance of the crop class and returns
     * @param name The Name of the crop whose instance you want to create
     * @return instance of the crop
     */
    public Cropable createCrop(String name);

    /**
     *  Method takes copies available objects using the methods in the Class passed in the parameter mClass
     *  The Names of the Objects are taken from the annotation {@link annotations.What What(PlantName or CropName)}
     * @param mClass contains methods that instances of the heirs of the class {@link Plant Plant} or {@link Crop Crop}
     */
    public void registration(Class mClass);

    /**
     * Method returns the names of plants that we can create with their cost
     * @return A Map that contains the name of the plant in the key and its cost
     */
    public Map<String, Double> getPlantNamesWithCost();
}
