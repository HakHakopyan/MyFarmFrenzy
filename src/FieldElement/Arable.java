package FieldElement;

import Plant.Plantable;

import Observer.*;

public interface Arable extends Plantable {

    public boolean plantExist();

    public void setPlant(String plantName);

    public void watchFor(MyObservable<Arable> ob);
}
