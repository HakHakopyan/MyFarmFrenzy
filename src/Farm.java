import java.util.*;

import Base.ObserverTime;
import Command.Command;
import Crop.*;
import Factory.*;
import FieldElement.*;
import Storage.*;
import Visitor.Visitor;


public class Farm implements Observer {
    Storable<Cropable> myStorage = new Storage();

    List<Arable> myField = new ArrayList<>();

    Factoriable myFactory = new Factory();

    MyObserbarable myOb = new MyObserbarable();
    MyObserbarable myExternalOb = new MyObserbarable();

    TimeThread tThread = new TimeThread();

    Farm(int arableCount, Observer externalObserver) {
        myOb.addObserver(this);
        myExternalOb.addObserver(externalObserver);
        myFactory.registre(Plants.class);

        for (int i = 1; i <= arableCount; i++) {
            addArable();
        }
    }

    public void addArable() {

        Arable newParsel = new Parsel(myFactory);
        newParsel.watchFor(this.myOb);
        myField.add(newParsel);
    }

    public void setGreenHouse(int position) {
        if (position > 0 && position <= this.myField.size()) {
            position--;
            Arable greenH = new GreenHouse(this.myField.get(position));
            greenH.watchFor(this.myOb);
            this.myField.remove(position);
            this.myField.add(position, greenH);
        }
    }

    public void setPlant(String plantName, int position) {
        if (position > 0 && position <= this.myField.size()) {
            position--;
            this.myField.get(position).setPlant(plantName);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            for (Arable a: this.myField) {
                if (a.isCropReady()) {
                    Cropable newCrop = a.getDelivery();
                    myStorage.store(newCrop);
                    myExternalOb.notifyObservers(newCrop);
                }
            }
        }
    }

    public List<String> getFieldRepresentation() {
        List<String> representList = new ArrayList<>();
        for (Arable ar: this.myField) {
            representList.add(ar.getRepresentation());
        }

        return representList;
    }

    public void checkStorage(Visitor visitor) {
        this.myStorage.acceptVisit(visitor);
    }
}
