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
        myFactory.registre(PlantInstances.class);

        for (int i = 1; i <= arableCount; i++) {
            addArable();
        }
    }

    /**
     * Add new Field element
     */
    public void addArable() {

        Arable newParsel = new Parsel(myFactory);
        newParsel.watchFor(this.myObservarable);
        myField.add(newParsel);
    }

    public int getArablesCount() {
        return myField.size();
    }

    public boolean setGreenHouse(int position) {
        if (position > 0 && position <= this.myField.size()) {
            position--;
            Arable ar = this.myField.get(position);
            if (ar instanceof GreenHouse)
                return false;
            Arable greenH = new GreenHouse(ar);
            this.myObservarable.deleteObserver(ar);
            greenH.watchFor(this.myObservarable);
            this.myField.remove(position);
            this.myField.add(position, greenH);
        } else
            throw new IllegalArgumentException("Wrong position in Field -> " + position);
        return true;
    }

    public void setPlant(String plantName, int position) {
        if (position > 0 && position <= this.myField.size()) {
            position--;
            this.myField.get(position).setPlant(plantName);
            this.myField.get(position).changeSeason(mySeason);
        } else
            throw new IllegalArgumentException("Wrong position in the Field -> " + position);
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

    public String[] getFieldRepresentation() {
        String[] fRep = new String[this.myField.size()];
        int i = 0;
        for (Arable ar: this.myField) {
            fRep[i] = ar.getRepresentation();
            i++;
        }

        return fRep;
    }

    public void checkStorage(Visitor visitor) {
        this.myStorage.acceptVisit(visitor);
    }

    @Override
    public void updateTime() {
        myTime++;

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

    /**
     * Method returns the names of plants that we can create with their cost
     * @return A Map that contains the name of the plant in the key and its cost
     */
    public Map<String, Double> getPlantNamesWCost() {
        return myFactory.getPlantNamesWithCost();
    }
}
