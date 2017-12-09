package Factory;

import Annotations.GetPlant;
import Annotations.What;
import Crop.Cropable;
import Plant.Plant;
import Plant.Plantable;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Factory implements Factoriable {
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
    public void registre(Class cl) {
        if (cl.isAnnotationPresent(GetPlant.class)){
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

    /**
     * gives the names of plants that we can create with the help of this factory
     * @return Plant Names which we can create with
     */
    @Override
    public String[] getPlantNames() {
        String[] strings = new String[myPlants.size()];
        int i=0;
        for (Object key: myPlants.keySet()) {
            strings[i] = key.toString();
            i++;
        }

        return strings;
    }
}
