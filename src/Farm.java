import java.util.*;

import Observer.*;
import Crop.*;
import Factory.*;
import FieldElement.*;
import Season.Season;
import Storage.*;
import Visitor.*;


public class Farm implements Observer, ObserverTime, Visitable {
    Storable<Cropable> myStorage = new Storage();

    List<Arable> myField = new ArrayList<>();

    Factoriable myFactory = new Factory();

    MyObservable<Arable> myOb = new MyObservable();

    MyExternalObservable myExternalOb = new MyExternalObservable();

    TimeThread tThread = new TimeThread();

    Season mySeason = Season.SUMMER;

    int myTime = 0;

    Farm(int arableCount, Observer externalObserver) {
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

        }
    }

    public void checkCrop() {
        for (Arable a: this.myField) {
            if (a.isCropReady()) {
                Cropable newCrop = a.getCrop();
                myStorage.store(newCrop);

                myExternalOb.setChanged();
                myExternalOb.notifyObservers(newCrop);
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

    int newTime = 0;
    @Override
    public void updateTime() {
        myTime++;
        newTime++;

        myOb.notifyTimeUpdate();
        checkCrop();

        if (myTime == 6) {
                System.out.println("Yep! " + newTime);
            myTime = 0;
            if (mySeason == Season.SUMMER)
                mySeason = Season.WINTER;
            else
                mySeason = Season.SUMMER;

            myOb.notifySeasonChange(mySeason);

            myExternalOb.setChanged();
            myExternalOb.notifyObservers(mySeason);
        }
    }

    @Override
    public void acceptVisit(Visitor v) {
        this.myStorage.acceptVisit(v);
    }
}
