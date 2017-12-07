package Factory;

import Annotations.GetPlant;
import Annotations.What;
import Crop.Cropable;
import Plant.Plant;
import Plant.Plantable;

import java.lang.reflect.Method;
import java.util.HashMap;

public class Factory implements Factoriable {
    private HashMap myPlants = new HashMap<String, Plantable>();

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
    public void registre(Class cl) {
        if (cl.isAnnotationPresent(What.class)){
            What nameAnno = (What) cl.getAnnotation(What.class);
            if (nameAnno.value().toString().equals("Plant"))
                registrePlant(cl);
        }
    }

    public void registrePlant(Class cl) {
        Method[] mets = cl.getDeclaredMethods();
        for (Method met: mets) {
            met.setAccessible(true);
            String plantName;
            if (met.isAnnotationPresent(GetPlant.class)) {
                if (met.isAnnotationPresent(What.class)) {
                    What nameAnno = met.getAnnotation(What.class);
                    plantName = nameAnno.value().toString();

                    try {
                    this.myPlants.put(plantName, (Plantable) met.invoke(cl.newInstance()));
                    } catch (Exception ex) {

                    }
                }
            }
        }
    }
}
