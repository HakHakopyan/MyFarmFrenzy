package storage;

import visitor.*;
import crop.*;

import java.util.*;

public class Storage<C extends Cropable> implements Storable{
    /**
     * contains references to the objects implements interface {@link Cropable Cropable}
     */
    private Map<String, C>  myCrops;

    /**
     * Initialize Crop fields
     */
    public Storage() {
        super();
        this.myCrops = new HashMap<>();
    }

    @Override
    public void store(Cropable newCrop) {
        String cropName = newCrop.getClass().getSimpleName();
        if (myCrops.containsKey(cropName)) {
            Cropable crop = myCrops.get(cropName);
            crop.setCount(crop.getCount() + newCrop.getCount());
            newCrop = null;
        } else
            myCrops.put(newCrop.getClass().getSimpleName(),(C) newCrop);
    }

    @Override
    public void doEmpty() {
        myCrops.clear();
    }

    @Override
    public void acceptVisit(Visitor visitor) {
        Set<Map.Entry<String, C>> set = myCrops.entrySet();
        for (Map.Entry<String, C> me : set) {
            me.getValue().acceptVisit(visitor);
        }
    }
}
