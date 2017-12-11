package factory;

import annotations.GetPlant;
import annotations.What;
import base.Costable;
import crop.Cropable;
import plant.Plant;
import plant.Plantable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that acts as a pattern as an abstract factory and creates instances of the required objects
 */
public class Factory implements Factoriable {
    /**
     * Contains instances of plants that possible to create
     */
    private Map myPlants = new HashMap<String, Plantable>();

    @Override
    public Plantable createPlant(String name) {
        Plantable newPlant = null;
        try {
            if (myPlants.containsKey(name))
                newPlant = ((Plant) myPlants.get(name)).clone();

        } catch (CloneNotSupportedException ex){
            newPlant = null;
        }

        return newPlant;
    }

    @Override
    public Cropable createCrop(String name) {
        return null;
    }

    @Override
    public void registration(Class cl) {
        if (cl.isAnnotationPresent(GetPlant.class)){
            registrationPlant(cl);
        }
    }

    /**
     *  Method takes copies available plants using the methods in the Class passed in the parameter mClass
     *  The Names of the Plants are taken from the annotation What(PlantName)
     * @param mClass contains methods that instances of the heirs of the class {@link Plant Plant}
     */
    public void registrationPlant(Class mClass) {
        Method[] mets = mClass.getDeclaredMethods();
        for (Method met: mets) {
            met.setAccessible(true);
            String plantName;
            if (met.isAnnotationPresent(GetPlant.class)) {
                if (met.isAnnotationPresent(What.class)) {
                    What nameAnno = met.getAnnotation(What.class);
                    plantName = nameAnno.value().toString();

                    try {
                    this.myPlants.put(plantName, (Plantable) met.invoke(mClass.newInstance()));
                    } catch (Exception ex) {

                    }
                }
            }
        }
    }

    /**
     * Method returns the names of plants that we can create with their cost
     * @return A Map that contains the name of the plant in the key and its cost
     */
    @Override
    public Map<String, Double> getPlantNamesWithCost() {
        Map<String, Double> namesWCost = new HashMap<>();
        for (Object key: myPlants.keySet()) {
            namesWCost.put(key.toString(), ((Costable) myPlants.get(key)).getCost());
        }

        return namesWCost;
    }
}
