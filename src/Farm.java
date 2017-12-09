import java.util.*;

import Command.Command;
import Command.Commandable;
import Observer.*;
import Crop.*;
import Factory.*;
import FieldElement.*;
import Season.Season;
import Storage.*;
import Visitor.*;


public class Farm implements Observer, ObserverTime, Visitable, Commandable {
    Storable<Cropable> myStorage = new Storage();

    List<Arable> myField = new ArrayList<>();

    Factoriable myFactory = new Factory();

    MyObservable<Arable> myObservarable = new MyObservable();

    MyExternalObservable myExternalOb = new MyExternalObservable();

    TimeThread tThread = new TimeThread();

    Season mySeason = Season.SUMMER;

    int myTime = 0;

    Farm(int arableCount, Observer externalObserver) {
        myExternalOb.addObserver(externalObserver);
        myFactory.registre(Factory.PlantInstances.class);

        for (int i = 1; i <= arableCount; i++) {
            addArable();
        }
    }

    public void addArable() {

        Arable newParsel = new Parsel(myFactory);
        newParsel.watchFor(this.myObservarable);
        myField.add(newParsel);
    }

    public int getArablesCount() {
        return myField.size();
    }

    public void setGreenHouse(int position) {
        if (position > 0 && position <= this.myField.size()) {
            position--;
            Arable ar = this.myField.get(position);
            Arable greenH = new GreenHouse(ar);
            this.myObservarable.deleteObserver(ar);
            greenH.watchFor(this.myObservarable);
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

        myObservarable.notifyTimeUpdate();
        checkCrop();

        if (myTime == 6) {
            myTime = 0;
            if (mySeason == Season.SUMMER)
                mySeason = Season.WINTER;
            else
                mySeason = Season.SUMMER;

            myObservarable.notifySeasonChange(mySeason);

            myExternalOb.setChanged();
            myExternalOb.notifyObservers(mySeason);
        }
    }

    /**
     * Освоболить хранилище/ склад урожая
     */
    public  void releaseStorage() {
        this.myStorage.doEmpty();
    }

    @Override
    public void acceptVisit(Visitor v) {
        this.myStorage.acceptVisit(v);
    }

    @Override
    public void doCommand(List<Command> commandList) {
        for (Arable ar: myField) {
            if (ar.plantExist()) {
                ar.doCommand(commandList);
            }
        }
    }

    public String[] getPlantNames() {
        return myFactory.getPlantNames();
    }
}
