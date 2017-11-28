import Storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class Farm<T extends Plant, C extends Crop> implements Timable {
    /**
     * contains the number of arable unity on which our myField is divided
     */
    int arableCount;
    Well myWell;
    Storage myStorage;
    List<Arable> myField;

    Farm(int arableCount, int storageCount) {
        myField = new ArrayList<>();
        myStorage = new Storage(storageCount);

        for (int i = 1; i <= arableCount; i++)
            addArable();
    }

    public void addArable() {
        myField.add(new Arable<T>());
    }


    @Override
    public void addTime() {
        for (int i = 1; i <= arableCount; i++) {
            Arable ar = myField.get(i);
            if (myWell.deliveryIsPossible()) {
                myWell.getDelivery();
                ar.addTime();
            }
            else {
                ar.destroyPlant();
                throw new IllegalArgumentException("В колодце закончилась вода! Растение погибло...");
            }
        }
    }
}
