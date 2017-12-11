import java.util.*;

import command.Command;
import command.Commandable;
import observer.*;
import crop.*;
import factory.*;
import fieldelement.*;
import plant.Plant;
import season.Season;
import storage.*;
import visitor.*;


public class Farm implements Observer, ObserverTime, Visitable, Commandable {
    Storable<Cropable> myStorage = new Storage();

    /**
     * contains a reference to instances of objects in the field sections
     */
    List<Arable> myFarmField = new ArrayList<>();
    /**
     * contains a reference to the factory, which is used to create new plants
     */
    Factoriable myFactory = new Factory();
    /**
     * object for which the observed object inside the class farm
     */
    MyObservable<Arable> myObservarable = new MyObservable();

    /**
     * object for which the observed object outside the class farm
     */
    MyExternalObservable myExternalOb = new MyExternalObservable();

    /**
     * This parameter is needed when creating new plants, in order to transfer to them the current season after creation
     */
    Season mySeason = Season.SUMMER;

    /**
     * necessary to calculate the moments of transition between seasons
     */
    int myTime = 0;

    /**
     * Initialize fields
     * @param arableCount Count of {@link Parsel Parsel} on the Field
     * @param externalObserver link to the outside observer
     */
    Farm(int arableCount, Observer externalObserver) {
        myExternalOb.addObserver(externalObserver);
        myFactory.registration(PlantInstances.class);

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
        myFarmField.add(newParsel);
    }

    /**
     * method returns the number of parsel on the field
     * @return parsels count
     */
    public int getArablesCount() {
        return myFarmField.size();
    }

    /**
     * Set Green House in position if it is not there
     * @param position position {@link Parsel parsel} on the farm field
     * @return false if in position exist greenHouse
     */
    public boolean setGreenHouse(int position) {
        if (position > 0 && position <= this.myFarmField.size()) {
            position--;
            Arable ar = this.myFarmField.get(position);
            if (ar instanceof GreenHouse)
                return false;
            Arable greenH = new GreenHouse(ar);
            this.myObservarable.deleteObserver(ar);
            greenH.watchFor(this.myObservarable);
            this.myFarmField.remove(position);
            this.myFarmField.add(position, greenH);
        } else
            throw new IllegalArgumentException("Wrong position in Field -> " + position);
        return true;
    }

    /**
     * set new {@link Plant plant} to be placed in position on the farm field
     * @param plantName The name of the plant is the reference to the object to be placed on the position
     * @param position position one farm field
     */
    public void setPlant(String plantName, int position) {
        if (position > 0 && position <= this.myFarmField.size()) {
            position--;
            this.myFarmField.get(position).setPlant(plantName);
            this.myFarmField.get(position).changeSeason(mySeason);
        } else
            throw new IllegalArgumentException("Wrong position in the Field -> " + position);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {

        }
    }

    /**
     * check whether there is a new crop in the farm {@link Storage Storage} and notify the external observers
     */
    public void checkCrop() {
        for (Arable a: this.myFarmField) {
            if (a.isCropReady()) {
                Cropable newCrop = a.getCrop();
                myStorage.store(newCrop);

                myExternalOb.setChanged();
                myExternalOb.notifyObservers(newCrop);
            }
        }
    }

    /**
     * Get information about the state of the field - what is on it
     * @return field representation to the parsels
     */
    public String[] getFieldRepresentation() {
        String[] fRep = new String[this.myFarmField.size()];
        int i = 0;
        for (Arable ar: this.myFarmField) {
            fRep[i] = ar.getRepresentation();
            i++;
        }

        return fRep;
    }

    /**
     * check the warehouse for the presence of the crop
     * @param visitor
     */
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
    public void acceptVisit(Visitor visitor) {
        this.myStorage.acceptVisit(visitor);
    }

    @Override
    public void doCommand(List<Command> commandList) {
        for (Arable ar: myFarmField) {
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
